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
    private List<Point> possibles = new ArrayList<Point>();

    //Inicial position of the piece
    public int y=0;
    public int x=gridX/2;

    public Assistant aux; //Assistant class (only functions)

    //Will be diferent for each piece
    protected abstract void calc();
    protected abstract void reCalc();

    //Movements that every piece will have 
    public void move(Direction dir)
    {
        int auxX=0;
        int auxY=0;

        switch(dir)
        {
            case DOWN:
                auxY++;
            break;

            case RIGHT:
                auxX++;
            break;

            case LEFT:
                auxX--;
            break;

            default:
            break;
        }

        for(Point cell : cells)
            cell.setPoint(cell.getX()+auxX, cell.getY()+auxY);
    }
    
    protected void setPossibles()
    {
        for(Point cell : cells)
            possibles.add(new Point(0, 0));
        
        for(Point poss : possibles)
            poss.setArrPos(gridX);
    }

    public List<Point> rotate(int op, Direction dir)
    {
        int newX;
        int newY;

        //Pivots
        int oX=cells.get(1).getX();
        int oY=cells.get(1).getY();

        if(op==0)
        {
            if(dir==Direction.TORIGHT)
                for(int i=0; i<cells.size(); i++)
                {
                    newX = oX+(cells.get(i).getY()-oY);
                    newY = oY-(cells.get(i).getX()-oX);

                    cells.get(i).setPoint(newX, newY);
                }
    
            else if(dir==Direction.TOLEFT)
                for(int i=0; i<cells.size(); i++)
                {
                    newX = oX-(cells.get(i).getY()-oY);
                    newY = oY+(cells.get(i).getX()-oX);

                    cells.get(i).setPoint(newX, newY);
                }
        }
        else
        {
            if(dir==Direction.TORIGHT)
                for(int i=0; i<cells.size(); i++)
                {
                    newX = oX+(cells.get(i).getY()-oY);
                    newY = oY-(cells.get(i).getX()-oX);

                    possibles.get(i).setPoint(newX, newY);
                }
    
            else if(dir==Direction.TOLEFT)
                for(int i=0; i<cells.size(); i++)
                {
                    newX = oX-(cells.get(i).getY()-oY);
                    newY = oY+(cells.get(i).getX()-oX);

                    possibles.get(i).setPoint(newX, newY);
                }

            return possibles;
        }

        return null;
    }

    //Checs if the position of the piece is valid for a certain movement
    public boolean isValid(Direction dir)
    {
        //Assistant variables
        int auxX=0;
        int auxY=0;
        List<Point> possibles = new ArrayList<Point>();

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

            case TORIGHT:
                possibles = rotate(1, Direction.TORIGHT);
            break;

            case TOLEFT:
                possibles = rotate(1, Direction.TOLEFT);
            break;

            default:
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

        if((dir==Direction.TORIGHT) || (dir==Direction.TOLEFT))
            for(Point poss : possibles)
                for(Point blocked : occupied)
                    if((poss.getArrPos()==blocked.getArrPos()) || 
                    (poss.getX()<0) || 
                    (poss.getX()>gridX-1) || 
                    (poss.getY()<0) || 
                    (poss.getY()>gridY-1))
                        return false;
        
        return true;
    }
}