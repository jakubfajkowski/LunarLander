package game.model;

import game.model.Point;
import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {
    @Test
    public void testConstructingFromString() throws Exception {
        Point point = new Point("100;200");

        assertTrue(point.getX() == 100.0);
        assertTrue(point.getY() == 200.0);
    }
}