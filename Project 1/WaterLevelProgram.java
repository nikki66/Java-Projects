import java.util.Scanner;
public class WaterLevelProgram{

    public static int addInteger(int[] arrint, int arrsize, int addedint){

        arrint[arrsize++]=addedint;
        return arrsize;
    }
    public static void water(){
        Scanner sc = new Scanner(System.in);
        int start;
        int[] arr = new int[500];
        int sizeOfArr = 0;
        while(true){
            System.out.println("What is the water level at now (in mm): ");
            start = sc.nextInt();
            sizeOfArr=addInteger(arr, sizeOfArr, start);
            if(sizeOfArr>4 && check(arr,sizeOfArr)){
                break;
            }
        }System.out.println("It appears that the flood is subsiding.");
    }
    public static boolean check(int[] arr, int sizeOfArr){
        if((arr[sizeOfArr-1]<arr[sizeOfArr-2])&&(arr[sizeOfArr-2]<arr[sizeOfArr-3])&&(arr[sizeOfArr-3]<arr[sizeOfArr-4])){
            return true;
        }return false;
    }
    public static void main (String[] args){
        water();

    }
}