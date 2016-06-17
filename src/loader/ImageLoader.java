package loader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class ImageLoader {
    static public Image loadImage(String imageName) {
        Image image = null;
        InputStream inputStream = ImageLoader.class.getResourceAsStream("/" + imageName);

        try{
            image = ImageIO.read(inputStream);
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return image;
    }
}
