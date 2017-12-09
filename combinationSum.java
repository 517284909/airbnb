import java.util.*;

class CombinationSum {
    public List<List<Double>> combinationSum(double[] prices, double target) {
        List<List<Double>> results = new ArrayList<>();
        combinationSum(prices, 0, target, 0, new ArrayList<Double>(), results);
        return results;
    }

    private void combinationSum(double[] prices,int idx, double target, double sum, List<Double> current, List<List<Double>> results) {
        if (Math.abs(sum - target) < 1.0e-6) {
            results.add(new ArrayList<Double>(current));
            return;
        }
        if (idx == prices.length) {
            return;
        }
        for (int i = idx; i < prices.length; i++) {
            current.add(prices[i]);
            combinationSum(prices, i + 1, target, sum + prices[i], current, results);
            current.remove(current.size() - 1);
        }
    }

    public final static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        System.out.println(combinationSum.combinationSum(new double[]{1.53, 3.5, 1.542, 2.456}, 5.03));
        System.out.println(combinationSum.combinationSum(new double[]{1.53, 3.5, 1.542, 2.456}, 5.02));
        System.out.println(combinationSum.combinationSum(new double[]{1.53, 3.5, 1.542, 2.456, 1.0, 4.03, 0.9, 0.03, 4.1}, 5.03));
    }

}

