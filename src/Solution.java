import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Node root = new Node();

        root.left = new Node();
        root.left.parent = root;

        root.right = new Node();
        root.right.parent = root;

        System.out.println(root.equals(leastCommonAncestor(root.left, root.right)));

        System.out.println(isPalindrome("asdfdsa"));
    }

    public static boolean isPalindrome(String input) {
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString().equals(input);
    }

    // Given 2 nodes, find the least common ancestor of both.
    private static Set<Node> path = new HashSet<>();

    private static Node leastCommonAncestor(Node n1, Node n2) {
        if(n1.equals(n2)) return n1;

        path.add(n1);
        path.add(n2);

        if (path.contains(n1.parent)) return n1.parent;
        else if (path.contains(n2.parent)) return n2.parent;
        else return leastCommonAncestor(n1.parent, n2.parent);
    }

    static class Node {
        public Node left;
        public Node right;
        public Node parent;
    }
}
