package game.view;

import game.model.environment.Map;

import java.awt.*;

public class MapRenderer implements Renderable {
    private Map map;

    public MapRenderer(Map map) {
        this.map = map;
    }


    @Override
    public void render(Graphics g) {
        renderLandingSpot(g);
        renderGround(g);
    }

    private void renderLandingSpot(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        g2.fill(map.getLandingSpot().getRect());
    }

    private void renderGround(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fill(map.getGround());
    }
}
