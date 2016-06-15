package gui.menu;

import javax.swing.*;
import java.awt.*;

/**
 * Ta klasa reprezentuje <i>okieneko menu</i>.
 *
 * @author Jakub
 * @version 1.0
 */

public class MenuWindow extends JPanel{
    /**
     * Składa się ono z trzech podpaneli będących odpowiednio:
     * <ul>
     *     <li>nagłówkiem,</li>
     *     <li>centalnym panelem,</li>
     *     <li>stopką.</li>
     * </ul>
     */
    private JPanel headerPanel;
    private JPanel centralPanel;
    private JPanel footerPanel;

    /**
     * Konstruktor okienka menu. Jego tło ustawiane jest na ciemnoszare.
     * @param updatedHeaderPanel panel będący nagłówkiem
     * @param updatedCentralPanel panel główny (np.: z przyciskami)
     * @param updatedFooterPanel stopka
     */
    public MenuWindow(JPanel updatedHeaderPanel, JPanel updatedCentralPanel, JPanel updatedFooterPanel){
        headerPanel = updatedHeaderPanel;
        centralPanel = updatedCentralPanel;
        footerPanel = updatedFooterPanel;

        setMenuWindow();
        this.setBackground(Color.DARK_GRAY);
    }

    /**
     * Metoda umożliwiająca jednolity layout stron nawet przy zmienianiu rozmiaru okienka.
     */
    private void setMenuWindow(){
        GridBagLayout menuWindowLayout = new GridBagLayout();
        this.setLayout(menuWindowLayout);
        GridBagConstraints c = new GridBagConstraints();

        c.ipady = 0;
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 0;
        this.add(headerPanel, c);

        c.insets = new Insets(25,0,0,0);
        c.gridx = 0;
        c.gridy = 1;
        this.add(centralPanel, c);

        c.insets = new Insets(25,0,0,0);
        c.gridx = 0;
        c.gridy = 2;
        this.add(footerPanel, c);
    }

}
