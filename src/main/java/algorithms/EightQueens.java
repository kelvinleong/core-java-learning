package algorithms;

public class EightQueens {
    private Boolean isSafeToPlace(int[][] board, int col, int row, final int n) {
        // check previous columns
        for (int i = 0; i < col; ++i) {
            if (board[row][i] > 0) return false;
        }

        // check previous rows
        for (int i = 0; i < row; ++i) {
            if (board[row][col] > 0) return false;
        }

        // check diagonal
        for (int i = row, j = col; ; --i, --j) {
            if (i < 0 || j < 0) break;

            if (board[i][j] > 0) return false;
        }

        for (int i = row, j = col; ; ++i, --j) {
            if (i >= n || j < 0) break;

            if (board[i][j] > 0) return false;
        }


        return true;
    }

    private Boolean backtrack(int[][] board, int col, final int n) {
        if (col == n) {
            for(int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    System.out.print(board[i][j] + "  ");
                }
                System.out.print("\n");
            }
            System.out.println();

            return true;
        }

        Boolean result = false;
        for (int i = 0; i < n; ++i) {
            if (isSafeToPlace(board, col, i, n)) {
                board[i][col] = 1;

                result = backtrack(board, col + 1, n) || result;

                // back track
                board[i][col] = 0;
            }
        }

        return result;
    }

    public Boolean solve() {
        int N = 4;
        int[][] board = new int[N][N];

        return backtrack(board, 0, N);
    }
}
