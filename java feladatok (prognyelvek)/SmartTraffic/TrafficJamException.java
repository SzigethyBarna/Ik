package traffic;

public class TrafficJamException extends Exception{
    public TrafficJamException(){
        super();
    }

    public TrafficJamException(String msg){
        super(msg);
    }
}