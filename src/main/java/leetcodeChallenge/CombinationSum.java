package leetcodeChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    private void backTrack(int[] array, int target, int curIdx, List<Integer> s, List<List<Integer>> rs) {
        int remain = target - array[curIdx];

        for (int i = curIdx; i < array.length; ++i) {
            if (remain < 0) {
                s.clear();
                return;
            } else if (remain == 0) {
                s.add(array[i]);
                rs.add(new ArrayList<>(s));
                s.clear();
                return;
            } else {
                s.add(array[i]);
                backTrack(array, remain, i, s, rs);
            }
        }
    }

    public void solve(int[] array, int target) {
        Arrays.sort(array);

        List<Integer> s = new ArrayList<>();
        List<List<Integer>> rs = new ArrayList<>();
        backTrack(array, target, 0, s, rs);
        System.out.println("done");
    }
}
