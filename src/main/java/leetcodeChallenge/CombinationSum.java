package leetcodeChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    private void backTrack(int[] array, int target, int curIdx, List<Integer> s, List<List<Integer>> rs) {
        if (target < 0) return;
        else if (target == 0) rs.add(new ArrayList<>(s));
        else {
            for (int i = curIdx; i < array.length; ++i) {
                s.add(array[i]);
                backTrack(array, target - array[i], i, s, rs);
                s.remove(s.size() - 1);
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
