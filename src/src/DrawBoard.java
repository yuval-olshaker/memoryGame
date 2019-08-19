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
        Font currentFont = g.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 3F);
        g.setFont(newFont);

        for (RectToDraw rect : rectsToDraw) {
            g.drawRect(rect.getMinX(), rect.getMinY(), rect.getWidth(), rect.getHeight());
            if (rect.shouldShowValue()){
                g.drawString(String.valueOf(rect.getValue()), rect.getMinX() + (rect.getWidth()/2),
                        rect.getMinY() + (rect.getHeight() / 2));
            }
        }
    }

}