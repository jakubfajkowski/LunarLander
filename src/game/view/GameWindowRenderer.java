package game.view;

import game.model.GameWindow;
import game.model.Lander;
import loader.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindowRenderer implements Renderable {
    private GameWindow gameWindow;
    private MapRenderer mapRenderer;
    private StatsRenderer statsRenderer;
    private Image crashedLanderImage;
    private Image landedLanderImage;

    public GameWindowRenderer(GameWindow gameWindow, MapRenderer mapRenderer, StatsRenderer statsRenderer){
        this.gameWindow = gameWindow;
        this.mapRenderer = mapRenderer;
        this.statsRenderer = statsRenderer;

        crashedLanderImage = ImageLoader.loadImage("crashed.gif");
        landedLanderImage = ImageLoader.loadImage("landed.gif");
    }


    @Override
    public void render(Graphics g) {
        renderMap(g);
        renderStats(g);
        renderLander(g);
    }

    private void renderLander(Graphics g){
        Lander lander = gameWindow.getLander();

        int xCenter = (int) lander.getPosition().getX();
        int yCenter = (int)(GameWindow.HEIGHT - lander.getPosition().getY());

        g.setColor(Color.white);
        g.drawLine(xCenter-5, yCenter+5, xCenter-10, yCenter+15);
        g.drawLine(xCenter, yCenter+5, xCenter, yCenter+15);
        g.drawLine(xCenter+5, yCenter+5, xCenter+10, yCenter+15);
        g.fillOval(xCenter-10,yCenter-10,20,20);
        g.setColor(Color.blue);
        g.fillOval(xCenter-5,yCenter-5,10,10);

        if(lander.isLeftBoosterWorking()){
            g.setColor(Color.red);
            g.fillOval(xCenter-11,yCenter+10,10,10);
            g.setColor(Color.yellow);
            g.fillOval(xCenter-6,yCenter+10,5,5);
        }

        if(lander.isRightBoosterWorking()){
            g.setColor(Color.red);
            g.fillOval(xCenter+1,yCenter+10,10,10);
            g.setColor(Color.yellow);
            g.fillOval(xCenter+1,yCenter+10,5,5);
        }
        if(lander.isCrashed()) {
            g.drawImage(crashedLanderImage,
                    xCenter-32,yCenter-32,null);

        }
        if(lander.isLanded()){
            g.drawImage(landedLanderImage,
                    xCenter-32,yCenter-32,null);
        }

        lander.setLeftBoosterWorking(false);
        lander.setRightBoosterWorking(false);

    }

    private void renderMap(Graphics g){
        mapRenderer.render(g);
    }

    private void renderStats(Graphics g){
        statsRenderer.render(g);
    }
}
