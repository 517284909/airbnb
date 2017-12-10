import java.util.*;

class EditDistance {

    public int editDistance(String w1, String w2) {
        int[][] dp = new int[w1.length() + 1][w2.length() + 1];
        for (int i = 0; i < w1.length(); i++) {
            dp[i + 1][0] = i + 1;
        }
        for (int j = 0; j < w2.length(); j++) {
            dp[0][j + 1] = j + 1;
        }
        for (int i = 0; i < w1.length(); i++) {
            for (int j = 0; j < w2.length(); j++) {
                int p = Math.min(dp[i][j + 1], dp[i + 1][j]) + 1;
                int q = dp[i][j] + (w1.charAt(i) == w2.charAt(j) ? 0 : 1);
                dp[i + 1][j + 1] = Math.min(p, q);
            }
        }
        return dp[w1.length()][w2.length()];
    }

    public boolean oneEditDistance(String w1, String w2) {
        if (Math.abs(w1.length() - w2.length()) > 1) {
            return false;
        }
        String longer = w1.length() >= w2.length() ? w1 : w2;
        String shorter = w1.length() < w2.length() ? w1 : w2;
        int i = 0, j = 0, cnt = 0;
        while (i < longer.length() && j < shorter.length()) {
            if (longer.charAt(i) == shorter.charAt(j)) {
                ++i;
                ++j;
            } else {
                ++cnt;
                if (cnt > 1) {
                    break;
                }
                ++i;
                if (longer.length() == shorter.length()) {
                    ++j;
                }
            }
        }
        if (cnt == 1 || cnt == 0 && j == shorter.length() && i < longer.length()) {
            return true;
        }
        return false;
    }

    public final static void main(String[] args) {
        EditDistance editDistance = new EditDistance();
        System.out.println(editDistance.editDistance("abc", "abc"));
        System.out.println(editDistance.editDistance("abc", "cba"));
        System.out.println(editDistance.editDistance("abc", "aabbcc"));
        System.out.println(editDistance.editDistance("", ""));
    }
}


