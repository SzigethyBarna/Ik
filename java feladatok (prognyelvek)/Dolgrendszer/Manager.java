package dolgrendszer;

public class Manager extends Employee{
    private int teamSize;
    public Manager(String name, Role role, int baseSalary, int teamSize ){
        super(name,role,baseSalary);
        this.teamSize=teamSize;
    }

    @Override
    public int calculateBonus(){
        return super.calculateBonus()+(this.teamSize*10000);
    }
    public void addTeamMember(){
        this.teamSize+=1;
    }
}