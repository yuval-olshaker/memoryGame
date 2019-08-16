import java.awt.*;


public class Main {

    private static final int boardHeight = 5;
    private static final int boardWidth = 10;
    private static Board board;

    public static void main(String args[]){
        createBoard();
        runGame();
    }

    private static void runGame() {
        System.out.println(board);

        for (int i=0; i<100; i++){
            try {
                Thread.sleep(1000);
                double x = MouseInfo.getPointerInfo().getLocation().getX();
                double y = MouseInfo.getPointerInfo().getLocation().getY();
//                System.out.println("x is: " + x + " y is: " + y);
                System.out.println("you are pointing at: " + board.getCard(x,y));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createBoard() {
        board = new Board(boardHeight, boardWidth);
    }

}
