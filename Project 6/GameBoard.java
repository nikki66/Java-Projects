import javafx.scene.paint.Color;

public class GameBoard {
    public static final int WIDTH = 6;
    public static final int HEIGHT = 6;
    public static final int MAX_GAME_PIECES = 15;

    private GamePiece[]     gamePieces;
    private int             numGamePieces;
    private boolean         completed;

    public GameBoard(int numPieces) {
        gamePieces = new GamePiece[numPieces];
        numGamePieces = 0;
        completed = false;
    }

    public static GameBoard board1() {
        GameBoard b = new GameBoard(2);
        b.add(new VerticalGamePiece(2, Color.YELLOW, 5, 0));
        b.add(new GoalPiece(1, 2));
        return b;
    }

    public static GameBoard board2() {
        GameBoard b = new GameBoard(8);
        b.add(new GoalPiece(1, 2));
        b.add(new HorizontalGamePiece(2, Color.LIGHTGREEN, 0, 0));
        b.add(new HorizontalGamePiece(2, Color.LIGHTBLUE, 4, 4));
        b.add(new HorizontalGamePiece(3, Color.GREEN, 2, 5));
        b.add(new VerticalGamePiece(3, Color.YELLOW, 5, 0));
        b.add(new VerticalGamePiece(3, Color.PURPLE, 0, 1));
        b.add(new VerticalGamePiece(3, Color.BLUE, 3, 1));
        b.add(new VerticalGamePiece(2, Color.ORANGE, 0, 4));
        return b;
    }

    public static GameBoard board3() {
        GameBoard b = new GameBoard(9);
        b.add(new GoalPiece(1, 2));
        b.add(new HorizontalGamePiece(2, Color.PINK, 3, 4));
        b.add(new HorizontalGamePiece(2, Color.PURPLE, 0, 5));
        b.add(new VerticalGamePiece(3, Color.GOLD, 0, 0));
        b.add(new VerticalGamePiece(3, Color.VIOLET, 3, 0));
        b.add(new VerticalGamePiece(3, Color.BLUE, 5, 2));
        b.add(new VerticalGamePiece(2, Color.GREEN, 5, 0));
        b.add(new VerticalGamePiece(2, Color.ORANGE, 4, 2));
        b.add(new VerticalGamePiece(2, Color.LIGHTBLUE, 2, 4));
        return b;
    }

    public static GameBoard board4() {
        GameBoard b = new GameBoard(13);
        b.add(new GoalPiece(2, 2));
        b.add(new HorizontalGamePiece(3, Color.BLUE, 0, 0));
        b.add(new HorizontalGamePiece(2, Color.PINK, 1, 1));
        b.add(new HorizontalGamePiece(2, Color.LIGHTGREEN, 0, 3));
        b.add(new HorizontalGamePiece(2, Color.YELLOW, 2, 5));
        b.add(new HorizontalGamePiece(2, Color.BROWN, 4, 4));
        b.add(new HorizontalGamePiece(2, Color.GRAY, 4, 5));
        b.add(new VerticalGamePiece(3, Color.GOLD, 4, 0));
        b.add(new VerticalGamePiece(2, Color.SADDLEBROWN, 3, 0));
        b.add(new VerticalGamePiece(2, Color.GREEN, 5, 0));
        b.add(new VerticalGamePiece(2, Color.PURPLE, 0, 1));
        b.add(new VerticalGamePiece(2, Color.ORANGE, 2, 3));
        b.add(new VerticalGamePiece(2, Color.LIGHTBLUE, 1, 4));
        return b;
    }


    public static GameBoard board5() {
        GameBoard b = new GameBoard(13);
        b.add(new GoalPiece(3, 2));
        b.add(new HorizontalGamePiece(2, Color.LIGHTGREEN, 1, 0));
        b.add(new HorizontalGamePiece(3, Color.BLUE, 0, 3));
        b.add(new HorizontalGamePiece(2, Color.BLACK, 4, 4));
        b.add(new HorizontalGamePiece(2, Color.BROWN, 0, 5));
        b.add(new HorizontalGamePiece(2, Color.YELLOW, 3, 5));
        b.add(new VerticalGamePiece(3, Color.GOLD, 0, 0));
        b.add(new VerticalGamePiece(2, Color.LIGHTBLUE, 1, 1));
        b.add(new VerticalGamePiece(2, Color.PINK, 2, 1));
        b.add(new VerticalGamePiece(2, Color.ORANGE, 4, 0));
        b.add(new VerticalGamePiece(3, Color.VIOLET, 5, 1));
        b.add(new VerticalGamePiece(2, Color.PURPLE, 3, 3));
        b.add(new VerticalGamePiece(2, Color.GREEN, 2, 4));
        return b;
    }

    public void add(GamePiece gp) {
        if (numGamePieces < MAX_GAME_PIECES)
            gamePieces[numGamePieces++] = gp;
    }

    public int getNumGamePieces() { return numGamePieces; }
    public GamePiece[] getGamePieces() { return gamePieces; }
    public boolean isCompleted() { return completed; }

    // Return the piece at the given position
    public GamePiece pieceAt(int x, int y) {
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        GamePiece piece = null;
        for(int i =0; i<getNumGamePieces(); i++){
            if(getGamePieces()[i] instanceof VerticalGamePiece){
                if((getGamePieces()[i].topLeftY<= (y-1)) && ((y-1)<getGamePieces()[i].topLeftY+getGamePieces()[i].height)&&
                        (x-1)==getGamePieces()[i].topLeftX){
                    piece=getGamePieces()[i];
                }
            }if(getGamePieces()[i] instanceof HorizontalGamePiece){
                if((getGamePieces()[i].topLeftX<= (x-1)) && ((x-1)<getGamePieces()[i].topLeftX+getGamePieces()[i].width)&&
                        (y-1)==getGamePieces()[i].topLeftY){
                    piece=getGamePieces()[i];
                }
            }
        }
        return piece;

    }

    // Check if the board has been completed, and set the boolean accordingly
    public boolean checkCompletion(GamePiece gp) { //changed void to boolean?
        // WRITE YOUR CODE HERE
        boolean goal = false;
        if(gp instanceof GoalPiece){
            if(gp.topLeftX == 5 && gp.topLeftY == 2)
                goal = true;

        }return goal;
    }
}