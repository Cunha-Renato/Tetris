package game;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import game.dirEnum.Direction;

import java.awt.Dimension;
import java.awt.Image;

public class Matrix 
{
    private final int x = 10;
    private final int y = 20;
    private final int cellSize = 20;

    private List<ImageIcon> color = new ArrayList<ImageIcon>();
    private List<JLabel> cells = new ArrayList<JLabel>();
    private List<Point> occupied = new ArrayList<Point>();
    private Auxiliar aux = new Auxiliar();
    private Piece piece;

    private ImageIcon background = new ImageIcon(new ImageIcon("./background.png").getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT));


    public Matrix()
    {
        setColors();
        setCells();
        newPiece();
    }

    private void setCells()
    {
        for(int i=0; i<(x*y); i++)
        {
            cells.add(new JLabel(background));
            cells.get(i).setSize(new Dimension(30, 30));
        }
    }

    public void setCellsPosition(int screenWidth, int screenHeight)
    {
        int iniX = (screenWidth/2) - (5*cellSize);
        int iniY = (screenHeight/2) - (10*cellSize);

        //Setting the position
        for(int i=0; i<y; i++)
        {
            for(int j=0; j<x; j++)
            {
                cells.get(aux.arrPos(x, j, i)).setLocation(iniX+(j*cellSize), iniY);;
            }
            iniY+=cellSize;
        }
    }

    private void newPiece() {this.piece = new Square(x, y, occupied);}

    private void setColors()
    {
        for(int i=0; i<2; i++)
            color.add(new ImageIcon(new ImageIcon("./"+ i +".png").getImage().getScaledInstance(cellSize, cellSize, Image.SCALE_DEFAULT)));

    }

    public void update()
    {
        for(Point a : this.piece.cells)
            cells.get(a.getArrPos()).setIcon(color.get(this.piece.color));
    }

    private void eraseGhost(List<Point> list)
    {
        
        for(Point a : list)
            cells.get(a.getArrPos()).setIcon(background);
    }

    public void sinkPiece()
    {
        if(piece.isValid(Direction.DOWN))
        {
            eraseGhost(piece.cells);
            piece.sink();
        }
        else
        {
            for(Point cell : piece.cells)
                occupied.add(cell);

            newPiece();
        }

        update();
    }
    public void move(Direction dir)
    {
        eraseGhost(piece.cells);

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
        
        update();
    }

    //Getters
    public int getX() {return x;}
    public int getY() {return y;}
    public List<JLabel> getCells() {return cells;}

}
