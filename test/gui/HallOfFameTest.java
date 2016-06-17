package gui;

import game.model.Player;
import loader.HallOfFame;
import org.junit.Test;

import static org.junit.Assert.*;


public class HallOfFameTest {

    @Test
    public void testLoadFromFile() throws Exception {
        HallOfFame hallOfFame = new HallOfFame();
        hallOfFame.loadFromFile("/bestscores.txt");
        String[] actualScores = hallOfFame.toScoresList();


        String[] expectedScores = new String[10];

        expectedScores[0] = "AAA 108";
        expectedScores[1] = "AAA 108";
        expectedScores[2] = "BBB 107";
        expectedScores[3] = "BBB 106";
        expectedScores[4] = "CCC 105";
        expectedScores[5] = "CCC 104";
        expectedScores[6] = "DDD 103";
        expectedScores[7] = "DDD 102";
        expectedScores[8] = "EEE 101";
        expectedScores[9] = "EEE 100";

        for (int i = 0; i < expectedScores.length; i++)
            assertTrue(expectedScores[i].equals(actualScores[i]));
    }

    @Test
    public void testAddRecord() throws Exception {
        HallOfFame hallOfFame = new HallOfFame();
        hallOfFame.loadFromFile("/bestscores.txt");
        Player playerToAdd = new Player("FFF", 109);
        hallOfFame.addRecord(playerToAdd);
        String[] actualScores = hallOfFame.toScoresList();

        String[] expectedScores = new String[10];

        expectedScores[0] = "FFF 109";
        expectedScores[1] = "AAA 108";
        expectedScores[2] = "AAA 108";
        expectedScores[3] = "BBB 107";
        expectedScores[4] = "BBB 106";
        expectedScores[5] = "CCC 105";
        expectedScores[6] = "CCC 104";
        expectedScores[7] = "DDD 103";
        expectedScores[8] = "DDD 102";
        expectedScores[9] = "EEE 101";

        for (int i = 0; i < expectedScores.length; i++)
            assertTrue(expectedScores[i].equals(actualScores[i]));
    }
}