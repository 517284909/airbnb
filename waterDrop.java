import java.util.*;


class Solution {
    public int[] pourWater(int[] heights, int V, int K) {
        
        int v = 0;
        while (v < V) {
            ++v;
            int l = K, lowestL = K;
            while (l - 1 >= 0 && heights[l - 1] <= heights[l]) {
                if (heights[l - 1] < heights[lowestL])
                    lowestL = l - 1;
                --l;
            }
            if (lowestL != K) {
                ++heights[lowestL];
                continue;
            }
            int r = K, lowestR = K;
            while (r + 1 < heights.length && heights[r + 1] <= heights[r]) {
                if (heights[r + 1] < heights[lowestR])
                    lowestR = r + 1;
                ++r;
            }
            if (lowestR != K) {
                ++heights[lowestR];
                continue;
            }
            ++heights[K];
        }

        return heights;
    }
}


class WaterDrop {
    public void dropWater(int[] heights, int water, int location) {
        int[] waters = new int[heights.length];

        while (water > 0) {
            int left = location;
            while (left - 1 >= 0) {
                if (heights[left - 1] + waters[left - 1] > heights[left] + waters[left]) {
                    break;
                }
                --left;
            }
            if (heights[left] + waters[left] < heights[location] + waters[location]) {
                ++waters[left];
                --water;
                continue;
            }

            int right = location;
            while (right + 1 < heights.length) {
                if (heights[right + 1] + waters[right + 1] > heights[right] + waters[right]) {
                    break;
                }
                ++right;
            }
            if (heights[right] + waters[right] < heights[location] + waters[location]) {
                ++waters[right];
                --water;
                continue;
            }
            ++waters[location];
            --water;
        }
        print(heights, waters);
    }

    private void print(int[] heights, int[] waters) {
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            max = Math.max(max, heights[i] + waters[i]);
        }
        List<List<Character>> matrix = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            List<Character> line = new ArrayList<>();
            for (int j = 0; j < heights[i]; j++)
                line.add('+');
            for (int j = 0; j < waters[i]; j++)
                line.add('W');
            for (int j = line.size(); j < max; j++)
                line.add(' ');
            matrix.add(line);
        }
        for (int j = max - 1; j >= 0; j--) {
            for (int i = 0; i < matrix.size(); i++) {
                System.out.print(matrix.get(i).get(j));
            }
            System.out.println();
        }
        System.out.println();
    }

    public final static void main(String[] args) {
        WaterDrop waterDrop = new WaterDrop();
        waterDrop.dropWater(new int[]{5,4,2,1,2,3,2,1,0,1,2,4}, 8, 5);
        waterDrop.dropWater(new int[]{5,4,2,1,2,3,2,1,0,1,2,4}, 5, 5);
        waterDrop.dropWater(new int[]{5,4,2,1,2,3,2,1,0,1,2,4}, 10, 5);
        waterDrop.dropWater(new int[]{5,4,2,1,2,3,2,1,0,1,2,4}, 1, 5);
        waterDrop.dropWater(new int[]{5,4,2,1,2,3,2,1,0,1,2,4}, 14, 5);
        System.out.println();
        waterDrop.dropWater(new int[]{1,1,2,1,2,3,2,1,0,1,2,0}, 1, 5);
        waterDrop.dropWater(new int[]{1,1,2,1,2,3,2,1,0,1,2,0}, 3, 5);
        waterDrop.dropWater(new int[]{1,1,2,1,2,3,2,1,0,1,2,0}, 6, 5);
        waterDrop.dropWater(new int[]{1,1,2,1,2,3,2,1,0,1,2,0}, 8, 5);
        waterDrop.dropWater(new int[]{1,1,2,1,2,3,2,1,0,1,2,0}, 9, 5);
        waterDrop.dropWater(new int[]{1,1,2,1,2,3,2,1,0,1,2,0}, 10, 5);
        waterDrop.dropWater(new int[]{1,1,2,1,2,3,2,1,0,1,2,0}, 12, 5);
        waterDrop.dropWater(new int[]{1,1,2,1,2,3,2,1,0,1,2,0}, 14, 5);
    }

}


