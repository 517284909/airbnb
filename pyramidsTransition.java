import java.util.*;

class PyramidsTransition {
    private Map<String, Set<Character>> map;
    private Map<String, Boolean> cache;

    public PyramidsTransition(List<String> lines) {
        cache = new HashMap<>();
        map = new HashMap<>();
        for (String line: lines) {
            String[] t = line.trim().split(",");
            String key = t[0] + "#" + t[1];
            map.put(key, new HashSet<Character>());
            for (char ch: t[2].toCharArray()) {
                map.get(key).add(ch);
            }
        }
    }

    public boolean check(String text) {
        if (cache.containsKey(text)) {
            return cache.get(text);
        }
        if (text.length() == 0) {
            return false;
        }
        if (text.length() == 1) {
            return true;
        }

        List<String> candidates = new ArrayList<>();
        getCandidates(text, 0, new String(), candidates);
        for (String c: candidates) {
            if (check(c)) {
                cache.put(c, true);
                return true;
            }
        }
        cache.put(text, false);
        return false;
    }

    private void getCandidates(String text, int idx, String current, List<String> candidates) {
        if (idx == text.length() - 1) {
            candidates.add(current);
            return;
        }

        String key = text.charAt(idx) + "#" + text.charAt(idx + 1);
        if (!map.containsKey(key)) {
            return;
        }
        Set<Character> value = map.get(key);
        for (char ch: value) {
            getCandidates(text, idx + 1, current + ch, candidates);
        }
    }

    public final static void main(String[] args) {
        List<String> lines = new ArrayList<>();
        lines.add("A,A,B");
        lines.add("A,B,AC");
        lines.add("A,C,D");
        lines.add("A,D,A");
        lines.add("B,A,D");
        lines.add("B,B,BC");
        lines.add("B,C,A");
        lines.add("C,D,B");
        PyramidsTransition transition = new PyramidsTransition(lines);
        System.out.println(transition.check("ABCD"));
        System.out.println(transition.check("ACCD"));
        System.out.println(transition.check("CCDC"));
        System.out.println(transition.check("AD"));
        System.out.println(transition.check("ADDA"));
        System.out.println(transition.check(""));
    }
}

