package guild;
import InvalidStartEception;

public class Hero{
    protected String name; protected HeroType type; protected int power;

    public Hero(String name, HeroType type, int power) throws InvalidStartException{
        this.name=name;
        this.type=type;
        this.power=power;
        if(power<1) throw new InvalidStartException("Nem jo");
    }

    public int attack(){
        int attpow=this.power;
        if(this.type==HeroType.WARRIOR) attpow=attpow*2;
        return attpow;
    }

}