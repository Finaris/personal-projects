import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by finaris on 12/3/16.
 */


class Solver extends JFrame {

    private JButton start;
    private Cell[][] cells = new Cell[10][10];
    private int[][] nums = new int[10][10];
    private boolean[][] check;
    private int count = 1;
    private HashMap<Cell, Integer> pathMeasure = new HashMap<>();

    Solver() {
        super("Brick Pop Solver");
        Handler handle = new Handler();
        JPanel panel = new JPanel(new FlowLayout());
        start = new JButton("Start");
        start.addActionListener(handle);
        panel.add(start);
        add(panel);
    }

    private void getCells() throws AWTException, InterruptedException {
        Robot robot = new Robot();

        int x = 498, y = 293;
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                cells[i][j] = new Cell(robot.getPixelColor(x, y), x, y, i, j);
                x += 31;
            }
            x -= 310;
            y += 31;
        }
    }

    private void buildNums() {
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                Color tempColor = cells[i][j].getColor();
                nums[i][j] = tempColor.getGreen() + tempColor.getBlue() + tempColor.getRed();
            }
        }
    }

    private void solve() throws AWTException, InterruptedException {
        check = new boolean[10][10];
        for(int i = 0; i < 10; i ++) {
            for(int j = 0; j < 10; j++) {
                count = 1;
                if(!check[i][j] && nums[i][j] != 701) {
                    check[i][j] = true;
                    calculatePath(cells[i][j]);
                }
            }
        }
        Cell tempKey = cells[0][0];
        int tempVal = 0;
        for(Cell key: pathMeasure.keySet()) {
            if(pathMeasure.get(key) > tempVal) {
                tempKey = key;
                tempVal = pathMeasure.get(key);
            }
        }
        selectLargest(tempKey);
    }

    private void selectLargest(Cell cell) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        int tempX = MouseInfo.getPointerInfo().getLocation().x;
        int tempY = MouseInfo.getPointerInfo().getLocation().y;
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.mouseMove(cell.getX(), cell.getY());
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        Thread.sleep(1250);
        robot.mouseMove(tempX, tempY);
    }

    private void calculatePath(Cell cell) {
        if(!hasNeighbor(cell)) {}
        else {
            populateNeighbors(cell);
            if(count > 1)
                pathMeasure.put(cell, count);
        }
    }

    private void populateNeighbors(Cell cell) {
        if(cell.getI() != 0)    // Looks up
            if(cell.getColor().equals(cells[cell.getI()-1][cell.getJ()].getColor()) && !check[cell.getI()-1][cell.getJ()]) {
                check[cell.getI()-1][cell.getJ()] = true;
                count++;
                populateNeighbors(cells[cell.getI()-1][cell.getJ()]);
            }
        if(cell.getJ() != 0)    // Looks to the left
            if(cell.getColor().equals(cells[cell.getI()][cell.getJ()-1].getColor()) && !check[cell.getI()][cell.getJ()-1]) {
                check[cell.getI()][cell.getJ()-1] = true;
                count++;
                populateNeighbors(cells[cell.getI()][cell.getJ()-1]);
            }
        if(cell.getI() != 9)    // Looks down
            if(cell.getColor().equals(cells[cell.getI()+1][cell.getJ()].getColor()) && !check[cell.getI()+1][cell.getJ()]) {
                check[cell.getI()+1][cell.getJ()] = true;
                count++;
                populateNeighbors(cells[cell.getI()+1][cell.getJ()]);
            }
        if(cell.getJ() != 9)    // Looks to the right
            if(cell.getColor().equals(cells[cell.getI()][cell.getJ()+1].getColor()) && !check[cell.getI()][cell.getJ()+1]) {
                check[cell.getI()][cell.getJ()+1] = true;
                count++;
                populateNeighbors(cells[cell.getI()][cell.getJ()+1]);
            }
    }

    private boolean hasNeighbor(Cell cell) {
        if(cell.getI() != 0)
            if(cell.getColor().equals(cells[cell.getI()-1][cell.getJ()].getColor()))
                return true;
        if(cell.getJ() != 0)
            if(cell.getColor().equals(cells[cell.getI()][cell.getJ()-1].getColor()))
                return true;
        if(cell.getI() != 9)
            if(cell.getColor().equals(cells[cell.getI()+1][cell.getJ()].getColor()))
                return true;
        if(cell.getJ() != 9)
            if(cell.getColor().equals(cells[cell.getI()][cell.getJ()+1].getColor()))
                return true;
        return false;
    }

    private boolean isPlayable() throws AWTException, InterruptedException {
        Robot robot = new Robot();
        Color c1 = robot.getPixelColor(665, 500);
        int checkVal1 = c1.getBlue() + c1.getGreen() + c1.getRed();
        Color c2 = robot.getPixelColor(500, 570);
        int checkVal2 = c2.getBlue() + c2.getGreen() + c2.getRed();
        if(checkVal2 == 701) {
            Thread.sleep(7500);
            return true;
        }
        if(checkVal1 == 765)
            return false;
        else
            return true;
    }

    private class Handler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == start) {
                try {
                    while(isPlayable()) {
                        getCells();
                        buildNums();
                        solve();
                        pathMeasure.clear();
                    }
                } catch (AWTException e1) {
                    e1.printStackTrace();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
