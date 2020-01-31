package algorithms;

public class CommonAlgoUtils {

    public Boolean isNumPowerOfTwo(int num) {
        if (num <= 1) return false;
        return (num & (num -1)) == 0;
    }

    /**
     *  some mathematical trick behind:
     *  log(n) base m = log(n) / log (m) where built-in log() is natural constant base
     *  if num is power of n, i.e., num = n^k where k is an integer
     *  therefore result (log(n) / log (m)) should be an integer
     *  checking if a double is integer, let's compare it with its ceil() value
     */
    public Boolean isNumPowerOfN(int n, int num) {
        double r = Math.log(num) / Math.log(n);
        return (r - Math.ceil(r)) == 0;
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
    public boolean isRepeatedPattern(String m, String pattern) {
        return m.equals(pattern) || (m + m).contains(pattern);
    }

    /**
     *
     * @param nums (all the elements in nums occur even times and only one element occurs odd times
     * @return the only element occur odd times
     */
    public int theOnlyNumberThatOccurOddTimes(int[] nums) {
        int xor = 0;
        for(int num: nums) xor ^= num;
        return xor;
    }
}
