package GameBase.Chess.Figures;

import GameBase.Base.Coordinate;
import GameBase.Chess.ChessFigure;
import GameBase.Game.Main;

public class Pawn extends ChessFigure {

    boolean isFirstStep;

    public Pawn(boolean colorIsWhite, Coordinate coordinate) {
        super(colorIsWhite, colorIsWhite ? '\u2659' : '\u265f', coordinate);
        this.isFirstStep = true;
    }

    @Override
    public boolean canMove(Coordinate to) {
        boolean isStepByOne = from.getX() == to.getX() &&
                (colorIsWhite ? to.getY() - from.getY() == 1 : from.getY() - to.getY() == 1)
                &&(colorIsWhite&&Main.getStepCounter()==1||!colorIsWhite&&Main.getStepCounter()==0);
        boolean isStepByTwo = from.getX() == to.getX() &&
                (colorIsWhite ? to.getY() - from.getY() == 2 : from.getY() - to.getY() == 2)
                &&(colorIsWhite&&Main.getStepCounter()==1||!colorIsWhite&&Main.getStepCounter()==0);
        boolean isAttack = Math.abs(from.getX() - to.getX()) == 1 &&
                (colorIsWhite ? to.getY() - from.getY() == 1 : from.getY() - to.getY() == 1)
                &&(colorIsWhite&&Main.getStepCounter()==1||!colorIsWhite&&Main.getStepCounter()==0);
        if (isFirstStep) {
            if (isStepByOne || isStepByTwo || isAttack) {
                isFirstStep = false;
                return true;
            } else return false;
        } else {
            return isStepByOne || isAttack;
        }
    }
}