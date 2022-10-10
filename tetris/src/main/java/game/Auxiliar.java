package game;

import java.util.List;

public class Auxiliar 
{
    public int arrPos(int x, int j, int i) {return j+(x*i);}

    public int countInstanceOfY(int val, List<Point> list)
    {
        int count=0;

        for(Point cell : list)
            if(cell.getY()==val)
                count++;

        return count;
    }

    public void shellSortPointY(List<Point> list)
    {
        int j;
        int h = list.size();
        Point aux = new Point(0, 0);

        while(h>=1)
        {
            for(int i=0; i<list.size(); i++)
            {
                aux=list.get(i);

                for(j=i-h; (j>=0) && (list.get(j).getY()>aux.getY()); j-=h)
                    list.set(j+h, list.get(j));
            
                list.set(j+h, aux);
            }

            h/=2;
        }
    }
}
