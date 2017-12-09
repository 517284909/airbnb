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
        text = text.toLowerCase();
        List<String> results = new ArrayList<>();
        results.add(text);
        for (int i = 0; i < text.length(); i++) {
            int size = results.size();
            for (int j = 0; j < size; j++) {
                StringBuilder t = new StringBuilder(results.get(j));
                t.setCharAt(i, Character.toUpperCase(results.get(j).charAt(i)));
                results.add(t.toString());
            }
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

