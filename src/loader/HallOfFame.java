package loader;

import game.model.Player;

import java.util.*;

import static loader.PropertiesLoader.loadFromLocalFile;

public class HallOfFame{
    ArrayList<Player> players = new ArrayList<>(10);

    public void loadFromFile(String resourceName){
        Properties properties = loadFromLocalFile(resourceName);

        Enumeration e = properties.propertyNames();

        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String[] values = properties.getProperty(key).split(";");

            for(String value: values) {
               players.add(new Player(key, Integer.parseInt(value)));
            }
        }

        sortPlayers();
    }

    public void addRecord(Player player){
        Player worsePlayer = players.get(players.size() - 1);

        if(player.getScore() > worsePlayer.getScore()){
            players.remove(players.size() - 1);
            players.add(player);
        }

        sortPlayers();
    }

    public String[] toScoresList() {
        String[] scores = new String[players.size()];

        for (int i = 0; i < players.size(); i++) {
            scores[i] = players.get(i).printScore();
        }
        return scores;
    }

    private void sortPlayers(){
        players.sort(Player::compareTo);
    }
}
