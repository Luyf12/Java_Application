package Model;

import java.io.Serializable;
import java.awt.*;

public class Item implements Serializable {
    protected int type;// 1:line 2:rectangle 3:ellipse 4:text
    protected Point start_p, end_p;
    protected int width;
    protected Color color;
    protected Shape shape;
    protected String text;

    public Item(int type, Shape shape) {
        this.type = type;
        this.shape = shape;
        start_p = Model.start_p;
        end_p = Model.end_p;
        width = 10;
        color = Color.black;
    }

    public Item(int type, String text) {
        this.type = type;
        this.text = text;
        start_p = new Point(600, 300);
        end_p = null;
        width = 30;
        shape = null;
        color = Color.black;
    }

    public void setPoint(Point s, Point e) {
        this.start_p = s;
        this.end_p = e;
    }

    public void setWidth(int w) {
        width = w;
    }

    public Point getStartP() {
        return start_p;
    }

    public Point getEndP() {
        return end_p;
    }

    public void setShape(Shape s) {
        shape = s;
    }

    public void setColor(Color c) {
        color = c;
    }

    public int getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public int getWidth() {
        return width;
    }

    public Shape getShape() {
        return shape;
    }

    public String getText() {
        return text;
    }

    public boolean isContain() {
        if (this.type == 4) {
            if (Model.start_p.x >= start_p.getX() && Model.start_p.x <= (start_p.getX() + text.length() * width)
                    && Model.start_p.y >= start_p.getY() - width && Model.start_p.y <= start_p.getY()) {
                return true;
            }
            return false;
        } else if (this.type == 1) {
            double dis;
            if (Model.start_p.x < Math.min(start_p.x, end_p.x) - width
                    || Model.start_p.y < Math.min(start_p.y, end_p.y) - width
                    || Model.start_p.x > Math.max(start_p.x, end_p.x) + width
                    || Model.start_p.y > Math.max(start_p.y, end_p.y) + width)
                return false;
            if (Math.abs(start_p.x - end_p.x) < 1) {
                dis = Math.abs(Model.start_p.x - start_p.x);
                return dis < width;
            }
            double k = (double) (start_p.y - end_p.y) / (double) (start_p.x - end_p.x);
            double b = (double) start_p.y - k * (double) start_p.x;
            dis = Math.abs(k * Model.start_p.x + b - Model.start_p.y) / Math.sqrt(k * k + 1);
            return dis < width;
        }
        return shape.contains(Model.start_p.x, Model.start_p.y);
    }
}