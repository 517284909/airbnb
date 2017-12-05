import java.util.*;

class Solution {
    Map<Integer, Integer> table;

    public Solution() {
        table = new HashMap<>();
    }

    public int findLongestSteps(int num) {
        if (num < 1)  return 0;
        int max = 0;
        for (int i = 1; i <= num; i++) {
            int t = findSteps(i);
            table.put(i, t);
            max = Math.max(max, t);
        }
        return max;
    }

    private int findSteps(int num) {
        if (num == 1) return 0;
        if (table.containsKey(num)) return table.get(num);
        if (num % 2 == 0) return 1 + findSteps(num / 2);
        return 1 + findSteps(3 * num + 1);
    }


    public final static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findLongestSteps(27));
    }
}

