import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner("6");
        int N = sc.nextInt();
        for (int n = 1; n <= N; n++) {
            StringBuilder sb = new StringBuilder();
            for (int i = N - n; i > 0; i--) sb.append(" ");
            for (int j = 1; j <= 2 * n - 1; j++) sb.append("#");
            for (int k = N - n; k > 0; k--) sb.append(" ");
            System.out.println(sb.toString());
        }
    }
}
