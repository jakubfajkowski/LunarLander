package game.model;

/**
 * Ta klasa reprezentuje <i> informacje wyświetlane na ekranie podczas gry </i>
 * Klasa odpowiada za wyświetlanie napisów w 2 miejsach - lewym-górnym i prawym-górnym rogu okienka.
 * @author Jerzy
 * @version 1.2
 */
public class Stats {
    private String levelName = "";
    private int score = 25000;
    private double height = 0;
    private double fuel = 0;
    private double horizontalVelocity = 0;
    private double verticalVelocity = 0;
    private int lives = 0;
    private String communicate = "";

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public double getHorizontalVelocity() {
        return horizontalVelocity;
    }

    public void setHorizontalVelocity(double horizontalVelocity) {
        this.horizontalVelocity = horizontalVelocity;
    }

    public double getVerticalVelocity() {
        return verticalVelocity;
    }

    public void setVerticalVelocity(double verticalVelocity) {
        this.verticalVelocity = verticalVelocity;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public String getCommunicate() {
        return communicate;
    }

    public void setCommunicate(String communicate) {
        this.communicate = communicate;
    }

    public void deleteCommunicate() {
        this.communicate = "";
    }
}
