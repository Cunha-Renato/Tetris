package game;

import java.util.List;

public class Square extends Piece
{
    Square(int x, int y, List<Point> occupied)
    {
        gridX=x;
        gridY=y;

        this.y=0;
        this.x=x/2;
        
        this.occupied=occupied;
        aux=new Auxiliar();

        calc();
    }
    
    @Override
    protected void calc()
    {
        cells.add(new Point(x, y+1));
        cells.add(new Point(x-1, y+1));
        cells.add(new Point(x-1, y));
        cells.add(new Point(x, y));

        for(Point cell : cells)
            cell.setArrPos(gridX);
    }

    @Override
    protected void reCalc()
    {
        cells.get(3).setPoint(x, y);
        cells.get(2).setPoint(x-1, y);
        cells.get(1).setPoint(x-1, y+1);
        cells.get(0).setPoint(x, y+1);
    }
}