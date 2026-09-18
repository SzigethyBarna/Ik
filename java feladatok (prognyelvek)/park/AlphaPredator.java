package park;

public class AlphaPredator extends Dinosaur{
    private int packSize;
    public AlphaPredator(String name, Diet diet, int dangerLevel,int packSize){
        super(name,diet,dangerLevel);
        this.packSize=packSize;
    }

    @Override
    public int getFoodRequired(){
        int food;
        food= (super.getFoodRequired()+this.packSize*10);
        return food;
    }

    public void triggerEscape() throws ContainmentFailureException{
        if(this.dangerLevel+this.packSize>10) throw new ContainmentFailureException();
        this.dangerLevel+=1;
    }
}