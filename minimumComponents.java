import java.util.*;

class MinimumComponents {

    public List<Integer> minimumComponents(List<List<Integer>> graph, int n) {
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            search(i, visited, graph);
            visited[i] = false;
        }
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < n; i++)
            if (!visited[i])
                results.add(i);

        return results;
    }

    private void search(int node, boolean[] visited, List<List<Integer>> graph) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;
        List<Integer> neighbors = graph.get(node);
        if (neighbors == null) {
            return;
        }
        for (int neighbor: neighbors) {
            search(neighbor, visited, graph);
        }
    }

    public final static void main(String[] args) {
        MinimumComponents minimumComponents = new MinimumComponents();
        {
            List<List<Integer>> graph = new ArrayList<>();
            List<Integer> e1 = new ArrayList<>();
            e1.add(1);
            List<Integer> e2 = new ArrayList<>();
            e2.add(2);
            List<Integer> e3 = new ArrayList<>();
            e3.add(0);
            graph.add(e1);
            graph.add(e2);
            graph.add(e3);

            System.out.println(minimumComponents.minimumComponents(graph, 3));
        }

        {
            List<List<Integer>> graph = new ArrayList<>();
            List<Integer> e0 = null;
            List<Integer> e1 = new ArrayList<>();
            e1.add(0);
            e1.add(2);
            List<Integer> e2 = new ArrayList<>();
            List<Integer> e3 = new ArrayList<>();
            e3.add(1);
            e3.add(5);
            List<Integer> e5 = new ArrayList<>();
            e5.add(4);
            e5.add(6);
            List<Integer> e4 = new ArrayList<>();
            List<Integer> e6 = new ArrayList<>();
            graph.add(e0);
            graph.add(e1);
            graph.add(e2);
            graph.add(e3);
            graph.add(e4);
            graph.add(e5);
            graph.add(e6);

            System.out.println(minimumComponents.minimumComponents(graph, 7));
        }

        {
            List<List<Integer>> graph = new ArrayList<>();
            List<Integer> e0 = null;
            List<Integer> e1 = new ArrayList<>();
            e1.add(0);
            e1.add(2);
            List<Integer> e2 = new ArrayList<>();
            List<Integer> e3 = new ArrayList<>();
            e3.add(1);
            e3.add(5);
            List<Integer> e5 = new ArrayList<>();
            e5.add(4);
            e5.add(6);
            List<Integer> e4 = new ArrayList<>();
            List<Integer> e6 = new ArrayList<>();
            graph.add(e0);
            graph.add(e1);
            graph.add(e2);
            graph.add(e3);
            graph.add(e4);
            graph.add(e5);
            graph.add(e6);
            graph.add(null);

            System.out.println(minimumComponents.minimumComponents(graph, 8));
        }

        {
            List<List<Integer>> graph = new ArrayList<>();
            List<Integer> e0 = null;
            List<Integer> e1 = new ArrayList<>();
            e1.add(0);
            e1.add(2);
            e1.add(4);
            e1.add(5);
            List<Integer> e2 = new ArrayList<>();
            List<Integer> e3 = new ArrayList<>();
            e3.add(1);
            e3.add(5);
            List<Integer> e5 = new ArrayList<>();
            e5.add(4);
            e5.add(6);
            List<Integer> e4 = new ArrayList<>();
            List<Integer> e6 = new ArrayList<>();
            graph.add(e0);
            graph.add(e1);
            graph.add(e2);
            graph.add(e3);
            graph.add(e4);
            graph.add(e5);
            graph.add(e6);
            List<Integer> e7 = new ArrayList<>();
            e7.add(1);
            e7.add(5);
            e7.add(4);
            graph.add(e7);

            System.out.println(minimumComponents.minimumComponents(graph, 8));
        }


    }
}



