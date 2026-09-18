package dolgrendszer;

public class Employee{
    protected String name;
    protected Role role; 
    protected int baseSalary;

    public Employee(String name, Role role, int baseSalary){
        this.name=name;
        this.role=role;
        this.baseSalary=baseSalary;
        if(baseSalary<0) throw new IllegalArgumentException();
    }
    public int calculateBonus(){
        if(this.role==Role.JUNIOR) return (int)(this.baseSalary*0.1);
        else if(this.role==Role.MEDIOR) return (int)(this.baseSalary*0.2);
        else return (int)(this.baseSalary*0.3);
    }

    public int getMonthlyPay(){
        return this.baseSalary+this.calculateBonus();
    }
}