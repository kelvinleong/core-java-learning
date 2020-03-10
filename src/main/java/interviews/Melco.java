package interviews;

public class Melco {
    private int min(int a, int b) {
        return a > b ? b : a;
    }

    public int solution(int A[]) {
        int[] odd = {0, 0};
        int[] even = {0, 0};

        for (int i = 0; i < A.length; ++i) {
            if (i % 2 == 0) {
                if (A[i] == 1) {
                    even[1] += 1;
                } else {
                    even[0] += 1;
                }
            } else {
                if (A[i] == 1) {
                    odd[1] += 1;
                } else {
                    odd[0] += 1;
                }
            }
        }
        return min(odd[0] + even[1], odd[1] + even[0]);
    }

    private int count = 0;

    private boolean isValidToMove(String[] B, int i, int j) {
        if (i == 0) {
            return false;
        } else if ("X".equals(String.valueOf(B[i -1].charAt(j)))) {
            return false;
        } else if (j == B.length - 1) {
            return false;
        } else if ("X".equals(String.valueOf(B[i - 1].charAt(j + 1)))) {
            return i - 2 <= 0 || j + 2 >= B.length || !"X".equals(String.valueOf(B[i - 2].charAt(j + 2)));
        } else {
            return false;
        }
    }

    private void calculate(String[] B, int i, int j) {
        if (isValidToMove(B, i, j)) {
            ++count;
            calculate(B, i - 1, j + 2);
        }

        if (i - 1 >= 0 && j - 1 >= 0 && "X".equals(String.valueOf(B[i - 1].charAt(j - 1)))) {
            ++count;
            calculate(B, i - 1, j - 1);
        }
    }

    public int solution(String[] B) {
        // write your code in Java SE 8
        int posX = 0;
        int posY = 0;

        for (int i = 0; i < B.length; ++i) {
            for (int j = 0; j < B.length; ++j) {
                if ("O".equals(String.valueOf(B[i].charAt(j)))) {
                    posX = i;
                    posY = j;
                    break;
                }
            }
        }

        calculate(B, posX, posY);
        return count;
    }
}
