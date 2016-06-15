package game.model;

import game.model.environment.Map;


public class GameWindow {
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    private String configurationFileName;
    private Map map;
    private Stats stats;
    private Lander lander;
    private Player player;
    private int score;


    public GameWindow(String configurationFileName, Player player){
        this.stats = new Stats();
        this.configurationFileName = configurationFileName;
        this.map = new Map(configurationFileName);
        this.lander = new Lander(configurationFileName);
        this.player = player;
        this.score = 25000;
    }

    public Map getMap() {
        return map;
    }

    public Stats getStats() {
        return stats;
    }

    public Lander getLander() {
        return lander;
    }

    public Player getPlayer() {
        return player;
    }

    public String getConfigurationFileName() {
        return configurationFileName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
