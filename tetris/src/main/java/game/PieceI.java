package game;

import java.util.List;

public class PieceI extends Piece
{
    PieceI(int x, int y, List<Point> occupied)
    {
        gridX=x;
        gridY=y;

        this.y=0;
        this.x=x/2;
        
        this.occupied=occupied;
        aux=new Assistant();

        calc();
    }

    @Override
    protected void calc() //Calculates/Sets the positions of cells forming an square
    {
        cells.add(new Point(x, y));
        cells.add(new Point(x, y+1));
        cells.add(new Point(x, y+2));
        cells.add(new Point(x, y+3));

        for(Point cell : cells)
            cell.setArrPos(gridX);
    }

    @Override
    protected void reCalc() //Recalculates the positions
    {
        cells.get(0).setPoint(x, y);
        cells.get(1).setPoint(x, y+1);
        cells.get(2).setPoint(x, y+2);
        cells.get(3).setPoint(x, y+3);
    }
}