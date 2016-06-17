package loader;

import gui.MainWindow;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class LevelLoader {
    private String currentLevelName;
    private ArrayList<String> levelNames = new ArrayList<>();

    public LevelLoader() {
        try{
            try {
                String[] serverResponseNames = MainWindow.getInstance().getClient().getMapListFromServer().split("\n");

                for (String n: serverResponseNames) {
                    levelNames.add(n);
                }
                loadNextLevel();

            } catch (ClientException e) {
                JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
                levelNames.add("/1");
                levelNames.add("/2");

                loadNextLevel();
            }

        }catch(LevelLoaderException e){
                JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }

    }

    public void loadNextLevel() throws LevelLoaderException{
        if (levelNames.size() > 0){
            currentLevelName = levelNames.remove(0);
        }
        else throw new LevelLoaderException("No more levels to load.");

    }

    public String getCurrentLevelName() {
        return currentLevelName;
    }
}
