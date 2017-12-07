import java.util.*;

class FindMedian {

    /*
     * Find the virtual border first, then find real border by virtual border
     *
    */
    public int findMedian(int[] nums, int k) {
        long upper = Integer.MAX_VALUE;
        long lower = Integer.MIN_VALUE;

        while (lower + 1 < upper) {
            long p = lower + (upper - lower) / 2;
            int cnt = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= p) {
                    ++cnt;
                }
            }
            if (cnt <= k) {
                lower = p;
            } else {
                upper = p;
            }
        }

        int chk_cnt = 0, true_lower = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= lower) {
                ++chk_cnt;
                true_lower = Math.max(true_lower, nums[i]);
            }
        }
        if (chk_cnt >= k) {
            return true_lower;
        }
        int true_upper = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= upper) {
                true_upper = Math.min(true_upper, nums[i]);
            }
        }
        return true_upper;
    }

    public final static void main(String[] args) {
        FindMedian findMedian = new FindMedian();
        {
            int[] nums = new int[]{5, 5, 7, 14, 2, 5, 2};
            System.out.println(findMedian.findMedian(nums, 4));
        }
        {
            // 2, 2, 5, 5, 7, 14
            int[] nums = new int[]{5, 7, 14, 2, 5, 2};
            System.out.println(findMedian.findMedian(nums, 6));
            System.out.println(findMedian.findMedian(nums, 1));
        }

    }
}


