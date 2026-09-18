import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BaseModelTest{
    @Test
    public void constructorTest(){
        assertThrows(IllegalArgumentException.class,()->{new BaseModel(null,Category.LIGHT);});
    }
    @Test
    public void toStringTest(){
        BaseModel model=new BaseModel( "FRIDGE1-IDx", Category.FRIDGE);
        exp=model.toString();
         assertEquals("Device: BaseModel, Type: FRIDGE, Identifier: FRIDGE1-IDx, PowerStatus: true",exp,"HIBA");
    }
}