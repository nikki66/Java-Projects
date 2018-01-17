import java.util.*;

import static java.lang.Math.random;

public class DispatchCenter {
    public static String[] AREA_NAMES = {"Downtown", "Airport", "North", "South", "East", "West"};

    private int[][]  stats; // You'll need this for the last part of the assignment
    private HashMap<Integer,Taxi> taxis;
    private HashMap<String,ArrayList<Taxi>> areas;


    // Constructor
    public DispatchCenter() {
        // You'll need this for the last part of the assignment
        stats = new int[AREA_NAMES.length][AREA_NAMES.length];
        areas = new HashMap<String,ArrayList<Taxi>>();
        taxis = new HashMap<Integer,Taxi>();
        for(int i =0; i<AREA_NAMES.length; i++){
             areas.put(AREA_NAMES[i], new ArrayList<Taxi>());
        }

        for(int i =0; i< 50; i++){
            int ar = (int) (Math.random()*(AREA_NAMES.length));
            int plate = (int) (Math.random()*899+100);
            //create new taxi
            Taxi taxi = new Taxi(plate);
            addTaxi(taxi,AREA_NAMES[ar]);

        }

    }


    // You'll need this for the last part of the assignment
    public int[][]   getStats() { return stats; }
    //added
    public  HashMap<Integer,Taxi> getTaxis(){
        return taxis;
    }
    public HashMap<String,ArrayList<Taxi>> getAreas(){
        return areas;}


    // Update the statistics for a taxi going from the pickup location to the dropoff location
    public void updateStats(String pickup, String dropOff) {
        int x=0;
        int y=0;
        for(int i =0; i<AREA_NAMES.length; i++){
            if(pickup.equals(AREA_NAMES[i])){
                x=i;
            }
            if(dropOff.equals(AREA_NAMES[i])){
                y=i;
            }
        }
        stats[x][y] += 1;

    }

    // Determine the travel times from one area to another
    public static int computeTravelTimeFrom(String pickup, String dropOff) {
        int[] down = {10,40,20,20,20,20};
        int[] air ={40,10,40,40,20,60};
        int[] north ={20,40,10,40,20,20};
        int[] south ={20,40,40,10,20,20};
        int[] east ={20,20,20,20,10,40};
        int[] west ={20,60,20,20,40,10};

        int[][] time = {down,air,north,south,east,west};

        int x=0;
        if(pickup.equals(AREA_NAMES[0])){ //Downtown
            x =0;
        }else if(pickup.equals(AREA_NAMES[1])){//Airport
            x=1;
        }else if(pickup.equals(AREA_NAMES[2])){//North
            x=2;
        }else if(pickup.equals(AREA_NAMES[3])){//South
            x=3;
        }else if(pickup.equals(AREA_NAMES[4])){//East
            x=4;
        }else{ //West
            x=5;
        }

        int y=0;
        if(dropOff.equals(AREA_NAMES[0])){ //Downtown
            y =0;
        }else if(dropOff.equals(AREA_NAMES[1])){//Airport
            y=1;
        }else if(dropOff.equals(AREA_NAMES[2])){//North
            y=2;
        }else if(dropOff.equals(AREA_NAMES[3])){//South
            y=3;
        }else if(dropOff.equals(AREA_NAMES[4])){//East
            y=4;
        }else{ //West
            y=5;
        }



        return time[x][y];
    }

    // Add a taxi to the hashmaps
    public void addTaxi(Taxi aTaxi, String area) {
        taxis.put(aTaxi.getPlateNumber(),aTaxi);
        areas.get(area).add(aTaxi);

    }

    // Return a list of all available taxis within a certain area
    private ArrayList<Taxi> availableTaxisInArea(String s) {
        ArrayList<Taxi> result = new ArrayList<Taxi>();
        for(int i = 0; i<areas.get(s).size(); i++){
            if(areas.get(s).get(i).getAvailable()){
                result.add(areas.get(s).get(i));
            }
        }

        return result;
    }

    // Return a list of all busy taxis
    public ArrayList<Taxi> getBusyTaxis() {
        ArrayList<Taxi> result = new ArrayList<Taxi>();
        for(int j =0; j<AREA_NAMES.length; j++) {
            String name = AREA_NAMES[j];
            for (int i = 0; i < areas.get(name).size(); i++) {
                if (!(areas.get(name).get(i).getAvailable())) {
                    result.add(areas.get(name).get(i));
                }
            }
        }
        return result;
    }

    // Find a taxi to satisfy the given request
    public Taxi sendTaxiForRequest(ClientRequest request) {
        //Available taxis find one with less estimated time to dest
        ArrayList<Taxi> avaTaxi = availableTaxisInArea(request.getPickupLocation());
        if(avaTaxi.size() != 0) {
            int i = 0;
            int min = 0;
            Taxi name = avaTaxi.get(i);
            for (i = 0; i < avaTaxi.size(); i++) {
                while (min < avaTaxi.get(i).getEstimatedTimeToDest()) {
                    min = avaTaxi.get(i).getEstimatedTimeToDest();
                    name = avaTaxi.get(i);
                }

            }
            areas.get(request.getPickupLocation()).remove(name);//remove taxi from hashmap using current location
            areas.get(request.getDropoffLocation()).add(name); //add the taxi in dest
            name.setAvailable(false); //not availa
            name.setEstimatedTimeToDest(computeTravelTimeFrom(request.getPickupLocation(),request.getDropoffLocation()));

            name.setDestination(request.getDropoffLocation()); //destination
            updateStats(request.getPickupLocation(),request.getDropoffLocation());


            return name;
        }//taxis from other areas
        else if(avaTaxi.size() == 0){
            for (int i =0; i<AREA_NAMES.length;i++){
                ArrayList<Taxi> avaTaxi2 = availableTaxisInArea(AREA_NAMES[i]);
                if(avaTaxi2.size() != 0){
                    i = 0;
                    int min = 0;
                    Taxi name = avaTaxi2.get(i);
                    for (i = 0; i < avaTaxi2.size(); i++) {
                        while (min < avaTaxi2.get(i).getEstimatedTimeToDest()) {
                            min = avaTaxi2.get(i).getEstimatedTimeToDest();
                            name = avaTaxi2.get(i);
                        }

                    }
                    areas.get(AREA_NAMES[i]).remove(name);//remove taxi from hashmap using current location
                    areas.get(request.getDropoffLocation()).add(name); //add the taxi in dest
                    name.setAvailable(false); //not availa
                    name.setEstimatedTimeToDest(computeTravelTimeFrom(request.getPickupLocation(),request.getDropoffLocation()));

                    name.setDestination(request.getDropoffLocation()); //destination
                    updateStats(AREA_NAMES[i],request.getDropoffLocation());

                    return name;

                }

            }
        }
        //no taxis
        return null;



    }


}
