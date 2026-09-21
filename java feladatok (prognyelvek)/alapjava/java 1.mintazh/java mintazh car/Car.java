package rental;

public class Car{
    private String brand;
    private String licensePlate;
    private double price;
    private static final double MAX_PRICE=500.0;
    private static final Car CAR_OF_THE_YEAR = new Car("Alfa Romeo", "ABC 123", MAX_PRICE);
    
    private Car(String brand, String licensePlate, double price){
        this.brand=brand;
        this.licensePlate=licensePlate;
        this.price=price;
    }

    public static Car make(String brand, String licensePlate, double price){
        
        
        Car car = new Car(brand,licensePlate,price);
        if(!isValidCar(car)) return null;
        return car;
    }

    private static boolean isValidCar(Car car){
        if(car.brand.length()<2) return false;
        if(!isValidLicensePlate(car.licensePlate)) return false;
        if(car.price<0.0 || car.price>MAX_PRICE) return false;
        return true;

    }

    private static boolean isValidLicensePlate(String licensePlate){
        
        
        if(licensePlate == null || licensePlate.length()!=7) return false;
        for(int i=0;i<licensePlate.length();i++){
            char c = licensePlate.charAt(i);
            if(i<3){
                if(!Character.isUpperCase(c)) return false;
            }
            else if(i>3){
                if(!Character.isDigit(c)) return false;
            }
            else{
                if(c != ' ') return false;
            }
            
        }
        return true;
    }

    public void decreasePrice(){
        if (this.price > 10.0 && this.price != MAX_PRICE) {
        this.price -= 10.0;}
    }

    public boolean isCheaperThan(Car car1){
        if(car1.price<this.price) return true;
        else return false;
    }

    public double getPrice(){
        return this.price;
    }

    @Override
    public String toString(){
        return "%s (%s) %5.1f EUR".formatted(brand,licensePlate,price);
    }
     
    

}