package algorithms;

import java.util.List;

public class KadaneAlgorithm {

    public Integer findLargestContinguousSubArraySum(List<Integer> array) {
        int maxEndingHere = 0;
        int maxSoFar = 0;

        for(Integer v: array) {
            maxEndingHere = maxEndingHere + v;
            if (maxEndingHere < 0) maxEndingHere = 0;
            if (maxSoFar < maxEndingHere) maxSoFar = maxEndingHere;
        }

        return maxSoFar;
    }
}
