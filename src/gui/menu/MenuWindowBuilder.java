package gui.menu;

import gui.Language;
import gui.MainWindow;
import game.model.Player;

import javax.swing.*;
import java.awt.*;

/**
 * Ta klasa reprezentuje <i>builder okienek menu</i>.
 * Składa się z metod statycznych i działa jako nadzorca odwołujący się do konstruktora okna menu.
 * @author Jakub
 * @version 1.0
 */

public class MenuWindowBuilder {

    /**
     * Tworzy przezroczysty JPanel
     * @return przezroczysty JPanel
     */
    static private JPanel createTransparentJPanel(){
        JPanel p = new JPanel();
        p.setOpaque(false);

        return p;
    }

    /**
     * Tworzy tytuł gry wyświetlany w nagłówku.
     * @return tytuł
     */
    static private JLabel createTitle(){
        JLabel title = new JLabel("Lunar Lander", JLabel.CENTER);
        title.setForeground(Color.GREEN);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 80));

        return title;
    }

    /**
     * Tworzy mniejszy tekst wyświetlany nad guzikami.
     * @param s tekst do wyświetlenia
     * @return  podtytuł menu
     */
    static private JLabel createSubtitle(String s){
        JLabel title = new JLabel(s, JLabel.CENTER);
        title.setForeground(Color.GREEN);
        title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

        return title;
    }

    /**
     * Tworzy stopkę.
     * @return stopa
     */
    static private JPanel createFooterPanel(){
        JPanel footerPanel = createTransparentJPanel();

        GridLayout footerPanelLayout = new GridLayout();
        footerPanelLayout.setHgap(20);
        footerPanel.setLayout(footerPanelLayout);

        JButton back = new JButton(Language.MAIN_MENU);
        back.addActionListener(MainWindow.getInstance());
        footerPanel.add(back);

        return  footerPanel;
    }

    /**
     * Tworzy nagłówek wraz z podtytułem
     * @param subtitle podtytuł
     * @return nagłówek
     */
    static private JPanel createHeaderPanel(String subtitle){
        JPanel headerPanel = createTransparentJPanel();

        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(createTitle(), BorderLayout.CENTER);
        headerPanel.add(createSubtitle(subtitle), BorderLayout.SOUTH);

        return headerPanel;
    }

    /**
     * Szablon podpinania guzików pod okno programu.
     * @param button_text tekst na guziku
     * @return guzik
     */
    static private JButton connectButtonToMainWindow(String button_text){
        JButton button = new JButton(button_text);
        button.addActionListener(MainWindow.getInstance());
        return button;
    }

    /**
     * @see MenuWindow#MenuWindow(JPanel, JPanel, JPanel)
     * @return skonstruowane menu główne
     */
    static public MenuWindow buildMainMenu(){
        return new MenuWindow(createHeaderPanel(Language.MAIN_MENU), createMainMenuCentralPanel(), createFooterPanel());
    }

    /**
     * Tworzy centralny panel menu głównego.
     * @return centralny panel
     */
    static private JPanel createMainMenuCentralPanel(){
        JPanel centralPanel = createTransparentJPanel();
        centralPanel.setPreferredSize(new Dimension(200,300));
        GridLayout centralPanelLayout = new GridLayout(5,1);
        centralPanelLayout.setVgap(20);

        centralPanel.setLayout(centralPanelLayout);

        JButton current_button = connectButtonToMainWindow(Language.START_GAME);
        centralPanel.add(current_button);

        current_button = connectButtonToMainWindow(Language.HALL_OF_FAME);
        centralPanel.add(current_button);

        current_button = connectButtonToMainWindow(Language.OPTIONS);
        centralPanel.add(current_button);

        return centralPanel;
    }

    /**
     * @see MenuWindow#MenuWindow(JPanel, JPanel, JPanel)
     * @return skonstruowany ekran najlepszych wyników
     */
    static public MenuWindow buildHallOfFame(){
        return new MenuWindow(createHeaderPanel(Language.HALL_OF_FAME), createHallOfFameCentralPanel(), createFooterPanel());
    }

    /**
     * Tworzy centralny panel ekranu najlepszych wyników.
     * @return centralny panel
     */
    static private JPanel createHallOfFameCentralPanel(){
        JPanel centralPanel = createTransparentJPanel();
        centralPanel.setPreferredSize(new Dimension(400,300));
        GridLayout centralPanelLayout = new GridLayout(10,1);
        centralPanelLayout.setVgap(10);

        centralPanel.setLayout(centralPanelLayout);

        addHallOfFameList(centralPanel);

        return centralPanel;
    }

    static private void addHallOfFameList(JPanel panel){
        MainWindow.getInstance().getBestScores().loadFromFile("/bestscores.txt");
        for (String record: MainWindow.getInstance().getBestScores().toScoresList()) {
            panel.add(createSubtitle(record));
        }
    }

    /**
     * @see MenuWindow#MenuWindow(JPanel, JPanel, JPanel)
     * @return skonstruowany ekran opcji
     */
    static public MenuWindow buildOptions(){
        return new MenuWindow(createHeaderPanel(Language.OPTIONS), createOptionsCentralPanel(), createFooterPanel());
    }

    /**
     * Tworzy centralny panel ekranu opcji.
     * @return centralny panel
     */
    static private JPanel createOptionsCentralPanel(){
        JPanel centralPanel = createTransparentJPanel();
        centralPanel.setPreferredSize(new Dimension(200,300));
        GridLayout centralPanelLayout = new GridLayout(5,1);
        centralPanelLayout.setVgap(20);

        centralPanel.setLayout(centralPanelLayout);

        JButton current_button = connectButtonToMainWindow(Language.MUSIC);
        centralPanel.add(current_button);

        current_button = connectButtonToMainWindow(Language.SOUND_EFFECTS);
        centralPanel.add(current_button);

        current_button = connectButtonToMainWindow(Language.LANGUAGE);
        centralPanel.add(current_button);

        current_button = connectButtonToMainWindow(Language.NETWORK_SETTINGS);
        centralPanel.add(current_button);

        return centralPanel;
    }

    /**
     * @see MenuWindow#MenuWindow(JPanel, JPanel, JPanel)
     * @return skonstruowany ekran obecnego poziomu
     */
    static public MenuWindow buildCurrentLevelWindow(Player player, String levelName){
        return new MenuWindow(createHeaderPanel(
                Language.HELLO + " " + player.getName() + "!"
                        + " Poziom: " + levelName.split("/")[1] + ". Wynik: " + player.getScore()),
                createCurrentLevelWindowCentralPanel(),
                createFooterPanel());
    }

    /**
     * Tworzy centralny panel ekranu obecnego poziomu.
     * @return centralny panel
     */
    static private JPanel createCurrentLevelWindowCentralPanel(){
        JPanel centralPanel = createTransparentJPanel();
        centralPanel.setPreferredSize(new Dimension(200,300));
        GridLayout centralPanelLayout = new GridLayout(5,1);
        centralPanelLayout.setVgap(20);

        centralPanel.setLayout(centralPanelLayout);

        JButton current_button = connectButtonToMainWindow(Language.PLAY);
        centralPanel.add(current_button);

        current_button = connectButtonToMainWindow(Language.SHOP);
        centralPanel.add(current_button);

        return centralPanel;
    }

    /**
     * @see MenuWindow#MenuWindow(JPanel, JPanel, JPanel)
     * @return skonstruowany ekran sklepu
     */
    static public MenuWindow buildShop(){
        return new MenuWindow(createHeaderPanel(Language.SHOP), createShopCentralPanel(), createShopFooterPanel());
    }

    /**
     * Tworzy centralny panel ekranu sklepu.
     * @return centralny panel
     */
    static private JPanel createShopCentralPanel(){
        JPanel centralPanel = createTransparentJPanel();
        centralPanel.setPreferredSize(new Dimension(200,300));
        GridLayout centralPanelLayout = new GridLayout(5,1);
        centralPanelLayout.setVgap(20);

        centralPanel.setLayout(centralPanelLayout);

        JButton current_button = connectButtonToMainWindow(Language.ADDITIONAL_LIFE);
        centralPanel.add(current_button);

        current_button = connectButtonToMainWindow(Language.CANISTER);
        centralPanel.add(current_button);

        current_button = connectButtonToMainWindow(Language.PARACHUTE);
        centralPanel.add(current_button);

        current_button = connectButtonToMainWindow(Language.BETTER_LANDER);
        centralPanel.add(current_button);

        return centralPanel;
    }

    /**
     * Tworzy stopkę ekranu sklepu.
     * @return stopka
     */
    static private JPanel createShopFooterPanel(){
        JPanel footerPanel = createTransparentJPanel();

        GridLayout footerPanelLayout = new GridLayout();
        footerPanelLayout.setHgap(20);
        footerPanel.setLayout(footerPanelLayout);

        JButton back = new JButton(Language.BACK);
        back.addActionListener(MainWindow.getInstance());
        footerPanel.add(back);

        return  footerPanel;
    }
}