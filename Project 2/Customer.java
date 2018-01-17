public class Customer {
    String name;
    CellPhone cellPhone;
    PhonePlan plan;
    int callsMade;
    float balance;

    public Customer(String n, CellPhone c, PhonePlan p) {
        name = n;
        cellPhone = c;
        plan = p;
        callsMade = 0;
        if(plan.planType == false){
            balance = 0;
        }else{
            balance = 0.4f*plan.minutesAllowed;
        }

    }

    //New customers should have an
    //account balance initially equal to 0 for regular plans and $0.40 per minute purchased (i.e., initial
    //minutes allowed) for Pay-as-you-go plans.

    public String getName() {
        return name;
    }
    public CellPhone getCellPhone() {
        return cellPhone;
    }
    public PhonePlan getPlan() {
        return plan;
    }

    public void setName(String n) {
        name = n;
    }
    public void setCellPhone(CellPhone c) {
        cellPhone = c;
    }
    public void setPlan(PhonePlan p) {
        plan = p;
    }

    public String toString() {
        return (name + " with a " + cellPhone.getModel() + " on a " + plan.toString());
    }

    //question 5
    public void phone(Customer c, int callLength) {

        if (c.plan.planType == true && this.plan.planType == true) {

            if (callLength > c.plan.getMinutesRemaining() || callLength > this.plan.getMinutesRemaining()) {
                return;
            }

        }else if(c.plan.planType == true && this.plan.planType == false){
            if (callLength > c.plan.getMinutesRemaining() ) {
                return;
            }

        }else if(c.plan.planType == false && this.plan.planType == true){
            if (callLength > this.plan.getMinutesRemaining() ) {
                return;
            }
        }
         //if (c.plan.planType == false && this.plan.planType == false) {
            c.callsMade += 1;
            this.callsMade += 1;
            c.plan.minutesUsed += callLength;
            this.plan.minutesUsed += callLength;




    }
    public void buyMinutes(float buymins){
        this.plan.minutesAllowed += buymins;
        if(plan.planType == true){
            this.balance += 0.4f*buymins;
            //this.plan.minutesAllowed += buymins;

        }

    }
    public void accessInternet(float dataacc){
        plan.dataUsed += dataacc; //datallowed -=dataacc
    }


//question 6
    public float MonthlyCharges(){
        float charges=0;
        if(plan.minutesAllowed == 200){
            charges = 25 +((plan.dataAllowed/1000000f)*10);

        }else{
            charges = 15+((plan.dataAllowed/1000000f)*10);
        }
        return charges;

    }
    public float Voice(){
        float voice = 0;
        if(plan.getMinutesRemaining()< 0){
            voice = 0.15f*(plan.minutesUsed - plan.minutesAllowed);

        }return voice;
    }
    public float Data(){
        float data=0;
        if(plan.getDataRemaining()<0){
            data = 0.00005f*(plan.dataUsed - plan.dataAllowed);
        }return data;

    }
    public float HST(){
        float hst;
        hst = (Voice() + Data() + MonthlyCharges())*0.13f;
        return hst;
    }
    public float total(){
        float total;
        if(plan.planType ==false){
            total = Voice() + Data() + MonthlyCharges()+HST();
        }else{
            total = HST() + MonthlyCharges();
        }
        return total;
    }

   public void printMonthlyStatement() {
        if (plan.planType == true) {
            System.out.println("");
            System.out.println("Name:                      " + name);
            System.out.println("Plan Type:                 " +"Pay-as-you-go");
            System.out.println("Minutes Used               " + plan.minutesUsed);
            System.out.println("Minutes Remaining:         " + plan.getMinutesRemaining());
            System.out.println("Data Used:                 " + plan.dataUsed);
            System.out.println("Data Remaining:            " + plan.getDataRemaining());
            System.out.println("Calls Made:                " + callsMade);
            System.out.println("Monthly Charges:           " + String.format("$%1.2f",MonthlyCharges()));
            System.out.println("HST:                       " + String.format("$%1.2f",HST()));
            System.out.println("Total Due:                 " + String.format("$%1.2f",total()));


        }
        else {
            System.out.println("");
            System.out.println("Name:                      " + name);
            System.out.println("Plan Type:                 " + "Regular Monthly ("+ plan.minutesAllowed + " minute, " + (plan.dataAllowed/1000000f) +"GB data)");
            System.out.println("Minutes Used               " + plan.minutesUsed);
            System.out.println("Data Used:                 " + plan.dataUsed);
            System.out.println("Calls Made:                " + callsMade);
            System.out.println("Monthly Charges:           " + String.format("$%1.2f",MonthlyCharges()));
            System.out.println("Voice Overtime Charges:    " + String.format("$%1.2f",Voice()));
            System.out.println("Data Overusage Charges:    " + String.format("$%1.2f",Data()));
            System.out.println("HST:                       " + String.format("$%1.2f",HST()));
            System.out.println("Total Due:                 " + String.format("$%1.2f",total()));


        }

   }
}