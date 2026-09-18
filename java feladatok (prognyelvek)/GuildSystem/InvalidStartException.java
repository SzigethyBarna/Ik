package guild;

public class InvalidStartException extends Exception{
    public InvalidStartException(){
        super();
    }
     public InvalidStartException(String msg){
        super(msg);
    }
}