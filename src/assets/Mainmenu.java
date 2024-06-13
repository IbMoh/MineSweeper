package assets;
import java.awt.*;
import javax.swing.*;

public class Mainmenu extends JButton {

    int borderWeidth = 400;
    int borderHeight = 660;

    JFrame menuFrame = new JFrame();
    JPanel menuPanel = new JPanel();

    /*int mineCount;
    int numRows;
    int tileSize;
    Minesweeper game = new Minesweeper(tileSize, numRows, mineCount);
    */

    public void start(){
        
        menuFrame.setSize(borderWeidth,borderHeight);
        menuFrame.setResizable(false);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(new BorderLayout());
        menuFrame.setVisible(true);

    }

}
