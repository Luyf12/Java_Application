package View;

import javax.swing.*;
import java.awt.*;
import Control.*;
import Model.Model;

class Board extends JComponent {
    Board() {
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw static objects
        for (int i = 0; i < Model.now_Items.size(); i++) {
            g2.setColor(Model.now_Items.get(i).getColor());
            int width = Model.now_Items.get(i).getWidth();
            if (i == Model.now_chosen) {
                width += 5;
            }
            g2.setStroke(new BasicStroke(width));
            if (Model.now_Items.get(i).getType() == 4) {
                g2.setFont(new Font(null, Font.PLAIN, width));
                g2.drawString(Model.now_Items.get(i).getText(), Model.now_Items.get(i).getStartP().x,
                        Model.now_Items.get(i).getStartP().y);
            } else
                g2.draw(Model.now_Items.get(i).getShape());
        }

        // draw dynamic object
        if (Model.start_p != null && Model.end_p != null) {
            Shape temp = null;
            g2.setPaint(Color.BLACK);
            g2.setStroke(new BasicStroke(10));
            temp = CreateShape.create(Model.start_p, Model.end_p, Model.now_func);
            if (temp != null)
                g2.draw(temp);
        }
    }
}