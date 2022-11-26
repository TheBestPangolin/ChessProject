package GameBase.Chess.Figures;

import GameBase.Chess.ChessFigure;
import GameBase.Base.Coordinate;

public class Rook extends ChessFigure {
    boolean isFirstStep;
    public Rook(boolean colorIsWhite, Coordinate coordinateFrom) {
        super(colorIsWhite, colorIsWhite ? '\u2656' : '\u265c', coordinateFrom);
        this.isFirstStep=true;
    }

    @Override
    public boolean canMove(Coordinate to) {
        boolean isStep = from.getY() == to.getY() || from.getX() == to.getX();
        boolean isCastling = (from.getY()== to.getY()&&from.getX()==0&&to.getX()- from.getX()==2)
                ||(from.getY()== to.getY()&&from.getX()==7&&from.getX()- to.getX()==3);
        if (isFirstStep) {
            if (isStep) {
                isFirstStep = false;
                return true;
            } else return false;
        } else return isStep;
    }
}
