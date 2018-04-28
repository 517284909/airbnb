import java.util.*;

class CoinChange {

    public int coinChange(int[] coins, int target) {
        int[] dp = new int[target + 1];
        for (int i = 1; i <= target; i++) {
            int min = Integer.MAX_VALUE;
            for (int c: coins) {
                if (i < c || dp[i - c] == -1)
                    continue;
                min = Math.min(min, 1 + dp[i - c]);
            }
            dp[i] = (min == Integer.MAX_VALUE) ? -1 : min;
        }
        return dp[target];
    }

}
