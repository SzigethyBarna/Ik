package academy.util;

public class RestrictedSpellException extends Exception{
    public RestrictedSpellException(){
        super();
    }
    public RestrictedSpellException(String msg){
        super(msg);
    }
}