import javax.swing.*;
import java.awt.*;

/**
 * Created by finaris on 12/3/16.
 */

public class Display {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Solver solve = new Solver();
                Dimension size = new Dimension();

                solve.pack();
                solve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                solve.setLocation(((int)size.getWidth()/2) - 200, ((int)size.getHeight()/2) - 75);
                solve.setSize(200, 75);
                solve.setVisible(true);
                solve.setResizable(false);
            }
        });

    }

}
