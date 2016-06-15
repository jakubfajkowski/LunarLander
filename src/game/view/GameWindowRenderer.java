package game.view;

import game.model.GameWindow;
import game.model.Lander;
import gui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class GameWindowRenderer implements Renderable {
    private GameWindow gameWindow;
    private MapRenderer mapRenderer;
    private StatsRenderer statsRenderer;

    public GameWindowRenderer(GameWindow gameWindow, MapRenderer mapRenderer, StatsRenderer statsRenderer){

        this.gameWindow = gameWindow;
        this.mapRenderer = mapRenderer;
        this.statsRenderer = statsRenderer;
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

    }

    private void renderMap(Graphics g){
        mapRenderer.render(g);
    }

    private void renderStats(Graphics g){
        statsRenderer.render(g);
    }

}
