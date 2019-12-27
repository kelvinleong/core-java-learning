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
