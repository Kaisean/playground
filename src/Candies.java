import java.util.Scanner;

public class Candies {
    public static void main(String[] args) {
        //Scanner sc = new Scanner("3 1 2 2");
        Scanner sc = new Scanner("10 2 4 2 6 1 7 8 9 2 1");
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int n = 0; n < N; n++) {
            arr[n] = sc.nextInt();
        }
        System.out.println(candies(arr));
    }

    private static int candies(int[] arr) {
        printArray(arr);

        int[] candies = new int[arr.length];
        int c = 1;
        int index = 0;
        candies[index++] = c;
        while (index < arr.length) {
            if (arr[index] > arr[index - 1]) c++;
            else c--;
            candies[index++] = c;
        }
        printArray(candies);

        int[] backward = new int[arr.length];
        c = 1;
        index = arr.length - 1;
        backward[index--] = c;
        while(index >= 0) {
            if (arr[index] > arr[index+1]) c++;
            else c--;
            backward[index--] = c;
        }
        printArray(backward);

        return sum(candies);
    }

    private static void printArray(int[] arr) {
        for(int a : arr) System.out.print(a + " ");
        System.out.println();
    }

    private static int sum(int[] arr) {
        int sum = 0;
        for (int a : arr) sum += a;
        return sum;
    }
}
