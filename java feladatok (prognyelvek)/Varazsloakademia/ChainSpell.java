package academy.magic;

import academy.util.BaseSpell;
import academy.util.Castable;
import java.util.ArrayList;

public class ChainSpell extends BaseSpell implements Castable{
    private String auraColor;
    private ArrayList<BaseSpell> linkedSpells;

    public ChainSpell(String spellName,SpellType spellType){
        super(spellName,spellType);
        this.linkedSpells=new ArrayList<>();
    }

    public String getAuraColor(){
        return this.auraColor;
    }
    public ArrayList<BaseSpell> getLinkedSpells() {
        return new ArrayList<>(this.linkedSpells);
    }
    
    public void setAuraColor(String auraColor){
        this.auraColor=auraColor;
    }

    public void link(BaseSpell[] list){
        for(int i=0;i<list.length;++i){
            if(!linkedSpells.contains(list[i])){
            linkedSpells.add(list[i]);}
        }
    }


    public void syncAuras(){
        for(BaseSpell bs : this.linkedSpells){
            if (bs instanceof ChainSpell){
                ChainSpell cs=(ChainSpell) bs;
            if (cs.getSpellType()==SpellType.CHARM || cs.getSpellType()==SpellType.ILLUSION){
                cs.setAuraColor(this.auraColor);
            }
            }
        }
    }

    @Override
    public String toString(){
        return super.toString()+", Aura: "+this.getAuraColor();
    }
}