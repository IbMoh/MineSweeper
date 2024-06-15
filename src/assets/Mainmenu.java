package assets;
import java.awt.*;
import javax.swing.*;

public class Mainmenu extends JButton {

    int borderWeidth = 400;
    int borderHeight = 660;

    JFrame menuFrame = new JFrame();
    JPanel menuPanel = new JPanel();

    JButton play = new JButton("Play") {
        {
            setSize(150, 75);
            setMaximumSize(getSize());
        }
    };

    /*int mineCount;
    int numRows;
    int tileSize;
    Minesweeper game = new Minesweeper(tileSize, numRows, mineCount);
    */

    public void start(){
        // create frame will be the last thing to call because it will add everything created from the
        // methods we'll create to the frame
        createFrame();
    }

    private void createFrame(){
        menuFrame.setSize(borderWeidth,borderHeight);
        menuFrame.setResizable(false);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(new BorderLayout());
        menuFrame.setVisible(true);
    }

}
