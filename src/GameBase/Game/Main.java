package GameBase.Game;

import GameBase.Base.Board;
import GameBase.Base.Coordinate;
import GameBase.Chess.ChessGame;

import java.util.Arrays;
import java.util.Scanner;

public class Main extends Board {
    static private ChessGame chessGame;
    static private boolean isEndGame;
    static private int stepCounter;
    static private Coordinate[] coor;
    static boolean isWhiteWin;
    static String prize= "\uD83C\uDFC6";
    static {
        isEndGame = false;
        stepCounter = 0;
        coor = new Coordinate[2];
    }

    public static void main(String[] args) {
        newGame();
        while (!isEndGame) {
            showMessage();
            checkAnswer(readAnswer());
            makeStep();
            System.out.println();
        }
        if(isWhiteWin) System.out.println("Mr. White wins!!!");
        else System.out.println("Mr. Black wins!!!");
        System.out.println(isWhiteWin?"Congratulations Mr. White!!!":"Congratulations Mr.Black!!!");
        System.out.println("Here's your prize! Enjoy it! "+ prize);
    }

    static private void newGame() {
        chessGame = ChessGame.getInstance();
        chessGame.newGame();
    }

    static private void showMessage() {
        System.out.println("Press \"exit\" to stop game!");
        System.out.println("Or input your next step, " + (((++stepCounter) % 2 == 1) ? "mr.White" : "mr.Black"));
        System.out.println("Type in 'A1-A2' or 'A1A2'\nTo win simply kill the enemy's King");
    }

    static private String readAnswer() {
        return (new Scanner(System.in)).nextLine().toLowerCase();
    }

    static private void checkAnswer(String st) {
        System.out.println("st = " + st);
        if (st.equals("exit")) {
            endGame();
        } else {
            isInputCorrectStep(st);
            showStepMessage(st);
        }
    }

    static private void makeStep() {
        chessGame.makeStep(coor);
    }

    static public void endGame() {
        System.out.println("Thank you for game! Bye!!! Bye!!!");
        isEndGame = true;
    }

    static private void isInputCorrectStep(String st) {
        coor = new Coordinate[2];
        char[] data = st.toCharArray();
        System.out.println(Arrays.toString(data));
        if (!(data.length == 4 || data.length == 5)) {
            System.out.println("Incorrect input data");
            stepCounter--;
        } else {
            int t = 0;
            for (int i = 0; i < 2; i++) {
                if ((data[i * 2 + t] >= 'a' && data[i * 2 + t] <= 'h')
                        && (data[i * 2 + 1 + t] >= '1' && data[i * 2 + 1 + t] <= '8'))
                    coor[i] = new Coordinate(data[i * 2 + t] - 'a', data[i * 2 + 1 + t] - '1');
                if (data.length == 5) t++;
            }
        }
    }

    static private void showStepMessage(String st) {
        System.out.println((((stepCounter) % 2 == 1) ? "Mr.White" : "Mr.Black") + ", your turn is: " + st);
        System.out.println("And coordinate on field are:" + Arrays.toString(coor));
    }

    public static int getStepCounter(){return stepCounter%2;}

    public static void setStepCounterBack() {stepCounter--;}
    public static void WHendGame(){isWhiteWin=true;endGame();}
    public static void BLendGame(){isWhiteWin=false;endGame();}
}