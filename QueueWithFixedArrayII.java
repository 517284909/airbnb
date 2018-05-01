import java.util.*;

class QueueWithFixedArrayII {

    class Node {
        int[] queue;
        int size;
        int head, tail;
        Node next;

        Node(int s) {
            size = s;
            head = 0;
            tail = 0;
            queue = new int[size];
            next = null;
        }

        boolean isEmpty() {
            return head == size;
        }

        boolean isFull() {
            return tail == size;
        }

        void add(int v) {
            queue[tail++] = v;
        }

        int pop() {
            return queue[head++];
        }
    }

    private Node head, tail;
    private int unitSize;

    public QueueWithFixedArrayII(int unitSize) {
        this.unitSize = unitSize;
        head = new Node(this.unitSize);
        tail = head;
    }

    public void offer(int value) {
        if (tail.isFull()) {
            tail.next = new Node(this.unitSize);
            tail = tail.next;
        }
        tail.add(value);
    }

    public int poll() {
        if (head.isEmpty()) {
            head = head.next;
        }
        return head.pop();
    }

    public boolean isEmpty() {
        return head == tail && head.head == head.tail;
    }

    public final static void main(String[] args) {
        QueueWithFixedArrayII queue = new QueueWithFixedArrayII(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);
        queue.offer(9);
        queue.offer(10);
        queue.offer(11);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.isEmpty());

        queue.offer(12);
        System.out.println(queue.poll());
        System.out.println(queue.isEmpty());
    }
}

