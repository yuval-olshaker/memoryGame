import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class DrawBoard extends JPanel {

    private ArrayList<RectToDraw> rectsToDraw;

    DrawBoard(){
        rectsToDraw = new ArrayList<>();
    }

    void addRect(RectToDraw rect){
        rectsToDraw.add(rect);
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (RectToDraw rect : rectsToDraw) {
            g.drawRect(rect.getMinX(), rect.getMinY(), rect.getWidth(), rect.getHeight());
        }
    }

}