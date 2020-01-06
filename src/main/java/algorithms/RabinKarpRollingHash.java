package algorithms;

import java.math.BigInteger;
import java.util.Random;

public class RabinKarpRollingHash {
    private long patHash; // pattern hash value
    private int M; // pattern length
    private long Q; // modulus
    private int R; // radix
    private long RM1; // R^(M-1) % Q
    private final String pattern;

    public RabinKarpRollingHash(String pat) {
        this.pattern = pat;
        this.M = pat.length();
        this.Q = getRandomPrimeNumber();
        this.R = 256;
        this.RM1 = 1L;
        for (int i = 1; i <= M - 1; ++i) {
            RM1 = (R * RM1) % Q;
        }
        patHash = hash(pat, M);
    }

    private long hash(String s, int M) {
        long h = 0;
        for (int i = 0; i < M; ++i) {
            h = (h * R +  s.charAt(i)) % Q;
        }
        return h;
    }

    public int searchFrom(String txt)  {
        int n = txt.length();
        long tHash = hash(txt, M);

        for (int i = 0; i < n; ++i) {
            if ((tHash == patHash) && isEqualToPattern(txt, i)) {
                return i;
            }

            //  note, the reason why add Q is because tHash maybe less than txt.charAt(i) * RM1 % Q
            //  which a negative case will generate chaos, plus the Q will avoid negative case without affecting the result
            tHash = (tHash + Q - txt.charAt(i) * RM1 % Q) % Q;
            tHash = (tHash * R + txt.charAt((i + M) % n)) % Q;
        }

        return -1;
    }

    private boolean isEqualToPattern(String txt, int idx) {
        int n = txt.length();
        for (int i = 0; i < M; ++i) {
            if (pattern.charAt(i) != txt.charAt((idx + i) % n)) {
                return false;
            }
        }

        return true;
    }

    private long getRandomPrimeNumber() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
}
