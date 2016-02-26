import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestor {

    public static void main(String[] args) {
        NodeWithParent n0 = new NodeWithParent(3);
        NodeWithParent n1 = new NodeWithParent(5);
        n1.parent = n0;
        NodeWithParent n2 = new NodeWithParent(1);
        n2.parent = n0;

        System.out.println(lowestCommonAncestor(n0, n1, n2));

        NodeWithChildren m0 = new NodeWithChildren(3);
        NodeWithChildren m1 = new NodeWithChildren(5);
        NodeWithChildren m2 = new NodeWithChildren(1);
        m0.left = m1;
        m0.right = m2;

        System.out.println(lowestCommonAncestor(m0, m1, m2));
    }

    private static NodeWithParent lowestCommonAncestor(NodeWithParent root, NodeWithParent p, NodeWithParent q) {
        Set<NodeWithParent> visited = new HashSet<>();
        while (p != null || q != null) {
            if (p != null) {
                if (!visited.add(p)) return p; // p already in set
                p = p.parent;
            }
            if (q != null) {
                if (!visited.add(q)) return q;
                q = q.parent;
            }
        }
        return null;
    }

    private static NodeWithChildren lowestCommonAncestor(NodeWithChildren root, NodeWithChildren p, NodeWithChildren q) {
        if (root == null) return null;
        if (root.equals(p) || root.equals(q)) return root;  // If root is either p or q, it is a common ancestor.

        NodeWithChildren left = lowestCommonAncestor(root.left, p, q);      // Sub-Tree of left
        NodeWithChildren right = lowestCommonAncestor(root.right, p, q);    // Sub-Tree of right

        if (left != null && right != null) return root;// If left and right return something, root is a common ancestor.
        else if (left != null) return left;   // If right is null, the left look-ahead must contain the common ancestor.
        else if (right != null) return right; // If left is null, the right look-ahead must contain the common ancestor.
        else return null; // If both left and right are null, root is not a common ancestor.
    }

    private abstract static class Node {
        int key;

        protected Node(int key) {
            this.key = key;
        }

        public String toString() {
            return "key : " + key;
        }
    }

    private static class NodeWithParent extends Node {
        NodeWithParent parent;

        public NodeWithParent(int key) {
            super(key);
        }
    }

    private static class NodeWithChildren extends Node {
        NodeWithChildren left;
        NodeWithChildren right;

        public NodeWithChildren(int key) {
            super(key);
        }
    }
}
