import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class GameSolver {

    public static class Grid {
        int rows;
        int columns;
        int[][] data;

        public Grid(int rows, int columns, int[][] data) {
            this.rows = rows;
            this.columns = columns;
            this.data = data;
        }
    }


    public static int transmit(int[][] grille) {
        Queue queue = new Queue();
        int rows = grille.length;
        int columns = grille[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grille[i][j] == 2) {
                    queue.enqueue(i * columns + j);
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
                int position = queue.dequeue();
                int row = position / columns;
                int col = position % columns;

                for (int[] offset : offsets) {
                    int nextRow = row + offset[0];
                    int nextCol = col + offset[1];
                    if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < columns && grille[nextRow][nextCol] == 1) {
                        grille[nextRow][nextCol] = 2;
                        queue.enqueue(nextRow * columns + nextCol);
                        newInfections++;
                    }
                }
            }
        }

        if (newInfections == 0) {
            return -1;
        }

        // Check if there are still 1 in the grid
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
        int[][][] grids = new int[100][100][100];

        int gridCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("sample.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(" ");
                int m = Integer.parseInt(parts[0]);
                int n = Integer.parseInt(parts[1]);

                // Parse grid data
                int[][] grid = new int[m][n];
                for (int i = 0; i < m; i++) {
                    line = br.readLine();
                    String[] values = line.split(" ");
                    for (int j = 0; j < n; j++) {
                        grid[i][j] = Integer.parseInt(values[j]);
                    }
                }

                grids[gridCount++] = grid;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < gridCount; i++) {
            // Example: print each grid
            transmit(grids[i]);
            printGrid(grids[i]);
        }
    }

    public static void printGrid(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}



