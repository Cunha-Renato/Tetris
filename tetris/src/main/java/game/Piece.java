package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.dirEnum.Direction;

public abstract class Piece 
{
    public int gridX;
    public int gridY;
    public int color=new Random().nextInt(2);
    public List<Point> cells = new ArrayList<Point>();
    public List<Point> occupied;
    public int y=0;
    public int x=gridX/2;

    //Auxiliar class (only one function)
    public Auxiliar aux;

    protected abstract void calc();
    protected abstract void reCalc();

    public void sink()
    {
        y++;
        reCalc();
    };
    public void moveRight()
    {
        x++;
        reCalc();
    }
    public void moveLeft()
    {
        x--;
        reCalc();
    }


    public boolean isValid(Direction dir)
    {
        int auxX=0;
        int auxY=0;

        switch(dir)
        {
            case UP:
                auxY=-1;       
            break;

            case DOWN:
                auxY=1; 
            break;

            case LEFT:
                auxX=-1; 
            break;

            case RIGHT:
                auxX=1;
            break;
        }

        for(Point cell : cells)
        {
            for(Point blocked : occupied)
            {
                if((cell.getX()+auxX == blocked.getX()) && (cell.getY()+auxY == blocked.getY()))
                    return false;
            }

            if(cell.getY()>gridY-2)
                return false;
            
            else if((dir==Direction.RIGHT) && (cell.getX()>gridX-2))
                return false;

            else if((dir==Direction.LEFT) && (cell.getX()<1))
                return false;
        }
            
        return true;
    }

}