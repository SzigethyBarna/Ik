package commerce.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PublicTransportCompany extends Company{
    private final List<String> places;

    public PublicTransportCompany(int establishmentYear, String name, List<String> places){
        super(establishmentYear, name);

        this.places = new ArrayList<>(places);
    }

    @Override
    public String toString(){
        return getName() + ":" + getEstablishmentYear() + ": " + String.join(", ", places);
    }

    public static PublicTransportCompany createFromString(String str){
        String[] parts = str.split(":");
        String name = parts[0];
        int year = Integer.parseInt(parts[1]);

        String placesString = parts[2].trim();
        String[] placesArray = placesString.split(", ");
        List<String> placesList = Arrays.asList(placesArray);

        return new PublicTransportCompany(year, name, placesList);
    }
}