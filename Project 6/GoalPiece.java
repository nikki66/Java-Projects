import javafx.scene.paint.Color;

public class GoalPiece extends HorizontalGamePiece {
    public GoalPiece(int x, int y) {
        super(2, Color.RED, x, y);
    }
    public boolean canMoveRightIn(GameBoard b) {
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        boolean checker = true;
        if (this.topLeftX + this.width < 6) {//not hitting right wall
            for (int i = 0; i < b.getNumGamePieces(); i++) {// go through game pieces
                if (this.topLeftX < b.getGamePieces()[i].topLeftX) {//only checking pieces on right of this.
                    if (this.topLeftX + this.width == b.getGamePieces()[i].topLeftX) {//checking if x-axis line up
                        if (this.topLeftY >= b.getGamePieces()[i].topLeftY) {
                            if (b.getGamePieces()[i].topLeftY + b.getGamePieces()[i].height > this.topLeftY) {//checking if y blocks same us previous
                                checker = false;//then cant move
                                break;
                            }
                        }
                    }
                }
            }

        }return checker;
    }

}
