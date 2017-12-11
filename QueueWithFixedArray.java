import java.util.*;

class QueueWithFixedArray {
    private int fixedSize;
    private int count;
    private int head;
    private int tail;

    private List<Object> headList;
    private List<Object> tailList;

    public QueueWithFixedArray(int n) {
        fixedSize = n;
        headList = new LinkedList<>();
        tailList = headList;
        head = 0;
        tail = 0;
    }

    public void offer(int num) {
        if (tail == fixedSize - 1) {
            List<Object> new_list = new LinkedList<>();
            tailList.add(new_list);
            tailList = new_list;
            tail = 0;
        }
        tailList.add(num);
        ++tail;
        ++count;
    }

    public Integer poll() {
        if (head == fixedSize - 1) {
            headList = (List<Object>)headList.get(head);
            head = 0;
        }
        if (headList == tailList && head == tail) {
            assert count != 0;
            return null;
        }
        Integer ret = (Integer)headList.get(head);
        --count;
        ++head;
        return ret;
    }

    public final static void main(String[] args) {
        QueueWithFixedArray queue = new QueueWithFixedArray(5);
        for (int i = 0; i < 20; i++) {
            queue.offer(i);
        }

        for (int i = 0; i < 15; i++) {
            System.err.print(queue.poll());
            System.err.print(" ");
        }

        for (int i = 20; i < 30; i++) {
            queue.offer(i);
        }

        for (int i = 15; i < 35; i++) {
            System.err.print(queue.poll());
            System.err.print(" ");
        }
        System.err.println();
    }

}
