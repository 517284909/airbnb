import java.util.*;

class Wizards {
    class Wizard {
        int idx, distance, parent;
        Wizard(int i, int d, int p) {
            idx = i;
            distance = d;
            parent = p;
        }
    }

    public List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target) {
        int n = wizards.size();
        Map<Integer, Wizard> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new Wizard(i, Integer.MAX_VALUE, -1));
        }

        PriorityQueue<Wizard> queue = new PriorityQueue<Wizard>(new Comparator<Wizard>() {
            public int compare(Wizard a, Wizard b) {
                return a.distance - b.distance;
            }
        });
        queue.add(new Wizard(source, 0, source));
        while (!queue.isEmpty()) {
            Wizard top = queue.poll();
            List<Integer> neighbors = wizards.get(top.idx);
            for (int i = 0; i < neighbors.size(); i++) {
                Wizard neighbor = graph.get(neighbors.get(i));
                int curr_distance = top.distance + (int)Math.pow(top.idx - neighbor.idx, 2);
                if (curr_distance < neighbor.distance) {
                    queue.remove(neighbor);
                    neighbor.distance = curr_distance;
                    neighbor.parent = top.idx;
                    queue.add(neighbor);
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        if (graph.get(target).parent == -1) {
            return path;
        }
        int runner = target;
        while (runner != source) {
            path.add(runner);
            runner = graph.get(runner).parent;
        }
        path.add(source);
        Collections.reverse(path);
        return path;
    }

    public final static void main(String[] args) {
        Wizards wizards = new Wizards();
        {
            List<List<Integer>> graph = new ArrayList<>();
            graph.add(Arrays.asList(new Integer[]{1, 2, 4}));
            graph.add(Arrays.asList(new Integer[]{3}));
            graph.add(Arrays.asList(new Integer[]{3, 4}));
            graph.add(Arrays.asList(new Integer[]{1, 4}));
            graph.add(Arrays.asList(new Integer[]{3}));
            System.out.println(wizards.getShortestPath(graph, 0, 4));
        }
        {
            List<List<Integer>> graph = new ArrayList<>();
            graph.add(Arrays.asList(new Integer[]{1, 2, 3, 4}));
            graph.add(Arrays.asList(new Integer[]{0, 2, 3, 4}));
            graph.add(Arrays.asList(new Integer[]{0, 1, 3, 4}));
            graph.add(Arrays.asList(new Integer[]{0, 1, 2, 4}));
            graph.add(Arrays.asList(new Integer[]{0, 1, 2, 3}));
            System.out.println(wizards.getShortestPath(graph, 0, 4));
        }

    }
}

