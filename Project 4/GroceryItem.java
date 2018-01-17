public class GroceryItem implements Carryable{
    private String name;
    private float price;
    private float weight;
    //private boolean perishable;

    public GroceryItem(){
        name = "";
        price =0f;
        weight =0f;
        //perishable = false;
    }

    public GroceryItem(String n, float p, float w){
        name = n;
        price = p;
        weight = w;
        //perishable = false;
    }
   /* public GroceryItem(String n, float p, float w, boolean s){
        name = n;
        price = p;
        weight = w;
        perishable = s;
    }*/
    public String getName() {return name;}
    public float getPrice() {return price;}
    public float getWeight() {return weight;}
    //public boolean isPerishable() {return perishable;}

    public String toString(){
        return(name + " weighing " + weight + "kg with price " + "$" + price);
    }

    //question1 Assignment 4
    public String getContents() {return "";}
    public String getDescription() {return name;}

}
