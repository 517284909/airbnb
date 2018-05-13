import java.util.*;


class Solution {
    private Iterator<List<Integer>> rowIter;
    private Iterator<Integer> colIter;

    public Solution(List<List<Integer>> vecs) {
        rowIter = vecs.iterator();
        colIter = null;
    }

    public int next() {
        return colIter.next();
    }

    public boolean hasNext() {
        while (rowIter.hasNext() && (colIter == null || !colIter.hasNext())) {
            colIter = rowIter.next().iterator();
        }
        return colIter != null && colIter.hasNext();
    }

    public void remove() {
        if (colIter != null) {
            colIter.remove();
        }
    }

    public final static void main(String[] args) {
        List<List<Integer>> vectors = new ArrayList<>();
        List<Integer> row0 = new ArrayList<>();
        vectors.add(row0);
        vectors.add(new ArrayList<>(row0));
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(2);
        row1.add(3);
        vectors.add(row1);
        List<Integer> row2 = new ArrayList<>();
        row1.add(4);
        row1.add(5);
        vectors.add(row2);

        Solution solution = new Solution(vectors);

        System.out.println(solution.hasNext());
        System.out.println(solution.next());
        solution.remove();
        System.out.println(solution.hasNext());
        System.out.println(solution.next());
        solution.remove();
        System.out.println(solution.hasNext());
        System.out.println(solution.next());
        solution.remove();
        System.out.println(solution.hasNext());
        System.out.println(solution.next());
        solution.remove();
        System.out.println(solution.hasNext());
        System.out.println(solution.next());
        solution.remove();
        System.out.println(solution.hasNext());

        for (int i = 0; i < vectors.size(); i++) {
            for (int j = 0; j < vectors.get(i).size(); j++) {
                System.out.print(vectors.get(i).get(j));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
