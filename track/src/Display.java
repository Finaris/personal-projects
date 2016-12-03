import javax.swing.*;
import java.awt.*;

/**
 * Created by finaris on 12/3/16.
 */


public class Display {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                TrackBot bot = new TrackBot();
                Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

                bot.pack();
                bot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                bot.setLocation( ((int)size.getWidth()/2) - 275, ((int)size.getHeight()/2) - 225);
                bot.setSize(200, 75);
                bot.setVisible(true);
                bot.setResizable(false);


            }
        });
    }

}
