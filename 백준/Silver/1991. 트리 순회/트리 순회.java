import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        Node left;
        Node right;
        char data;

        public Node(char data) {
            this.data = data;
        }

        public Node() {
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Node[] tree = new Node[n];
        for (int i = 0; i < n; i++) {
            tree[i] = new Node((char) ('A' + i));
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char data = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            if (left != '.') {
                tree[data - 'A'].left = tree[left - 'A'];
            }

            if (right != '.') {
                tree[data - 'A'].right = tree[right - 'A'];
            }
        }

        prevTravel(tree[0]);
        System.out.println();
        midTravel(tree[0]);
        System.out.println();
        afterTravel(tree[0]);
    }

    private static void prevTravel(Node now) {
        if (now == null) {
            return;
        }

        System.out.print(now.data);
        prevTravel(now.left);
        prevTravel(now.right);
    }

    private static void midTravel(Node now) {
        if (now == null) {
            return;
        }

        midTravel(now.left);
        System.out.print(now.data);
        midTravel(now.right);
    }

    private static void afterTravel(Node now) {
        if (now == null) {
            return;
        }

        afterTravel(now.left);
        afterTravel(now.right);
        System.out.print(now.data);
    }
}