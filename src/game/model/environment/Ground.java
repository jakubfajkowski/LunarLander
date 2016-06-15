package game.model.environment;

import game.model.*;
import game.model.Point;

import java.awt.*;
import java.util.ArrayList;

public class Ground extends Polygon {
    public Ground(ArrayList<Point> points)
    {
        // dodanie lewego dolnego dolnego rogu
        this.addPoint(0, GameWindow.HEIGHT);

        for (Point point : points) {
            this.addPoint((int) point.getX(), (int) point.getY());
        }
        // dodanie prawego dolnego dolnego rogu
        this.addPoint(GameWindow.WIDTH, GameWindow.HEIGHT);
    }

    public boolean isInside(int x,int y)
    {
        return contains(x,y);
    }
}