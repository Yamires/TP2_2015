import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameSolver {

    public static class Grid {
        int rows;
        int columns;
        int[][] data;

        public Grid(int rows, int columns, int[][] data){
            this.rows = rows;
            this.columns = columns;
            this.data = data;
        }
    }
    public static Grid parseInput(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int[][] grid = null;
        int rows = 0;
        int cols = 0;
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            if (i % 4 == 0) {
                rows = Integer.parseInt(line.trim());
            } else if (i % 4 == 1) {
                cols = Integer.parseInt(line.trim());
                grid = new int[rows][cols];
            } else if (i % 4 == 2) {
                String[] values = line.trim().split("\\s+");
                for (int j = 0; j < cols; j++) {
                    grid[i / 4][j] = Integer.parseInt(values[j]);
                }
            }
            i++;
        }
        reader.close();
        return new Grid(rows, cols, grid);
    }

    public int transmit(Grid grille) {
        Queue queue = new Queue();
        int rows = grille.length();
        int columns = grille.columns;
        int[][] data = grille.data;

        // Enqueue zombies positions
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (data[i][j] == 2) {
                    queue.enqueue(new int[]{i, j});
                }
            }
        }

        int[][] offsets = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int iterations = 0;

        int newInfections = 0;
        while (!queue.isEmpty()) {
            iterations++;

            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] position = queue.dequeue();
                int row = position[0];
                int col = position[1];

                for (int[] offset : offsets) {
                    int nextRow = row + offset[0];
                    int nextCol = col + offset[1];
                    if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < col && grille[nextRow][nextCol] == 1) {
                        grille[nextRow][nextCol] = 2;
                        queue.enqueue(new int[]{nextRow, nextCol});
                        newInfections++;
                    }
                }

            }
        }

        if (newInfections == 0) {
            break;
        }

        for (int[] row : grille) {
            for (int cell : row) {
                if (cell == 1) {
                    return -1;
                }
            }
        }
        return iterations;

    }

    public static void main(String[] args) {
        String filePath = "samples.txt"; // Change this to the path of your input file
        try {
            Grid[] grids = new Grid[]{GameSolver.parseInput(filePath)};
            for (int i = 0; i < grids.length; i++) {
                System.out.println("Grille " + (i + 1) + ":");
                int iterations = GameSolver.transmit(grids[i]);
                if (iterations != -1) {
                    System.out.println("Nombre d'itérations " + iterations);
                } else {
                    System.out.println("Il ne reste plus de personnes à infecter");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}




