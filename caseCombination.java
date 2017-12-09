import java.util.*;

class CaseCombination {
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

    public final static void main(String[] args) {
        CaseCombination caseCombination = new CaseCombination();
        System.out.println(caseCombination.combination("abc"));
        System.out.println(caseCombination.combination(""));
    }
}

