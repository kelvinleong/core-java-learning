package leetcodeChallenge;

import java.util.*;

public class ThreeSum {
    /**
     *  this solution beats 31% of Java submissions
     */
    public List<List<Integer>> solution1(int[] nums) {
        Arrays.sort(nums);

        Set<List<Integer>> r = new HashSet<>();
        for (int i = 0; i < nums.length - 2; ++i) {
            int end = nums.length - 1;
            int start = i + 1;
            while (start < end) {
                int s = nums[i] + nums[start] + nums[end];
                if (s == 0) {
                    r.add(Arrays.asList(nums[i], nums[start++], nums[end--]));
                } else if (s > 0) {
                    end -= 1;
                } else {
                    start += 1;
                }
            }
        }

        return new ArrayList<>(r);
    }

    /**
     *   this non-trivial solution beats 75% of Java submissions
     *   the core idea is to reduce times on comparing
     */
    public List<List<Integer>> solution2(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> r = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; ++i) {
            if (i == 0 || (i > 0 && nums[i] > nums[i - 1])) {
                int end = nums.length - 1;
                int start = i + 1;
                while (start < end) {
                    int s = nums[i] + nums[start] + nums[end];
                    if (s == 0) {
                        r.add(Arrays.asList(nums[i], nums[start], nums[end]));
                        while (start < end && nums[start] == nums[start + 1]) ++start;
                        while (start < end && nums[end] == nums[end - 1]) --end;
                        ++start;
                        --end;
                    } else if (s > 0) {
                        end -= 1;
                    } else {
                        start += 1;
                    }
                }
            }
        }

        return r;
    }
}
