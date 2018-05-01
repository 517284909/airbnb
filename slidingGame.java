import java.util.*;

class SlidingGame {
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, -1, 0, 1};

    class Status {
        int[][] matrix;
        int x, y;
        Status(int[][] m, int i, int j) {
            matrix = new int[m.length][m[0].length];
            for (int ii = 0; ii < m.length; ii++)
                matrix[ii] = m[ii].clone();
            x = i;
            y = j;
        }
        String encodeMatrix() {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix[i].length; j++)
                    builder.append(matrix[i][j]).append(",");
            return builder.toString();
        }
        void move(int i, int j) {
            matrix[x][y] = matrix[i][j];
            matrix[i][j] = 0;
            x = i;
            y = j;
        }
    }

    public boolean canSolve(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int startX = 0, startY = 0;
        int[][] finalMatrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                finalMatrix[i][j] = i * m + j;
                if (matrix[i][j] == 0) {
                    startX = i;
                    startY = j;
                }
            }
        }

        Status initial = new Status(matrix, startX, startY);
        Status finals = new Status(finalMatrix, 0, 0);
        Queue<Status> queue = new LinkedList<>();
        queue.add(initial);
        Set<String> visited = new HashSet<>();
        visited.add(initial.encodeMatrix());

        while (!queue.isEmpty()) {
            Status current = queue.poll();
            int xx = current.x, yy = current.y;
            for (int i = 0; i < 4; i++) {
                int x = xx + dx[i];
                int y = yy + dy[i];
                if (x < 0 || x >= m || y < 0 || y >= n)
                    continue;
                current.move(x, y);
                if (current.encodeMatrix().equals(finals.encodeMatrix()))
                    return true;
                if (!visited.contains(current.encodeMatrix())) {
                    visited.add(current.encodeMatrix());
                    queue.add(new Status(current.matrix, current.x, current.y));
                }
                current.move(xx, yy);
            }
        }

        return false;
    }

    public final static void main(String[] args) {
        SlidingGame sg = new SlidingGame();
        System.out.println(sg.canSolve(new int[][]{{2, 3, 8}, {1, 4, 7}, {6, 0, 5}}));
        System.out.println(sg.canSolve(new int[][]{{8, 7, 6}, {5, 4, 3}, {1, 2, 0}}));
    }

}

