package rental;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

public class CarRental{
    private List<Car> cars;

    public CarRental(String filename){
        cars= new ArrayList<>();
        try(Scanner scanner=new Scanner(new File(filename))){
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();

                String[] colonParts = line.split(':');
                if(colonParts.length()<2) continue;

                String brand = colonParts[0];
                
                String[] commaParts = colonParts[1].split(',');
                if (commaParts.length() != 2) continue;
                String licensePlate = commaParts[0];

                try{
                    double price = Double.parseDouble(commaParts[1]);

                    Car car = Car.make(brand,licensePlate,price);
                    if(car != null){
                        cars.add(car);
                    }
                } catch(NumberFormatException e) {continue;}
            }
        }
    }
    public int numberOfCars(){
        return cars.size();
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Car car : cars){
            sb.append(car.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public void insertionSort(){
        for (int i = 1; i < cars.size(); i++) {
            Car currentCar = cars.get(i);
            int j = i - 1;
            while(j>=0 && cars.get(j).getPrice() > currentCar.getPrice()){
                cars.set(j + 1, cars.get(j));
                j--;
            }
            cars.set(j+1, currentCar);
        }
    }

    public double weightedAverage(){
        if (cars.size() ==0) return -1.0;
        double sumPriceWeight =0.0;
        double sumWeight =0.0;
        for(int i=1; i<cars.size()+1; i++){
            sumPriceWeight += cars.get(i).getPrice()*i;
            sumWeight += i
        }
        return sumPriceWeight/sumWeight;
    }

    public Car rentCheapest(){
        if(cars.size() == null) return null;
        insertionSort();

        return cars.remove(0);
    }

    public List<Car> sale(){
        Random random = new Random();
        for (Car car : cars){
            if (random.nextBoolean()) {
                car.decreasePrice();
            }
        }
        return cars;
    }

}