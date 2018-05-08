import java.util.*;

class Solution {
    public int findCheapestPrice(int m, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] f: flights) {
            if (!graph.containsKey(f[0]))
                graph.put(f[0], new HashMap<>());
            graph.get(f[0]).put(f[1], f[2]);
        }
        
        //PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b)->(a[2] - b[2]));
        PriorityQueue<Station> pq = new PriorityQueue<Station>((a, b)->(a.cost - b.cost));

        //pq.add(new int[]{src, 0, 0});
        pq.add(new Station(src, 0, 0, new ArrayList<Integer>()));
        while (!pq.isEmpty()) {
            //int[] curr = pq.poll();
            Station curr = pq.poll();
            //int s = curr[0], k = curr[1], cost = curr[2];
            int s = curr.id, k = curr.k, cost = curr.cost;
            List<Integer> path = curr.path;
            if (k > K + 1) continue;
            if (s == dst) {
                System.out.println(path);
                return cost;
            }
            Map<Integer, Integer> neighbors = graph.get(s);
            if (neighbors == null) continue;
            for (Map.Entry<Integer, Integer> n: neighbors.entrySet()) {
                //pq.add(new int[]{n.getKey(), k + 1, n.getValue() + cost});
                pq.add(new Station(n.getKey(), k + 1, n.getValue() + cost, new ArrayList<Integer>(path)));
            }
        }
        return -1;
    }
    
    class Station {
        int id, k, cost;
        List<Integer> path;
        
        Station(int id, int k, int cost, List<Integer> path) {
            this.id = id;
            this.k = k;
            this.cost = cost;
            this.path = path;
            this.path.add(id);
        }
    }
}


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


