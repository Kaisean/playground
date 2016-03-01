public class Fibonacci {

    private static int fibRecur(int n) throws IllegalArgumentException {
        if (n < 0) throw new IllegalArgumentException();
        if (n < 2) return n;
        return fibRecur(n - 1) + fibRecur(n - 2);
    }

    private static int fibIter(int n) throws IllegalArgumentException {
        if (n < 0) throw new IllegalArgumentException();
        if (n < 2) return n;

        int f1 = 0, f2 = 1, f = 0;
        while (n-- > 1) {
            f = f1 + f2;
            f1 = f2;
            f2 = f;
        }
        return f;
    }

    public static void main(String[] args) {
        System.out.println("----------RECURSIVE----------");
        for (int n = 0; n < 10; n++) System.out.println(fibRecur(n));
        System.out.println("----------ITERATIVE----------");
        for (int n = 0; n < 10; n++) System.out.println(fibIter(n));
    }

}
