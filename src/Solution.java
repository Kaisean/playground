public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindrome("asdfdsa"));
    }

    public static boolean isPalindrome(String input) {
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString().equals(input);
    }
}
