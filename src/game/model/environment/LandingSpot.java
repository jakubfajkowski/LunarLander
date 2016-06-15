package game.model.environment;

import game.model.GameWindow;
import game.model.Point;

import java.awt.geom.Rectangle2D;

public class LandingSpot {
    private Point landingPoint;
    private Rectangle2D.Double rect; //TODO grafika lądowiska
    private double width;
    private double height;

    public Rectangle2D.Double getRect() {
        return rect;
    }

    public double getHeight() {
        return height;
    }

    public LandingSpot(Point landingPoint, double width)
    {
        this.landingPoint = landingPoint;
        this.height = landingPoint.getY();
        this.width = width;
        rect = new Rectangle2D.Double(
                this.landingPoint.getX(), GameWindow.HEIGHT - this.landingPoint.getY(),
                this.width,
                this.height);
    }


    public boolean isOn(int x,int y)
    {
        return rect.contains(x,y);
    }
}
