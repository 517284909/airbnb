import java.util.*;

class TrieNode {
    char value;
    TrieNode[] neighbors;
    boolean isLeaf;
    TrieNode() {
        neighbors = new TrieNode[26];
        isLeaf = false;
    }
}

class Trie {
    TrieNode root;
    int height;

    Trie() {
        root = new TrieNode();
    }

    void insert(String word) {
        TrieNode runner = root;
        height = Math.max(height, word.length() + 1);
        for (char ch: word.toCharArray()) {
            int idx = ch - 'a';
            if (runner.neighbors[idx] == null) {
                runner.neighbors[idx] = new TrieNode();
                runner.neighbors[idx].value = ch;
            }
            runner = runner.neighbors[idx];
        }
        runner.isLeaf = true;
    }

    List<String> search(String target, int k) {
        List<String> results = new ArrayList<>();
        int[][] dp = new int[height][target.length() + 1];
        dp[0][0] = 0;
        search(target, k, root, 0, dp, new String(), results);
        return results;
    }

    void search(String target, int k, TrieNode runner, int d, int[][] dp, String current, List<String> results) {
        if (runner == null) {
            return;
        }
        if (d == 0) {
            for (int j = 0; j < target.length(); j++) {
                dp[d][j + 1] = j + 1;
            }
        } else {
            dp[d][0] = d;
            for (int j = 0; j < target.length(); j++) {
                int p = Math.min(dp[d - 1][j + 1], dp[d][j]) + 1;
                int q = dp[d - 1][j] + (runner.value == target.charAt(j) ? 0 : 1);
                dp[d][j + 1] = Math.min(p, q);
            }
        }

        if (runner.isLeaf && dp[d][target.length()] <= k) {
            results.add(current);
        }
        for (TrieNode n: runner.neighbors) {
            if (n == null) {
                continue;
            }
            search(target, k, n, d + 1, dp, current + n.value, results);
        }
    }
}

class KEditDistance {
    public List<String> kEditDistance(String[] words, String target, int k) {
        Trie trie = new Trie();
        for (String w: words) {
            trie.insert(w);
        }
        return trie.search(target, k);
    }

    public final static void main(String[] args) {
        KEditDistance kEditDistance = new KEditDistance();
        System.out.println(kEditDistance.kEditDistance(new String[]{"abc", "abcd"}, "ab", 1));
        System.out.println(kEditDistance.kEditDistance(new String[]{"abc","abd","abcd","adc","mart","ka","rma","kaarmmaa","km","kpm","kmp","pmaa"}, "karma", 3));
    }
}

