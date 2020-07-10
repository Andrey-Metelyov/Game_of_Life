package life;

import java.util.Random;

interface LifeGenerator {
    boolean[][] generate(boolean[][] grid);
}

class FirstLifeGenerator implements LifeGenerator {

    @Override
    public boolean[][] generate(boolean[][] grid) {
        boolean[][] newGrid = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int neigh = numberOfAliveNeighbours(grid, i, j);
                if (grid[i][j]) { // alive
                    newGrid[i][j] = neigh >= 2 && neigh <= 3;
                } else { // dead
                    newGrid[i][j] = neigh == 3;
                }
            }
        }
        return newGrid;
    }

    private int numberOfAliveNeighbours(boolean[][] grid, int i, int j) {
        int count = 0;
        int n = i - 1;
        int s = i + 1;
        int e = j + 1;
        int w = j - 1;

        if (w < 0) {
            w = grid[i].length - 1;
        }
        if (e > grid[i].length - 1) {
            e = 0;
        }
        if (s > grid.length - 1) {
            s = 0;
        }
        if (n < 0) {
            n = grid.length - 1;
        }
        // W
        count += grid[i][w] ? 1 : 0;
        // N
        count += grid[n][j] ? 1 : 0;
        // NW
        count += grid[n][w] ? 1 : 0;
        // S
        count += grid[s][j] ? 1 : 0;
        // SW
        count += grid[s][w] ? 1 : 0;
        // E
        count += grid[i][e] ? 1 : 0;
        // SE
        count += grid[s][e] ? 1 : 0;
        // NE
        count += grid[n][e] ? 1 : 0;

        return count;
    }
}

class Universe {
    private boolean[][] grid;
    private LifeGenerator lifeGenerator;
    private Random random = new Random();
    private int generation = 0;

    public Universe(int size, int seed, LifeGenerator generator) {
        this.grid = new boolean[size][size];
        this.random.setSeed(seed);
        this.lifeGenerator = generator;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = random.nextBoolean();
            }
        }
    }

    public void generate() {
        grid = lifeGenerator.generate(grid);
        generation++;
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public int getAlive() {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getGeneration() {
        return generation;
    }
}

public class Main {
    public static void main(String[] args) {
        Universe model = new Universe(20, 30, new FirstLifeGenerator());
        GameOfLife view = new GameOfLife();
        UniverseController controller = new UniverseController(model, view);
        controller.start();
    }
}
