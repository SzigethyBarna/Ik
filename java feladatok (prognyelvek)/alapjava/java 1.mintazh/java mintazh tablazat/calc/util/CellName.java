package calc.util;

public class CellName{
    public static final String colIndexes="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static boolean isCellNameValid(String cellName){
        if(cellName==null || cellName.length()<2) return false;
        char firstChar=cellName.charAt(0);
        if(colIndexes.indexOf(firstChar)==-1) return false;
        String rowPart = cellName.substring(1);
        if(rowPart.isEmpty()) return false;
        for (char c : rowPart.toCharArray()){
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static int getRowIndexFromCellName(String cellName) throws SheetException{
        if(!isCellNameValid(cellName)) throw new SheetException("Invalid cell name:"+cellName);
        return Integer.parseInt(cellName.substring(1));
    }

    public static int getColIndexFromCellName(String cellName) throws SheetException{
        if(!isCellNameValid(cellName)) throw new SheetException("Invalid cell name"+cellName);
        return colIndexes.indexOf(cellName.charAt(0));
    }
}