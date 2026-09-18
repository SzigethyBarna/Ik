package webshop;

public class Product{
    protected String name;
    protected Category category;
    protected int basePrice;

    public Product(String name,Category category,int basePrice){
        this.name=name;
        this.basePrice=basePrice;
        this.category=category;
        if(basePrice<=0) throw new IllegalArgumentException();
    }

    public double calculateDiscount(){
        if(this.category==Category.ELECTRONICS) return (this.basePrice*0.05);
        else if(this.category==Category.CLOTHING) return (this.basePrice*0.2);
        else return (double)(0.0);
    }

    public int getFinalPrice(){
        return (int)((double)this.basePrice-this.calculateDiscount());
    }
}