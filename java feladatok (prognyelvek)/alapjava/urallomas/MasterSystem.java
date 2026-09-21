package station.core;

import station.util.*;
import station.core.SystemType;
import java.util.ArrayList;
public class MasterSystem extends BaseSystem{
    private ArrayList<BaseSystem> subSystem;
    private String powerLevel;
    public MasterSystem(String systemCode, SystemType sysType){
        super(systemCode,sysType);
        this.subSystem = new ArrayList<>();
    }

    public void setPowerLevel(String pw){
        this.powerLevel=pw;
    }

    public String getPowerLevel(){
        return this.powerLevel;
    }

    public ArrayList<BaseSystem> getSubSystem(){
        ArrayList<BaseSystem> list =new ArrayList<>(this.subSystem);
        return list;
    }
    
    public void integrate(BaseSystem[] list){
        for(BaseSystem bs : list){
            if(!this.subSystem.contains(bs)){
                this.subSystem.add(bs);
            }
        }
    }

    public void syncPower(){
        for(BaseSystem bs : this.subSystem){
            if(bs instanceof MasterSystem){
                MasterSystem ms = (MasterSystem) bs;
                if(ms.sysType==SystemType.WEAPON || ms.sysType==SystemType.SHIELD ){
                    ms.setPowerLevel(this.powerLevel);
                }
            }
        }
    }

    @Override 
    public String toString(){
        return super.toString()+", Power: "+this.powerLevel;
    }
}
