package core;
import javax.swing.JButton;

public class MineTile extends JButton{

    public int roww;
    public int coloumnn;

    public MineTile(int rowConst, int coloumnConst){
        this.roww = rowConst;
        this.coloumnn = coloumnConst;
    }

}
