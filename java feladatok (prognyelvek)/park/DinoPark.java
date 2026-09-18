package park;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DinoPark{
    private List<Dinosaur> roster;
    public DinoPark(){
        this.roster=new ArrayList<>();
    }
    public void loadDinos(String filename){
        
        try{
            Scanner scanner=new Scanner(new File(filename));
            while(scanner.hasNextLine()){
                String line=scanner.nextLine();
                String[] parts = line.split(";");
                if(parts.length==3){
                    try {
                        String name=parts[0];
                        Diet diet=Diet.valueOf(parts[1]);
                        int dangerLevel = Integer.parseInt(parts[2]);
                        if(dangerLevel>=8){
                            AlphaPredator alpha= new AlphaPredator(name,diet,dangerLevel,3);
                            roster.add(alpha);
                        }else{
                        Dinosaur dino=new Dinosaur(name,diet,dangerLevel);
                        roster.add(dino);}
                    }catch(Exception ex) { System.out.println("Nemjo");
                    }
                }
            }
            scanner.close();
        }catch(FileNotFoundException ex) {System.out.println("Nemjo");}
    }
}