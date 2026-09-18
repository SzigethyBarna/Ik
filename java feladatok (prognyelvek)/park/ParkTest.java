package park;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkTest{
    public void constTestAlpha(){
        AlphaPredator pred=new AlphaPredator("j",Diet.CARNIVORE,8,4);
        assertThrows(ContainmentFailureException.class,()->{pred.triggerEscape();});
    }
}