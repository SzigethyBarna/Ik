package smarthaven.device;

import java.util.ArrayList;
import java.util.List;

public class InteractiveDevice extends BaseModel implements IotFunction{
    private ArrayList<BaseModel> connectedDevices=new ArrayList<>();
    private String lightColor;

    public void setLightColor(String color){
        this.lightColor=color;
    }

    public String getLightColor(){
        return this.lightColor;
    }

    public InteractiveDevice(String identifier, Category deviceType){
        super(identifier,deviceType);
    }

    public ArrayList<BaseModel> getConnectedDevices(){
        return new ArrayList<>(this.connectedDevices);
    }

    public void link(BaseModel[] list){
        for (int i=0; i<list.length;++i){
            if (!this.connectedDevices.contains(list[i])) {
                this.connectedDevices.add(list[i]);
                }
        }
    }

    public void syncLights(){
        for(BaseModel device : connectedDevices){
            if(device instanceof InteractiveDevice){
                InteractiveDevice interactive = (InteractiveDevice) device;
                Category type = interactive.getDeviceType();
                if(type==Category.LIGHT || type==Category.LED_STRIP){
                    interactive.setLightColor(this.lightColor);
                }
            }
        }
    }

    @Override
    public String toString(){

        return super.toString()+", Color: "+this.lightColor;
    }
}