package shop;

public class InvoiceGenerator{
    public String generateInvoice(String[] items,int[] prices){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<items.length;++i){
            sb.append(items[i]).append(" - ").append(prices[i]).append("Ft\n");
        }

        return sb.toString();
    }
}