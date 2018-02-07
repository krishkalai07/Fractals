import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class PyramidionViewController extends JPanel {
    private static final int ITERATION_COUNT = 4;

    private static final int base_x_center = 750;
    private static final int base_y_center = 450;
    private static final int base_radius = 400;

    private ArrayList<Integer> x_points;
    private ArrayList<Integer> y_points;

    public PyramidionViewController() {
        setBackground(Color.BLACK);

        x_points = new ArrayList<>();
        y_points = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);

        x_points.clear();
        y_points.clear();

        int[] l_x_points = new int[3];
        int[] l_y_points = new int[3];

        generate_triangle(base_x_center, base_y_center, base_radius, 0, l_x_points, l_y_points);
        insert_array_to_list(x_points, l_x_points, 0);
        insert_array_to_list(y_points, l_y_points, 0);

        for (int i = 0; i < ITERATION_COUNT; i++) {
            for (int j = 0; j < x_points.size(); j+=4) {
                int x_midpoint = (x_points.get(j) + x_points.get((j + 1) % x_points.size())) / 2;
                int y_midpoint = (y_points.get(j) + y_points.get((j + 1) % x_points.size())) / 2;

                int inner_center_x = (2 * x_midpoint + base_x_center) / 3;
                int inner_center_y = (2 * y_midpoint + base_y_center) / 3;

                generate_triangle(inner_center_x, inner_center_y, base_radius / 3, 0, l_x_points, l_y_points);

                l_x_points = reverse_array(l_x_points);
                l_y_points = reverse_array(l_y_points);

                l_x_points = rotate_array(l_x_points, j / 4 + 1);
                l_y_points = rotate_array(l_y_points, j / 4 + 1);

                insert_array_to_list(x_points, l_x_points, j + 1);
                insert_array_to_list(y_points, l_y_points, j + 1);
            }
        }


        int[] l_x_draw_points = x_points.stream().filter(Objects::nonNull).mapToInt(t->t).toArray();
        int[] l_y_draw_points = y_points.stream().filter(Objects::nonNull).mapToInt(t->t).toArray();

        g.drawPolygon(l_x_draw_points, l_y_draw_points, l_x_draw_points.length);
    }

    private void generate_triangle (int x_center, int y_center, float radius, int rotation, int[] x_points, int[] y_points) {
        for (int i = 0; i < 3; i++) {
            double angle = ((4*i+6)%12) * PI / 6;
            x_points[i] = (int)(radius * sin(angle + rotation) + x_center);
            y_points[i] = (int)(radius * cos(angle + rotation) + y_center);
        }
    }

    private int[] reverse_array (int[] array) {
        for (int i = 0; i < array.length/2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }

        return array;
    }

    private int[] rotate_array (int[] array, int iterations) {
        for (int i = 0; i < iterations; i++) {
            int temp = array[array.length - 1];
            System.arraycopy(array, 0, array, 1, 2);
            array[0] = temp;
        }
        return array;
    }

    private void insert_array_to_list (ArrayList<Integer> list, int[] array, int pos) {
        for (int i = 0; i < array.length; i++) {
            list.add(pos+i, array[i]);
        }
    }
}
