package Control;

import java.awt.geom.*;
import java.awt.*;

public class CreateShape {
    CreateShape() {
    }

    public static Shape create(Point x1, Point x2, int type) {
        Shape shape = null;
        switch (type) {
            case 1:
                shape = new Line2D.Float(x1, x2);
                break;
            case 2:
                shape = new Rectangle2D.Float(Math.min(x1.x, x2.x), Math.min(x1.y, x2.y), Math.abs(x2.x - x1.x),
                        Math.abs(x2.y - x1.y));
                break;
            case 3:
                shape = new Ellipse2D.Float(Math.min(x1.x, x2.x), Math.min(x1.y, x2.y), Math.abs(x2.x - x1.x),
                        Math.abs(x2.y - x1.y));
                break;
        }
        return shape;
    }
}
