public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindrome("asdfdsa"));

        System.out.println("HashCode of 1 (Integer): " + new Integer(1).hashCode());
        System.out.println("HashCode of 1 (Double): " + new Double(1).hashCode());
    }

    public static boolean isPalindrome(String input) {
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString().equals(input);
    }
}
