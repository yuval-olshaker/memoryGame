import java.awt.geom.Point2D;

class MemCard {

    private boolean onDisplay;
    private int value;
    private Point2D maxPoint;
    private Point2D minPoint;

    MemCard(int value){
        this.value = value;
        onDisplay = false;
    }

    public boolean isOnDisplay() {
        return onDisplay;
    }

    public int getValue() {
        return value;
    }

    void flip(){
        onDisplay = !onDisplay;
    }

    /**
     * this function checks if the mouse is currently at the area covered by the card.
     *
     * @return MousePosition according to the position of the mouse and card boundaries
     */
    MousePosition isMouseOnCard(double x, double y){
        if (x > maxPoint.getX()){
            return MousePosition.BIGGER_WIDTH;
        }
        if (y > maxPoint.getY()){
            return MousePosition.BIGGER_HEIGHT;
        }

        return MousePosition.ON_CARD;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    void setMaxPoint(Point2D maxPoint) {
        this.maxPoint = maxPoint;
    }

    void setMinPoint(Point2D.Double minPoint) {
        this.minPoint = minPoint;
    }

    void printCardFrame() {

    }
}
