package park;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DinosaurTest{
    @ParameterizedTest
    @CsvSource({
        "j, CARNIVORE, 1, 50",
        "g, HERBIVORE, 1, 20",
        "k, OMNIVORE, 1, 30"
    })
    public void testgetFoodRq(String name, Diet diet, int dangerLevel,int expected){
        Dinosaur dino=new Dinosaur(name,diet,dangerLevel);
        int curr=dino.getFoodRequired();
        assertEquals(expected,curr,"HIBA");
    }
    @Test
    public void construcTst(){
        assertThrows(IllegalArgumentException.class,()->{new Dinosaur("g",Diet.CARNIVORE,-1);});

    }

}