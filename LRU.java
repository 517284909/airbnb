public class LRUCache {
    class Node {
        int key, value;
        Node next, pre;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.pre = null;
        }
    }
    
    private int capacity;
    private Node head, tail;
    private Map<Integer, Node> mapping;
    
    public LRUCache(int capacity) {
        this.mapping = new HashMap<>();
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (!mapping.containsKey(key)) return -1;
        Node curr = mapping.get(key);
        curr.pre.next = curr.next;
        curr.next.pre = curr.pre;
        
        tail.pre.next = curr;
        curr.pre = tail.pre;
        curr.next = tail;
        tail.pre = curr;
        return curr.value;
    }

    public void put(int key, int value) {
        if (mapping.containsKey(key)) {
            get(key);
            tail.pre.value = value;
        } else {
            if (mapping.size() == capacity) {
                mapping.remove(head.next.key);
                head.next.next.pre = head;
                head.next = head.next.next;
            }
            Node n = new Node(key, value);
            n.next = tail;
            n.pre = tail.pre;
            tail.pre.next = n;
            tail.pre = n;
            mapping.put(key, n);
        }
    }
}