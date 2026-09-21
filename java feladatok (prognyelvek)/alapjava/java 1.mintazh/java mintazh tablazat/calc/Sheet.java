package calc;

import calc.util.CellName;
import calc.util.SheetException;

public class Sheet{
    private final Evaluable[][] table;
    private final int rows;
    private final int cols;

    public Sheet(int rows, int cols){
        if(rows<=0 ||cols<=0 || cols>CellName.colIndexes.length()) throw new IllegalArgumentException();
        this.rows = rows;
        this.cols = cols;
        this.table= new Evaluable[rows][cols];
    }

    public void insertToSheet(String cellName, Evaluable evaluable) throws SheetException{
        int r= CellName.getRowIndexFromCellName(cellName);
        int c= CellName.getColIndexFromCellName(cellName);
        table[r][c] = evaluable;
    }

    public Evaluable getFromSheet(String cellName) throws SheetException{
        int r= CellName.getRowIndexFromCellName(cellName);
        int c= CellName.getColIndexFromCellName(cellName);
        return table[r][c];
    }

    @Override
    public String toStrig(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<rows;i++){
            for(int j=0; j<cols;j++){
                if(table[i][j] == null) sb.append("null");
                else{
                    try {
                        sb.append(table[i][j].eval(this));
                        
                    } catch (Exception e) {
                        sb.append("error");
                    }
                }
                if(j<cols-1) sb.append(" ");
            }
            if(i<rows-1) sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}