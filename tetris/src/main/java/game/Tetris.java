package game;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.dirEnum.Direction;

public class Tetris extends JFrame
{
    private Matrix matrix = new Matrix();
    private MyTimer timer = new MyTimer();
    private JPanel panelGame = new JPanel();
    private JPanel panelOver = new JPanel();
    private JLabel pCounter = new JLabel();
    private JLabel gOver = new JLabel();
    private JButton restart = new JButton();

    public Tetris()
    {
        setJFrame();
        setJPanelGame();
    }

    //Setup of the JFrame
    private void setJFrame()
    {
        setTitle("Tetris");
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Adding a keyListener
        addKeyListener(new MyKeyAdapter());

        setVisible(true);
    }

    //Setup of the JPanel
    private void setJPanelGame()
    {
        remove(panelOver);

        //Resets the counter
        matrix.setPoints(0);

        panelGame.setLayout(null);
        panelGame.setBackground(Color.DARK_GRAY);
        panelGame.setDoubleBuffered(true);
        panelGame.setSize(getSize());

        matrix.setCellsPosition(getWidth(), getHeight());
        for(JLabel a : matrix.getCells())
            panelGame.add(a);

        setPointCounterGame();
        panelGame.add(pCounter);
        add(panelGame);
    }

    //Setup of the Game Over JPanel
    private void setJPanelOver()
    {
        remove(panelGame);

        restart.setEnabled(true);

        gOver.setText("GAME OVER");
        gOver.setSize(new Dimension(300, 50));
        gOver.setForeground(Color.WHITE);
        gOver.setLocation((int)(getWidth()/2.85f), getHeight()/6);
        gOver.setFont(new Font("Arial Black", Font.BOLD, 35));

        panelOver.setLayout(null);
        panelOver.setBackground(Color.DARK_GRAY);
        panelOver.setSize(getSize());

        setPointCounterOver();
        setButton();
        panelOver.add(gOver);
        panelOver.add(pCounter);
        panelOver.add(restart);

        add(panelOver);
    }

    //Sets up the point counter JLabel
    public void setPointCounterGame()
    {
        pCounter.setText("Points: "+matrix.getPoints());
        pCounter.setSize(new Dimension(200, 50));
        pCounter.setForeground(Color.WHITE);
        pCounter.setLocation(190, 80);
        pCounter.setFont(new Font("Arial Black", Font.BOLD, 16));
    }

    //Changes the point counter JLabel properties
    private void setPointCounterOver()
    {
        pCounter.setText("Points: "+matrix.getPoints());
        pCounter.setSize(new Dimension(300, 50));
        pCounter.setForeground(Color.WHITE);
        pCounter.setLocation((int)(getWidth()/2.7f), getHeight()/4);
        pCounter.setFont(new Font("Arial Black", Font.BOLD, 25));
    }

    //Configures the button
    private void setButton()
    {
        restart.setText("Restart");
        restart.setSize(new Dimension(200, 30));
        restart.setForeground(Color.black);
        restart.setLocation((int)(getWidth()/2.7f), getHeight()/3);
        restart.setFont(new Font("Arial Black", Font.BOLD, 16));
    }

    //Check if the button is pressed
    private void checkButton()
    {
        if(restart.getModel().isPressed())
        {
            restart.setEnabled(false);
            matrix.reseted=false;
            setJPanelGame();
        }
    }

    private void checkEnd()
    {
        if(matrix.reseted)
            setJPanelOver();
    }

    //Timed sink
    private void timedSink()
    {
        if(timer.checkTime(500) && matrix.reseted==false)
            matrix.sinkPiece();
    }

    public void paint(Graphics g)
    {
        //Super ineficient way of doing things, i know that is not good to check all this stuf every frame, but it will have to do

        super.paint(g);
        timedSink();
        pCounter.setText("Points: " + matrix.getPoints());
        checkEnd();
        checkButton();
        repaint();
    }

    //A "custom" KeyAdapter to control the movement
    private class MyKeyAdapter extends KeyAdapter
    {
        @Override public void keyPressed(KeyEvent event) 
        {
            if(matrix.reseted==false)
            {
                if(event.getKeyCode() == KeyEvent.VK_D)
                matrix.move(Direction.RIGHT);
            
                else if(event.getKeyCode() == KeyEvent.VK_A)
                    matrix.move(Direction.LEFT);

                else if(event.getKeyCode() == KeyEvent.VK_S)
                    matrix.sinkPiece();

                else if(event.getKeyCode() == KeyEvent.VK_E)
                    matrix.move(Direction.TORIGHT);

                else if(event.getKeyCode() == KeyEvent.VK_Q)
                    matrix.move(Direction.TOLEFT);

                else if(event.getKeyCode() == KeyEvent.VK_SPACE)
                    matrix.instantSink();

                repaint();
            }
        }
    }

    public static void main( String[] args )
    {
        Tetris tetris = new Tetris();
    }
}