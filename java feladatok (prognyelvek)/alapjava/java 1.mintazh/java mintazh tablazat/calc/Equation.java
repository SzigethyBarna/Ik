package calc;
import calc.util.CellName;
import calc.util.SheetException;

public class Equation implements Evaluable{
    private String operand1;
    private String operand2;
    private char operator;

    public Equation(String formula){
        if(formula.contains(" ")) throw new IllegalArgumentException();
        int opPos=-1;
        char[] operators={'+','-','*','/'};
        for (char op : operators){
            int pos = formula.indexOf(op);
            if (pos!=-1){
                opPos = pos;
                this.operator=op;
                break;
            }
        }

        if (opPos==-1) throw new IllegalArgumentException();
        this.operand1 = formula.substring(0,opPos);
        this.operand2 = formula.substring(opPos+1);

        if(Character.isLetter(this.operand1.charAt(0)) && !CellName.isCellNameValid(this.operand1)) throw new IllegalArgumentException();
        if(Character.isLetter(this.operand2.charAt(0)) && !CellName.isCellNameValid(this.operand2)) throw new IllegalArgumentException();
        
        if (formula.matches(".*[a-z].*")) throw new IllegalArgumentException();
    }

    private int constructIntFromOperandStr(String operandStr, Sheet sheet) throws SheetException{
        if(Character.isLetter(operandStr.charAt(0))){
            Evaluable cell = sheet.getFromSheet(operandStr);
            if(cell==null) return 0;
            return cell.eval(sheet);
        }else return Integer.parseInt(operandStr);
    }


    @Override
    public int eval(Sheet sheet) throws SheetException{
        int val1 = constructIntFromOperandStr(operand1,sheet);
        int val2 = constructIntFromOperandStr(operand2,sheet);

        switch(operator){
            case '+': return val1+val2;
            case '-': 
                if(val1-val2<0) throw new ArithmeticException();
                return val1-val2;
            case '*':return val1*val2;
            case '/':
                if(val2==0) throw new ArithmeticException();
                return val1/val2;
            default: return 0;

        }
    }
}