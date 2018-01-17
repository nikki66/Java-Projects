import javafx.scene.paint.Color;

public class VerticalGamePiece extends GamePiece {
    public VerticalGamePiece(int h, Color c, int x, int y) {
        super(1, h, c, x, y);
    }

    public boolean canMoveDownIn(GameBoard b) {
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        boolean checker = true;

        if(this.topLeftY+this.height==6)//wall
            checker=false;

        if(this.topLeftY+this.height<6){//not hitting bottom wall
            for (int i = 0; i < b.getNumGamePieces(); i++) {// go through game pieces
                if(this.topLeftY< b.getGamePieces()[i].topLeftY){//only checking pieces on bottom of this.
                    if(this.topLeftY+this.height==b.getGamePieces()[i].topLeftY){//checking if y-axis line up
                        if(this.topLeftX>=b.getGamePieces()[i].topLeftX){
                            if(b.getGamePieces()[i].topLeftX+ b.getGamePieces()[i].width>this.topLeftX){
                                checker=false;//then cant move
                                break;
                            }
                        }
                    }
                }
            }
        }return checker;

    }
    public boolean canMoveUpIn(GameBoard b) {
        // REPLACE THE CODE BELOW WITH YOUR OWN CODE
        boolean checker = true;

        if(this.topLeftY==0) //wall
            checker = false;

        if(this.topLeftY>0){//not hitting top wall
            for (int i = 0; i < b.getNumGamePieces(); i++) {//go through game pieces
                if(b.getGamePieces()[i].topLeftY< this.topLeftY){//only checking pieces on above of this.
                    if(b.getGamePieces()[i].topLeftY + b.getGamePieces()[i].height == this.topLeftY){//checking if y-axis line up
                        if(this.topLeftX>=b.getGamePieces()[i].topLeftX){
                            if(b.getGamePieces()[i].topLeftX+ b.getGamePieces()[i].width>this.topLeftX){//checking if x blocks
                                checker=false;//then cant move
                                break;
                            }
                        }
                    }
                }


            }
        }return checker;

    }
}
