package algorithms;

public class CommonAlgoUtils {

    public Boolean isPowerOfTwo(int num) {
        if (num <= 1) return false;
        return (num & (num -1)) == 0;
    }

    public int getRightMostSetBit(int num) {
        return num & -num;
    }
}
