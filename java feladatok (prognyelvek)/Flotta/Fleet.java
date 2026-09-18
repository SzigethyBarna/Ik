package flotta;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Fleet{
    private List<SpaceShip> ships;
    public Fleet(){
        this.ships= new ArrayList<>();
    }

    public int loadFleet(String filename){
        int counter=0;
        try{
            Scanner scanner=new Scanner(new File(filename));
            while(scanner.hasNextLine()){
                String line=scanner.nextLine();
                String[] parts = line.split(";");

                if(parts.length==3){
                    try{
                    String name=parts[0];
                    ShipClass class=ShipClass.valueOf(parts[1]);
                    int energyLevel=Integer.parseInt(parts[2]);
                    SpaceShip ship= new SpaceShip(name,class,energyLevel);
                    this.ships.add(ship);
                    counter++;
                    }catch(CoreOverLoadException e){System.out.println("HIBA");}
                }
            }
            scanner.close();
        }catch(Exception e){
            System.out.println("HIBA");


        }
        return counter;
    }
}