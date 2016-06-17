package loader;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties loadFromLocalFile(String fileName){
        Properties properties = null;
        try {
            properties = loadFromInputStream(PropertiesLoader.class.getResourceAsStream("/" + fileName));
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return properties;
    }

    public static Properties loadFromInputStream(InputStream inputStream){
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return properties;
    }
}
