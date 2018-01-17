import java.util.Arrays;
public class HospitalBuilderProgram {
    private static byte[][][] maps = {{{2, 2}, {2, 8}, {5, 15}, {19, 1}, {17, 17}},
            {{1, 1}, {7, 19}, {13, 19}, {19, 7}, {19, 13}},
            {{0, 19}, {2, 19}, {4, 19}, {6, 19}, {18, 19}},
            {{7, 19}, {13, 19}, {19, 19}, {19, 13}, {19, 7}}};
    private static void map1(){
        int i, j;
        double x=0, y=0;
        double d = 0;
        double max=0, min = 500;
        double[] info = {x,y,d};

        for (i = 0; i < 1; i++) {
            for (x = 0; x < 20; x++) { // x-coor
                for (y = 0; y < 20; y++) { //y-coor
                    max =0;
                    for (j = 0; j < 5; j++) { // town
                        d = Math.sqrt((((maps[i][j][0]) - x) * ((maps[i][j][0]) - x)) + ((((maps[i][j][1]) - y) * ((maps[i][j][1]) - y))));
                        if (d > max){
                            max=d;
                        }
                    }if(min > max){
                        min = max;
                        info[0]= x;
                        info[1]= y;

                    }
                }
            }System.out.println("The coordinates of the hospital are:");
            System.out.print(info[0]);
            System.out.println(","+info[1]);
            System.out.println("And the optimal distance is:");
            System.out.println(min);



        }

    }

    public static void main(String[] args) {
        map1();//Testing for map 1
        int i, j;
        double x=0, y=0;
        double d = 0;
        double max=0, min = 500;
        double[] info = {x,y,d};


        for (i = 1; i < 4; i++) {
            for (x = 0; x < 20; x++) { // x-coor
                for (y = 0; y < 20; y++) { //y-coor
                    max = 0;
                    for (j = 0; j < 5; j++) { // town
                        d = Math.sqrt((((maps[i][j][0]) - x) * ((maps[i][j][0]) - x)) + ((((maps[i][j][1]) - y) * ((maps[i][j][1]) - y))));
                        if (d > max) {
                            max = d;
                        }
                    }
                    if (min > max) {
                        min = max;
                        info[0] = x;
                        info[1] = y;

                    }
                }
            }
            System.out.println("The coordinates of the hospital are:");
            System.out.print(info[0]);
            System.out.println("," + info[1]);
            System.out.println("And the optimal distance is:");
            System.out.println(min);


        }

    }
}

