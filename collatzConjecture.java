import java.util.*;

class Solution {
    private Map<Long, Long> table;
//    private long debug_max;

    public Solution() {
//        debug_max = 0;
        table = new HashMap<>();
    }

    public long findLongestSteps(long num) {
        if (num < 1)  return 0;
        long max = 0;
        for (long i = 1; i <= num; i++) {
            long t = findSteps(i);
            table.put(i, t);
            max = Math.max(max, t);
        }
        return max;
    }

    private long findSteps(long num) {
        if (num == 1) return 0;
        if (table.containsKey(num)) return table.get(num);
        if (num % 2 == 0) return 1 + findSteps(num / 2);
//        debug_max = Math.max(debug_max, (3 * num + 1));
        return 1 + findSteps(3 * num + 1);
    }


    public final static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findLongestSteps(1));
//        System.out.println(solution.debug_max);
    }
}

