package GameBase.Chess;

import GameBase.Base.Board;
import GameBase.Base.Coordinate;
import GameBase.Base.Movable;
import GameBase.Chess.Figures.*;
import GameBase.Game.Main;

import java.util.Arrays;

public class ChessBoard extends Board {
    private static final int chessFieldSize;
    private static King king;


    static {
        chessFieldSize = 8;
    }

    private static ChessBoard instance;


    private ChessBoard() {
        this.field = new ChessFigure[chessFieldSize][chessFieldSize];
        for (Movable[] cf : field) Arrays.fill(cf, null);

    }

    public static ChessBoard getInstance() {
        if (instance == null) instance = new ChessBoard();
        return instance;
    }


    private void resetField() {
        for (Movable[] cf : field) Arrays.fill(cf, null);
    }

    public void newField() {
        this.resetField();
        // white
        field[0][0] = new Rook(true, new Coordinate(0, 0));
        field[0][1] = new Knight(true, new Coordinate(1, 0));
        field[0][2] = new Bishop(true, new Coordinate(2, 0));
        field[0][3] = new Queen(true, new Coordinate(3, 0));
        field[0][4] = new King(true, new Coordinate(4, 0));
        field[0][5] = new Bishop(true, new Coordinate(5, 0));
        field[0][6] = new Knight(true, new Coordinate(6, 0));
        field[0][7] = new Rook(true, new Coordinate(7, 0));
        for (int i = 0; i < chessFieldSize; i++)
            field[1][i] = new Pawn(true, new Coordinate(i, 1));
        //black
        field[7][0] = new Rook(false, new Coordinate(0, 7));
        field[7][1] = new Knight(false, new Coordinate(1, 7));
        field[7][2] = new Bishop(false, new Coordinate(2, 7));
        field[7][3] = new Queen(false, new Coordinate(3, 7));
        field[7][4] = new King(false, new Coordinate(4, 7));
        field[7][5] = new Bishop(false, new Coordinate(5, 7));
        field[7][6] = new Knight(false, new Coordinate(6, 7));
        field[7][7] = new Rook(false, new Coordinate(7, 7));
        for (int i = 0; i < chessFieldSize; i++)
            field[6][i] = new Pawn(false, new Coordinate(i, 6));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" |A|B|C|D|E|F|G|H\n").append("-----------------\n");
        for (int i = 0; i < chessFieldSize; i++) {
            sb.append(8 - i);
            for (int j = 0; j < chessFieldSize; j++)
                sb.append("|").append(
                        field[chessFieldSize - 1 - i][j] != null ? field[chessFieldSize - 1 - i][j] : " "
                );
            sb.append("\n-----------------\n");
        }
        sb.append(" |A|B|C|D|E|F|G|H\n").append("-----------------\n");
        return sb.toString();
    }

    public boolean canMove(Coordinate from, Coordinate to) {
        if (field[from.getY()][from.getX()] == null) return false;
        else {
            ChessFigure tempFrom = (ChessFigure) field[from.getY()][from.getX()];
            ChessFigure tempTo = (ChessFigure) field[to.getY()][to.getX()];
            int ix = from.getX(), iy = from.getY();
//            int x1= from.getX();
//            int y1= from.getY();
//            boolean b=false;
//            for(; x1<= to.getX(); x1++){
//                for (; y1<= to.getY();y1++){
//                    if (field[y1][x1]==null){
//                        b=true;
//                    }
//                    else b=false;
//                }
//            }
            System.out.println("from = " + from);
            System.out.println("Ffrom = " + tempFrom);
            switch (tempFrom.getClass().getSimpleName()) {
                case "Bishop":
                    if (tempFrom.canMove(to)) {
                        if (tempTo == null) {
                            if(from.getY()-to.getY()<0
                                    &&from.getX()-to.getX()<0){
                                while(ix<to.getX()&&iy<to.getY()){
                                    ix++;
                                    iy++;
                                    if (field[iy][ix] != null) return false;
                                    else continue;
                                }
                            } else if(from.getY()-to.getY()>0
                                    &&from.getX()-to.getX()>0){
                                while(ix>to.getX()&&iy>to.getY()){
                                    ix--;
                                    iy--;
                                    if (field[iy][ix] != null) return false;
                                    else continue;
                                }
                            } else if (from.getY()-to.getY()<0
                                    &&from.getX()-to.getX()>0) {
                                while(ix> to.getX()&&iy<to.getY()){
                                ix--;
                                iy++;
                                if (field[iy][ix] != null) return false;
                                else continue;}
                            } else if (from.getY()-to.getY()>0
                                    &&from.getX()-to.getX()<0) {
                                while(ix< to.getX()&&iy>to.getY()){
                                    ix++;
                                    iy--;
                                    if (field[iy][ix] != null) return false;
                                    else continue;}}
                            return tempFrom.isColorIsWhite() && Main.getStepCounter() == 1
                                || tempFrom.isColorIsWhite() == false && Main.getStepCounter() == 0;}
                        else {if(from.getY()-to.getY()<0
                                &&from.getX()-to.getX()<0){
                            while(ix<to.getX()-1&&iy<to.getY()-1){
                                ix++;
                                iy++;
                                if (field[iy][ix] != null) return false;
                                else continue;
                            }
                        } else if(from.getY()-to.getY()>0
                                &&from.getX()-to.getX()>0){
                            while(ix>to.getX()+1&&iy>to.getY()+1){
                                ix--;
                                iy--;
                                if (field[iy][ix] != null) return false;
                                else continue;
                            }
                        } else if (from.getY()-to.getY()<0
                                &&from.getX()-to.getX()>0) {
                            while(ix> to.getX()+1&&iy<to.getY()-1){
                                ix--;
                                iy++;
                                if (field[iy][ix] != null) return false;
                                else continue;}
                        } else if (from.getY()-to.getY()>0
                                &&from.getX()-to.getX()<0) {
                            while(ix< to.getX()-1&&iy>to.getY()+1){
                                ix++;
                                iy--;
                                if (field[iy][ix] != null) return false;
                                else continue;}}
                            if(tempTo.getClass().getSimpleName().equals("King")
                                    &&tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()){
                                if(tempFrom.isColorIsWhite()){
                                    Main.WHendGame(); return true;}
                                else Main.BLendGame(); return true;
                            }
                            return (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()
                                && tempFrom.isColorIsWhite() && Main.getStepCounter() == 1)
                                || (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()
                                && tempFrom.isColorIsWhite() == false && Main.getStepCounter() == 0);}
                    } else return false;
                case "King":
                    if (tempFrom.canMove(to)) {
                        if (tempTo == null) return tempFrom.isColorIsWhite() && Main.getStepCounter() == 1
                                || tempFrom.isColorIsWhite() == false && Main.getStepCounter() == 0;
                        else{ if(tempTo.getClass().getSimpleName().equals("King")
                                &&tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()){
                            if(tempFrom.isColorIsWhite()){
                                Main.WHendGame(); return true;}
                            else Main.BLendGame(); return true;
                        }return (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()
                                && tempFrom.isColorIsWhite() && Main.getStepCounter() == 1)
                                || (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()
                                && tempFrom.isColorIsWhite() == false && Main.getStepCounter() == 0);}
                    } else return false;
                case "Queen":
                    if (tempFrom.canMove(to)) {
                        if (tempTo == null) {
                            if (from.getX() < to.getX()&&from.getY()==to.getY()) {
                                while (ix < to.getX()) {
                                    ix++;
                                    if (field[to.getY()][ix] != null) return false;
                                    else continue;
                                }
                            } else if(from.getX() > to.getX()&&from.getY()==to.getY()){
                                while (ix > to.getX()) {
                                    ix--;
                                    if (field[to.getY()][ix] != null) return false;
                                    else continue;
                                }
                            }
                            else if (from.getY() < to.getY()&&from.getX()==to.getX()) {
                                while (iy < to.getY()) {
                                    iy++;
                                    if (field[iy][to.getX()] != null) return false;
                                    else continue;
                                }
                            } else if(from.getY() > to.getY()&&from.getX()==to.getX()) {
                                while (iy > to.getY()) {
                                    iy--;
                                    if (field[iy][to.getX()] != null) return false;
                                    else continue;
                                }
                            } if(from.getY()-to.getY()<0
                                    &&from.getX()-to.getX()<0){
                                while(ix<to.getX()&&iy<to.getY()){
                                    ix++;
                                    iy++;
                                    if (field[iy][ix] != null) return false;
                                    else continue;
                                }
                            } else if(from.getY()-to.getY()>0
                                    &&from.getX()-to.getX()>0){
                                while(ix>to.getX()&&iy>to.getY()){
                                    ix--;
                                    iy--;
                                    if (field[iy][ix] != null) return false;
                                    else continue;
                                }
                            } else if (from.getY()-to.getY()<0
                                    &&from.getX()-to.getX()>0) {
                                while(ix> to.getX()&&iy<to.getY()){
                                    ix--;
                                    iy++;
                                    if (field[iy][ix] != null) return false;
                                    else continue;}
                            } else if (from.getY()-to.getY()>0
                                    &&from.getX()-to.getX()<0) {
                                while(ix< to.getX()&&iy>to.getY()){
                                    ix++;
                                    iy--;
                                    if (field[iy][ix] != null) return false;
                                    else continue;}}
                            return tempFrom.isColorIsWhite() && Main.getStepCounter() == 1
                                    || tempFrom.isColorIsWhite() == false && Main.getStepCounter() == 0;
                        } else {
                            if (from.getX() < to.getX()&&from.getY()==to.getY()) {
                                while (ix < to.getX()-1) {
                                    ix++;
                                    if (field[to.getY()][ix] != null) return false;
                                    else continue;
                                }
                            } else if(from.getX() > to.getX()&&from.getY()==to.getY()) {
                                while (ix > to.getX()+1) {
                                    ix--;
                                    if (field[to.getY()][ix] != null) return false;
                                    else continue;
                                }
                            }
                            else if (from.getY() < to.getY()&&from.getX()==to.getX()) {
                                while (iy < to.getY()-1) {
                                    iy++;
                                    if (field[iy][to.getX()] != null) return false;
                                    else continue;
                                }
                            } else if(from.getY() > to.getY()&&from.getX()==to.getX()) {
                                while (iy > to.getY()+1) {
                                    iy--;
                                    if (field[iy][to.getX()] != null) return false;
                                    else continue;
                                }
                            } else if(from.getY()-to.getY()<0
                                    &&from.getX()-to.getX()<0){
                                while(ix<to.getX()-1&&iy<to.getY()-1){
                                    ix++;
                                    iy++;
                                    if (field[iy][ix] != null) return false;
                                    else continue;
                                }
                            } else if(from.getY()-to.getY()>0
                                    &&from.getX()-to.getX()>0){
                                while(ix>to.getX()+1&&iy>to.getY()+1){
                                    ix--;
                                    iy--;
                                    if (field[iy][ix] != null) return false;
                                    else continue;
                                }
                            } else if (from.getY()-to.getY()<0
                                    &&from.getX()-to.getX()>0) {
                                while(ix> to.getX()+1&&iy<to.getY()-1){
                                    ix--;
                                    iy++;
                                    if (field[iy][ix] != null) return false;
                                    else continue;}
                            } else if (from.getY()-to.getY()>0
                                    &&from.getX()-to.getX()<0) {
                                while(ix< to.getX()-1&&iy>to.getY()+1){
                                    ix++;
                                    iy--;
                                    if (field[iy][ix] != null) return false;
                                    else continue;}}
                            if(tempTo.getClass().getSimpleName().equals("King")
                                    &&tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()){
                                if(tempFrom.isColorIsWhite()){
                                    Main.WHendGame(); return true;}
                                else Main.BLendGame(); return true;
                            }
                            return (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()
                                    && tempFrom.isColorIsWhite() && Main.getStepCounter() == 1)
                                    || (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()
                                    && tempFrom.isColorIsWhite() == false && Main.getStepCounter() == 0);
                        }
                    } else return false;
                case "Rook":
                    if (tempFrom.canMove(to)) {
                        if (tempTo == null) {
                            if (from.getX() < to.getX()) {
                                while (ix < to.getX()) {
                                    ix++;
                                    if (field[to.getY()][ix] != null) return false;
                                    else continue;
                                }
                            } else {
                                while (ix > to.getX()) {
                                    ix--;
                                    if (field[to.getY()][ix] != null) return false;
                                    else continue;
                                }
                            }
                            if (from.getY() < to.getY()) {
                                while (iy < to.getY()) {
                                    iy++;
                                    if (field[iy][to.getX()] != null) return false;
                                    else continue;
                                }
                            } else {
                                while (iy > to.getY()) {
                                    iy--;
                                    if (field[iy][to.getX()] != null) return false;
                                    else continue;
                                }
                            }
                            return
                                    tempFrom.isColorIsWhite() && Main.getStepCounter() == 1
                                            || tempFrom.isColorIsWhite() == false && Main.getStepCounter() == 0;
                        } else {
                            if (from.getX() < to.getX()) {
                                while (ix < to.getX()-1) {
                                    ix++;
                                    if (field[to.getY()][ix] != null) return false;
                                    else continue;
                                }
                            } else {
                                while (ix > to.getX()+1) {
                                    ix--;
                                    if (field[to.getY()][ix] != null) return false;
                                    else continue;
                                }
                            }
                            if (from.getY() < to.getY()) {
                                while (iy < to.getY()-1) {
                                    iy++;
                                    if (field[iy][to.getX()] != null) return false;
                                    else continue;
                                }
                            } else {
                                while (iy > to.getY()+1) {
                                    iy--;
                                    if (field[iy][to.getX()] != null) return false;
                                    else continue;
                                }
                            } if(tempTo.getClass().getSimpleName().equals("King")
                                    &&tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()){
                                if(tempFrom.isColorIsWhite()){
                                    Main.WHendGame(); return true;}
                                else Main.BLendGame(); return true;
                            }
                            return (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()
                                    && tempFrom.isColorIsWhite() && Main.getStepCounter() == 1)
                                    || (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()
                                    && tempFrom.isColorIsWhite() == false && Main.getStepCounter() == 0);
                        }
                    } else return false;
                case "Knight":
                    if (tempFrom.canMove(to))
                        if (tempTo == null) return tempFrom.isColorIsWhite() && Main.getStepCounter() == 1
                                || tempFrom.isColorIsWhite() == false && Main.getStepCounter() == 0;
                        else{ if(tempTo.getClass().getSimpleName().equals("King")
                                &&tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()){
                            if(tempFrom.isColorIsWhite()){
                                Main.WHendGame(); return true;}
                            else Main.BLendGame(); return true;
                        } return (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()
                                && tempFrom.isColorIsWhite() && Main.getStepCounter() == 1)
                                || (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()
                                && tempFrom.isColorIsWhite() == false && Main.getStepCounter() == 0);}
                    else return false;
                case "Pawn":
                    if (tempFrom.canMove(to)) {
                        if (tempTo == null) {
                            if (from.getY() < to.getY()) {
                                while (iy < to.getY()) {
                                    iy++;
                                    if (field[iy][to.getX()] != null) return false;
                                    else continue;
                                }
                            }
                            return (from.getX() == to.getX()
                                    && tempFrom.isColorIsWhite() && Main.getStepCounter() == 1)
                                    || (from.getX() == to.getX()
                                    && tempFrom.isColorIsWhite() == false && Main.getStepCounter() == 0);
                        } else { if(tempTo.getClass().getSimpleName().equals("King")
                                &&tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()){
                            if(tempFrom.isColorIsWhite()){
                                Main.WHendGame(); return true;}
                            else Main.BLendGame(); return true;
                        }
                            return (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()
                                    && from.getX() != to.getX()
                                    && tempFrom.isColorIsWhite() && Main.getStepCounter() == 1)
                                    || (tempFrom.isColorIsWhite() != tempTo.isColorIsWhite()
                                    && from.getX() != to.getX()
                                    && tempFrom.isColorIsWhite() == false && Main.getStepCounter() == 0);
                        }
                    } else {
                        return false;
                    }
                default:
                    return false;
            }
        }


    }

    public void move(Coordinate from, Coordinate to) {
        ((ChessFigure) field[from.getY()][from.getX()]).moveTo(to);
        field[to.getY()][to.getX()] = field[from.getY()][from.getX()];
        field[from.getY()][from.getX()] = null;
    }

}


