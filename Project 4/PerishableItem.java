public abstract class PerishableItem extends GroceryItem   {

    //Assignment 4 question 2 part1
    public String toString(){
        return super.toString() + " (perishable)"; //  String representation of the PerishableItem

    }
    //Assignment 4 question 2 part3
    public PerishableItem(){
        super();
        //perishable = false;
    }

    public PerishableItem(String n, float p, float w){
        super(n,p,w);
        //perishable = false;
    }
}
