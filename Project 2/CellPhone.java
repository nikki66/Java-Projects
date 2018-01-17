public class CellPhone {
    private String model;
    private String manufacturer;
    private int monthsWarranty;
    private float price;

    public CellPhone(){
        model = "UNKNOWN";
        manufacturer = "UNKNOWN";
        monthsWarranty = 0;
        price = 0f;
    }

    public CellPhone(String f, String l, int a, float p ){
        model = f;
        manufacturer = l;
        monthsWarranty = a;
        price = p;
    }
    public String getModel(){ return model;}
    public String getManufacturer(){return manufacturer;}
    public int getMonthsWarranty(){return monthsWarranty;}
    public float getPrice(){return price;}

    public void setModel(String f) {model = f;}
    public void setManufacturer(String l) {manufacturer = l;}
    public void setMonthsWarranty(int a) {monthsWarranty = a;}
    public void setPrice(float p) {price = p;}
}
