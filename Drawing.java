import java.awt.*;
import javax.swing.JFrame;

public class Drawing extends Canvas {
    public static void main(String[] args) {
    }
    public void paint(Graphics g, int n) {
        g.setColor(Color.white);
        g.fillOval(100, 100, 10, 10);
        g.setColor(Color.pink);
        g.fillOval(300, 250, 10, 10);
    }


}
