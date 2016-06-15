package loader;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class ImageLoader {
    static public Image loadImage(String imageName) {
        Image image = null;
        URL imageURL = ImageLoader.class.getResource("/" + imageName);

        try{
            image = new ImageIcon(imageURL).getImage();
        }
        catch(NullPointerException e){
            JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }

        return image;
    }
}
