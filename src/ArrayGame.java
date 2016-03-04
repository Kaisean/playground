import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ArrayGame {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner("4\n" +
                "5 3\n" +
                "0 0 0 0 0\n" +
                "6 5\n" +
                "0 0 0 1 1 1\n" +
                "6 3\n" +
                "0 0 1 1 1 0\n" +
                "3 1\n" +
                "0 1 0");
        //Scanner sc = new Scanner(new File("test/array_game_input0.dat"));
        /*Scanner sc = new Scanner("1 84 14\n" +
                "0 0 0 0 0 1 0 1 1 1 1 0 1 0 1 0 0 1 0 0 0 1 0 1 0 0 0 0 1 1 0 0 1 0 0 1 1 1 1 0 0 0 0 0 0 1 0 0 0 0 0 1 1 0 0 1 1 1 0 0 0 0 1 0 0 1 1 1 0 0 1 1 1 1 1 1 1 1 0 1 1 0 0 0");*/
        int T = sc.nextInt();
        for (int t = 0; t < T; t++) {
            int N = sc.nextInt();
            int m = sc.nextInt();
            int[] arr = new int[N];
            for (int n = 0; n < N; n++) {
                arr[n] = sc.nextInt();
            }

            System.out.println(isSolvable(m, arr, 0) ? "YES" : "NO");
        }
    }

    private static boolean isSolvable(int m, int[] arr, int i) {
        if (i < 0 || arr[i] == 1) return false;
        if ((i == arr.length - 1) || i + m > arr.length - 1) return true;

        arr[i] = 1;
        return isSolvable(m, arr, i + 1) || isSolvable(m, arr, i - 1) || isSolvable(m, arr, i + m);
    }
}
