public class CellPhoneTestProgram {
    public static void main(String args[]) {

        // Create three CellPhone objects.
        // One should be an "iPhone6Plus" cell from Apple which has a 12 month warranty and costs
        // $915. Another cell should be a "Galaxy S7" from Samsung with an 18 month warranty, and
        // a price of $900.00. The last phone should be a "PRIV" from BlackBerry with a 24 month
        // warranty and a price of $890.00.
        CellPhone iPhone = new CellPhone("iPhone6Plus", "Apple", 12, 915f);
        CellPhone galaxy = new CellPhone("Galaxy S7", "Samsung", 18, 900f);
        CellPhone priv = new CellPhone ("PRIV", "BlackBerry", 24, 890f);

        System.out.println("Here is the Apple phone information:");
        System.out.println(iPhone.getModel());
        System.out.println(iPhone.getManufacturer());
        System.out.println(iPhone.getMonthsWarranty());
        System.out.println(String.format("$%1.2f",iPhone.getPrice()));
        System.out.println("\nHere is the Samsung phone information:");
        System.out.println(galaxy.getModel());
        System.out.println(galaxy.getManufacturer());
        System.out.println(galaxy.getMonthsWarranty());
        System.out.println((String.format("$%1.2f",galaxy.getPrice())));
        System.out.println("\nHere is the BlackBerry phone information:");
        System.out.println(priv.getModel());
        System.out.println(priv.getManufacturer());
        System.out.println(priv.getMonthsWarranty());
        System.out.println((String.format("$%1.2f",priv.getPrice())));
        // Write code to change the Apple phone's
        // model to "iPhoneSE" and its price to $590.00
        // ... WRITE CODE HERE...

        iPhone.setModel("iPhoneSE");
        iPhone.setPrice(590f);

        System.out.println("\nHere is the Apple phone's new information:");
        System.out.println(iPhone.getModel());
        System.out.println(iPhone.getManufacturer());
        System.out.println(iPhone.getMonthsWarranty());
        System.out.println((String.format("$%1.2f",iPhone.getPrice())));
        // Complete the line below so that it shows the combined total
        // of all three phone prices
        System.out.println("The total cost of all phones is $" + (iPhone.getPrice() + galaxy.getPrice() + priv.getPrice()));
        // Complete the code below so that it determines which phone has the longest
        // warranty and displays the result using a nice readable sentence
        if ((iPhone.getMonthsWarranty()> galaxy.getMonthsWarranty()) && (iPhone.getMonthsWarranty() > priv.getMonthsWarranty())){
            System.out.println("The Apple phone has the longest warranty");
        }else if ((galaxy.getMonthsWarranty() > iPhone.getMonthsWarranty())&&(galaxy.getMonthsWarranty()>priv.getMonthsWarranty())) {
            System.out.println("The Samsung phone has the longest warranty");
        }else{
            System.out.println("The Blackberry phone has the longest warranty");
        }

    }
}