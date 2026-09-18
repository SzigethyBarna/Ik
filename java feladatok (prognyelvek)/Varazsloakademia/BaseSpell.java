package academy.util;
import academy.magic.*;

public class BaseSpell implements Castable{
    protected boolean isActive;
    protected String spellName;
    private final SpellType spellType;
    public BaseSpell(String spellName,SpellType spellType){
        this.isActive=false;
        this.spellName=spellName;
        this.spellType=spellType;
        if(this.spellName==null) throw new IllegalArgumentException();
    }

    public boolean getIsActive(){
        return this.isActive;
    }

    public String getSpellName(){
        return this.spellName;
    }

    public SpellType getSpellType(){
        return this.spellType;
    }

    public void cast(){
        this.isActive=true;
    }

    public void dispel(){
        this.isActive=false;
    }

    @Override
    public String toString(){
        return "Spell: "+this.getClass().getSimpleName()+", Type: "+this.getSpellType()+", Name: "+this.getSpellName()+", Active: "+this.getIsActive();
    }
}