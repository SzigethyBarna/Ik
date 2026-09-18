package guild;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GuildTest{
    @ParameterizedTest
    @CsvSource({
        "hose, WARRIOR,5,10",
        "hosk, MAGE, 5, 5",
        "hosh, ROGUE, 10, 10"
    })
    public void attackTest(String name, HeroType type, int power, int expected){
        Hero hero = new Hero(name,type,power);
        int tam=hero.attack();
        assertEquals(expected,tam,"HIBA");
    }

    @Test
    public void constructorTest(){
        assertThrows(InvalidStartException.class,()->{new Hero("Alma",HeroType.WARRIOR,-10)});
    }
}