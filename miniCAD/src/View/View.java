package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Control.Control;

public class View extends JFrame{
    private final String TITLE = "MiniCAD";
    private final int HEIGHT = 800;
    private final int WIDTH = 1200;
    private static Timer timer;
    Control ctrl=new Control();

    public View() {
        super();
        setTitle(TITLE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Panel panel=new Panel();
        setContentPane(panel);

        Board board = new Board();
        add(board, BorderLayout.CENTER);
        Control.setControl(board);
        board.addMouseMotionListener(ctrl);
        board.addMouseListener(ctrl);

        setVisible(true);

        timer = new Timer(100, new ReboundListener());
        timer.start();
    }

    private class ReboundListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }
    
}
