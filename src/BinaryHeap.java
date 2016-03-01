import java.util.Comparator;
import java.util.Scanner;

/*
 * Binary Heap
 *
 * Use index k with 1-indexing to perform arithmetic linking
 * to create a complete binary tree (fully balanced except
 * for bottom level) where the root is the minimum/maximum
 * element.
 *
 * Binary Heaps have 1 invariant:
 *
 *      Parents' keys are no smaller than their childrens' keys.
 *
 * Node at index k: Parent at k/2, Children at 2k, 2k+1
 */
public class BinaryHeap<Key extends Comparable<Key>> {
    private Key[] pq;   // pq[i] = ith element on pq
    private int N;      // number of elements on pq

    public BinaryHeap(int capacity) {
        pq =  (Key[]) new Comparable[capacity];
    }

    /*
     * Add node at end, then swim it up.
     */
    public void insert(Key x) {
        pq[++N] = x;
        swim(N);
    }

    /*
     * Poll the root.
     * Swap root with node at end, then sink it down.
     */
    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = null;
        return max;
    }

    public Key getMax() {
        return pq[1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    /*
     * Promote a node at pq[k] until heap order is restored.
     * Called when a child's key becomes larger than its parent's.
     *
     * The parent of a node at index k is at index k/2.
     */
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    /*
     * Demote a node at pq[k] until heap order is restored.
     * Called when a parent's key becomes smaller than one
     * (or both) of its children.
     *
     * The child of a node at index k is at indices 2k, 2k+1.
     */

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private boolean greater(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Key key : pq) sb.append(key.toString()).append(" ");
        return sb.toString();
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> bh = new BinaryHeap<>(11);
        Scanner sc = new Scanner("2 5 4 6 7 1 8 9 0 3");
        while (sc.hasNext()) bh.insert(sc.nextInt());

        StringBuilder sb = new StringBuilder();
        while (!bh.isEmpty()) sb.append(bh.delMax()).append(" ");
        System.out.println(sb.toString());
        sb.hashCode();
    }

}
