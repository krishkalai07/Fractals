import javax.swing.*;
import java.awt.*;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame("Magic Fractal");
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);

        DragonCurveViewController content = new DragonCurveViewController();
//        KochAntiSnowflakeViewController content = new KochAntiSnowflakeViewController();
//        KochSnowflakeViewController content = new KochSnowflakeViewController();
//        KochCurveViewController content = new KochCurveViewController();
//        PyramidionViewController content = new PyramidionViewController();

        window.setContentPane(content);
        System.out.print(Toolkit.getDefaultToolkit().getScreenSize().width + " ");
        System.out.println(Toolkit.getDefaultToolkit().getScreenSize().height);
        window.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        window.setUndecorated(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setVisible(true);
    }
}