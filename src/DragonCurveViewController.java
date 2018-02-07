import javax.swing.*;
import java.awt.*;

public class DragonCurveViewController extends JPanel {

    public static final int ITERATION = 1000000;
    public static final int DISTANCE = 50;
    private int x_position;
    private int y_position;
    private int direction;

    DragonCurveViewController() {
        setLocation(100, 100);
        setBackground(Color.BLACK);

        x_position = 720;
        y_position = 450;
        direction = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int l_x_position;
        int l_y_position;
        int l_direction;

        for (int i = 0; i < 1; i++) {
            if (i == 0) {
                g.setColor(Color.YELLOW);
            }
            else if (i == 1) {
                g.setColor(Color.BLUE);
            }
            else if (i == 2) {
                g.setColor(Color.GREEN);
            }
            else {
                g.setColor(Color.RED);
            }

            l_direction = i;
            l_x_position = x_position;
            l_y_position = y_position;

            switch (l_direction) {
                case 0:
                    g.drawLine(l_x_position, l_y_position, l_x_position, l_y_position - DISTANCE);
                    l_y_position -= DISTANCE;
                    break;
                case 1:
                    g.drawLine(l_x_position, l_y_position, l_x_position + DISTANCE, l_y_position);
                    l_x_position += DISTANCE;
                    break;
                case 2:
                    g.drawLine(l_x_position, l_y_position, l_x_position, l_y_position + DISTANCE);
                    l_y_position += DISTANCE;
                    break;
                case 3:
                    g.drawLine(l_x_position, l_y_position, l_x_position - DISTANCE, l_y_position);
                    l_x_position -= DISTANCE;
                    break;
            }

            for (int j = 1; j < ITERATION; j++) {
                l_direction = ((((j & -j) << 1) & j) == 0 ? (l_direction + 1) % 4 : (l_direction + 3) % 4);

                switch (l_direction) {
                    case 0:
                        g.drawLine(l_x_position, l_y_position, l_x_position, l_y_position - DISTANCE);
                        l_y_position -= DISTANCE;
                        break;
                    case 1:
                        g.drawLine(l_x_position, l_y_position, l_x_position + DISTANCE, l_y_position);
                        l_x_position += DISTANCE;
                        break;
                    case 2:
                        g.drawLine(l_x_position, l_y_position, l_x_position, l_y_position + DISTANCE);
                        l_y_position += DISTANCE;
                        break;
                    case 3:
                        g.drawLine(l_x_position, l_y_position, l_x_position - DISTANCE, l_y_position);
                        l_x_position -= DISTANCE;
                        break;
                }
            }
        }
    }
}
