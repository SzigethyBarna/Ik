import commerce.company.PublicTransportCompany;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import jdk.jfr.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest{
    @Test
    public void testPublicTransportCompanyCreation(){
        List<String> places= new ArrayList<>(List.of("Budapest","Szeged"));
        PublicTransportCompany ptc = new PublicTransportCompany(1927, "Volanbusz", places);
        assertEquals("Volanbusz", ptc.getName());
        assertEquals(1927, ptc.getEstablishmentYear());
        assertEquals(List.of("Budapest", "Szeged"), ptc.getPlaces());
    }

    @Test
    public void testToStringFormat() {
        List<String> places = List.of("Buadepst","Szeged", "Gyor", "Debrecen", "Kaposvar");
        PublicTransportCompany ptc = new PublicTransportCompany(1927, "Volanbusz", places);

        String expected = "Volanbusz:1927: Budapest, Szeged, Gyor, Debrecen, Kaposvar";
        assertEquals(expected, ptc.toString());
    }

    @Test
    public void testCreateFromString() {
        String input = "MAV-START:1868: Budapest, Ukk, Apc";
        PublicTransportCompany ptc = PublicTransportCompany.createFromString(input);

        assertEquals("MAV-START", ptc.getName());
        assertEquals(1868, ptc.getEstablishmentYear());
        assertEquals(List.of("Budapest", "Ukk", "Apc"), ptc.getPlaces());
    }

    @Test
    public void testNoDataLeak(){
        List<String> originalPlaces = new ArrayList<>(List.of("Pecs", "Gyula"));
        PublicTransportCompany ptc = new PublicTransportCompany(2000, "TesztBusz", originalPlaces);

        originalPlaces.add("Sopron");
        assertFalse(ptc.getPlaces().contains("Sopron"));
        List<String> retrievedPlaces = ptc.getPlaces();
        retrievedPlaces.clear();
        assertEquals(2,ptc.getPlaces().size);
    }
}