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

    // Fonction pour transmettre les infections dans la grille
    public static int solvingGame(int[][] grille) {
        Queue queue = new Queue();
        int rows = grille.length;
        int columns = grille[0].length;

        // Recherche des positions initiales des zombies pour les placer dans une file
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grille[i][j] == 2) {
                    queue.enqueue(i * columns + j);
                }
            }
        }

        // Définition des offsets
        int[][] offsets = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int iterations = 0;
        int newInfections = 0;

        // Boucle principale pour transmettre les infections
        while (!queue.isEmpty()) {
            iterations++;
            System.out.println("Iteration: " + iterations);

            printGrid(grille); // Afficher la grille à chaque itération

            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int position = queue.dequeue();
                int row = position / columns;
                int col = position % columns;

                // Vérifier les cellules voisines
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


        // Vérifier s'il reste des cellules non infectées dans la grille
        for (int[] row : grille) {
            for (int cell : row) {
                if (cell == 1) {
                    return -1; // Retourne -1 s'il reste des cellules non infectées
                }
            }
        }
        return iterations;
    }

    // Fonction principale
    public static void main(String[] args) {
        int[][][] grids = new int[100][100][100];

        int gridCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("sample.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(" ");
                int m = Integer.parseInt(parts[0]);
                int n = Integer.parseInt(parts[1]);

                // Analyse des données de la grille
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

        // Transmission pour chaque grille
        for (int i = 0; i < gridCount; i++) {

            System.out.println("Game solved in " +  solvingGame(grids[i]) + " iterations");
        }
    }

    // Fonction pour afficher la grille
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
