public class GroceryBag implements Carryable {
    public static final double MAX_WEIGHT = 5;
    public static final int MAX_ITEMS = 25;
    private GroceryItem[] items;
    private int numItems;
    private float weight;

    public GroceryBag() {
        items = new GroceryItem[MAX_ITEMS];
        numItems = 0;
        weight = 0;

    }
    public GroceryItem[] getItems() {
        return items;
    }

    public int getNumItems() {
        return numItems;
    }

    public float getWeight() {
        return weight;
    }

    public String toString() {
        if (numItems > 0) {
            return ("A " + weight + "kg grocery bag with " + numItems + " items");

        } else {
            return ("An empty grocery bag ");
        }
    }
    //used PROFS CODES!!!!!!!
    public boolean canHold(GroceryItem g) {
        return (((weight + g.getWeight()) <= MAX_WEIGHT) && (numItems <= MAX_ITEMS));
    }

    public void addItem(GroceryItem g) {
        if (canHold(g)) {
            items[numItems++] = g;
            weight += g.getWeight();
        }
    }

    public void removeItem(GroceryItem item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i] == item) {
                weight -= items[i].getWeight();
                items[i] = items[numItems - 1];
                numItems -= 1;
                return;
            }
        }
    }

    // Finds and returns the heaviest item in the shopping cart
    public GroceryItem heaviestItem() {
        if (numItems == 0)
            return null;
        GroceryItem heaviest = items[0];
        for (int i=0; i<numItems; i++) {
            if (items[i].getWeight() > heaviest.getWeight()) {
                heaviest = items[i];
            }
        }
        return heaviest;
    }

    // Determines whether or not the given item in the shopping cart
    public boolean has(GroceryItem item) {
        for (int i = 0; i < numItems; i++) {
            if (items[i] == item) {
                return true;
            }
        }
        return false;
    }

    // Remove all perishables from the bag and return an array of them
    public PerishableItem[] unpackPerishables() { // A4 lastpart of question 2 //modified again in q4
        int perishableCount = 0;
        for (int i=0; i<numItems; i++) {
            if (items[i] instanceof PerishableItem) //modified
                perishableCount++;
        }
        GroceryItem[] perishables = new GroceryItem[perishableCount];
        perishableCount = 0;
        for (int i=0; i<numItems; i++) {
            if (items[i] instanceof PerishableItem) { //modified
                perishables[perishableCount++] = items[i];
                removeItem(items[i]);
                i--;
            }
        }
        return (PerishableItem[]) perishables; //changed
    }
    //question1 Assignment 4 //modified for final output
    public String getContents() {
        String obj="";
        for(int i=0; i<numItems; i++){
            if (items[i] instanceof FreezerItem) {
                obj = obj + "\n   " + items[i].getName() + " weighing " + weight + "kg with price " + "$" + items[i].getPrice() + " (perishable)[keep frozen]";
            }else if(items[i] instanceof RefrigeratorItem){
                obj = obj + "\n   " + items[i].getName() + " weighing " + weight + "kg with price " + "$" + items[i].getPrice() + " (perishable)[keep refrigerated]";
            }else{
                obj = obj + "\n   " + items[i].getName() + " weighing " + weight + "kg with price " + "$" + items[i].getPrice();
            }
        }
        return obj;//groceryItem
    }

    public String getDescription() {
        return "GROCERY BAG (" + weight + "kg)";
    }

    public float getPrice() {
        float total = 0;
        for (int i = 0; i < numItems; i++) {
            total += items[i].getPrice();
        }
        return total;
    }

}
