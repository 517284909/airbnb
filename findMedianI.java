import java.util.*;

class Solution {

    private long search(int[] nums, int k, long left, long right) {
        long res = left;
        long guess = left + (right - left) / 2;
        int count = 0;
        for (int num : nums) {
            if (num <= guess) {
                count++;
                res = Math.max(res, num);
            }
        }
        if (count == k) {
            return res;
        } else if (count < k) {
            return search(nums, k, Math.max(res + 1, guess), right);
        } else {
            return search(nums, k, left, res - 1);
        }
    }

    public double findMedian(int[] nums) {
        int len = 0;
        for (int num : nums)  len++;
        if (len % 2 == 1) {
            return (double) search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        } else {
            return (double) (search(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE) +
                search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE)) / 2;
        }
    }

    public static final void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.findMedian(new int[]{5, 2, 34, 2, 2}));
    }
}

