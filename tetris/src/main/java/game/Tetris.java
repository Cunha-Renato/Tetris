package game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;

public class Tetris extends JFrame
{
    Matrix matrix = new Matrix();

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

    public void paint(Graphics g)
    {
        super.paint(g);
        matrix.sinkPiece();
    }
}