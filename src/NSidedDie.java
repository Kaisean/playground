import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NSidedDie {
    private int N;
    private final Random rng = new Random();
    private boolean fair;
    private double[] cumsum;

    public NSidedDie(int N) {
        this.N = N;
        this.fair = true;
    }

    public NSidedDie(double[] distributions) throws IOException {
        if (distributions == null) throw new IOException();
        N = distributions.length;
        cumsum = new double[N];
        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += distributions[i];
            cumsum[i] = sum;
        }
        if (sum != 1) throw new IOException();
        this.fair = false;
    }

    public int roll() {
        if (fair) return rng.nextInt(N);
        else {
            /*
            Partition a Uniform Distribution into N parts
            Range        |   Value
            0 -> d0      |   0
            d0 -> d1     |   1
            d1 -> d2     |   2
            ...
            d_N-1 -> d_N |   N-1
             */

            double z = rng.nextDouble();
            for (int i = 0; i < cumsum.length; i++) {
                if (z < cumsum[i]) return i;
            }
            return -1;
        }
    }

    public static void main(String[] args) throws IOException {
        NSidedDie fairDie = new NSidedDie(6);
        Map<Integer, Integer> fairResults = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int roll = fairDie.roll();
            if (fairResults.containsKey(roll)) fairResults.put(roll, fairResults.get(roll) + 1);
            else fairResults.put(roll, 1);
        }
        System.out.println("Fair: " + fairResults.toString());

        NSidedDie unfairDie = new NSidedDie(new double[]{0.1, 0.1, 0.1, 0.1, 0.1, 0.5});
        Map<Integer, Integer> unfairResults = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int roll = unfairDie.roll();
            if (unfairResults.containsKey(roll)) unfairResults.put(roll, unfairResults.get(roll) + 1);
            else unfairResults.put(roll, 1);
        }
        System.out.println("Unfair: " + unfairResults.toString());
    }
}
