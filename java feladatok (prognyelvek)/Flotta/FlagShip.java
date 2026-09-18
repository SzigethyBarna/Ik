package flotta;

public class FlagShip extends  SpaceShip{
    private int fleetBonus;

    public FlagShip(String name,ShipClass shipClass,int energyLevel,int fleetBonus){
        super(name,shipClass,energyLevel);
        this.fleetBonus=fleetBonus;
    }

    @Override
    public int fireWeapons(){
        int wep= super.fireWeapons()+this.fleetBonus;
        return wep;
    }
    public void boostEnergy(int amount)throws CoreOverLoadException{
        if(this.energyLevel+amount>100) throw new CoreOverLoadException();
        this.energyLevel=this.energyLevel+amount;
        
    }
}