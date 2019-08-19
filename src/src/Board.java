import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Board {

    private JFrame frame;
    private static final String DELIM = "  ";
    private MemCard[][] board;
    private static final int min = 0;
    private static final int max = 100;
    private static final double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static final double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private DrawBoard oldDrawBoard = null;

    Board(int boardHeight, int boardWidth) {
        frame = new JFrame("Memory Game");
        MemCard[] oneDimBoard = createOneDimBoard(boardHeight * boardWidth);

        // from one dim to 2 dim
        board = new MemCard[boardHeight][boardWidth];
        int k = 0;
        for (int i=0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = oneDimBoard[k];
                setMinMaxPoints(boardHeight, boardWidth, i, j, board, true);
                setMinMaxPoints(boardHeight, boardWidth, i, j, board, false);
                k++;
            }
        }
    }

    private void setMinMaxPoints(int boardHeight, int boardWidth, int i, int j, MemCard[][] board, boolean isMax) {
        int multH = i;
        int multW = j;
        if(isMax){
            multH++;
            multW++;
        }
        double maxY = (screenHeight / boardHeight) * multH;
        double maxX = (screenWidth / boardWidth) * multW;
        if(isMax) {
            board[i][j].setMaxPoint(new Point2D.Double(maxX, maxY));
        } else {
            board[i][j].setMinPoint(new Point2D.Double(maxX, maxY));
        }


    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Board{" + "board=\n");
        for (MemCard[] aBoard : board) {
            for (int j = 0; j < board[0].length; j++) {
                s.append(aBoard[j].toString()).append(DELIM);
            }
            s.append("\n");
        }
        s.append('}');
        return s.toString();
    }

    private void addNextPlace(int value, MemCard[] oneDimBoard, boolean[] used){
        int place = 0;
        while ( used[place] ) {
            place = ThreadLocalRandom.current().nextInt(0, oneDimBoard.length);
        }
        oneDimBoard[place] = new MemCard(value);
        used[place] = true;
    }

    /**
     * this function creates one dimension array the have all the cards with random values.
     * have pairs and they are unique
     * @param size - the sie of the array
     * @return one dimension array
     */
    private MemCard[] createOneDimBoard(int size){
        MemCard[] oneDimBoard = new MemCard[size];
        boolean[] usedPlaces = new boolean[size];
        List<Integer> usedValues = new ArrayList<>();
        int value = ThreadLocalRandom.current().nextInt(min, max + 1);
        for (int i = 0; i < oneDimBoard.length / 2; i++){
            // unique pairs
            while (usedValues.contains(value)){
                value = ThreadLocalRandom.current().nextInt(min, max + 1);
            }

            usedValues.add(value);
            // add the pair at random places
            addNextPlace(value, oneDimBoard, usedPlaces);
            addNextPlace(value, oneDimBoard, usedPlaces);
        }

        return oneDimBoard;
    }

    /**
     * this function returns the card that the mouse is pointing at
     * @param x - x position of the mouse
     * @param y - y position of the mouse
     * @return the card that the mouse is pointing at
     */
    MemCard getCard(double x, double y){
        int widthC = 0;
        int heightC = 0;

        while (board[heightC][widthC].isMouseOnCard(x,y) == MousePosition.BIGGER_WIDTH){
            widthC++;
        }

        while (board[heightC][widthC].isMouseOnCard(x,y) == MousePosition.BIGGER_HEIGHT){
            heightC++;
        }

        return board[heightC][widthC];
    }


    /**
     * prints an empty board - all cards face down
     */
    boolean printBoard() {
        boolean hasHiddenCard = false;
        DrawBoard drawBoard = new DrawBoard();
        for (MemCard[] oneDimBoard : board) {
            for (MemCard memCard : oneDimBoard) {
                memCard.addCardToDrawBoard(drawBoard);
                hasHiddenCard |= !memCard.isOnDisplay();
            }
        }

        if(oldDrawBoard != null) {
            frame.remove(oldDrawBoard);
        }
        frame.setSize((int)screenWidth, (int)screenHeight);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.add(drawBoard);
        oldDrawBoard = drawBoard;
        frame.validate();
        frame.repaint();
        frame.setVisible(true);

        return hasHiddenCard;

    }

}
