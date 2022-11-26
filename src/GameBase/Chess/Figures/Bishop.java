package GameBase.Chess.Figures;

import GameBase.Chess.ChessFigure;
import GameBase.Base.Coordinate;

public class Bishop extends ChessFigure {
    public Bishop(boolean colorIsWhite, Coordinate coordinateFrom) {
        super(colorIsWhite, colorIsWhite ? '\u2657' : '\u265d', coordinateFrom);
    }

    @Override
    public boolean canMove(Coordinate to) {
        boolean isStep= Math.abs(from.getX()- to.getX())==Math.abs(from.getY()- to.getY());
        return isStep;
    }
}
