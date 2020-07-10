package life;

import javax.swing.*;
import java.awt.*;

class LifePanel extends JPanel {
    boolean[][] grid = null;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (grid == null) {
            return;
        }
        int w = getWidth();
        int h = getHeight();
        int adaptedGridSizeX = w / (grid.length);
        int adaptedGridSizeY = h / (grid[0].length);
        int maxX = adaptedGridSizeX * grid.length;
        int maxY = adaptedGridSizeY * grid[0].length;
        for (int i = 0; i <= grid.length; i++) {
            g.drawLine(0,  i * adaptedGridSizeY, maxX, i * adaptedGridSizeY);
        }
        for (int i = 0; i <= grid[0].length; i++) {
            g.drawLine(i * adaptedGridSizeX, 0, i * adaptedGridSizeX, maxY);
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                g.fillRect(i * adaptedGridSizeX + 1, j * adaptedGridSizeY + 1,
                        adaptedGridSizeX - 2, adaptedGridSizeY - 2);
            }
        }
    }

    public void drawGrid(boolean[][] grid) {
        this.grid = grid;
        repaint();
    }
}

public class GameOfLife extends JFrame {
    JLabel generationLabel;
    JLabel aliveLabel;
    LifePanel lifePanel;
    JToggleButton playButton;
    JButton resetButton;

    public GameOfLife() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        generationLabel = new JLabel("Generation #0");
        generationLabel.setName("GenerationLabel");
        aliveLabel = new JLabel("Alive: 0");
        aliveLabel.setName("AliveLabel");
        playButton = new JToggleButton("Play");
        playButton.setName("PlayToggleButton");
        resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(playButton);
        panel.add(resetButton);
        panel.add(generationLabel);
        panel.add(aliveLabel);
        add(panel, BorderLayout.WEST);

        lifePanel = new LifePanel();

        add(lifePanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
