package leetcodeChallenge;

import java.util.Optional;

public class DistributeCoins {
    private class Secret {
        private String key;

        public Secret(String key) {
            this.key = key;
        }

        public String getKey() { return key; }
    }

    public void solve() {
        Secret s = new Secret("this is a secret!");
        var os = Optional.of(s);
        System.out.println("key::" + os.get().getKey());

        Secret sp = null;
        var osp = Optional.ofNullable(sp).or(() -> Optional.of(s));
        System.out.println(osp.get().getKey());
    }
}
