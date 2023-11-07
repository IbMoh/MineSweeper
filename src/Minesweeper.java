import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList; // <- store all the tiles with the mines
import java.util.Random; // <- place mines in random tiles
import javax.swing.*;

public class Minesweeper {

    private class MineTile extends JButton{

        int roww;
        int coloumnn;

        MineTile(int rowConst, int coloumnConst){
            this.roww = rowConst;
            this.coloumnn = coloumnConst;
        }

    }

    int tileSize = 70;
    int numRows = 8;
    int numColoums = numRows;
    int boardWidth = numColoums * tileSize;
    int boardHight = numRows * tileSize;
    
    JFrame frame = new JFrame("Minesweeper");

    int mineCount = 10;

    Random random = new Random();

    JLabel textLabel = new JLabel();
    JPanel texPanel = new JPanel();

    JPanel boardPanel = new JPanel();

    MineTile[][] board = new MineTile[numRows][numColoums];
    ArrayList<MineTile> mineList;

    int tileClicked = 0; // goal is to click all the times excluding the bomb tiles

    boolean gameOver = false;

    Minesweeper(){
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
                //tile.setText("💣");\
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
                                    reavelMines();
                                }
                                else{
                                    checkMine(tile.roww, tile.coloumnn);
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

        setMines();
    }

    void setMines(){
        mineList = new ArrayList<MineTile>();

        // mineList.add(board[2][2]);
        // mineList.add(board[2][3]);
        // mineList.add(board[5][6]);
        // mineList.add(board[3][4]);
        // mineList.add(board[1][1]);

        int mineLeft = mineCount;
        while (mineLeft > 0){
            int r = random.nextInt(numRows);
            int c = random.nextInt(numColoums);

            MineTile tile = board[r][c];
            if (!mineList.contains(tile)){
                mineList.add(tile);
                mineLeft -= 1; // ...
            }
        }
    }

    void reavelMines(){
        for (int i = 0; i < mineList.size(); i++){
            MineTile tile = mineList.get(i);
            tile.setText("💣");
        }

        gameOver = true;
        textLabel.setText("Game Over!");
    }

    void checkMine(int rw, int cl){

        if (rw < 0 || rw >= numRows || cl < 0 || cl >= numColoums){ 
            return ; // out of bounds
        }

        MineTile tile = board[rw][cl];
        if (!tile.isEnabled()){
            return;
        }
        tile.setEnabled(false);
        tileClicked += 1;

        int MinesFound = 0;

        //top 3

        MinesFound += countMine(rw - 1, cl - 1); // top left
        MinesFound += countMine(rw - 1, cl); // directly top
        MinesFound += countMine(rw - 1, cl + 1); // top rigt

        // left and right (same row)

        MinesFound += countMine(rw, cl - 1); // left
        MinesFound += countMine(rw, cl + 1); // right 

        // bottom 3

        MinesFound += countMine(rw + 1, cl - 1); // bottom left
        MinesFound += countMine(rw + 1, cl ); // directly below
        MinesFound += countMine(rw + 1, cl + 1); // bottom right

        if (MinesFound > 0){
            tile.setText(Integer.toString(MinesFound));
        }
        else{
            tile.setText("");

            // top 3
            checkMine(rw - 1, cl - 1); // top left
            checkMine(rw - 1, cl); // directly top
            checkMine(rw - 1, cl + 1); // top rigt

            // left and Right
            checkMine(rw ,cl - 1); // left
            checkMine(rw ,cl + 1); // right 

            // Bottom 3
            checkMine(rw + 1 ,cl - 1); // bottom left
            checkMine(rw + 1 ,cl); // directly below
            checkMine(rw + 1 ,cl + 1); // bottom right
        }

        if (tileClicked == numRows * numColoums - mineList.size()){
            
            gameOver = true;

            textLabel.setText("Mines Cleared!");
        }

    }

    int countMine(int ro, int col){
        if (ro < 0 || ro >= numRows || col < 0 || col >= numColoums){ // out of bounds
            return 0;

        }
        if (mineList.contains(board[ro][col])){ // <- if this tile is in the mineList return 1 // contained a mine
            return 1;
        }
        return 0; // didnt contain a mine
    }

}
