package game;

public class Point 
{
    private int x;
    private int y;
    private int arrPos; //Represents the (x,y) position in an 2d array 
    private int gridX;
    private int color;

    private Assistant aux = new Assistant();
    
    public Point(int x, int y)
    {
        setPoint(x, y);

        arrPos=0;
        gridX=0;
    }

    //Setters for the grid position
    public void setX(int x) 
    {
        this.x=x;
        
        //Uptading the array position 
        setArrPos(gridX);
    }
    public void setY(int y) 
    {
        this.y=y;

        //Uptading the array position 
        setArrPos(gridX);
    }
    public void setPoint(int x, int y) 
    {
        setX(x);
        setY(y);
    }
    
    //Setter for the (x,y) in array position
    public void setArrPos(int gridX) 
    {
        this.gridX=gridX;
        arrPos=aux.arrPos(gridX, x, y);
    }

    //Color seter
    public void setColor(int color) {this.color=color;}

    //Getters
    public int getX() {return x;}
    public int getY() {return y;}
    public int getArrPos() {return arrPos;}
    public int getColor() {return color;}
}
