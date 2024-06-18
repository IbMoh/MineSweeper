package assets;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Mainmenu extends JButton {

    Minesweeper game = new Minesweeper();

    int borderWeidth = 400;
    int borderHeight = 660;

    JFrame menuFrame = new JFrame("Minesweeper");
    JPanel menuPanel = new JPanel();

    JButton play = new JButton("Play");
    JButton leaderBoards = new JButton("Leader Boards");
    JButton rules = new JButton("Rules");

    /*int mineCount;
    int numRows;
    int tileSize;
    Minesweeper game = new Minesweeper(tileSize, numRows, mineCount);
    */

    public void start(){

        // create frame will be the last thing to call because it will add everything created from the
        // methods to the frame
        createButtons();
        createPanel();
        createFrame();

        play();

    }

    private void createFrame(){

        menuFrame.setSize(borderWeidth,borderHeight);
        menuFrame.setResizable(false);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLayout(new BorderLayout());

        menuFrame.add(menuPanel);

        menuFrame.setVisible(true);

    }

    private void createButtons(){

        play.setPreferredSize(new Dimension(200, 40));
        play.setBackground(new Color(244, 244, 244));
        buttonEffects(play);

        leaderBoards.setPreferredSize(new Dimension(200, 40));
        leaderBoards.setBackground(new Color(244, 244, 244));
        buttonEffects(leaderBoards);

        rules.setPreferredSize(new Dimension(200, 40));
        rules.setBackground(new Color(244, 244, 244));
        buttonEffects(rules);

    }

    private void createPanel(){

        menuPanel.setSize(700,200);
        menuPanel.setBackground(new Color(255, 255, 255));
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        menuPanel.add(play, "align center");
        menuPanel.add(leaderBoards, "align center");
        menuPanel.add(rules, "align center");

    }

    private void play(){

        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                if(e.getButton() == 1) {
                    game.startGame();
                }
            }
        });
        
    }

    private void buttonEffects(JButton button){
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                button.setBackground(new Color(220, 220, 220));
            }
            public void mouseExited(MouseEvent e){
                button.setBackground(new Color(244, 244, 244));
            }
        });
    }

}
