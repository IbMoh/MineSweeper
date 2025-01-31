package assets;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; // <- store all the tiles with the mines
import javax.swing.*;

public class Minesweeper {

    // changeable variables
    int tileSize = 70;
    int numRows = 8;
    int mineCount = 10;
    int tileClicked = 0; // goal is to click all the tiles excluding the bomb tiles
    boolean gameOver = false;

    int numColoums = numRows;
    int boardWidth = numColoums * tileSize;
    int boardHight = numRows * tileSize;
    
    JFrame frame = new JFrame("Minesweeper");
    JLabel textLabel = new JLabel();
    JPanel texPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    MineTile[][] board = new MineTile[numRows][numColoums];
    ArrayList<MineTile> mineList = new ArrayList<>();
    SetUp bombSetUp = new SetUp(this);

   public void startGame(){
    //frame.setVisible(true);
        frame.setSize(boardWidth, boardHight);
        frame.setLocationRelativeTo(null); // <- this will make the gui open in the center of the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.BOLD, 25));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Minesweeper: " + Integer.toString(mineCount));
        textLabel.setOpaque(true);

        texPanel.setLayout(new BorderLayout());
        texPanel.add(textLabel);

        frame.add(texPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(numRows, numColoums)); // 8x8
        frame.add(boardPanel);
        //boardPanel.setBackground(Color.LIGHT_GRAY);

        for (int r = 0; r < numRows; r++){
            for (int c = 0; c < numColoums; c++){
                MineTile tile = new MineTile(r, c);
                board[r][c] = tile;

                tile.setFocusable(false);
                tile.setMargin(new Insets(0, 0, 0, 0));
                tile.setFont(new Font("Arial Unicode MS", Font.PLAIN, 45));
                tile.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e){
                        if (gameOver){
                            return;
                        }

                        MineTile tile = (MineTile) e.getSource();

                        // left click
                        if (e.getButton() == MouseEvent.BUTTON1){
                            if (tile.getText() == ""){ // will only triger if the tile is empty
                                if (mineList.contains(tile)){
                                    bombSetUp.reavelMines();;
                                }
                                else{
                                    bombSetUp.checkMine(tile.roww, tile.coloumnn);
                                }
                            }
                        }
                        else if (e.getButton() == MouseEvent.BUTTON3){ // right click
                            if (tile.getText() == "" && tile.isEnabled()){
                                tile.setText("🚩");
                            }
                            else if (tile.getText() == "🚩"){
                                tile.setText("");
                            }
                        }
                    }
                });
                boardPanel.add(tile);

            }
        }

        frame.setVisible(true); // makes the frame visible after everything is loaded

        bombSetUp.setMines();
        
   } 

}
