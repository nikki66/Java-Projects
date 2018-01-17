public class Shopper {
    public Carryable[] cart;
    public int NumItems;
    public static final int MAX_CART_ITEMS = 100;

    public Shopper() {
        cart = new Carryable[MAX_CART_ITEMS];
        NumItems = 0;
    }

    public Carryable[] getCart() {
        return cart;
    }

    public int getNumItems() {
        return NumItems;
    }

    public String toString() {
        return ("Shopper with shopping cart containing " + NumItems + " items");
    }



    // Return the total cost of the items in the cart
    public float totalCost() {
        float total = 0;
        for (int i = 0; i < NumItems; i++) {
            total += cart[i].getPrice();
        }
        return total;
    }

    // Add an item to the shopper's shopping cart
    public void addItem(Carryable g) {
        if (NumItems < MAX_CART_ITEMS)
            cart[NumItems++] = g;
    }

    // Removes the given item from the shopping cart
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
    public void packBags() {
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
        for (int i = 0; i < bagCount; i++)
            addItem(packedBags[i]);

    }
    // Display the contents of the cart
    public void displayCartContents() {
        for (int i=0; i<NumItems; i++) {
            System.out.println(cart[i].getDescription());
            System.out.print(cart[i].getContents());
        }
    }

    // Remove and return all PerishableItems from the Shopping Cart
    public PerishableItem[] removePerishables() {
        PerishableItem[] pItems = new PerishableItem[MAX_CART_ITEMS];
        int pItemCount = 0;
        PerishableItem[] perishables;
        for (int i=0; i<NumItems; i++) {
            if (cart[i] instanceof GroceryBag) {
                perishables = ((GroceryBag) cart[i]).unpackPerishables();
                for (int j=0; j<perishables.length; j++) {
                    pItems[pItemCount++] = perishables[j];
                }
            }
            else {
                if (cart[i] instanceof PerishableItem) {
                    pItems[pItemCount++] = (PerishableItem) cart[i];
                    removeItem(cart[i]);
                    i--;
                }
            }
        }

        // Now create the proper size array
        PerishableItem[] result = new PerishableItem[pItemCount];
        for (int i=0; i<pItemCount; i++)
            result[i] = pItems[i];
        return result;
    }

    // Return the amount of money that would be lost if the freezer breaks down
    public float computeFreezerItemCost() {
        float total = 0;
        for (int i=0; i<NumItems; i++) {
            if (cart[i] instanceof GroceryBag) {
                GroceryItem[] itemsInBag = ((GroceryBag)cart[i]).getItems();
                for (GroceryItem item: itemsInBag) {
                    if (item instanceof FreezerItem)
                        total += item.getPrice();
                }
            }
            if (cart[i] instanceof FreezerItem)
                total += cart[i].getPrice();
        }
        return total;
    }

    // Return the total cost of all items in the packed cart
    public float computeTotalCost() {
        float total = 0;
        for (int i=0; i<NumItems; i++)
            total += cart[i].getPrice();
        return total;
    }
}


