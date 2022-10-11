package game;

import java.util.Calendar;
import java.util.TimerTask;

public class MyTimer extends TimerTask
{
    @Override
    public void run() {}

    //Standard timer
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

    //Basicaly a timer, but at runtime, returns true if the current time modulus a number == 0
    public boolean checkTime(int mod)
    {
        //Current time in miliseconds
        long end = Calendar.getInstance().getTime().getTime();

        if(end%mod == 0)
            return true;
        
        return false;
    }   
}
