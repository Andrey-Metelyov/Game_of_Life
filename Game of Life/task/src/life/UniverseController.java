package life;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class UniverseController extends Thread {
    final String GENERATIONS_FORMAT = "Generation #%d";
    final String ALIVE_FORMAT = "Alive: %d";
    private Universe universe;
    private GameOfLife view;
    private boolean paused = false;

    public UniverseController(Universe universe, GameOfLife view) {
        this.universe = universe;
        this.view = view;
        update(universe, view);
    }

    @Override
    public void run() {
        super.run();
        view.playButton.addActionListener(e -> paused = !paused);
        view.resetButton.addActionListener(e -> {
            Random random = new Random();
            universe = new Universe(universe.getGrid().length, random.nextInt(), new FirstLifeGenerator());
            paused = false;
        });
        while (true) {
            if (!paused) {
                update(universe, view);
                universe.generate();
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
            }
        }
    }

    private void update(Universe universe, GameOfLife view) {
        view.generationLabel.setText(String.format(GENERATIONS_FORMAT, universe.getGeneration()));
        view.aliveLabel.setText(String.format(ALIVE_FORMAT, universe.getAlive()));
        view.lifePanel.drawGrid(universe.getGrid());
    }
}
