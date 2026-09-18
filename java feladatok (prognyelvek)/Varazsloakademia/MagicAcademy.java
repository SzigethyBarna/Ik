package academy;

import academy.magic.ChainSpell;
 import academy.util.BaseSpell;
 import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MagicAcademy{
    private final ArrayList<BaseSpell> spells;
    public MagicAcademy(BaseSpell[] bss){
        this.spells=new ArrayList<>();
        for(BaseSpell bs : bss){
            this.spells.add(bs);
        }
    }

    private void printStatus(StringBuilder sb){
        for (BaseSpell bs : this.spells){
            sb.append(bs.toString()).append("\n");
        }

        System.out.println(sb);
    }

    private String runCommand(String step){
        String[] parts=step.split(" ");
        if(parts.length==2 || parts.length==3){
            int idx=Integer.parseInt(parts[0]);
            if(idx<0 || this.spells.size() <= idx) return "";
            String parancs=parts[1];
            BaseSpell currentSpell = this.spells.get(idx);
            if(parancs.equals("cast"))
                {currentSpell.cast();}
            else if(parancs.equals("dispel")) {currentSpell.dispel();}
            else{
                if(this.spells.get(idx) instanceof ChainSpell){
                    ChainSpell cs=(ChainSpell)(this.spells.get(idx));
                    cs.setAuraColor(parts[2]);
                }
                
            }
            
            return currentSpell.toString();
        }else{return "";}
        
    }

    public String runFile(String filename){
       StringBuilder sb=new StringBuilder();
       try{
         Scanner scanner=new Scanner(new File (filename));
         while (scanner.hasNextLine())
            {
                String line=scanner.nextLine();
                sb.append(runCommand(line)).append("\n");
            }
            scanner.close();
            return sb.toString();
       }catch(FileNotFoundException e){return "File not found.";}
    }
}