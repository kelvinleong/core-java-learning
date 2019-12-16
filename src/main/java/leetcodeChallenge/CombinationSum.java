package leetcodeChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    /**
     *  BackTracking Algorithm pseudo code
     *  Start from initial item (index = 0)
     *  If all items are iterated without leading to a solution, end
     *  Else if some target meets, add as a solution
     *  Else BackTracking from next item (index + 1)
     */

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

        Arrays.binarySearch(array, 1);
        List<Integer> s = new ArrayList<>();
        List<List<Integer>> rs = new ArrayList<>();
        backTrack(array, target, 0, s, rs);
        System.out.println("done");
    }

    private class Solution {
        private void backTrack(int curIdx, int cap, int max, List<Integer> s, List<List<Integer>> rs) {
            if (cap == 0) {
                rs.add(new ArrayList<>(s));
                return;
            }

            for (int i = curIdx; i <= max; ++i) {
                s.add(i);
                backTrack(i + 1, cap - 1, max, s, rs);
                s.remove(s.size() - 1);
            }
        }

        public List<List<Integer>> combine(int n, int k) {
            List<Integer> s = new ArrayList<>();
            List<List<Integer>> rs = new ArrayList<>();
            backTrack(1, k, n, s, rs);
            return rs;
        }
    }
}
