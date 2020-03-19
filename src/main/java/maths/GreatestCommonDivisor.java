package maths;

public class GreatestCommonDivisor {

    public int educlid(int a, int b) {
        if (a == b) return a;
        else if (a > b) return educlid(a -b, b);
        else return educlid(a, b - a);
    }

    public int binarySearch(int a, int b) {
        int d = 0;
        while (a % 2 == 0 && b % 2 == 0) {
            a = a >> 1;
            b = b >> 1;
            ++d;
        }

        while (a != b) {
            if (a % 2 == 0) a = a >> 1;
            else if (b % 2 == 0) b = b >> 1;
            else if (a > b) a = (a -b) >> 1;
            else b = (b - a) >> 1;
        }

        return (int) (a * Math.pow(2.0, (double) d));
    }
}
