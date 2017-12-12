import java.util.*;


class UnionFind {
    private int[] fathers;
    private int cnt;

    public UnionFind(int n) {
        fathers = new int[n];
        cnt = n;
        for (int i = 0; i < cnt; i++) {
            fathers[i] = i;
        }
    }

    public int find(int x) {
        if (fathers[x] == x) {
            return fathers[x];
        }
        return fathers[x] = find(fathers[x]);
    }

    public void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            fathers[fx] = fy;
            --cnt;
        }
    }

    public int getCount() {
        return cnt;
    }
}

class IntersectedRectangles {
    private boolean intersect(int[][] r0, int[][] r1) {
        return !(r0[1][0] < r1[0][0] || r0[0][0] > r1[1][0] || r0[0][1] > r1[1][1] || r0[1][1] < r1[0][1]);
    }

    public int countIntersectedRectangles(int[][][] rectangles) {
        UnionFind uf = new UnionFind(rectangles.length);
        for (int i = 0; i < rectangles.length; i++) {
            for (int j = i + 1; j < rectangles.length; j++) {
                if (intersect(rectangles[i], rectangles[j])) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }

    public final static void main(String[] args) {
        IntersectedRectangles intersectedRectangles = new IntersectedRectangles();
        {
            int[][][] rectangles = {{{0, 0}, {1, 2}}, {{1, 1}, {2, 2}}};
            System.out.println(intersectedRectangles.countIntersectedRectangles(rectangles));
        }
        {
            int[][][] rectangles = {{{0, 0}, {2, 2}}, {{3, 1}, {2, 3}}};
            System.out.println(intersectedRectangles.countIntersectedRectangles(rectangles));
        }

    }
}

