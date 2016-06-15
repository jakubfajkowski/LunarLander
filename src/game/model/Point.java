package game.model;

import java.awt.geom.Point2D;

public class Point extends Point2D.Double{
    public Point(String pointCoordinates){
        String splittedString[] = pointCoordinates.split(" ");

        this.x = java.lang.Double.parseDouble(splittedString[0]);
        this.y = java.lang.Double.parseDouble(splittedString[1]);
    }

    public Point(double x, double y){
        super(x, y);
    }
}
