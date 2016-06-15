package game.model;

public class Player implements Comparable<Player>{
    private String name;
    private int score;
    private int livesLeft;

    public Player(String name){
        this.name = name;
        score = 0;
        livesLeft = 3;
    }

    public Player(String name, int score){
        this.name = name;
        this.score = score;
        livesLeft = 0;
    }

    public String printScore(){
        return name + " " + score;
    }

    @Override
    public int compareTo(Player p) {
        return Integer.compare(p.score, this.score);
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void appendScore(int score) {
        this.score += score;
    }

    public void resetScore(){
        this.score = 0;
    }

    public void resetLives(){
        livesLeft = 3;
    }

    public void gainLife() { livesLeft++; }

    public void loseLife() {livesLeft--; }

    public int getLivesLeft() {
        return livesLeft;
    }
}
