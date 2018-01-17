public class Shopper { // Assignment4 last part of question1
    public Carryable[] cart;
    public int NumItems;
    public static final int MAX_CART_ITEMS = 100;

    public Shopper() {
        cart = new Carryable[MAX_CART_ITEMS];//modified
        NumItems = 0;
    }

    public Carryable[] getCart() {
        return cart;
    }//modified

    public int getNumItems() {
        return NumItems;
    }

    public String toString() {
        return ("Shopper with shopping cart containing " + NumItems + " items");
    }

    //USED PROFS CODES!!!!!

    // Return the total cost of the items in the cart
    public float totalCost() {
        float total = 0;
        for (int i = 0; i < NumItems; i++) {
            total += cart[i].getPrice();
        }
        return total;
    }

    // Add an item to the shopper's shopping cart  //Assignment4 question 4 part1 (modified to carryable)
    public void addItem(Carryable g) {
        if (NumItems < MAX_CART_ITEMS)
            cart[NumItems++] = g;
    }

    // Removes the given item from the shopping cart //Assignment4 question 4 part1 (modified to carryable)
    public void removeItem(Carryable g) {
        for (int i = 0; i < NumItems; i++) {
            if (cart[i] == g) {
                cart[i] = cart[NumItems - 1];
                NumItems -= 1;
                return;
            }
        }
    }

    // Go through the shopping cart and pack all packable items into bags
    public void /*GroceryBag[]*/ packBags() {
        GroceryBag[] packedBags = new GroceryBag[NumItems];
        int bagCount = 0;

        GroceryBag currentBag = new GroceryBag();
        for (int i = 0; i < NumItems; i++) {
            GroceryItem item = (GroceryItem) cart[i]; //typecasted
            if (item.getWeight() <= GroceryBag.MAX_WEIGHT) {
                if (!currentBag.canHold(item)) {
                    packedBags[bagCount++] = currentBag;
                    currentBag = new GroceryBag();
                }
                currentBag.addItem(item);
                removeItem(item);
                i--;
            }
        }
        // Check this in case there were no bagged items
        if (currentBag.getWeight() > 0)
            packedBags[bagCount++] = currentBag;

        // Now create a new bag array which is just the right size
        // GroceryBag[] result = new GroceryBag[bagCount];
        for (int i = 0; i < bagCount; i++)
            addItem(packedBags[i]); //modified
        //return result;
    }

    //Assignment 4 question 4 part 3

    public void displayCartContents() {
        String display = "";
        for (int i = 0; i < NumItems; i++) {
            if (cart[i].getContents() == "") {
                display = display + cart[i].getDescription() + "\n";

            } else {
                display = display + cart[i].getDescription() + cart[i].getContents() + "\n";

            }

        }
        System.out.println(display);
    }
    //Assignment 4 question 4 lastpart
    public static int append(PerishableItem[] arr, int counter, PerishableItem number){
        arr[counter++]=number;
        return counter;
    }

    public PerishableItem[] removePerishables() {
        PerishableItem[] unpackperish = new PerishableItem [NumItems*25];
        int counter =0;
        for (int i = 0; i <NumItems; i++) {
            if (cart[i] instanceof PerishableItem) {
                counter = append(unpackperish,counter, (PerishableItem) cart[i]);
                removeItem(cart[i]);

            }else if(cart[i] instanceof GroceryBag){
                GroceryBag bag= (GroceryBag) cart[i];
                int length = bag.getNumItems();
                for(int j=0;j<length;j++) {
                    if (bag.getItems()[j] instanceof PerishableItem) {
                        counter = append(unpackperish, counter, (PerishableItem) bag.getItems()[j]);
                        bag.removeItem(bag.getItems()[j]);

                    }
                }
            }



        }PerishableItem[] finish = new PerishableItem[counter];
        for( int k = 0;k<counter;k++){
            finish[k]=unpackperish[k];
        }return finish;
        //return unpackperish;

    }
    //Assignment 4 question 5
    public String computeFreezerItemCost() {
        float sum = 0;
        FreezerItem[] unpackperish = new FreezerItem[NumItems * 25];
        int counter = 0;
        for (int i = 0; i < NumItems; i++) {
            if (cart[i] instanceof FreezerItem) {
                counter = append(unpackperish, counter, (FreezerItem) cart[i]);

            } else if (cart[i] instanceof GroceryBag) {
                GroceryBag bag = (GroceryBag) cart[i];
                int length = bag.getNumItems();
                for (int j = 0; j < length; j++) {
                    if (bag.getItems()[j] instanceof FreezerItem) {
                        counter = append(unpackperish, counter, (FreezerItem) bag.getItems()[j]);


                    }
                }
            }


        }
        PerishableItem[] finish = new PerishableItem[counter];
        for (int k = 0; k < counter; k++) {
            finish[k] = unpackperish[k];
            sum+=unpackperish[k].getPrice();


        }return String.format("%1.2f",sum); //for rounding purposes
    }

    public float computeTotalCost(){
        float sum =0f;
        for (int i = 0; i <NumItems; i++) {
            sum+=cart[i].getPrice();

        }return sum;

    }
}


