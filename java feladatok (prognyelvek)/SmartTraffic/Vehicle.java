package traffic;

public class Vehicle{
    protected  String licensePlate;
    protected VehicleType type;
    protected int baseSpeed;

    public Vehicle(String licensePlate,VehicleType type,int baseSpeed){
        this.licensePlate=licensePlate;
        this.type=type;
        this.baseSpeed=baseSpeed;
        if(baseSpeed<=0) throw new IllegalArgumentException("HIBA");
    }

    public int calculateSpeed(){
        int speed;
        if(this.type==VehicleType.CAR) speed=this.baseSpeed;
        else if(this.type==VehicleType.BUS) speed=this.baseSpeed-10;
        else speed=this.baseSpeed+30;
        return speed;
    }
}