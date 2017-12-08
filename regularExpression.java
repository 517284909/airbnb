import java.util.*;

class RegularExpression {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < p.length(); i+=2) {
            if (p.charAt(i) != '*')
                break;
            dp[0][i + 1] = true;
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '*') {
                    if (j > 0 && dp[i + 1][j - 1]) {
                        dp[i + 1][j + 1] = true;
                    } else if (j > 0 && dp[i][j + 1] && (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
                        dp[i + 1][j + 1] = true;
                    }
                } else if (p.charAt(j) == '+') {
                    if (j > 0 && dp[i][j + 1] && (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
                        dp[i + 1][j + 1] = true;
                    } else if (j > 0 && dp[i][j] && (s.charAt(i) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
                        dp[i + 1][j + 1] = true;
                    }
                } else {
                    dp[i + 1][j + 1] = dp[i][j] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                }
            }
        }

        return dp[s.length()][p.length()];
    }

    public final static void main(String[] args) {
        RegularExpression regularExpression = new RegularExpression();
        System.out.println(regularExpression.isMatch("", ".*"));
        System.out.println(regularExpression.isMatch("abc", "abc"));
        System.out.println(regularExpression.isMatch("abc", "abc+"));
        System.out.println(regularExpression.isMatch("abcc", "abc+"));
        System.out.println(regularExpression.isMatch("abccc", "abc+"));
        System.out.println(regularExpression.isMatch("abc", "abc*"));
        System.out.println(regularExpression.isMatch("abcc", "ab.+"));
        System.out.println(regularExpression.isMatch("abccc", "ab.+"));
    }
}


