import java.util.*;

class CaseCombination {
    // Recursion
    public List<String> combination(String text) {
        text = text.toLowerCase();
        List<String> results = new ArrayList<>();
        combination(text, new String(), results);
        return results;
    }

    private void combination(String text, String current, List<String> results) {
        if (current.length() == text.length()) {
            results.add(current);
            return;
        }

        int idx = current.length();
        combination(text, current + text.charAt(idx), results);
        combination(text, current + Character.toUpperCase(text.charAt(idx)), results);
    }

    // Iteration
    public List<String> combinationIter(String text) {
        List<String> results = new ArrayList<>();
        results.add("");
        for (int i = 0; i < text.length(); i++) {
            List<String> next_results = new ArrayList<>();
            for (int j = 0; j < results.size(); j++) {
                String tmp = results.get(j);
                next_results.add(tmp + Character.toLowerCase(text.charAt(i)));
                next_results.add(tmp + Character.toUpperCase(text.charAt(i)));
            }
            results = next_results;
        }
        return results;
    }

    public final static void main(String[] args) {
        CaseCombination caseCombination = new CaseCombination();
        System.out.println(caseCombination.combination("abc"));
        System.out.println(caseCombination.combination(""));
        System.out.println(caseCombination.combinationIter("abc"));
        System.out.println(caseCombination.combinationIter(""));
    }
}

