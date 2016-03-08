package yodle;

import java.util.*;

public class Overlap {
    public static void main(String[] args) {
        List<Pair> pairs = new ArrayList<>();
        Scanner sc = new Scanner("(1:3),(2:3),(3:5),(7:10)").useDelimiter(",");
        while (sc.hasNext()) {
            String token = sc.next();
            String[] loHi = token.substring(1, token.length() - 1).split(":");
            pairs.add(new Pair(Integer.parseInt(loHi[0]), Integer.parseInt(loHi[1])));
        }

        System.out.println("----------My Way----------");
        printMap(getOverlaps(pairs));
        System.out.println("----------Their Way----------");
        printMap(optimalOverlaps(pairs));
        System.out.println("--------------------");
    }

    private static void printMap(Map<Pair, List<Pair>> overlaps) {
        List<Pair> pairs = asSortedList(overlaps.keySet());
        for (Pair key : pairs) {
            System.out.println(key + " - " + overlaps.get(key));
        }
    }

    private static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list);
        return list;
    }

    private static Map<Pair, List<Pair>> getOverlaps(List<Pair> pairs) {
        Map<Pair, List<Pair>> overlaps = new HashMap<>();
        Collections.sort(pairs);

        for (int i = 0; i < pairs.size(); i++) {
            Pair key = pairs.get(i);
            List<Pair> value = new ArrayList<>();
            for (int j = 0; j < pairs.size() && key.overlaps(pairs.get(j)); j++) {
                value.add(pairs.get(j));
            }
            value.remove(key);
            overlaps.put(key, value);
        }

        return overlaps;
    }

    private static Map<Pair, List<Pair>> optimalOverlaps(List<Pair> pairs) {
        Map<Pair, List<Pair>> overlaps = new HashMap<>();
        Collections.sort(pairs);

        Stack<Pair> stack = new Stack<>();
        stack.push(pairs.get(0));
        for (int i = 1; i < pairs.size(); i++) {
            Pair p = pairs.get(i);
            if (p.overlaps(stack.peek())) stack.push(p);
            else {
                pushToMap(overlaps, stack);
                stack.push(p);
            }
        }
        pushToMap(overlaps, stack);

        return overlaps;
    }

    private static void pushToMap(Map<Pair, List<Pair>> overlaps, Stack<Pair> stack) {
        List<Pair> list = new ArrayList<>();
        while (!stack.empty()) list.add(stack.pop());
        for (Pair l : list) {
            List<Pair> clone = (List)((ArrayList) list).clone();
            clone.remove(l);
            overlaps.put(l, clone);
        }
    }

    static class Pair implements Comparable<Pair> {
        int lo;
        int hi;

        public Pair(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }

        public boolean overlaps(Pair p) {
            return this.hi >= p.lo && this.lo <= p.hi;
        }

        @Override
        public int compareTo(Pair p) {
            return this.lo - p.lo;
        }

        @Override
        public String toString() {
            return "{" + lo + ":" + hi + '}';
        }
    }
}
