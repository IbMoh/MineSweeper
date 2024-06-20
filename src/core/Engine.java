package core;
import java.net.URL;
import java.util.Random; // <- place mines in random tiles
import javax.swing.ImageIcon;

public class Engine{

    private Minesweeper game;
    Random random = new Random();

    public Engine(Minesweeper game) {
        this.game = game;
    }

    public void setMines(){

        game.mineList.clear();

        int mineLeft = game.mineCount;
        while (mineLeft > 0){
            int r = random.nextInt(game.numRows);
            int c = random.nextInt(game.numColoums);

            MineTile tile = game.board[r][c];
            if (!game.mineList.contains(tile)){
                game.mineList.add(tile);
                mineLeft -= 1; // ...
            }
        }
    }

    public void reavelMines(){
        ImageIcon bombIcon = null;

        URL bombPng = getClass().getResource("imgs/bomb.png");
        if (bombPng != null) {
            bombIcon = new ImageIcon(bombPng);
        }

        for (int i = 0; i < game.mineList.size(); i++){
            MineTile tile = game.mineList.get(i);
            tile.setIcon(bombIcon);
        }

        game.gameOver = true;
        game.minesLabel.setText("Game Over!");
    }

    public int countMine(int ro, int col){
        if (ro < 0 || ro >= game.numRows || col < 0 || col >= game.numColoums){ // out of bounds
            return 0;

        }
        if (game.mineList.contains(game.board[ro][col])){ // <- if this tile is in the mineList return 1
            // contained a mine
            return 1;
        }
        return 0; // didnt contain a mine
    }

    public void checkMine(int rw, int cl){

        if (rw < 0 || rw >= game.numRows || cl < 0 || cl >= game.numColoums){ 
            return ; // out of bounds
        }

        MineTile tile = game.board[rw][cl];
        if (!tile.isEnabled()){
            return;
        }
        tile.setEnabled(false);
        game.tileClicked += 1;

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
        MinesFound += countMine(rw + 1, cl); // directly below
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

        if (game.tileClicked == game.numRows * game.numColoums - game.mineList.size()){
            
            game.gameOver = true;

            game.minesLabel.setText("Mines Cleared!");
        }

    }

}
