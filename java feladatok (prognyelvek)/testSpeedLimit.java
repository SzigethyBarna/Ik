import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testSpeedLimit{
    @ParameterizedTest
    @CsvSource({
        "20, 90, true",
        "90, 90, true",
        "100, 90, false"
    })
    public void checkSpeedtest(int v, int maxv, boolean expected){
        boolean ae = checkSpeed(v,maxv);
        assertEquals(expected,ae,"HIBA");
    }

}