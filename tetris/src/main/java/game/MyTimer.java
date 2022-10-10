package game;

import java.util.Calendar;
import java.util.TimerTask;

public class MyTimer extends TimerTask
{
    @Override
    public void run() {}

    public void setTimer(int ms)
    {
        try 
        {
            Thread.sleep(ms);
        }  
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }

    public boolean checkTime(int mod)
    {
        long end = Calendar.getInstance().getTime().getTime();

        if(end%mod == 0)
            return true;
        

        return false;
    }   
}
