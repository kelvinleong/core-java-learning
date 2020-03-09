package interviews;

import java.security.InvalidParameterException;

public class GoldmanSachs {
    private int greatestCommonDivisor(int a, int b) {
        if (b == 0)
            return a;
        return greatestCommonDivisor(b, a % b);
    }

    private int leastCommonMultiplier(int a, int b) {
        int gcd = greatestCommonDivisor(Math.abs(a), Math.abs(b));
        return Math.abs(a) * Math.abs(b) / gcd;
    }

    public int[] addFractions(int[] fraction1, int[] fraction2) {
        if (fraction1[1] == 0 || fraction2[1] == 0) throw new InvalidParameterException("Denominator must not be zerp");

        int lcm = leastCommonMultiplier(fraction1[1], fraction2[1]);
        int numerator = (lcm / fraction1[1]) * fraction1[0] + (lcm / fraction2[1]) * fraction2[0];

        return new int[]{numerator, lcm};
    }
}
