import java.util.HashSet;
import java.util.HashMap;
public class HashSetpl{
    public static void main(){
    HashSet<String> nevek=new HashSet<>();
    nevek.add("a");
    nevek.add("b");
    nevek.add("c");

    //nevek.remove("a"), nevek.contains("b"), nincs index, nincs ismetlodo elem
    }

}

public class HashMappl{
    public static void main(){
        //szotar. put(kulcs,ertek), get(kulcs):ertek,containsKey(kulcs)

        HashMap<String, Integer> naplo=new HashMap<>();
        naplo.put("Anna", 5);
        naplo.put("Béla", 3);
        naplo.put("Cecília", 4);

        naplo.put("Béla",4);//Bélához most már a 4-es van bejegyezve

        if (naplo.containsKey("Dávid")) {
            System.out.println("Dávid jegye: " + naplo.get("Dávid"));
        } else {
            System.out.println("Dávid még nem kapott jegyet.");
        }
    }
}

