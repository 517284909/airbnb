import java.util.*;

class IP {

    private String longToIP(long x) {
        return String.format("%s.%s.%s.%s",
            x >> 24, (x >> 16) % 256, (x >> 8) % 256, x % 256);
    }

    private long String2Int(String ip) {
        String[] tokens = ip.trim().split("\\.");
        long value = Long.parseLong(tokens[0]) << 24;
        value += Long.parseLong(tokens[1]) << 16;
        value += Long.parseLong(tokens[2]) << 8;
        value += Long.parseLong(tokens[3]);
        return value;
    }

    private int lowestOneBit(long ip) {
        if (ip == 0)
            return 32;
        int p = 0;
        while ((ip & 1) == 0) {
            ip >>= 1;
            ++p;
        }
        return p;
    }

    private int highestOneBit(long ip) {
        int p = -1;
        while (ip > 0) {
            ip >>= 1;
            ++p;
        }
        return p;
    }

    public List<String> ipRange2CIDR(String ip, int n) {
        List<String> results = new ArrayList<>();
        long start = String2Int(ip);
        while (n > 0) {
            int p = Math.min(lowestOneBit(start), highestOneBit(n));
            results.add(longToIP(start) + "/" + Integer.toString(32 - p));
            n -= (1 << p);
            start += (1 << p);
        }

        return results;
    }

    public List<String> ipToCIDR(String ip, int n) {
        long start = ipToLong(ip);
        List<String> ans = new ArrayList();
        while (n > 0) {
            int mask = Math.max(33 - bitLength(Long.lowestOneBit(start)),
                                33 - bitLength(n));
            ans.add(longToIP(start) + "/" + mask);
            start += 1 << (32 - mask);
            n -= 1 << (32 - mask);
        }
        return ans;
    }

    private long ipToLong(String ip) {
        long ans = 0;
        for (String x: ip.split("\\.")) {
            ans = 256 * ans + Integer.valueOf(x);
        }
        return ans;
    }
    private String longToIP(long x) {
        return String.format("%s.%s.%s.%s",
            x >> 24, (x >> 16) % 256, (x >> 8) % 256, x % 256);
    }
    private int bitLength(long x) {
        if (x == 0) return 1;
        int ans = 0;
        while (x > 0) {
            x >>= 1;
            ans++;
        }
        return ans;
    }


    public final static void main(String[] args) {
        IP s = new IP();
        System.out.println(s.ipRange2CIDR("1.1.1.0", 4));
        System.out.println(s.ipToCIDR("1.1.1.0", 4));
        System.out.println(s.ipRange2CIDR("1.1.1.1", 4));
        System.out.println(s.ipToCIDR("1.1.1.1", 4));
        System.out.println(s.ipRange2CIDR("1.1.1.111", 10));
        System.out.println(s.ipToCIDR("1.1.1.111", 10));
        System.out.println(s.ipToCIDR("255.0.0.7", 10));
    }
}
