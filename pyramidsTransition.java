import java.util.*;

class Solution {
    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (String a: allowed) {
            if (!graph.containsKey(a.substring(0, 2)))
                graph.put(a.substring(0, 2), new HashSet<>());
            graph.get(a.substring(0, 2)).add(a.substring(2));
        }
    
        return search(bottom, graph);
    }
    
    private void getCandidates(String bottom, int idx, Map<String, Set<String>> graph, String current, Set<String> candidates) {
        if (idx == bottom.length() - 1) {
            candidates.add(current);
            return;
        }
        Set<String> values = graph.get(bottom.substring(idx, idx + 2));
        if (values == null) return;
        for (String v: values) {
            getCandidates(bottom, idx + 1, graph, current + v, candidates);
        }
    }
    
    private boolean search(String bottom, Map<String, Set<String>> graph) {
        if (bottom.length() == 1) return true;
        if (bottom.length() == 0) return false;
        
        Set<String> candidates = new HashSet<>();
        getCandidates(bottom, 0, graph, "", candidates);
        for (String c: candidates) if (search(c, graph))
            return true;

        return false;
    }
}


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

