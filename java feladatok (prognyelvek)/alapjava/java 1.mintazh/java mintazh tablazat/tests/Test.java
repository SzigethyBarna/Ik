package tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jumpiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ValueSource;

import calc.Num;
import calc.Equation;
import calc.Sheet;
import calc.util.CellName;
import calc.util.SheetException;

public class Test{
    @ParameterizedTest
    @CsvSource(testBlock="""
    
    'A 1', false
    a1, false
    Ab1, false
    AXYZ, false
    A, false
    A5, true
    K1, true
    """)

    public void testIsCellNameValid(String cellName, boolean expected){
        assertEquals(expected, CellName.isValidCellName(cellName),"hibas cellanev teszt:" +cellName);

    }

    @ParameterizedTest
    @CsvSource(textBolck="""
    A5, 5
    B15, 15
    """)

    public void testGetRowIndexFromCellName(String cellName, int expectedRow) throws SheetException{
        assertEquals(expectedRow,CellName.getRowFromCellName(cellName));
    }

    @ParameterizedTest
    @CsvSource(textBlock=""" 
    A1, 0
    C5, 2
    """)

    public void testGetColIndexFromCellName(String cellName, int expectedCol) throws SheetException{
        assertEquals(expectedCol,CellName.getColFromIndexCellName(cellName));
    }

    @Test
    public void testNumEval() throws SheetException{
        Num num = new Num(42);
        assertEquals(42, num.eval(null));
    }

    @ParameterizedTest
    @CsvSource(textBlock="""
    'A1 + B1'
    'a1+b2'
    'A1%B2'
     """)
     public void testEquationConstructors(String formula){
        assertThrows(IllegalArgumentException.class,()->new Equation(formula), "Kivetelt kellett volna dobni" +formula);
     }

     @Test
     public void testSheetToString() throws SheetException{
        Sheet sheet = new Sheet(3,2);
        sheet.insertToSheet("A0", new Num(6));
        sheet.insertToSheet("B0", new Num(5));
        sheet.insertToSheet("A1", new Num(2));
        sheet.insertToSheet("B1", new Num(6));
        sheet.insertToSheet("A2", new Num(2));
        sheet.insertToSheet("B2", new Num(9));

        String expected="6 5" +System.lineSeparator()+
                        "2 6" +System.lineSeparator()+
                        "2 9";
        assertEquals(expected, sheet.toString());
        }
    
    @Test
    public void testEquationDivision() throws SheetException{
        Sheet sheet = new Sheet(3, 4);
        sheet.insertToSheet("A0", new Num(6)); sheet.insertToSheet("B0", new Num(5));
        sheet.insertToSheet("A1", new Num(2)); sheet.insertToSheet("B1", new Num(6));
        sheet.insertToSheet("A2", new Num(2)); sheet.insertToSheet("B2", new Num(9));

        sheet.insertToSheet("C0", new Equation("A0+B0"));
        sheet.insertToSheet("C1", new Equation("A1+B1"));
        sheet.insertToSheet("C2", new Equation("A2+B2"));

        sheet.insertToSheet("D0", new Equation("C0/2"));
        sheet.insertToSheet("D1", new Equation("C1/2"));
        sheet.insertToSheet("D2", new Equation("C2/2"));

        String expected= "6 5 11 5" + System.lineSeparator() +
                          "2 6 8 4" + System.lineSeparator() +
                          "2 9 11 5";

        assertEquals(expected, sheet.toString());
    }

    @Test
    public void testEquationMultiplication() throws SheetException {
        Sheet sheet = new Sheet(3, 3);
        sheet.insertToSheet("A0", new Num(6)); sheet.insertToSheet("B0", new Num(5));
        sheet.insertToSheet("A1", new Num(2)); sheet.insertToSheet("B1", new Num(6));
        sheet.insertToSheet("A2", new Num(2)); sheet.insertToSheet("B2", new Num(9));

        sheet.insertToSheet("C0", new Equation("10*B0"));
        sheet.insertToSheet("C1", new Equation("10*B1"));
        sheet.insertToSheet("C2", new Equation("10*B2"));

        String expected = "6 5 50" + System.lineSeparator() +
                          "2 6 60" + System.lineSeparator() +
                          "2 9 90";
        assertEquals(expected, sheet.toString());
    } 
}