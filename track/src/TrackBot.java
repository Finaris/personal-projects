import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

/**
 * Created by finaris on 12/3/16.
 */
public class TrackBot extends JFrame {

    private JButton start;

    public TrackBot() {
        super("Ayy");
        Handler handle = new Handler();
        JPanel panel = new JPanel(new FlowLayout());
        start = new JButton("Start");
        start.addActionListener(handle);
        panel.add(start);
        add(panel);
    }

    public void play() throws AWTException, InterruptedException {
        Robot robot = new Robot();

        Thread.sleep(1);

        long t= System.currentTimeMillis();
        long end = t+12500;
        while(System.currentTimeMillis() < end) {
            robot.mouseMove(686, 608);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            robot.mouseMove(648, 623);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(1);
        }

    }

    private class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == start) {
                try {
                    play();
                } catch(Exception f) {
                    f.printStackTrace();
                }
            }
        }
    }

}
