package css.cis3334.fishtracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class FishTest {

    @Test
    public void getWeightInOz_numString() {
        // Arrange
        String origWt = "3 lbs.";
        Fish fish = new Fish(123L,"Walleye", origWt, "April 12");
        // Act
        String wt = fish.getWeightInOz();
        // Assert
        assertEquals(origWt,wt);
    }

    @Test
    public void getWeightInOz_num() {
        // Arrange
        String origWt = "3.45";
        Fish fish = new Fish(123L,"Walleye", origWt, "April 12");
        // Act
        String wt = fish.getWeightInOz();
        // Assert
        assertEquals(origWt,wt);
    }

    @Test
    public void setWeightInOz() {
        // Arrange
        String origWt = "3 lbs.";
        Fish fish = new Fish(123L,"Walleye", origWt, "April 12");
        // Act
        fish.setWeightInOz(origWt);
        // Assert
        String wt = fish.getWeightInOz();
        assertEquals(origWt,wt);
    }


}