import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Maze {
    private static final byte  OPEN = 0;
    private static final byte  WALL = 1;
    private static final byte  VISITED = 2;

    private int         rows, columns;
    private byte[][]    grid;


    // A constructor that makes a maze of the given size
    public Maze(int r, int c) {
        rows = r;
        columns = c;
        grid = new byte[r][c];
        for(r=0; r<rows; r++) {
            for (c = 0; c<columns; c++) {
                grid[r][c] = WALL;
            }
        }
    }

    public int getRows() { return rows; }
    public int getColumns() { return columns; }

    // Return true if a wall is at the given location, otherwise false
    public boolean wallAt(int r, int c) {
        return grid[r][c] == WALL;
    }

    // Return true if this location has been visited, otherwise false
    public boolean visitedAt(int r, int c) {
        return grid[r][c] == VISITED;
    }

    // Put a visit marker at the given location
    public void placeVisitAt(int r, int c) {
        grid[r][c] = VISITED;
    }

    // Remove a visit marker from the given location
    public void removeVisitAt(int r, int c) {
        grid[r][c] = OPEN;
    }

    // Put a wall at the given location
    public void placeWallAt(int r, int c) {
        grid[r][c] = WALL;
    }

    // Remove a wall from the given location
    public void removeWallAt(int r, int c) {
        grid[r][c] = 0;
    }

    // Carve out a maze
    public void carve() {
        int startRow = (int)(Math.random()*(rows-2))+1;
        int startCol = (int)(Math.random()*(columns-2))+1;
        carve(startRow, startCol);
    }

    // Directly recursive method to carve out the maze
    public void carve(int r, int c) {

        // Write your code here
        if(r==0 || r== rows-1 || c==0 || c==columns-1){ //do not remove wall
            ;
        }else if(!wallAt(r,c)){ //open space
            ;
        }else if((wallAt(r-1,c) && wallAt(r,c+1)&& wallAt(r+1,c) && wallAt(r,c-1))
                ||(wallAt(r-1,c) && wallAt(r,c+1)&& wallAt(r+1,c))
                ||(wallAt(r-1,c) && wallAt(r,c+1) && wallAt(r,c-1))
                ||(wallAt(r-1,c) && wallAt(r+1,c) && wallAt(r,c-1))
                ||(wallAt(r,c+1)&& wallAt(r+1,c) && wallAt(r,c-1))){

            removeWallAt(r,c);
            //added
            ArrayList<Integer> rowOffsets = new ArrayList<Integer>(Arrays.asList(-1, 1, 0, 0));
            ArrayList<Integer> colOffsets = new ArrayList<Integer>(Arrays.asList(0, 0, -1, 1));
            while (rowOffsets.size()>0){
                Random random = new Random();
                int i = random.nextInt(rowOffsets.size());
                int k = rowOffsets.remove(i);
                int g = colOffsets.remove(i);
                carve(r+k,c+g);
            }
        }
    }

    // Determine the longest path in the maze from the given start location
    public ArrayList<Point2D> longestPath() { // Replace this with your code
        ArrayList<Point2D> longPath = new ArrayList<Point2D>();

        for(int r =0; r< rows; r++){
            for(int c =0; c< columns; c++){
                if(longestPathFrom(r,c).size() > longPath.size()){
                    longPath = longestPathFrom(r,c);

                    //longPath.addAll(longestPathFrom(r,c));

                }
            }
        }

        return longPath;
    }


    // Determine the longest path in the maze from the given start location
    public ArrayList<Point2D> longestPathFrom(int r, int c) {
        ArrayList<Point2D> path = new ArrayList<Point2D>();
        Point2D newpath = new Point2D(r,c);

        // if (1,1) wall
        if(wallAt(r,c)){
            return path; //null if (1,1)
        }
        //notvisied and its not a wall
        else if(!visitedAt(r,c)) {
            placeVisitAt(r,c);
            path.add(newpath);

            ArrayList<Point2D> a = longestPathFrom(r+1, c);
            ArrayList<Point2D> b = longestPathFrom(r, c+1);
            ArrayList<Point2D> d = longestPathFrom(r-1, c);
            ArrayList<Point2D> e =longestPathFrom(r, c-1);

            int max = Math.max(a.size(), b.size());
            max = Math.max(max,d.size());
            max = Math.max(max,e.size());

            if(a.size() == max){
                path.addAll(a);
            }
            else if(b.size() == max){
                path.addAll(b);
            }
            else if(d.size() == max){
                path.addAll(d);
            }
            else if(e.size() == max){
                path.addAll(e);
            }

            removeVisitAt(r,c);

        }

        return path;
    }

}
