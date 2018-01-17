public class PhonePlan {
    int minutesAllowed;
    int minutesUsed;
    int dataAllowed;
    int dataUsed;
    boolean planType;

    public PhonePlan(int a, int d, boolean p) {
        minutesAllowed = a;
        dataAllowed = d;
        planType = p;
        minutesUsed = 0;
        dataUsed = 0;
    }

    public PhonePlan() {
        minutesAllowed = 0;
        dataAllowed = 0;
        planType = false;
        minutesUsed = 0;
        dataUsed = 0;
    }

    public int getMinutesAllowed() {return minutesAllowed;}
    public int getMinutesUsed() {return minutesUsed;}
    public int getDataAllowed() {return dataAllowed;}
    public int getDataUsed() {return dataUsed;}
    public boolean isPlanType() {return planType;}

    public void setMinutesAllowed(int a) {minutesAllowed = a;}
    public void setMinutesUsed(int u) {minutesUsed = u;}
    public void setDataAllowed(int d) {dataAllowed = d;}
    public void setDataUsed(int s) {dataUsed = s;}
    public void setPlanType(boolean p) {planType = p;}


    public int getMinutesRemaining() {
        int minremaining;
        if (minutesAllowed > minutesUsed || minutesAllowed < minutesUsed) {
            minremaining = minutesAllowed - minutesUsed;
        } else {
            minremaining = 0;
        }
        return minremaining;

    }

    public int getDataRemaining() {
        int dataremanining;
        if (dataAllowed > dataUsed || dataAllowed < dataUsed) {
            dataremanining = dataAllowed - dataUsed;
        } else {
            dataremanining = 0;

        }return dataremanining;
    }
    public String toString() {
        if(planType == false){
            return ("Regular (" + minutesAllowed + " minute, " + (dataAllowed/1000000f) +"GB data)" + " Monthly Plan with " + getMinutesRemaining() +
                    " minutes remaining and " + getDataRemaining() + "KB remaining" );
        }else{
            return("Pay-as-you-go Plan with " + getMinutesRemaining() + " minutes and " + getDataRemaining() + "KB remaining");
        }

    }
}