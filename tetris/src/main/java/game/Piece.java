package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.dirEnum.Direction;

public abstract class Piece 
{
    //Represents the limits of the grid
    public int gridX;
    public int gridY;

    public int color=new Random().nextInt(2); //Color of the piece
    public List<Point> cells = new ArrayList<Point>(); //Cells that form the piece
    public List<Point> occupied; //A list representing occupied cells on the grid

    //Inicial position of the piece
    public int y=0;
    public int x=gridX/2;

    public Auxiliar aux; //Auxiliar class (only functions)

    //Will be diferent for each piece
    protected abstract void calc();
    protected abstract void reCalc();

    //Movements that every piece will have 
    //TODO: Rotation 
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

    //Checs if the position of the piece is valid for a certain movement
    public boolean isValid(Direction dir)
    {
        //Auxiliar variables
        int auxX=0;
        int auxY=0;

        //Depending on the value of dir, changes the aux variables
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
            //Checks if the piece is in contact with an occupied
            for(Point blocked : occupied)
                if((cell.getX()+auxX == blocked.getX()) && (cell.getY()+auxY == blocked.getY()))
                    return false;

            
            //If the piece is in contact with the flor and walls
            if(cell.getY()>gridY-2 && dir==Direction.DOWN)
                return false;
            
            else if((dir==Direction.RIGHT) && (cell.getX()>gridX-2))
                return false;

            else if((dir==Direction.LEFT) && (cell.getX()<1))
                return false;
        }
            
        return true;
    }

}