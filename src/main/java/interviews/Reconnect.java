package interviews;

import java.util.*;
import java.util.stream.Collectors;

public class Reconnect {
    /**
     *  Given an array list, increment the duplicated elements so that they make the array unique
     *  with minimum times and return the sum of the unique array
     *  e.g., arr[1, 2, 2, 3] increment the third element to 4 so that the array becomes [1, 2, 4, 3]
     *  which is unique and return the sum = 1 + 2 + 4 + 3 = 10
     *  @param array
     */
    public Integer solve(List<Integer> array) {
        array = array.stream().sorted().collect(Collectors.toList());

        int prev = array.get(0);
        int sum = array.get(0);
        for (Integer integer : array) {
            if (integer <= prev) ++prev;
            else prev = integer;
            sum += prev;
        }

        return sum;
    }
}
