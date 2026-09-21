package station.util;
import station.util.Bootable;
import station.core.SystemType;

public class BaseSystem implements Bootable{
    protected boolean isOnline;
    protected String systemCode;
    private final SystemType sysType;

    public BaseSystem(String systemCode, SystemType sysType){
        this.isOnline = false;
        this.sysType=sysType;
        this.systemCode= systemCode;
        if(systemCode==null) throw new IllegalArgumentException();
    }

    public SystemType getSystemType(){
        return this.sysType;
    }

    public String getSystemCode(){
        return this.systemCode;
    }

    public boolean getIsOnline(){
        return this.isOnline;
    }

    public void bootUp(){
        this.isOnline=true;
    }

    public void shutDown(){
        this.isOnline=false;
    }

    @Override
    public String toString(){
        return "System: "+this.getClass().getSimpleName()+", Type: "+this.sysType+", Code: "+this.systemCode+", Online: "+this.isOnline;
    }
}