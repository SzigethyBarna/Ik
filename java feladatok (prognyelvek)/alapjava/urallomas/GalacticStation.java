package station;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import station.util.*;
import station.core.*;

public class GalacticStation{
    private final ArrayList<BaseSystem> systems;
    public GalacticStation(BaseSystem[] arr){
        this.systems=new ArrayList<>();
        for(int i=0;i<arr.length;++i){
            this.systems.add(arr[i]);
        }

    }

    private void printStatus(StringBuilder sb){
        for(BaseSystem bs : systems){
            sb.append(bs.toString()).append("\n");
        }
    }

    private String runCommand(String step){
        String[] line=step.split(" ");
        int idx=Integer.parseInt(line[0]);
        if(idx<0 || idx>=this.systems.size()) return "";
        if(line.length==2){
            if(line[1].equals("boot")){
                BaseSystem bs= this.systems.get(idx);
                bs.bootUp();
                return bs.toString();
            }
            else if(line[1].equals("shut")){
                BaseSystem bs= this.systems.get(idx);
                bs.shutDown();
                return bs.toString();
        
            }
            return "";
        }
        else if(line.length==3){
            if(line[1].equals("power")){
                BaseSystem bs = this.systems.get(idx);
                if(bs instanceof MasterSystem){
                    MasterSystem ms= (MasterSystem) bs;
                    ms.setPowerLevel(line[2]);
                    return ms.toString();
                }
                else{return "";}
            }return "";
        }
        return "";

    }

    public String runDiagnostics(String filename){
        StringBuilder sb= new StringBuilder();
        try{
            Scanner scanner=new Scanner(new File(filename));
            while(scanner.hasNextLine()){
                String line=scanner.nextLine();
                sb.append(this.runCommand(line)).append("\n");
            }
        }catch(FileNotFoundException ex){return "File not found.";}
        return sb.toString();
    }
}