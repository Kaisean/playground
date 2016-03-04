package bloomberg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a list of 1-D ranges [(-3, 3), (4, 5), (1, 4), ...]
 * <p>
 * Find an algorithm that can merge the overlapping ranges to
 * create a list of unique encompassing ranges.
 * <p>
 * i.e.
 * <p>
 * Input: [(-4, -2), (-3, 3), (7, 10)]
 * Output: [(-4, 3), (7, 10)]
 */
public class MergeRanges {
    static class Range {
        int lo;
        int hi;

        Range(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }

        @Override
        public String toString() {
            return "(" + lo + "," + hi + ')';
        }
    }

    public static void main(String[] args) {
        List<Range> input = new ArrayList<>();

        input.add(new Range(-4, -2));
        input.add(new Range(-1, 1));
        input.add(new Range(-3, 3));
        input.add(new Range(7, 10));

        long myStart = System.currentTimeMillis();
        printList(mergeRanges(input));
        long myEnd = System.currentTimeMillis();

        long theirStart = System.currentTimeMillis();
        printList(mergeRangesOfficial(input));
        long theirEnd = System.currentTimeMillis();

        System.out.println("My Time: " + (myEnd - myStart));
        System.out.println("Their Time: " + (theirEnd - theirStart));
    }

    private static void printList(List<Range> ranges) {
        for (Range r : ranges) {
            System.out.print(r.toString() + " ");
        }
        System.out.println();
    }

    private static List<Range> mergeRanges(List<Range> input) {
        List<Range> copyInput = new ArrayList<>(input);
        List<Range> output = new ArrayList<>();
        Collections.sort(copyInput, (o1, o2) -> o1.lo - o2.lo);
        for (int i = 0, lo = copyInput.get(0).lo, hi = copyInput.get(0).hi; i < copyInput.size(); i++) {
            Range curr = copyInput.get(i);
            if (i + 1 >= copyInput.size()) {
                output.add(new Range(lo, hi));
                break;
            }
            Range next = copyInput.get(i + 1);

            if (curr.hi < next.lo) {
                output.add(new Range(lo, hi));
                lo = next.lo;
            }
            if (curr.hi < next.hi) hi = next.hi;
        }
        return output;
    }

    private static List<Range> mergeRangesOfficial(List<Range> input) {
        Range max = Collections.max(input, (o1, o2) -> o1.hi - o2.hi);
        Range min = Collections.min(input, (o1, o2) -> o1.lo - o2.lo);
        boolean[] range = new boolean[max.hi - min.lo + 1];
        for (Range in : input) {
            for (int i = in.lo - min.lo; i < in.hi - min.lo + 1; i++) {
                range[i] = true;
            }
        }

        List<Range> output = new ArrayList<>();
        boolean curr = false, next = false;
        int lo = 0;
        for (int i = 0; i < range.length; i++) {
            if (!curr && range[i]) {
                curr = range[i];
                lo = i;
            }
            if (i + 1 < range.length) {
                if (curr && !range[i + 1]) {
                    output.add(new Range(lo + min.lo, i + min.lo));
                    curr = false;
                }
            } else {
                output.add(new Range(lo + min.lo, i + min.lo));
            }
        }
        return output;
    }
}
