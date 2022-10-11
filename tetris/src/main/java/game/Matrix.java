package game;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Image;
import game.dirEnum.Direction;

public class Matrix 
{
    //Grid size
    private final int x = 10;
    private final int y = 10;
    
    private final int cellSize = 20; //Cell size (for visuals)

    private List<ImageIcon> color = new ArrayList<ImageIcon>(); //Array that stores the ids for the colors
    private List<JLabel> cells = new ArrayList<JLabel>(); //JLabel array that stores the cells in the grid (for visuals)
    private List<Point> occupied = new ArrayList<Point>(); //Array that stores Points that are solid/occupied by previous pieces
    private Assistant aux = new Assistant(); //Assistant class (stores functions to assist the project)
    private Piece piece; //Current Piece, the one that the player moves

    //Background Icon
    private ImageIcon background = new ImageIcon(new ImageIcon("./background.png").getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT));


    public Matrix()
    {
        setColors(); //Initializes the colors array
        setCells(); //Initializes the cells array
        newPiece(); //Create a new Piece
        update(1); //Sets every cell with the correct icon/image
    }

    //Initializes every cell with the background icon
    private void setCells()
    {
        for(int i=0; i<(x*y); i++)
        {
            cells.add(new JLabel(background));
            cells.get(i).setSize(new Dimension(cellSize, cellSize));
        }
    }

    //Center the grid in relation to the window size
    public void setCellsPosition(int screenWidth, int screenHeight)
    {
        int iniX = (screenWidth/2) - (5*cellSize);
        int iniY = (screenHeight/2) - (10*cellSize);

        //Setting the position
        for(int i=0; i<y; i++)
        {
            for(int j=0; j<x; j++)
                cells.get(aux.arrPos(x, j, i)).setLocation(iniX+(j*cellSize), iniY);;
            
            iniY+=cellSize;
        }
    }

    //Adds a new pieace
    private void newPiece() {this.piece = new PieceO(x, y, occupied);}

    //Alocates the "id" of the color image into the colors array
    private void setColors()
    {
        for(int i=0; i<2; i++)
            color.add(new ImageIcon(new ImageIcon("./"+ i +".png").getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT)));

    }

    //Sets the right icon/image to every cell
    public void update(int op)
    {
        //Just painting the current Piece
        if(op!=0)
            for(Point a : this.piece.cells)
                cells.get(a.getArrPos()).setIcon(color.get(this.piece.color));

        //Repaints the entire grid
        else
        {
            //Sets the image as background for every cell
            for(JLabel cell : cells)
                cell.setIcon(background);

            //Paints the occupied ones
            for(Point a : occupied)
                cells.get(a.getArrPos()).setIcon(color.get(a.getColor()));
        } 
    }

    //Erases the current Piece (for movement)
    private void eraseGhost(List<Point> list)
    { 
        for(Point a : list)
            cells.get(a.getArrPos()).setIcon(background);
    }

    //Checks if a line is full, if it is then it clears it.
    private void clearLines()
    {
        //Checking if occupied has any cell
        if(occupied!=null)
        {
            aux.shellSortPointY(occupied); //Sorting the occupied list based on its Y value

            int qnt=0; //This represents the number of lines cleared
            int check=0; //This keeps track of what line its looking in the moment  
            int minY=occupied.get(0).getY(); //The minimum value of Y, this means the height of occupied cells
            
            //This one checks if the amount of occupied cells are equal to X, if it is then it clears it
            for(int i=y-1; i>=minY; i--)
                if((aux.countInstanceOfY(i, occupied)==x) && i!=check)
                {
                    check=i;
                    qnt++;
                    clearY(check);
                }

            if(check!=0)
                sinkOccupied(check, qnt);
        }
    }

    //Clears a single line
    private void clearY(int yPos)
    {
        int size=occupied.size(); //Size of the occupied array
        
        for(int j=0; j<size; j++)
            if(occupied.get(j).getY()==yPos)
            {
                occupied.remove(j);
                size--;
                j--;
            }
    }

    //Moves the piece dow by 1Y
    public void sinkPiece()
    {
        if(piece.isValid(Direction.DOWN))
        {
            eraseGhost(piece.cells); //Clears its previous position
            piece.sink(); //Moves down
        }
        else //Reached the bottom/landed on an occupied cell
        {
            //Adds its positions to occupied cells
            for(Point cell : piece.cells)
                occupied.add(cell);

            for(Point a : occupied)
            {
                for(Point cell : piece.cells)
                    if(a.getArrPos()==cell.getArrPos())
                        a.setColor(piece.color);   
            }

            

            clearLines(); //Checks if it theres lines to clear
            update(0);
            newPiece();
        }

        update(1); //Paints the Piece
    }
    
    //Sink occupied cells after clearing
    private void sinkOccupied(int yPos, int qnt)
    {
        //For every occupied above the cleared lines, sink the number of cleared lines
        for(Point blocked : occupied)
            if(blocked.getY()<yPos)
                blocked.setY(blocked.getY()+qnt);
            
        
    }

    //Movements for the current Piece
    public void move(Direction dir)
    {
        eraseGhost(piece.cells); //Clears its previous position

        switch(dir)
        {
            case RIGHT:
                if(piece.isValid(Direction.RIGHT))
                    piece.moveRight();
            break;

            case LEFT:
                if(piece.isValid(Direction.LEFT))
                    piece.moveLeft();
            break;

            case DOWN:
                if(piece.isValid(Direction.DOWN))
                    sinkPiece();
            break;

            default:
            break;
        }
        
        update(1); //Paints the Piece
    }

    //Getters
    public int getX() {return x;}
    public int getY() {return y;}
    public List<JLabel> getCells() {return cells;}

}
