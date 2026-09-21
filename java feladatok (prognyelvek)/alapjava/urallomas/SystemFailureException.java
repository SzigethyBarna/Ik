package station.util;

public class SystemFailureException extends Exception{
    public SystemFailureException(){
        super();
    }

     public SystemFailureException(String msg){
        super(msg);
    }
}