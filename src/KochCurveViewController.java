import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;
import static java.lang.Math.round;

public class KochCurveViewController extends JPanel {

    int iteration_count = 5;
    Map<String, String> rules;
    String axiom = "F-F-F-F";
    int value_type = 3;
    int delta_angle;

    KochCurveViewController() {
        setBackground(Color.BLACK);

        rules = new HashMap<>();
        delta_angle = 90;
        switch (value_type) {
            case 0:
                rules.put("F","FF-F-F-F-F-F+F");
                break;
            case 1:
                rules.put("F","FF-F-F-F-FF");
                break;
            case 2:
                rules.put("F","FF-F+F-F-FF");
                break;
            case 3:
                rules.put("F","FF-F--F-F");
                break;
            case 4:
                rules.put("F","F-FF--F-F");
                break;
            case 5:
                rules.put("F","F-F+F-F-F");
                break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);

        ArrayList<Double> x_positions = new ArrayList<>();
        ArrayList<Double> y_positions = new ArrayList<>();
        String l_axiom = axiom;
        double distance = 700;
        int current_angle = 0;
        double current_x = 370.0;
        double current_y = 780.0;

        for (int i = 0; i < iteration_count; i++) {
            l_axiom = evolve_axiom(l_axiom);
            distance /= 3;
        }
//        distance = 20;

        x_positions.add(current_x);
        y_positions.add(current_y);
        for (int i = 0; i < l_axiom.length(); i++) {
            switch (l_axiom.charAt(i)) {
                case 'F':
                    double future_x = distance*cos(toRadians(current_angle)) + current_x;
                    double future_y = distance*sin(toRadians(current_angle)) + current_y;
                    x_positions.add(future_x);
                    y_positions.add(future_y);
                    current_x = future_x;
                    current_y = future_y;
                    break;
                case '+':
                    current_angle += delta_angle;
                    break;
                case '-':
                    current_angle -= delta_angle;
                    break;
            }
        }
        int[] draw_x_pos = x_positions.stream().mapToInt(x->(int)round(x)).toArray();
        int[] draw_y_pos = y_positions.stream().mapToInt(x->(int)round(x)).toArray();

        g.drawPolygon(draw_x_pos, draw_y_pos, draw_x_pos.length);
    }

    private String evolve_axiom(String base) {
        StringBuilder new_axiom = new StringBuilder(base);
        int appended_size = 0;
        for (int j = 0; j < base.length(); j++) {
            String substr = base.substring(j, j + 1);
            if (rules.containsKey(substr)) {
                new_axiom.replace(j + appended_size, j + appended_size + 1, rules.get(substr));
                appended_size += rules.get(substr).length() - 1;
            }
        }
        return new_axiom.toString();
    }
}
