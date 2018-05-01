import java.util.*;

class GuessNumber {

    private Random random;
    private int[] target;
    private int step;

    public GuessNumber() {
        random = new Random();
        target = new int[4];
        for (int i = 0; i < 4; i++) {
            target[i] = random.nextInt(10);
            System.out.println(target[i]);
        }
        step = 0;
    }

    class Status {
        int[] value;
        int idx;
        int correct;

        Status(int[] v, int i, int c) {
            value = v;
            idx = i;
            correct = c;
        }

        String toValueString() {
            StringBuilder builder = new StringBuilder();
            for (int v: value)
                builder.append(v).append(" ");
            return builder.toString().trim();
        }
    }

    public void guess() {
        // Get candidate digits
        List<Integer> candidates = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            int[] guess = new int[]{i, i, i, i};
            int t = check(guess);
            for (int j = 0; j < t; j++)
                candidates.add(i);
            if (candidates.size() == 4)
                break;
        }

        int c = candidates.get(0);
        int q = check(new int[]{c, c, c, c});
        Status status = new Status(new int[]{c, c, c, c}, 0, q);
        while (true) {
            int correct = status.correct;
            System.out.println(status.toValueString());
            System.out.println(correct);

            if (correct == 4) {
                break;
            }

            int[] value = status.value.clone();
            int idx = status.idx;
            for (int i = 0; i < candidates.size(); i++) {
                if (candidates.get(i) == status.value[status.idx]) {
                    continue;
                }
                value[idx] = candidates.get(i);
                int t = check(value);
                if (t > correct) {
                    status = new Status(value, idx + 1, t);
                    candidates.remove(i);
                    break;
                }
                if (t < correct) {
                    status.idx++;
                    candidates.remove(new Integer(status.value[status.idx]));
                    break;
                }
            }
        }

        System.out.print("In TOTAL: ");
        System.out.println(step);
    }

    private int check(int[] guess) {
        int cnt = 0;
        for (int i = 0; i < 4; i++)
            if (guess[i] == target[i])
                ++cnt;
        ++step;
        return cnt;
    }

    public final static void main(String[] args) {
        GuessNumber gn = new GuessNumber();
        gn.guess();
    }
}

