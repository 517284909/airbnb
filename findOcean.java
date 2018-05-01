import java.util.*;


class Solution {

    private final static int[] dx = {-1, 0, 1, 0};
    private final static int[] dy = {0, -1, 0, 1};

    public static void floodFillDFS(char[][] board, int i, int j, char oldColor, char newColor) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != oldColor)
            return;
        board[i][j] = newColor;
        for (int k = 0; k < 4; k++)
            floodFillDFS(board, i + dx[k], j + dy[k], oldColor, newColor);
    }

    public static void floodFillBFS(char[][] board, int i, int j, char oldColor, char newColor) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length || board[i][j] != oldColor)
            return;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i * board[i].length + j);
        while (!queue.isEmpty()) {
            int p = queue.poll();
            int x = p / board[0].length, y = p % board[0].length;
            board[x][y] = newColor;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k], ny = y + dy[k];
                if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[nx].length || board[nx][ny] != oldColor)
                    continue;
                queue.add(nx * board[nx].length + ny);
            }
        }
    }

    public final static void main(String[] args) {
        {
            char[][] board = new char[][]{
                {'W', 'W', 'W', 'L', 'L', 'L', 'W'},
                {'W', 'W', 'L', 'L', 'L', 'W', 'W'},
                {'W', 'L', 'L', 'L', 'L', 'W', 'W'},
            };
            Solution.floodFillDFS(board, 0, 1, 'W', 'O');
            for (int i = 0; i < board.length; i++) {
                System.out.println(new String(board[i]));
            }
        }
        {
            char[][] board = new char[][]{
                {'W', 'W', 'W', 'L', 'L', 'L', 'W'},
                {'W', 'W', 'L', 'L', 'L', 'W', 'W'},
                {'W', 'L', 'L', 'L', 'L', 'W', 'W'},
            };
            Solution.floodFillBFS(board, 2, 5, 'W', 'O');
            for (int i = 0; i < board.length; i++) {
                System.out.println(new String(board[i]));
            }
        }

    }

}

