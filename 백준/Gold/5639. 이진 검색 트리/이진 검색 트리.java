import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static class Node {
        Node prev;
        Node next;
        int data;

        public Node() {
        }

        public Node(Node prev, Node next, int data) {
            this.prev = prev;
            this.next = next;
            this.data = data;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = getNode();
        String input = br.readLine();
        root.data = Integer.parseInt(input);

        Node node = root;
        while ((input = br.readLine()) != null) {
            int now = Integer.parseInt(input);
            makeTree(root, now);
        }
        printAfter(root);
    }

    private static Node getNode() {
        return new Node(null, null, 0);
    }

    private static void makeTree(Node node, int num) {
        if (node.data > num) {
            if (node.prev == null) {
                node.prev = getNode();
                node.prev.data = num;
            } else {
                makeTree(node.prev, num);
            }
        } else {
            if (node.next == null) {
                node.next = getNode();
                node.next.data = num;
            } else {
                makeTree(node.next, num);
            }
        }
    }

    private static void printAfter(Node node) {
        if (node == null) {
            return;
        }
        printAfter(node.prev);
        printAfter(node.next);
        System.out.println(node.data);
    }
}