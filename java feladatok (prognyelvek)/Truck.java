public class Truck extends Vehicle{
    private int cargoCapacity;
    public Truck(String brand, double startingSpeed, int cargoCapacity){
        super(brand, startingSpeed);
        this.cargoCapacity=cargoCapacity;
    }
    @Override
    public void brake(double decreaseAmount){
        double actualDecrease = decreaseAmount/2.0;
        double currentSpeed = this.getSpeed()-actualDecrease;

        if(currentSpeed <0) currentSpeed=0;

        this.setSpeed(currentSpeed);
    }
}