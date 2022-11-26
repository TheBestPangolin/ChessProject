package GameBase.Chess.Figures;

import GameBase.Base.Figure;
import GameBase.Chess.ChessFigure;
import GameBase.Base.Coordinate;
import GameBase.Game.Main;

public class King extends ChessFigure {
    boolean isFirstStep;
    public King(boolean colorIsWhite, Coordinate coordinateFrom) {
        super(colorIsWhite, colorIsWhite ? '\u2654' : '\u265a', coordinateFrom);
        this.isFirstStep=true;
    }
    public void end(){
        if(!this.isOnBoard) Main.endGame();
    }
    {end();}
    @Override
    public boolean canMove(Coordinate to) {
        boolean isStepStraight = Math.abs(from.getY() - to.getY())==1
                ||Math.abs(from.getX() - to.getX())==1;
        boolean isStepDiagonal= Math.abs(from.getX()- to.getX())==Math.abs(from.getY()- to.getY())
                && (Math.abs(from.getX()- to.getX())==1
                || Math.abs(from.getY()- to.getY())==1);
        if (isFirstStep) {
            if (isStepStraight || isStepDiagonal) {
                isFirstStep = false;
                return true;
            } else return false;
        } else {
            return isStepStraight||isStepDiagonal;
        }
    }
}
