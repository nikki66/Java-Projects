public class RefrigeratorItem extends PerishableItem {
    public RefrigeratorItem(){
        super();
    }

    public RefrigeratorItem(String n, float p, float w){
        super(n,p,w);
    }
    public String toString(){
        return super.toString() + "[keep refrigerated]"; //  String representation of the PerishableItem

    }
}
