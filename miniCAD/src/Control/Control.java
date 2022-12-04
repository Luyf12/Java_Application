package Control;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.io.File;
import java.awt.*;
import Model.*;

public class Control extends JFrame implements MouseListener, MouseMotionListener, ActionListener {
    static JComponent board;

    public Control() {
    }

    public static void setControl(JComponent c) {
        board = c;
    }

    public void mousePressed(MouseEvent e) {
        Model.start_p = new Point(e.getX(), e.getY());
        if (Model.now_func == 0) {
            int i = 0;
            for (Item t : Model.now_Items) {
                if (t.isContain()) {
                    Model.now_chosen = i;
                    break;
                }
                i++;
            }
        }
        board.repaint();
    }

    public void mouseDragged(MouseEvent e) {
        Model.end_p = new Point(e.getX(), e.getY());
        if (Model.now_func == 0) {
            if (Model.now_chosen >= 0) {
                Item t = Model.now_Items.get(Model.now_chosen);
                if (t.getType() == 4) {
                    t.setPoint(Model.end_p, null);
                } else {
                    Shape temp = null;

                    temp = CreateShape.create(
                            new Point((int) (t.getStartP().getX() + (e.getX() - Model.start_p.getX())),
                                    (int) (t.getStartP().getY() + (e.getY() - Model.start_p.getY()))),
                            new Point((int) (t.getEndP().getX() + (e.getX() - Model.start_p.getX())),
                                    (int) (t.getEndP().getY() + (e.getY() - Model.start_p.getY()))),
                            t.getType());
                    t.setShape(temp);
                }
            }
        }
        board.repaint();
    }

    public void mouseReleased(MouseEvent e) {
        if (Model.now_func != 0) {
            Shape s = CreateShape.create(Model.start_p, new Point(e.getX(), e.getY()), Model.now_func);
            Model.now_Items.add(new Item(Model.now_func, s));

        } else {
            if (Model.now_chosen >= 0) {
                Item t = Model.now_Items.get(Model.now_chosen);
                if (t.getType() == 4) {
                    t.setPoint(e.getPoint(), null);
                } else {
                    Point p1 = new Point((int) (t.getStartP().getX() + (e.getX() - Model.start_p.getX())),
                            (int) (t.getStartP().getY() + (e.getY() - Model.start_p.getY())));
                    Point p2 = new Point((int) (t.getEndP().getX() + (e.getX() - Model.start_p.getX())),
                            (int) (t.getEndP().getY() + (e.getY() - Model.start_p.getY())));
                    t.setPoint(p1, p2);
                }
            }
        }
        Model.start_p = null;
        Model.end_p = null;
        board.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("btn:" + e.getActionCommand());
        String func = e.getActionCommand();
        switch (func) {
            case "Choose":
                Model.now_chosen = -1;
                Model.now_func = 0;
                break;
            case "Line":
                Model.now_chosen = -1;
                Model.now_func = 1;
                Model.start_p = null;
                Model.end_p = null;
                break;
            case "Rectangle":
                Model.now_chosen = -1;
                Model.now_func = 2;
                Model.start_p = null;
                Model.end_p = null;
                break;
            case "Ellipse":
                Model.now_chosen = -1;
                Model.now_func = 3;
                Model.start_p = null;
                Model.end_p = null;
                break;
            case "Text":
                Model.now_chosen = -1;
                Model.now_func = 0;
                Model.start_p = null;
                Model.end_p = null;
                String now_text = JOptionPane.showInputDialog(null, "Please enter text:");
                Model.now_Items.add(new Item(4, now_text));
                break;
            case "Color":
                Color c = JColorChooser.showDialog(null, "Color", null);
                if (c != null) {
                    if (Model.now_chosen >= 0) {
                        Model.now_Items.get(Model.now_chosen).setColor(c);
                        repaint();
                    }
                }
                break;
            case "SizeInc":
                if (Model.now_chosen >= 0) {
                    Item i = Model.now_Items.get(Model.now_chosen);
                    if (i.getType() == 4) {
                        i.setWidth(i.getWidth() + 2);
                    } else {
                        Point p1 = i.getStartP();
                        Point p2 = i.getEndP();
                        p2.translate(5, 5 * (p2.y - p1.y) / (p2.x - p1.x));
                        i.setShape(CreateShape.create(p1, p2, i.getType()));
                    }
                }
                break;
            case "SizeDec":
                if (Model.now_chosen >= 0) {
                    Item i = Model.now_Items.get(Model.now_chosen);
                    if (i.getType() == 4) {
                        i.setWidth(i.getWidth() - 2);
                    } else {
                        Point p1 = i.getStartP();
                        Point p2 = i.getEndP();
                        p2.translate(-5, -5 * (p2.y - p1.y) / (p2.x - p1.x));
                        i.setShape(CreateShape.create(p1, p2, i.getType()));
                    }
                }
                break;
            case "WidthInc":
                if (Model.now_chosen >= 0) {
                    Item i = Model.now_Items.get(Model.now_chosen);
                    i.setWidth(i.getWidth() + 2);
                }
                break;
            case "WidthDec":
                if (Model.now_chosen >= 0) {
                    Item i = Model.now_Items.get(Model.now_chosen);
                    i.setWidth(i.getWidth() - 2);
                }
                break;
            case "Load":
                JFileChooser fileChooserL = new JFileChooser();
                fileChooserL.setCurrentDirectory(new File("."));
                fileChooserL.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fileChooserL.setMultiSelectionEnabled(false);
                int load = fileChooserL.showOpenDialog(null);
                if (load == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooserL.getSelectedFile();
                    System.out.println(file.getAbsolutePath());
                    try {
                        Load.loadItems(file);
                    } catch (Exception t) {
                        System.out.println("Something went wrong while loading a file");
                    }
                }
                break;
            case "Save":
                JFileChooser fileChooserS = new JFileChooser();
                fileChooserS.setSelectedFile(new File("File"));
                int save = fileChooserS.showSaveDialog(null);
                if (save == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooserS.getSelectedFile();
                    try {
                        Save.saveItems(file);
                    } catch (Exception t) {
                        System.out.println("Something went wrong while saving");
                    }
                }
                break;

        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
