package guild;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Guild{
    private List<Hero> roster;
    public Guild(){
        this.roster=new ArrayList<>();
    }

    public int loadHeroes(String fileName){
        int successCount=0;
        try {
            Scanner scanner= new Scanner(new File(fileName));
            while(scanner.hasNextLine()){
                String line= scanner.nextLine();
                String[] parts = line.split(";");

                if (parts.length==3){
                    try{
                        String name=parts[0];
                        HeroType = HeroType.valueOf(parts[1]);
                        int power= Integer.parseInt(parts[2]);

                        Hero hero= new Hero(name,type,power);
                        this.roster.add(hero);
                        successCount++;
                    } Catch(InvalidStartException e){
                        System.out.println("Nemjo");
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nemjo");
        }
        return successCount;
    }
}