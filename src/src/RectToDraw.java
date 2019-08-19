
class RectToDraw {
    private final int minX;
    private final int minY;
    private final int height;
    private final int width;
    private boolean showValue = false;

    int getMinX() {
        return minX;
    }

    int getMinY() {
        return minY;
    }

    int getHeight() {
        return height;
    }

    int getWidth() {
        return width;
    }

    public boolean isShowValue() {
        return showValue;
    }

    RectToDraw(int minX, int minY, int maxX, int maxY){
        this.minX = minX;
        this.minY = minY;
        this.height = maxY - minY;
        this.width = maxX - minX;
    }
}
