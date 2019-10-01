package algorithms;

import java.math.BigInteger;

/***
 * 间复杂度可以优化为O(N)
 * 假设第一个子串哈希值H0 = (S[0] * 10^2 + S[1] * 10^1 + S[2] * 10^0) mod m
 * 则第二个子串哈希值H1 = (10 * H0 - S[0] * 10^3 + S[3] * 10^0) mod m
 *
 * 代码如下：时间复杂度O(length(A) + length(B))
 */
public class RollingHash {
    private boolean check(int index, String A, String B) {
        for (int i = 0; i < B.length(); i++) {
            if (A.charAt((i + index) % A.length()) != B.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public int repeatedStringMatch(String A, String B) {
        int q = (B.length() - 1) / A.length() + 1;

        // 素数
        int p = 113;
        int MOD = 1_000_000_007;

        // 乘法逆模
        int pInv = BigInteger.valueOf(p).modInverse(BigInteger.valueOf(MOD)).intValue();

        // 计算字符串B的哈希值，时间复杂度 O(B.length())
        long bHash = 0, power = 1;
        for (int i = 0; i < B.length(); i++) {
            bHash += power * B.codePointAt(i);
            bHash %= MOD;
            power = (power * p) % MOD;
        }

        // 计算字符串A的第一个子串哈希值，时间复杂度 O(B.length())
        long aHash = 0; power = 1;
        for (int i = 0; i < B.length(); i++) {
            aHash += power * A.codePointAt(i % A.length());
            aHash %= MOD;
            power = (power * p) % MOD;
        }

        // 如果一个子串的哈希值与 B 的哈希值匹配，将该子串与 B 进行比较，时间复杂度 O(B.length())
        if (aHash == bHash && check(0, A, B)) return q;

        power = (power * pInv) % MOD;

        // 利用前一个子串的计算结果哈希值来计算当前子串的哈希值
        for (int i = B.length(); i < (q + 1) * A.length(); i++) {
            aHash -= A.codePointAt((i - B.length()) % A.length());
            aHash *= pInv;
            aHash += power * A.codePointAt(i % A.length());
            aHash %= MOD;

            if (aHash == bHash && check(i - B.length() + 1, A, B)) {
                return i < q * A.length() ? q : q + 1;
            }
        }

        return -1;
    }
}
