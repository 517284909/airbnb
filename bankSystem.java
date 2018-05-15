import java.util.*;

class Solution {

    class Transaction {
        int id, timestamp, amount, balance;
        String type;
        Transaction(int id, int timestamp, int amount, int balance, String type) {
            this.id = id;
            this.timestamp = timestamp;
            this.amount = amount;
            this.type = type;
            if (this.type.equals("withdraw")) {
                this.balance = balance - this.amount;
            } else {
                this.balance = balance + this.amount;
            }
            System.out.println("balance @ " + this.timestamp + ": " + this.balance);
        }
    }

    private Map<Integer, List<Transaction>> records;

    public Solution() {
        records = new HashMap<>();
    }

    public void deposite(int id, int timestamp, int amount) {
        if (!records.containsKey(id))
            records.put(id, new ArrayList<>());
        int balance = 0;
        if (records.get(id).size() > 0)
            balance = records.get(id).get(records.get(id).size() - 1).balance;
        records.get(id).add(new Transaction(id, timestamp, amount, balance, "deposite"));
    }

    public void withdraw(int id, int timestamp, int amount) {
        if (!records.containsKey(id))
            records.put(id, new ArrayList<>());
        int balance = 0;
        if (records.get(id).size() > 0)
            balance = records.get(id).get(records.get(id).size() - 1).balance;
        records.get(id).add(new Transaction(id, timestamp, amount, balance, "withdraw"));
    }

    public int getBalance(int id, int startTime, int endTime) {
        if (!records.containsKey(id)) return -1;
        if (endTime <= startTime) return -1;

        List<Transaction> transaction_list = records.get(id);
        int idx_s = firstLarger(startTime, transaction_list);
        if (idx_s == -1) return -1;
        int idx_e = lastSmaller(endTime, transaction_list);
        if (idx_e == -1) return -1;
        return transaction_list.get(idx_e).balance - transaction_list.get(idx_s).balance;
    }

    private int firstLarger(int time, List<Transaction> transaction_list) {
        int l = 0, r = transaction_list.size() - 1;
        while (l + 1 < r) {
            int p = l + (r - l) / 2;
            Transaction t = transaction_list.get(p);
            if (t.timestamp < time) {
                l = p;
            } else {
                r = p;
            }
        }
        if (transaction_list.get(l).timestamp > time)
            return l;
        if (transaction_list.get(r).timestamp > time)
            return r;
        return -1;
    }

    private int lastSmaller(int time, List<Transaction> transaction_list) {
        int l = 0, r = transaction_list.size() - 1;
        while (l + 1 < r) {
            int p = l + (r - l) / 2;
            Transaction t = transaction_list.get(p);
            if (t.timestamp <= time) {
                l = p;
            } else {
                r = p;
            }
        }
        if (transaction_list.get(r).timestamp <= time)
            return r;
        if (transaction_list.get(l).timestamp <= time)
            return l;
        return -1;
    }

    public final static void main(String[] args) {
        Solution s = new Solution();
        s.deposite(1, 0, 100);
        s.deposite(1, 10, 200);
        s.deposite(1, 100, 300);
        s.withdraw(1, 110, 250);
        s.deposite(1, 120, 400);
        s.deposite(1, 130, 500);
        System.out.println("From 0 to 120: " + s.getBalance(1, 0, 120));
        System.out.println("From 0 to 125: " + s.getBalance(1, 0, 125));
        System.out.println("From 0 to 130: " + s.getBalance(1, 0, 130));
        System.out.println("From -10 to 130: " + s.getBalance(1, -10, 130));
        System.out.println("From 9 to 130: " + s.getBalance(1, 9, 130));

    }
}




















