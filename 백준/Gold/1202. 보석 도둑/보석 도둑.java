import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int n, k;
    static long answer;
    static List<Ruby> ruby;
    static int[] bag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());    //보석 갯수
        k = Integer.parseInt(st.nextToken());    //가방 갯수
        ruby = new ArrayList<>();
        bag = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            ruby.add(new Ruby(w, p));
        }

        for (int i = 0; i < k; i++) {
            bag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(bag);
        Collections.sort(ruby, new Comparator<Ruby>() {
            @Override
            public int compare(Ruby o1, Ruby o2) {
                if (o1.weight == o2.weight) {
                    return o2.price - o1.price;
                }
                return o1.weight - o2.weight;
            }
        });

        answer = 0;
        solution();

        System.out.println(answer);
    }

    private static void solution() {
        PriorityQueue<Ruby> pq = new PriorityQueue<>(new Comparator<Ruby>() {
            @Override
            public int compare(Ruby o1, Ruby o2) {
                if (o1.price == o2.price) {
                    return o1.weight - o2.weight;
                }
                return o2.price - o1.price;
            }
        });

        int index = 0;
        for (int i = 0; i < k; i++) {
            while (index < n && bag[i] >= ruby.get(index).weight) {
                Ruby r = ruby.get(index++);
                pq.offer(new Ruby(r.weight, r.price));
            }
            if (!pq.isEmpty()) {
                answer += pq.poll().price;
            }
        }

    }
}

class Ruby {
    int weight;
    int price;

    public Ruby(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }
}