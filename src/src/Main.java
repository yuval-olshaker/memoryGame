import java.awt.*;


public class Main {

    private static final int boardHeight = 5;
    private static final int boardWidth = 10;
    private static Board board;
    private static final int second = 1000;
    private static final int flipCounter = 2;

    public static void main(String args[]){
        createBoard();
        runGame();
    }

    private static void runGame() {
        System.out.println(board);
        Point lastLocation = MouseInfo.getPointerInfo().getLocation();
        Point currentLocation;
        boolean game = true;
        int counter = 0;
        MemCard previousCard = null;
        MemCard currentCard;
        while (game) {
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
                            previousCard.flip();
                            currentCard.flip();
                        }
                        // anyway we start over with new pairs
                        previousCard = null;
                    }
                }
                lastLocation = currentLocation;
//                System.out.println("x is: " + x + " y is: " + y);
                System.out.println("you are pointing at: " + board.getCard(currentLocation.getX(), currentLocation.getY()));
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

    private static void createBoard() {
        board = new Board(boardHeight, boardWidth);
    }

}
