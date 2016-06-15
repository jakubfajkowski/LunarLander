package loader;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class LevelLoader {
    private String currentLevelName;
    private ArrayList<String> levelNames = new ArrayList<>();

    public LevelLoader() {
        URL mapLocation = this.getClass().getResource("/maps");

        try{
            File folder = getFileFromURL(mapLocation);
            for (File file: folder.listFiles()) {
                levelNames.add("/" + file.getName());
            }

            loadNextLevel();
        }
        catch(NullPointerException | LevelLoaderException e){
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private File getFileFromURL(URL url) {
        File file = null;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }
        return file;
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
