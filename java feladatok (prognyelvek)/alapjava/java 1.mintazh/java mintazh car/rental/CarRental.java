package rental;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CarRental {
    private List<Car> cars;

    // Konstruktor és fájlbeolvasás
    public CarRental(String filename) {
        cars = new ArrayList<>();
        
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // Formátum: márka:rendszám,kölcsönzési díj
                // Kettéválasztjuk először a kettőspont mentén
                String[] colonParts = line.split(":");
                if (colonParts.length != 2) {
                    continue; // Hibás sor átugrása
                }
                
                String brand = colonParts[0];
                
                // Utána a maradékot a vessző mentén
                String[] commaParts = colonParts[1].split(",");
                if (commaParts.length != 2) {
                    continue; // Hibás sor átugrása
                }
                
                String licensePlate = commaParts[0];
                
                try {
                    double price = Double.parseDouble(commaParts[1]);
                    
                    // Megpróbáljuk létrehozni
                    Car car = Car.make(brand, licensePlate, price);
                    if (car != null) {
                        cars.add(car);
                    }
                } catch (NumberFormatException e) {
                    // Ha a kölcsönzési díj nem szám, átugorjuk a sort
                    continue; 
                }
            }
        } catch (FileNotFoundException e) {
            // Ha nincs meg a fájl, a lista üres marad, és nem dobunk kivételt tovább
        }
    }

    public int numberOfCars() {
        return cars.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Car car : cars) {
            // Az utolsó autó után is lesz sortörés a lineSeparator() miatt
            sb.append(car.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    // Beszúró rendezés (Insertion Sort) implementációja
    public void insertionSort() {
        for (int i = 1; i < cars.size(); i++) {
            Car currentCar = cars.get(i);
            int j = i - 1;
            
            // Amíg az előző elemek ára nagyobb, mint az aktuális elem ára, jobbra toljuk őket
            while (j >= 0 && cars.get(j).getPrice() > currentCar.getPrice()) {
                cars.set(j + 1, cars.get(j));
                j--;
            }
            // A megtalált helyre beszúrjuk az elemet
            cars.set(j + 1, currentCar);
        }
    }

    // Súlyozott átlag
    public double weightedAverage() {
        if (cars.isEmpty()) {
            return -1.0;
        }
        
        double sumPriceWeight = 0.0;
        int sumWeight = 0;
        
        for (int i = 0; i < cars.size(); i++) {
            int weight = i + 1; // 0. indexű súlya 1, stb.
            sumPriceWeight += cars.get(i).getPrice() * weight;
            sumWeight += weight;
        }
        
        return sumPriceWeight / sumWeight;
    }

    // Legolcsóbb kikölcsönzése
    public Car rentCheapest() {
        if (cars.isEmpty()) {
            return null;
        }
        // Sorbarakjuk az árakat növekvőbe
        insertionSort();
        // A legelső elemet (0. index, a legolcsóbb) kivesszük és visszaadjuk
        return cars.remove(0);
    }

    // Véletlenszerű árcsökkentés
    public List<Car> sale() {
        Random random = new Random();
        for (Car car : cars) {
            // nextBoolean() véletlenszerűen true vagy false értéket ad
            if (random.nextBoolean()) {
                car.decreasePrice();
            }
        }
        return cars;
    }
}