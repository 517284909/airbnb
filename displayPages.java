import java.util.*;

class DisplayPages {
    public List<String> displayPages(List<String> input, int pageSize) {
        List<String> results = new ArrayList<>();
        if (input == null || input.size() == 0) {
            return results;
        }

        while (true) {
            pick(input, pageSize, results);
            if (input.size() > 0) {
                results.add("");
            } else {
                break;
            }
        }
        return results;
    }

    private int pick(List<String> input, int pageSize, List<String> results) {
        Iterator<String> iter = input.iterator();
        Set<String> visited = new HashSet<>();
        int i = 0;
        while (i < pageSize && iter.hasNext()) {
            String line = iter.next();
            String hostID = line.split(",")[0];
            if (!visited.contains(hostID)) {
                ++i;
                visited.add(hostID);
                results.add(line);
                iter.remove();
            }
        }
        iter = input.iterator();
        while (i < pageSize && iter.hasNext()) {
            results.add(iter.next());
            iter.remove();
            ++i;
        }
        return i;
    } 

    public final static void main(String[] args) {
        DisplayPages displayPages = new DisplayPages();

        {
            List<String> input = new LinkedList<>();
            input.add("1,28,310.6,SF");
            input.add("4,5,204.1,SF");
            input.add("20,7,203.2,Oakland");
            input.add("6,8,202.2,SF");
            input.add("6,10,199.1,SF");
            input.add("1,16,190.4,SF");
            input.add("6,29,185.2,SF");
            input.add("7,20,180.1,SF");
            input.add("6,21,162.1,SF");
            input.add("2,18,161.2,SF");
            input.add("2,30,149.1,SF");
            input.add("3,76,146.2,SF");
            input.add("2,14,141.1,San Jose");
            System.out.println(displayPages.displayPages(input, 5));
        }
        {
            List<String> input = new LinkedList<>();
            input.add("1,28,310.6,SF");
            input.add("2,5,204.1,SF");
            input.add("3,7,203.2,Oakland");
            input.add("4,8,202.2,SF");
            input.add("1,10,199.1,SF");
            input.add("5,16,190.4,SF");
            input.add("1,29,185.2,SF");
            input.add("2,20,180.1,SF");
            input.add("3,21,162.1,SF");
            input.add("1,18,161.2,SF");
            input.add("3,30,149.1,SF");
            System.out.println(displayPages.displayPages(input, 5));
        }

    }
}

