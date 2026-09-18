package pizza;

public class Pizza{
    private PizzaSize size;
    private int toppingsCount;

    public Pizza(PizzaSize size, int toppingsCount){
        this.size=size;
        this.toppingsCount=toppingsCount;
        if(this.toppingsCount<0) throw new IllegalArgumentException("Nem jo");
    }
    
    public int getPrice(){
        
        if (this.size == PizzaSize.LARGE) return 2500+this.toppingsCount*200;
        else if (this.size==PizzaSize.MEDIUM) return 2000+this.toppingsCount*200;
        else return 1500+this.toppingsCount*200;
    }

}