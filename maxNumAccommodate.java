import java.util.*;

class MaxNumAccommodate {
    public int accomodate(int[] nums) {
        if (nums == null || nums.length == 0)   return 0;
        if (nums.length == 1)   return nums[0];

        int f1 = nums[0], f2 = Math.max(f1, nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int f = Math.max(nums[i] + f1, f2);
            f1 = f2;
            f2 = f;
        }

        return f2;
    }
}



