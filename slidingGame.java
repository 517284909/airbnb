import java.util.*;

class Solution {
    class Status {
        int x, y;
        int[][] board;
        String encode;
        int step;

        Status(int[][] board, int step) {
            this.board = new int[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    this.board[i][j] = board[i][j];
                    if (board[i][j] == 0) {
                        this.x = i;
                        this.y = j;
                    }
                }
            }
            this.encode = encodeBoard(board);
            this.step = step;
        }

        String encodeBoard(int[][] board) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < board.length; i++)
                builder.append(Arrays.toString(board[i]));
            this.encode = builder.toString();
        }

        List<Status> nextStatus() {
            List<Status> nextS = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                int n_x = x + dx[i];
                int n_y = y + dy[i];
                if (n_x < 0 || n_x >= board.length || n_y < 0 || n_y >= board[n_x].length)
                    continue;
                board[x][y] = board[n_x][n_y];
                board[n_x][n_y] = 0;
                nextS.add(new Status(this.board, this.step + 1));
                board[n_x][n_y] = board[x][y];
                board[x][y] = 0;
            }
            return nextS;
        }
    }

    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, -1, 0, 1};

    public int slidingPuzzle(int[][] board) {
        Status initS = new Status(board, 0);
        int[][] finalBoard = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                finalBoard[i][j] = i * finalBoard[i].length + j + 1;
        finalBoard[finalBoard.length - 1][finalBoard[0].length - 1] = 0;
        Status finalS = new Status(finalBoard, -1);

        if (initS.encode.equals(finalS.encode)) {
            return initS.step;
        }

        Queue<Status> queue = new LinkedList<>();
        queue.add(initS);
        Set<String> visited = new HashSet<>();
        visited.add(initS.encode);
        while (!queue.isEmpty()) {
            Status s = queue.poll();
            List<Status> nextS = s.nextStatus();
            for (Status s: nextS) {
                if (s.encode.equals(finalS.encode)) {
                    return s.step;
                }
                if (visited.contains(s.encode)) {
                    continue;
                }
                nextS.add(s);
                visited.add(s.encode);
            }
        }
        return -1;
    }
}



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

