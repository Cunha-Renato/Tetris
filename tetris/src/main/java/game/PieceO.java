package game;

import java.util.List;

public class PieceO extends Piece
{
    PieceO(int x, int y, List<Point> occupied) {super(x, y, occupied);}
    
    @Override
    protected void reCalc() //Recalculates the positions
    {
        cells.get(0).setPoint(x, y);
        cells.get(1).setPoint(x-1, y);
        cells.get(2).setPoint(x-1, y+1);
        cells.get(3).setPoint(x, y+1);
    }
}