package dolgrendszer;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest{
    @ParameterizedTest
    @CsvSource({
        "Zsolt, JUNIOR, 200000, 220000",
        "Zoli, MEDIOR, 300000, 360000",
        "Juli, SENIOR, 500000, 650000"
    })
    public void getMonthlyPayTest(String name,Role role,int baseSalary,int expected){
        Employee employee = new Employee(name,role,baseSalary);
        int pay=employee.getMonthlyPay();
        assertEquals(expected,pay,"HIBA");
    }
}

public class ManagerTest{
    
    @ParameterizedTest
     @CsvSource({
        "Zsolt, JUNIOR, 200000, 10, 320000",})
    public void calculateBonusTest(String name, Role role, int baseSalary, int teamSize,int expected){
        Manager manager= new Manager(name,role,baseSalary,teamSize);
        int bonus=manager.calculateBonus();
        assertEquals(expected,bonus,"HIBA");
    }
}