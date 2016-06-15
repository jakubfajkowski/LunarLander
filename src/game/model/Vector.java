package game.model;

/**
 * Klasa reprezentująca matematyczny wektor.
 */
public class Vector {
    /**
     * Współrzędna x wektora.
     */
    private double x;

    /**
     * Getter.
     * @return współrzędna x wektora
     */
    public double getX() {
        return x;
    }

    /**
     * Współrzędna x wektora.
     */
    private double y;
    /**
     * Getter.
     * @return współrzędna y wektora
     */
    public double getY() {
        return y;
    }

    /**
     * Metoda realizująca dodawanie wektorów.
     * @param added dodawany wektor
     * @return suma wektorów
     */
    public Vector add(Vector added){
        x += added.getX();
        y += added.getY();

        return this;
    }

    /**
     * Konstruktor klasy.
     * @param x współrzędna x wektora
     * @param y współrzędna y wektora
     */
    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }
}
