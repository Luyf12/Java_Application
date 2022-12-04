package View;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import Control.Control;

public class Panel extends JPanel {
    Panel() {
        super(new BorderLayout());
        init();
    }

    private void init() {
        Control ctrl = new Control();
        String[] function = { "Choose", "Line", "Rectangle", "Ellipse", "Text", "Color", "SizeInc", "SizeDec",
                "WidthInc", "WidthDec", "Load", "Save" };
        JPanel button_box = new JPanel();
        button_box.setLayout(new GridLayout(2, 6));
        for (int i = 0; i < function.length; i++) {
            JButton btn = new JButton(function[i]);
            btn.setPreferredSize(new Dimension(200, 50));
            btn.setActionCommand(function[i]);
            btn.addActionListener(ctrl);
            button_box.add(btn);
        }
        add(button_box, BorderLayout.NORTH);
    }
}