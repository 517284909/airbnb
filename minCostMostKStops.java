import java.util.*;

class MinCostMostKStops {
    class Flight {
        String name;
        int cost;
        Flight(String name, int cost) {
            this.name = name;
            this.cost = cost;
        }
    }

    public int minCostMostKStops(List<String> lines, String source, String target, int k) {
        // Build graph
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        for (String line: lines) {
            String[] s = line.trim().split(",");
            String[] t = s[0].trim().split("->");
            String from = t[0].trim();
            String to = t[1].trim();
            int price = Integer.parseInt(s[1]);
            if (!graph.containsKey(from)) {
                graph.put(from, new HashMap<String, Integer>());
            }
            Map<String, Integer> details = graph.get(from);
            details.put(to, price);
        }

        // Traverse
        Queue<Flight> queue = new LinkedList<>();
        queue.add(new Flight(source, 0));
        int min = Integer.MAX_VALUE, stop = 0;
        while (!queue.isEmpty()) {
            if (stop++ > k) {
                break;
            }
            int sz = queue.size();
            int j = 0;
            while (j++ < sz) {
                Flight next = queue.poll();
                if (next.name.equals(target)) {
                    min = Math.min(min, next.cost);
                    continue;
                }
                Map<String, Integer> neighbors = graph.get(next.name);
                if (neighbors == null) {
                    continue;
                }
                for (Map.Entry<String, Integer> e: neighbors.entrySet()) {
                    queue.add(new Flight(e.getKey(), e.getValue() + next.cost));
                }
            }
        }

        return min;
    }

    public final static void main(String[] args) {
        MinCostMostKStops minCostMostKStops = new MinCostMostKStops();
        
        List<String> lines = new ArrayList<>();
        lines.add("A->B,100");
        lines.add("A->C,50");
        lines.add("B->C,60");
        lines.add("C->F,200");
        lines.add("B->F,200");
        lines.add("B->D,5");
        lines.add("B->E,10");
        lines.add("D->E,1");
        lines.add("E->F,5");
        System.out.println(minCostMostKStops.minCostMostKStops(lines, "A", "F", 3));
    }

}


