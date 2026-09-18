package traffic;

public class ElectricVehicle extends Vehicle{
    private int batteryLevel;
    public ElectricVehicle(String licensePlate,VehicleType type,int baseSpeed,int batteryLevel){
        super(licensePlate,type,baseSpeed);
        this.batteryLevel=batteryLevel;
        if(batteryLevel<0 || batteryLevel>100) throw new IllegalArgumentException("NEM");
    }

    public void drainBattery(int amount){
        this.batteryLevel-=amount;
        if(this.batteryLevel<0) this.batteryLevel=0;
    }

    @Override
    public int calculateSpeed(){
        int speed=super.calculateSpeed();
        if(this.batteryLevel<20){ 
            int speed2= speed/2;
            return speed2;
            }
        return speed;

    }
}