package calc;
import calc.util.SheetException;
public interface Evaluable{
    int eval(Sheet sheet) throws SheetException;
}