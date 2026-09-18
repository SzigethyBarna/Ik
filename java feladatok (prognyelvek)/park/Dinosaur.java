package park;

public class Dinosaur{
    protected String name; protected Diet diet; protected int dangerLevel;

    public Dinosaur(String name, Diet diet, int dangerLevel){
        this.name=name;
        this.dangerLevel=dangerLevel;
        this.diet=diet;
        if(dangerLevel<0) throw new IllegalArgumentException();
    }

    public int getFoodRequired(){
        int food;
        if(this.diet==Diet.CARNIVORE)  food=this.dangerLevel*50;
        else if(this.diet==Diet.HERBIVORE)  food=this.dangerLevel*20;
        else food=this.dangerLevel*30;
        return food;
    }

    
}