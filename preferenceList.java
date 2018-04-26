import java.util.*;

class PreferenceList {

    // 输出按照输入的优先次序
    public List<Integer> getPreferenceListOrder(List<List<Integer>> preferences) {
        Map<Integer, Integer> priority = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> degrees = new HashMap<>();
        for (int j = 0; j < preferences.size(); j++) {
            List<Integer> p = preferences.get(j);
            for (int i = 0; i < p.size() - 1; i++) {
                int from = p.get(i);
                int to = p.get(i + 1);
                if (!graph.containsKey(from)) {
                    graph.put(from, new ArrayList<Integer>());
                    degrees.put(from, 0);
                }
                if (!graph.containsKey(to)) {
                    graph.put(to, new ArrayList<Integer>());
                    degrees.put(to, 0);
                }
                graph.get(from).add(to);
                degrees.put(to, degrees.get(to) + 1);

                if (!priority.containsKey(from)) {
                    priority.put(from, j);
                }
                if (!priority.containsKey(to)) {
                    priority.put(to, j);
                }
            }
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return priority.get(a) - priority.get(b);
            }
        });
        for (Map.Entry<Integer, Integer> entry: degrees.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }
        List<Integer> results = new LinkedList<>();
        while (!queue.isEmpty()) {
            Integer top = queue.poll();
            results.add(top);
            List<Integer> neighbors = graph.get(top);
            for (Integer n: neighbors) {
                degrees.put(n, degrees.get(n) - 1);
                if (degrees.get(n) == 0) {
                    queue.add(n);
                }
            }
        }

        return results;
    }

    public List<Integer> getPreferenceList(List<List<Integer>> preferences) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> degrees = new HashMap<>();
        for (List<Integer> p: preferences) {
            for (int i = 0; i < p.size() - 1; i++) {
                int from = p.get(i);
                int to = p.get(i + 1);
                if (!graph.containsKey(from)) {
                    graph.put(from, new ArrayList<Integer>());
                    degrees.put(from, 0);
                }
                if (!graph.containsKey(to)) {
                    graph.put(to, new ArrayList<Integer>());
                    degrees.put(to, 0);
                }
                graph.get(from).add(to);
                degrees.put(to, degrees.get(to) + 1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Integer> entry: degrees.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        List<Integer> results = new LinkedList<>();
        while (!queue.isEmpty()) {
            int top = queue.poll();
            results.add(top);
            List<Integer> neighbors = graph.get(top);
            for (Integer n: neighbors) {
                degrees.put(n, degrees.get(n) - 1);
                if (degrees.get(n) == 0) {
                    queue.add(n);
                }
            }
        }

        return results;
    }

    public final static void main(String[] args) {
        PreferenceList preferenceList = new PreferenceList();
        List<List<Integer>> preferences = new ArrayList<>();
        preferences.add(Arrays.asList(new Integer[]{3, 5, 7, 9}));
        preferences.add(Arrays.asList(new Integer[]{2, 3, 8}));
        preferences.add(Arrays.asList(new Integer[]{5, 8}));
        System.out.println(preferenceList.getPreferenceList(preferences));
        System.out.println(preferenceList.getPreferenceListOrder(preferences));
    }

}


