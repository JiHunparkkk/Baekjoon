import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main {

    static int front = 0;
    static int back = 0;
    static int size = 0;
    static int[] deque = new int[10000];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            String[] s = br.readLine().split(" ");

            switch (s[0]) {

                case "push_front":
                    push_front(Integer.parseInt(s[1]));
                    break;
                case "push_back":
                    push_back(Integer.parseInt(s[1]));
                    break;
                case "pop_front":
                    sb.append(pop_front()).append("\n");
                    break;
                case "pop_back":
                    sb.append(pop_back()).append("\n");
                    break;
                case "size":
                    sb.append(size()).append("\n");
                    break;
                case "empty":
                    sb.append(empty()).append("\n");
                    break;
                case "front":
                    sb.append(front()).append("\n");
                    break;
                case "back":
                    sb.append(back()).append("\n");
                    break;
            }//switch
        }//for

        System.out.println(sb);
    }//main

    static int back() {
        if (size == 0) {
            return -1;
        }
        return deque[back];
    }

    static int front() {
        if (size == 0) {
            return -1;
        }
        return deque[(front + 1) % 10000];
    }

    static int empty() {
        if (size == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    static int size() {
        return size;
    }

    static int pop_back() {
        if (size == 0) {
            return -1;
        }
        int rst = deque[back];
        back = (back - 1 + 10000) % 10000;
        size--;
        return rst;
    }

    static int pop_front() {
        if(size == 0)
            return -1;

        int rst = deque[(front + 1) % 10000];
        front = (front + 1) % 10000;
        size--;
        return rst;
    }

    static void push_front(int x) {
        deque[front] = x;

        //음수가 되지 않도록 배열 크기만큼 더해준다.
        front = (front - 1 + 10000) % 10000;
        size++;
    }

    static void push_back(int x) {
        back = (back + 1) % 10000;
        size++;
        deque[back] = x;
    }

}
