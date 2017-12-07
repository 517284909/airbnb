import java.util.*;

class PalindromePair {


    /*
     * 第一种: w1反==w2, eg. w1 = abc, w2 = cba
     * 第二种: w1反==w2 suffix, eg. w1 = ab, w2 = ccba
     * 第三种: w1反==w2 prefix, eg. w1 = ba, w2 = abcc
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++)
            map.put(new StringBuilder(words[i]).reverse().toString(), i);

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) {
                String prefix = words[i].substring(0, j);
                String suffix = words[i].substring(j);
                if (map.containsKey(prefix) && isPalindrome(suffix) && map.get(prefix) != i) {
                    List<Integer> c_res = new ArrayList<>();
                    c_res.add(i);
                    c_res.add(map.get(prefix));
                    result.add(c_res);
                }
                if (prefix.length() != 0 && map.containsKey(suffix) && isPalindrome(prefix) && map.get(suffix) != i) {
                    List<Integer> c_res = new ArrayList<>();
                    c_res.add(map.get(suffix));
                    c_res.add(i);
                    result.add(c_res);
                }
            }
        }

        return result;
    }
    
    private boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r))
                return false;
            ++l;
            --r;
        }
        return true;
    }

    public final static void main(String[] args) {
        PalindromePair palindromePairs = new PalindromePair();
        List<List<Integer>> results = palindromePairs.palindromePairs(new String[]{"bat", "tab"});
        System.out.println(results.toString());
        results = palindromePairs.palindromePairs(new String[]{"abcd", "dcba", "lls", "s", "sssll"});
        System.out.println(results.toString());
    }
}


