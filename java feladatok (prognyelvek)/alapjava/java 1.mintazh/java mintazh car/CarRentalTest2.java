package rental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class CarRentalTest2{
    private void createTestFile(String filename, String content) throws IOException{
        Files.writeString(Path.of(filename), content);
    }

    @Test
    public void insertionSort() throws IOException{
        String filecontent = """
            Draga Auto:CCC 333,500.0
            Kozepes Auto:BBB 222,250.0
            Olcso Auto:AAA 111,50.0
         """;

         String filename="test_cars2.txt";
         createTestFile(filename,filecontent);

         CarRental rental = new CarRental(filename);

         rental.insertionSort();
         String output = rental.toString();
         String expectedFirstLine="Olcso Auto (AAA 111) 50.0 EUR";

         assertEquals(true, output.startsWith(expectedFirstLine));
         Files.deleteIfExists(Path.of(filename));
    }

    @Test
    public void testWeightedAverage() throws IOException{
        String fileContent="""
            Olcso:AAA 111,50.0
            Draga:BBB 222,100.0
         """;
         String filename = "test_avg.txt";
         createTestFile(filename,fileContent);
        CarRental rental = new CarRental(filename);

        assertEquals(83.333, rental.weightedAverage(), 0.01);
        Files.deleteIfExists(Path.of(filename));
    }
}