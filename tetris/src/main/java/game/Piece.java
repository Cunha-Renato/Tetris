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

    //Auxiliar class (only one function)
    public Auxiliar aux;

    public abstract boolean sink();
    public abstract void moveRight();
    
    public boolean isValid(List<Point> thisPiece, Direction dir)
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

        for(Point cell : thisPiece)
        {
            for(Point blocked : occupied)
            {
                if((cell.getX()+auxX == blocked.getX()) && (cell.getY()+auxY == blocked.getY()))
                    return false;
            }

            if((cell.getX()>gridX-2) || (cell.getX()<1) || (cell.getY()>gridY-2))
                return false;
        }
            
        return true;
    }

}