package game;

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

    public Tetris()
    {
        setJFrame();
        setJPanel();
    }

    //Setup of the JFrame
    private void setJFrame()
    {
        setTitle("Minesweeper");
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);setTitle("Minesweeper");
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Adding a keyListener
        addKeyListener(new MyKeyAdapter());

        setVisible(true);
    }

    //Setup of the JPanel
    private void setJPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);
        panel.setDoubleBuffered(true);
        panel.setSize(getSize());

        matrix.setCellsPosition(getWidth(), getHeight());
        for(JLabel a : matrix.getCells())
            panel.add(a);

        add(panel);
    }

    private void timedSink()
    {
        if(timer.checkTime(500))
            matrix.sinkPiece();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        timedSink();
        repaint();
    }

    //A "custom" KeyAdapter to control the movement
    private class MyKeyAdapter extends KeyAdapter
    {
        @Override public void keyPressed(KeyEvent event) 
        {
            if(event.getKeyCode() == KeyEvent.VK_D)
                matrix.move(Direction.RIGHT);
            
            else if(event.getKeyCode() == KeyEvent.VK_A)
                matrix.move(Direction.LEFT);

            else if(event.getKeyCode() == KeyEvent.VK_S)
                matrix.sinkPiece();

            repaint();
        }
    }
}