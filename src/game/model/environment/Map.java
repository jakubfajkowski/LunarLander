package game.model.environment;

import game.model.GameWindow;
import game.model.Point;
import gui.MainWindow;
import loader.ClientException;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.Properties;

import static loader.PropertiesLoader.loadFromLocalFile;

public class Map {
    private Ground ground;
    private LandingSpot landingSpot;

    private double maxVerticalVelocity;
    private double maxHorizontalVelocity;
    private double gravitationalAcceleration;
    private double viscosity;


    public Map(String mapFileName)
    {
        Properties properties;
        try {
            properties = MainWindow.getInstance().getClient().getMapFromServer(mapFileName);
        } catch (ClientException e) {
            properties = loadFromLocalFile("maps/" + mapFileName);
        }

        String[] pointValues = properties.getProperty("GROUND_POS").split(";");
        ArrayList<Point> points = initializeGroundPoints(pointValues);

        Point landingPoint = new Point(properties.getProperty("LANDING_SPOT_POS"));

        String landingWidthValue = properties.getProperty("LANDING_SPOT_WIDTH");
        Double landingWidth = Double.parseDouble(landingWidthValue);

        initializeParameters(properties);

        ground = new Ground(points);
        landingSpot = new LandingSpot(landingPoint, landingWidth);
    }

    private ArrayList<Point> initializeGroundPoints(String[] values){
        ArrayList<Point> points = new ArrayList<>();

        for (String value: values) {
            String[] splittedValue = value.split(" ");
            Double x = Double.parseDouble(splittedValue[0]);
            Double y = Double.parseDouble(splittedValue[1]);

            points.add(new Point(x, y));
        }

        points = transformPoints(points);

        return points;
    }

    private ArrayList<Point> transformPoints(ArrayList<Point> points){
        ArrayList<Point> transformedPoints = new ArrayList<>();

        for (Point point: points) {
            transformedPoints.add(new Point(point.x, GameWindow.HEIGHT - point.y));
        }
        return transformedPoints;
    }

    private void initializeParameters(Properties properties){
        this.maxVerticalVelocity = Double.parseDouble(properties.getProperty("MAX_VERTICAL_VEL"));
        this.maxHorizontalVelocity = Double.parseDouble(properties.getProperty("MAX_HORIZONTAL_VEL"));
        this.gravitationalAcceleration = Double.parseDouble(properties.getProperty("GRAVITY_CONSTANT"));
        this.viscosity = Double.parseDouble(properties.getProperty("VISCOSITY"));
    }

    public Ground getGround() {
        return ground;
    }

    public LandingSpot getLandingSpot() {
        return landingSpot;
    }

    public boolean collisionWithGround(int x,int y)
    {
        return ground.isInside(x,y);
    }

    public double getMaxHorizontalVelocity() {
        return maxHorizontalVelocity;
    }

    public double getMaxVerticalVelocity() {
        return maxVerticalVelocity;
    }

    public double getGravitationalAcceleration() {
        return gravitationalAcceleration;
    }

    public double getViscosity() {
        return viscosity;
    }
}
