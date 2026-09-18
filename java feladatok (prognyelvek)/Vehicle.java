public class Vehicle {
    protected String brand;
    private double currentSpeed; // Protected, hogy a gyermek is lássa!

    // Szülő konstruktora (paraméteres!)
    public Vehicle(String brand, double startingSpeed) {
        this.brand = brand;
        this.currentSpeed = startingSpeed;
    }

    public void brake(double decreaseAmount) {
        this.currentSpeed -= decreaseAmount;
        if (this.currentSpeed < 0) this.currentSpeed = 0;
    }

    public double getSpeed() {
        return this.currentSpeed;
    }
    public void setSpeed(double speed){
        this.currentSpeed=speed;
    }
}