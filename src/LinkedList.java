public class LinkedList {
    public static void main(String[] args) {
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        n0.next = n1;
        Node n2 = new Node(2);
        n1.next = n2;

        printList(n0);
        reverse(n0);
        printList(n2);
        reverseIter(n2);
        printList(n0);
    }

    private static void printList(Node head) {
        System.out.print("List: ");
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    private static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node newHead = reverse(head.next); // reverse all but first (start at first next)
        head.next.next = head; // make original second point at first (create link to previous)
        head.next = null; // make original first point at nothing (kill original links)

        return newHead;
    }

    private static void reverseIter(Node head) {
        if(head == null) return;
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            //Pointer to next node;
            Node next = curr.next;

            //Reverse linkage for current node to previous node
            curr.next = prev;

            //Iterate the pointers
            prev = curr;
            curr = next;
        }
    }

    static class Node {
        Node(int data) {
            this.data = data;
        }

        Node next;
        int data;
    }
}
