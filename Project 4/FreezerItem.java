public class FreezerItem extends PerishableItem { //Assignment 4 question 3 part1
    public FreezerItem(){
        super();
    }

    public FreezerItem(String n, float p, float w){
        super(n,p,w);
    }
    public String toString(){
        return super.toString() + "[keep frozen]"; //  String representation of the PerishableItem

    }
}
