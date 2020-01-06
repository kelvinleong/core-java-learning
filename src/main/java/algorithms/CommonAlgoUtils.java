package algorithms;

public class CommonAlgoUtils {

    public Boolean isPowerOfTwo(int num) {
        if (num <= 1) return false;
        return (num & (num -1)) == 0;
    }

    public int getRightMostSetBit(int num) {
        return num & -num;
    }
    
    /**
     *  Rotate pattern bit by bit and see if it can be m
     *  The magic behind is that, say we have a main String m = t1t2....tm
     *  Then we have a pattern String p = t2t3t4...tmt1
     *  Duplicate m, it becomes t1t2.....tmt1....tm
     *  Therefore, t2t3...tmt1 must bue found in the duplicated main string!
     *
     *  @param m (the main string)
     *  @param pattern  (pattern String, if pattern can be shifted to be a m)
     *  @return if pattern can be shifted so that it becomes m
     */
    public boolean checkIfRepeatedPattern(String m, String pattern) {
        return m.equals(pattern) || (m + m).contains(pattern);
    }

    /**
     *
     * @param nums (all the elements in nums occur even times and only one element occurs odd times
     * @return the only element occur odd times
     */
    public int checkOddOccurrence(int[] nums) {
        int xor = nums[0];
        for(int num: nums) xor ^= num;
        return xor;
    }
}
