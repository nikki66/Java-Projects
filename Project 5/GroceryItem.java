public class GroceryItem implements Carryable{
    private String name;
    private float price;
    private float weight;


    public GroceryItem(){
        name = "";
        price =0f;
        weight =0f;

    }

    public GroceryItem(String n, float p, float w){
        name = n;
        price = p;
        weight = w;

    }
    public String getName() {return name;}
    public float getPrice() {return price;}
    public float getWeight() {return weight;}


    public String toString(){
        return(name + " weighing " + weight + "kg with price " + "$" + price);
    }

    public String getContents() {return "";}
    public String getDescription() {return name;}

}
