package loader;

import org.junit.Test;

import static org.junit.Assert.*;

public class LevelLoaderTest {
    @Test
    public void testLoadNextLevel(){
        LevelLoader levelLoader = new LevelLoader();

        String actualMapName1 = levelLoader.getCurrentLevelName();
        try{
            levelLoader.loadNextLevel();
        }
        catch (LevelLoaderException e){

        }
        String actualMapName2 = levelLoader.getCurrentLevelName();

        String expectedMapName1 = "/Nice Map";
        String expectedMapName2 = "/Pretty Nice Map";

        assertTrue(expectedMapName1.equals(actualMapName1));
        assertTrue(expectedMapName2.equals(actualMapName2));
    }
}