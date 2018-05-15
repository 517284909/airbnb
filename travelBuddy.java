import java.util.*;

class Solution {

    class Buddy {
        String name;
        int numCommon;
        Set<String> wishList;
        Buddy(String name, int numCommon, Set<String> wishList) {
            this.name = name;
            this.numCommon = numCommon;
            this.wishList = new HashSet<>(wishList);
        }
    }

    public List<Buddy> getTravelBuddies(Set<String> myWishList, Map<String, Set<String>> buddyWishList, double threshold) {
        List<Buddy> results = new ArrayList<>();
        for (Map.Entry<String, Set<String>> e: buddyWishList.entrySet()) {
            String name = e.getKey();
            Set<String> intersection = new HashSet<>(e.getValue());
            intersection.retainAll(myWishList);
            if ((double)intersection.size() >= (double)e.getValue().size() * threshold) {
                results.add(new Buddy(e.getKey(), intersection.size(), e.getValue()));
            }
        }
        Collections.sort(results, new Comparator<Buddy>() {
            public int compare(Buddy a, Buddy b) {
                return b.numCommon - a.numCommon;
            }
        });
        return results;
    }

    public List<String> recommondCities(Set<String> myWishList, Map<String, Set<String>> buddyWishList, double threshold, int k) {
        List<Buddy> travel_buddies = getTravelBuddies(myWishList, buddyWishList, threshold);
        List<String> results = new ArrayList<>();

        int i = 0;
        while (k > 0 && i < travel_buddies.size()) {
            Set<String> diff = new HashSet<>(travel_buddies.get(i).wishList);
            diff.removeAll(myWishList);
            if (diff.size() < k) {
                results.addAll(diff);
                k -= diff.size();
                ++i;
            } else {
                Iterator<String> iter = diff.iterator();
                while (k > 0) {
                    results.add(iter.next());
                    --k;
                }
            }
        }
        return results;
    }

    public final static void main(String[] args) {
        Solution s = new Solution();
        Set<String> myWishList = new HashSet<>();
        myWishList.add("Harbin");
        myWishList.add("Shenzhen");
        myWishList.add("Beijing");

        Map<String, Set<String>> buddyWishList = new HashMap<>();
        {
            Set<String> wishList = new HashSet<>();
            wishList.add("Harbin");
            wishList.add("Shenzhen");
            wishList.add("Dallas");
            buddyWishList.put("Aaron", wishList);
        }
        {
            Set<String> wishList = new HashSet<>();
            wishList.add("Harbin");
            wishList.add("Dallas");
            buddyWishList.put("Bob", wishList);
        }
        {
            Set<String> wishList = new HashSet<>();
            wishList.add("Harbin");
            wishList.add("Shenzhen");
            wishList.add("San Froncisco");
            wishList.add("San Jose");
            buddyWishList.put("Steve", wishList);
        }
        System.out.println(s.getTravelBuddies(myWishList, buddyWishList, 0.6));
        System.out.println(s.recommondCities(myWishList, buddyWishList, 0.55, 3));
    }
}

