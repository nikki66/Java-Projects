import javafx.scene.paint.Color;

public class HorizontalGamePiece extends GamePiece {
    public HorizontalGamePiece(int w, Color c, int x, int y) {
        super(w, 1, c, x, y);
    }

    public boolean canMoveLeftIn(GameBoard b) {
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        boolean checker = true;
        if(this.topLeftX==0)//wall
            checker = false;

        if(this.topLeftX>0){//not hitting left wall
            for (int i = 0; i < b.getNumGamePieces(); i++) {//go through game pieces
                if(b.getGamePieces()[i].topLeftX< this.topLeftX){//only checking pieces on left of this.
                    if(b.getGamePieces()[i].topLeftX + b.getGamePieces()[i].width == this.topLeftX){//checking if x-axis line up
                        if(this.topLeftY>=b.getGamePieces()[i].topLeftY){
                            if(b.getGamePieces()[i].topLeftY+ b.getGamePieces()[i].height>this.topLeftY){//checking if y blocks
                                checker=false;//then cant move
                                break;
                            }
                        }
                    }
                }


            }
        }return checker;
    }
    public boolean canMoveRightIn(GameBoard b) {
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        boolean checker = true;
        if(this.topLeftX+this.width==6) //wall
            checker = false;

        if(this.topLeftX+this.width<6){//not hitting right wall
            for (int i = 0; i < b.getNumGamePieces(); i++) {// go through game pieces
                if(this.topLeftX< b.getGamePieces()[i].topLeftX){//only checking pieces on right of this.
                    if(this.topLeftX+this.width==b.getGamePieces()[i].topLeftX){//checking if x-axis line up
                        if(this.topLeftY>=b.getGamePieces()[i].topLeftY){
                                if (b.getGamePieces()[i].topLeftY + b.getGamePieces()[i].height > this.topLeftY) {//checking if y blocks same us previous
                                    checker = false;//then cant move
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        return checker;
    }
}
