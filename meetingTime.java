import java.util.*;

class Interval {
    int start, end;
    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class MeetingTime {
    public List<Interval> getAvailableIntervals(List<List<Interval>> intervals, int k) {
        int n = intervals.size();
        List<Integer> time_list = new ArrayList<>();
        for (int i = 0; i < intervals.size(); i++) {
            for (int j = 0; j < intervals.get(i).size(); j++) {
                time_list.add(intervals.get(i).get(j).start);
                time_list.add(-intervals.get(i).get(j).end);
            }
        }
        Collections.sort(time_list, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                if (a >= 0 && b >= 0 || a < 0 && b < 0) {
                    return Math.abs(a) - Math.abs(b);
                } else {
                    if (Math.abs(a) == Math.abs(b)) {
                        return b - a;
                    } else {
                        return Math.abs(a) - Math.abs(b);
                    }
                }
            }
        });

        List<Interval> idle_list = new ArrayList<>();
        int start = 0, end = 0, busy = 0; // n - busy >= k => "busy <= n - k"
        boolean isOpen = false;
        for (int i = 0; i < time_list.size(); i++) {
            if (time_list.get(i) >= 0) {
                ++busy;
            } else {
                --busy;
            }
            if (busy <= n - k) {
                if (!isOpen) {
                    start = Math.abs(time_list.get(i));
                    isOpen = true;
                }
            } else {
                if (isOpen) {
                    end = Math.abs(time_list.get(i));
                    idle_list.add(new Interval(start, end));
                    isOpen = false;
                }
            }
        }

        return idle_list;
    }

    public final static void main(String[] args) {
        MeetingTime meetingTime = new MeetingTime();

        List<List<Interval>> intervals = new ArrayList<>();
        List<Interval> a = new ArrayList<>();
        a.add(new Interval(1, 3));
        a.add(new Interval(6, 7));
        intervals.add(a);
        List<Interval> b = new ArrayList<>();
        b.add(new Interval(2, 4));
        intervals.add(b);
        List<Interval> c = new ArrayList<>();
        c.add(new Interval(2, 3));
        c.add(new Interval(9, 12));
        intervals.add(c);

        List<Interval> idle_list = meetingTime.getAvailableIntervals(intervals, 3);
        for (Interval slot: idle_list) {
            System.out.println(slot.start + " " + slot.end);
        }
    }

}

