package game;

import java.util.List;

import game.dirEnum.Direction;

public class Square extends Piece
{
    private int squareY=0;
    private int squareX=gridX/2;

    Square(int x, int y, List<Point> occupied)
    {
        gridX=x;
        gridY=y;
        squareY=0;
        squareX=x/2;
        this.occupied=occupied;
        aux=new Auxiliar();

        calc();
    }
    
    private void calc()
    {
        cells.add(new Point(squareX, squareY+1));
        cells.add(new Point(squareX-1, squareY+1));
        cells.add(new Point(squareX-1, squareY));
        cells.add(new Point(squareX, squareY));

        for(Point cell : cells)
            cell.setArrPos(gridX);
    }

    private void reCalc()
    {
        cells.get(3).setPoint(squareX, squareY);
        cells.get(2).setPoint(squareX-1, squareY);
        cells.get(1).setPoint(squareX-1, squareY+1);
        cells.get(0).setPoint(squareX, squareY+1);
    }

    @Override
    public boolean sink() 
    {
        if(isValid(cells, Direction.DOWN))
        {
            squareY++;
            reCalc();

            return true;
        }

        return false;
    }

    public void moveRight()
    {
        if(isValid(cells, Direction.DOWN))
        {
            squareY++;
            reCalc();
        }
    }
}