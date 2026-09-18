package park;

public class ContainmentFailureException extends Exception{
    public ContainmentFailureException(){
        super();
    }

    public ContainmentFailureException(String msg){
        super(msg);
    }
}