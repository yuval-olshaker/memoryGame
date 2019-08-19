import java.awt.*;


public class Main {

    private static final int boardHeight = 2;
    private static final int boardWidth = 3;
    private static Board board;
    private static final int second = 1000;
    private static final int flipCounter = 2;
    private static final int stopTomeToRemember = 2 * second;

    public static void main(String args[]){
        createBoard();
        runGame();
        System.exit(0);
    }

    private static void runGame() {
        System.out.println(board);
        Point lastLocation = MouseInfo.getPointerInfo().getLocation();
        Point currentLocation;
        boolean game = true;
        int counter = 0;
        MemCard previousCard = null;
        MemCard currentCard;
        while (game) { // maybe it is better to play full turns and not card by card.
            // this decision is not very important because the game is not complicated
            game = board.printBoard(); // we print the board every time
            try {
                Thread.sleep(second);
                currentLocation = MouseInfo.getPointerInfo().getLocation();
                // if same location of mouse for some time we flip
                if (currentLocation.equals(lastLocation)){
                    counter++;
                    if (counter == flipCounter){
                        counter = 0;
                        currentCard = flipCurrentCard(currentLocation, previousCard);
                        if (previousCard == null ) {
                            // we flipped the first in the pair
                            previousCard = currentCard;
                            continue;
                        }
                        else if (currentCard == null){
                            // stayed on same card or open card
                            continue;
                        }
                        if (!(currentCard.getValue() == previousCard.getValue())) { // different value, flip back
                            game = board.printBoard();
                            Thread.sleep(stopTomeToRemember); // time to remember
                            previousCard.flip();
                            currentCard.flip();
                        }
                        // anyway we start over with new pairs - end of turn
                        previousCard = null;
                    }
                }
                lastLocation = currentLocation;
            } catch (InterruptedException e) {
                game = false;
                e.printStackTrace();
            }
        }
    }

    /**
     * flips the card that the mouse points at. and returns it.
     * @param currentLocation - the location of the mouse
     * @param previousCard - the last card we were at
     * @return the current card that the mouse points at. if already on display or the previous returns null
     */
    private static MemCard flipCurrentCard(Point currentLocation, MemCard previousCard) {
        MemCard currentCard = board.getCard(currentLocation.getX(), currentLocation.getY());
        if (currentCard.isOnDisplay() || previousCard == currentCard){
            return null;
        }
        currentCard.flip();
        return currentCard;
    }

    /**
     * creates empty board and prints it
     */
    private static void createBoard() {
        board = new Board(boardHeight, boardWidth);
        board.printBoard();
    }

}
