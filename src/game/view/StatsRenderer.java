package game.view;

import game.model.GameWindow;
import game.model.Stats;

import java.awt.*;

public class StatsRenderer implements Renderable {
    private Stats stats;

    public StatsRenderer(Stats stats) {
        this.stats = stats;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);

        g.drawString("WYSOKOSC " + (int)stats.getHeight() + " M", 10, 10);
        g.drawString("PALIWO " + (int)stats.getFuel() + " L", 10, 20);
        g.drawString("PREDKOSC PIONOWA " + (int)stats.getVerticalVelocity() + " M/S", 10, 30);
        g.drawString("PREDKOSC POZIOMA " + (int)stats.getHorizontalVelocity() + " M/S", 10, 40);

        g.drawString("POZIOM " + stats.getLevelName(), GameWindow.WIDTH - 200, 10);
        g.drawString("PUNKTY " + stats.getScore(), GameWindow.WIDTH - 200, 20);
        g.drawString("LICZBA ŻYĆ " + stats.getLives(), GameWindow.WIDTH - 200, 30);

        if(!stats.getCommunicate().equals(""))
        {
            Font currentFont = g.getFont();
            Font newFont = currentFont.deriveFont(currentFont.getSize() * 10F);
            drawCenteredString(g, stats.getCommunicate(), new Dimension(GameWindow.WIDTH, GameWindow.HEIGHT), newFont);
        }
    }

    public void drawCenteredString(Graphics g, String text, Dimension dimension, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = (dimension.width - metrics.stringWidth(text)) / 2;
        int y = ((dimension.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
        g.dispose();
    }
}
