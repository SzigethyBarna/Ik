package calc;

import calc.util.SheetException;

public class Num implements Evaluable{
    private final int value;

    public Num(int value){
        if (value<0) throw new IllegalArgumentException();
        this.value = value;
    }
    
    @Override
    public int eval(Sheet sheet){
        return value;
    }
}