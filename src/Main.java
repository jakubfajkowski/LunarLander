import gui.MainWindow;

/**
 * Ta klasa reprezentuje <i>główną klasę programu </i>
 *
 * @author Jerzy
 * @version 1.0
 */
public class Main{
    /**
     * Metoda główna programu.
     * @param args argumenty uruchomienia
     */
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(() -> MainWindow.getInstance().initialize());
    }
}
