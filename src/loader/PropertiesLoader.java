package loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties loadFromLocalFile(String fileName){
        Properties properties = null;
        try {
            properties = loadFromInputStream(PropertiesLoader.class.getResourceAsStream(fileName));
        } catch (NullPointerException e) {
            //TODO okienko z errorem
        }

        return properties;
    }

    public static Properties loadFromInputStream(InputStream inputStream){
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            //TODO okienko z errorem
        }

        return properties;
    }
}
