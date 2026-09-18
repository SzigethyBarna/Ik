package traffic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoadSection{
    private List<Vehicle> vehicles;
    private int maxCapacity;
    public RoadSection(int maxCapacity){
        this.vehicles=new ArrayList<>();
        this.maxCapacity=maxCapacity;
    }
    public void enterRoad(Vehicle vehicle) throws TrafficJamException{
        if(this.vehicles.size()==this.maxCapacity) throw new TrafficJamException();

        this.vehicles.add(vehicle);
    }

    public int loadTraffic(String filename){
        int count=0;
        try{
            Scanner scanner = new Scanner(new File(filename));
            while(scanner.hasNextLine()){
                String line= scanner.nextLine();
                String[] parts=line.split(";");
                if(parts.length==3){
                    try{
                        String licensePlate=parts[0];
                        VehicleType type=VehicleType.valueOf(parts[1]);
                        int baseSpeed=Integer.parseInt(parts[2]);

                        Vehicle vehicle =new Vehicle(licensePlate,type,baseSpeed);
                        this.enterRoad(vehicle);
                        count++;
                    }catch(Exception ex){System.out.println("Nemjo");}
                }
                else if(parts.length==4){
                    try{
                        String licensePlate=parts[0];
                        VehicleType type=VehicleType.valueOf(parts[1]);
                        int baseSpeed=Integer.parseInt(parts[2]);
                        int battery=Integer.parseInt(parts[3]);

                        ElectricVehicle vehicle =new ElectricVehicle(licensePlate,type,baseSpeed,battery);
                        this.enterRoad(vehicle);
                        count++;
                    }catch(Exception ex){System.out.println("Nemjo");}
            }
            
        }
        scanner.close();
        
        
         }catch(FileNotFoundException ex){System.out.println("Nemjo");}
    return count;
    }

}