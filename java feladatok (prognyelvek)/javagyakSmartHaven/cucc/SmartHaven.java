package smarthaven;

import java.util.ArrayList;
import smarthaven.util.BaseModel;

public class SmartHaven{
    private ArrayList<BaseModel> devices;

    public SmartHaven(BaseModel[] devicesArray){
        this.devices= new ArrayList<>();
        for(int i=0;i<devicesArray.length;++i){
            this.devices.add(devicesArray[i]);
        }
    }

    private void printStatus(StringBuilder sb){
        for (BaseModel device : this.devices){
            sb.append(device.toString()).append("\n");
        }
    }

    // --- 2. RÉSZ: A PARANCSÉRTELMEZŐ (A KISAGY) ---

    // private (VISIBLE_TO_NONE)
    private String runCommand(String step) {
        // 1. Feldaraboljuk a parancsot a szóközök mentén
        String[] parts = step.split(" ");
        if (parts.length < 2) return ""; // Ha nincs legalább egy szám és egy parancs, kilépünk

        // 2. Megkeressük az eszközt a listában (az első szó a sorszám)
        int idx = Integer.parseInt(parts[0]);
        if (idx < 0 || idx >= this.devices.size()) return ""; // Ha érvénytelen a sorszám, nem csinál semmit
        
        BaseModel device = this.devices.get(idx);
        String command = parts[1]; // Ez a parancs (on, off, light, action)

        // 3. Végrehajtjuk a megfelelő parancsot
        if (command.equals("on")) {
            device.turnOn();
            return "";
        } 
        else if (command.equals("off")) {
            device.turnOff();
            return "";
        } 
        else if (command.equals("light") && parts.length >= 3) {
            // Itt a frissen tanult trükk! Csak akkor színezünk, ha tényleg InteractiveDevice.
            if (device instanceof smarthaven.device.InteractiveDevice) {
                smarthaven.device.InteractiveDevice okosEszkoz = (smarthaven.device.InteractiveDevice) device;
                okosEszkoz.setLightColor(parts[2]); // A 3. szó a szín (pl. "red")
            }
            return "";
        } 
        else if (command.equals("action") && parts.length >= 3) {
            // Ezt az osztályt a teszt alapján ActionDevice-nak hívják.
            if (device instanceof smarthaven.device.ActionDevice) {
                smarthaven.device.ActionDevice actionEszkoz = (smarthaven.device.ActionDevice) device;
                // Az ActionDevice performAction metódusa egy String-et ad vissza, ezt adjuk mi is tovább!
                return actionEszkoz.performAction(parts[2]);
            }
        }

        return "";
    }


    // --- 3. RÉSZ: A FÁJLBEOLVASÓ (A RECEPT) ---

    // public (VISIBLE_TO_ALL)
    public String runFile(String filename) {
        StringBuilder sb = new StringBuilder(); // Ide gyűjtjük a kimenetet
        
        try {
            // 1. A szent beolvasó sablonunk!
            java.util.Scanner scanner = new java.util.Scanner(new java.io.File(filename));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                
                // 2. Meghívjuk a kisagyat a beolvasott sorra
                String result = runCommand(line);
                
                // Ha a parancs visszaadott valami szöveget (pl. egy akció eredményét), eltesszük
                if (result != null && !result.isEmpty()) {
                    sb.append(result).append("\n");
                }
            }
            scanner.close(); // Mindig bezárjuk!
            
        } catch (java.io.FileNotFoundException e) {
            // A teszt pontosan ezt a hibaüzenetet várja!
            return "File not found.";
        }
        
        return sb.toString();
    }
}
    