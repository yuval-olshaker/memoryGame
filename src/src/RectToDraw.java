
class RectToDraw {
    private final int minX;
    private final int minY;
    private final int height;
    private final int width;
    private final int value;
    private boolean showValue = false;

    int getValue() {
        return value;
    }

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

    boolean shouldShowValue() {
        return showValue;
    }

    RectToDraw(int minX, int minY, int maxX, int maxY, int value, boolean showValue){
        this.minX = minX;
        this.minY = minY;
        this.height = maxY - minY;
        this.width = maxX - minX;
        this.value = value;
        this.showValue = showValue;
    }
}
