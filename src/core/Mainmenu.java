package core;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Mainmenu {

    Minesweeper game = new Minesweeper();

    int borderWeidth = 400;
    int borderHeight = 660;

    JFrame menuFrame = new JFrame("Minesweeper");
    JPanel menuPanel = new JPanel();
    JLabel textLabel = new JLabel();

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
        play.setBackground(new Color(238, 238, 238));
        buttonEffects(play);

        leaderBoards.setPreferredSize(new Dimension(200, 40));
        leaderBoards.setBackground(new Color(238, 238, 238));
        buttonEffects(leaderBoards);

        rules.setPreferredSize(new Dimension(200, 40));
        rules.setBackground(new Color(238, 238, 238));
        buttonEffects(rules);

    }

    private void createPanel(){

        menuPanel.setSize(700,200);
        menuPanel.setBackground(new Color(255, 255, 255));
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        textLabel.setText("Minesweeper");
        textLabel.setFont(new Font("Arial", Font.BOLD, 45));
        
        menuPanel.add(textLabel);
        menuPanel.add(play);
        menuPanel.add(leaderBoards);
        menuPanel.add(rules);

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

        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                button.setBackground(new Color(215, 215, 215));
            }
            public void mouseExited(MouseEvent e){
                button.setBackground(new Color(238, 238, 238));
            }
        });
    }

}
