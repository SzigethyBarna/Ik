package webshop;

public class PerishableProduct extends Product{
    private int daysToExpire;

    public PerishableProduct(String name,Category category,int basePrice,int daysToExpire){
        super(name,category,basePrice);
        this.daysToExpire=daysToExpire;
        
    }

        @Override
        public double calculateDiscount(){
            double ujar=this.basePrice-super.calculateDiscount();
            if(this.daysToExpire<3) {
                ujar=ujar*0.3;}
            return ujar;
        }

        public void passOneDay(){
            this.daysToExpire-=1;
        }


    
}