package flotta;

public class SpaceShip{
    protected String name; protected ShipClass shipClass; protected int energyLevel;

    public SpaceShip(String name,ShipClass shipClass,int energyLevel){
        this.energyLevel=energyLevel;
        this.name=name;
        this.shipClass=shipClass;

        if(this.energyLevel<0) throw new IllegalArgumentException("NEM");
    }

    public int fireWeapons(){
        int wep;
        if(this.shipClass==ShipClass.FIGHTER) wep=this.energyLevel*2;
        else if(this.shipClass==ShipClass.CRUISER) wep=this.energyLevel*5;
        else wep=this.energyLevel;
        return wep;
    }
}