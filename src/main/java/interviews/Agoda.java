package interviews;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Agoda {
    /**
     *  Given date format "dd mmm yyyy"  e.g.,  [02, Dec, 2018]
     *  return string list sorted by datetime in ascending order
     *  [02, Dec, 2018, 02, Dec, 2019]
     **/
    public List<String> getAscendingDates(List<String> dates) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return dates.stream()
                .sorted(Comparator.comparingLong(o -> {
                    LocalDate parsed = LocalDate.parse(o, formatter);
                    return ZonedDateTime.of(parsed, LocalTime.MIDNIGHT, ZoneId.systemDefault()).toEpochSecond();
                }))
                .collect(Collectors.toList());
    }

    /**
     *  String reversed,
     *  Given "BEST AGODA APP",
     *  Return "APP AGODA BEST"
     */
    public String reverse(String s) {
        String[] str = s.split(" ");

        StringBuilder sb = new StringBuilder();
        for (int i = str.length - 1; i >= 0; --i) {
            sb.append(str[i]);
            if (i != 0) sb.append(" ");
        }

        return sb.toString();
    }

    /**
     *   find two odd occurrence while the remained occurs even times
     */
    public List<Integer> findTwoOddOccurrence(List<Integer> list) {
        int xor = list.get(0);
        for (int i = 1; i < list.size(); ++i) {
            xor ^= list.get(i);
        }

        int set_bit_no = xor & -xor;
        int x = 0, y = 0;
        for (Integer val : list) {
            if ((val & set_bit_no) > 0) {
                x ^= val;
            } else {
                y ^= val;
            }
        }

        return Arrays.asList(x, y);
    }
}
