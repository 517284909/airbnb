import java.util.*;


class Price {
    double diff;
    int idx;
    Price(double d, int i) {
        diff = d;
        idx = i;
    }
}

class RoundPrices {
    public int[] roundPrices(double[] prices) {
        double sum = 0;
        int floor_sum = 0;
        for (double p: prices) {
            sum += p;
            floor_sum += (int)p;
        }
        int round_sum = (int)Math.round(sum);
        int diff = round_sum - floor_sum;
        PriorityQueue<Price> max_heap = new PriorityQueue<>(diff, new Comparator<Price>() {
            public int compare(Price a, Price b) {
                if (a.diff <= b.diff) {
                    return 1;
                }
                return -1;
            }
        });
        int[] results = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            max_heap.add(new Price(prices[i] - (int)prices[i], i));
            results[i] = (int)prices[i];
        }
        while (diff > 0) {
            Price top = max_heap.poll();
            results[top.idx]++;
            --diff;
        }
        return results;
    }

    public final static void main(String[] args) {
        RoundPrices roundPrices = new RoundPrices();
        System.out.println(Arrays.toString(roundPrices.roundPrices(new double[]{1.2, 2.3, 3.4})));
        System.out.println(Arrays.toString(roundPrices.roundPrices(new double[]{1.2, 2.5, 3.6, 4.0})));
    }
}

