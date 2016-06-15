package gui;

import game.model.Player;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void testPrintScore() throws Exception {
        Player player = new Player("AAA", 100);
        String actualPrintScoreResult = player.printScore();

        String expectedPrintScoreResult = "AAA 100";

        assertTrue(actualPrintScoreResult.equals(expectedPrintScoreResult));

    }

    @Test
    public void testCompareTo() throws Exception {
        Player player1 = new Player("AAA", 100);
        Player player2 = new Player("BBB", 101);

        int actual = player1.compareTo(player2);

        int expected = -1;

        assertEquals(expected, actual);
    }
}