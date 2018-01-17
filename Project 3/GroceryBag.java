public class GroceryBag {
    GroceryItem bag;
    private static final double MAX_WEIGHT = 5;
    private static final int MAX_ITEMS = 25;
    private GroceryItem[] items ;
    private int numItems;
    private float weight;

    public GroceryItem getBag() {return bag;}
    public static double getMaxWeight() {return MAX_WEIGHT;}
    public static int getMaxItems() {return MAX_ITEMS;}
    public GroceryItem[] getItems() {return items;}
    public int getNumItems() {return numItems;}
    public float getWeight() {return weight;}

    public GroceryBag(){
        items = new GroceryItem[25];
        numItems = 0;
        weight = 0f;

    }
    public String toString() {
        if (numItems > 0) {
            return ( "A " + weight + "kg grocery bag with " + numItems + " items");

        }else{
            return("An empty grocery bag ");
        }
    }
    public void addItem(GroceryItem item){
        if((this.weight + item.getWeight() <=5.0) && (this.numItems<26)){
            this.items[numItems++]=item;
            this.weight+=item.getWeight();
        }

    }
    public void removeItem(GroceryItem item){
        if(this.numItems>0){
            for(int i = 0;i<numItems;i++){
                if(this.items[i].getName()==item.getName()){
                    GroceryItem tmp = this.items[i];
                    this.items[i]=this.items[this.numItems-1];
                    this.items[this.numItems-1]=tmp;
                    this.items[this.numItems-1]=null;
                    this.numItems--;
                }
            }
        }
    }
    public GroceryItem heaviestItem(){
        float base = 0f;
        GroceryItem heaviest=null;

        if(this.numItems==0){
            ;
        }else{
            for(int i = 0;i<this.numItems;i++){
                if (this.items[i].getWeight()>base){
                    base= this.items[i].getWeight();
                    heaviest= this.items[i];
                }
            }

        }return heaviest;
    }
    public boolean has(GroceryItem item){
        boolean flag = false;
        for(int i = 0;i<numItems;i++){
            if(this.items[i].getName()==item.getName()){
                flag = true;
                break;
            }
        }return flag;

    }
    public GroceryItem[] unpackPerishables() {
        GroceryItem[] perish = new GroceryItem[this.numItems];
        int index=0;
        int a = this.numItems;
        for(int i =0;i<a;i++ ){
            if(this.items[i].isPerishable()){
                perish[index]=this.items[i];
                index++;
            }
        }
        GroceryItem[] copy = new GroceryItem[index];
        for(int k = 0;k<index;k++){
            copy[k]=perish[k];
            removeItem(perish[k]);
            weight-=perish[k].getWeight();
        }return copy;


    }

}
