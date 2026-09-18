package smarthaven.util;
import smarthaven.util.IotFunction;

public class BaseModel implements IotFunction{
    protected boolean powerStatus;
    protected String identifier;
    protected final Category deviceType;
    public BaseModel( String identifier, Category deviceType){
        this.powerStatus=false;
        this.deviceType=deviceType;
        this.identifier=identifier;
        if(identifier==null) throw new IllegalArgumentException();
    }

    public boolean isPowerStatus() { return powerStatus; }
    public String getIdentifier() { return identifier; }
    public Category getDeviceType() { return deviceType; }
    public void turnOn(){
        this.powerStatus=true;
    }

    public void turnOff(){
        this.powerStatus=false;
    }

    @Override
    public String toString(){
        return "Device: " +this.getClass().getSimpleName()+", Type: "+this.deviceType+", Identifier: "+this.identifier+", PowerStatus: " +this.powerStatus;
    }



}