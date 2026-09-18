package smarthaven.util;

public class UnsupportedDeviceException extends Exception{
    public UnsupportedDeviceException(){
        super();
    }
     public UnsupportedDeviceException(String msg){
        super(msg);
    }
}