package guild;

public class VeteranHero extends Hero{
    private int bonusDamage;
    public VeteranHero(String name, HeroType type, int power,int bonusDamage){
        super(name,type,power);
        this.bonusDamage=bonusDamage;
    }
    @Override
    public int attack(){
        int attpower=super.attack()+this.bonusDamage;
        return attpower;
    }
}