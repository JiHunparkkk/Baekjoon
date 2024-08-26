import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N;
    private static Egg[] eggs;
    private static int answer;

    private static class Egg {
        int durability;
        int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }


    }

    public static void main(String[] args) throws IOException {
        init();
        solution(0, 0);
        System.out.println(answer);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int durability = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(durability, weight);
        }
    }

    private static void solution(int hold, int cnt) {
        if (cnt == N - 1 || hold == N) {
            answer = Math.max(answer, cnt);
            return;
        }

        if (eggs[hold].durability <= 0) {
            solution(hold + 1, cnt);
        } else {
            for (int i = 0; i < N; i++) {
                if (i == hold) {
                    continue;
                }

                if (eggs[i].durability > 0) {
                    eggs[i].durability -= eggs[hold].weight;
                    eggs[hold].durability -= eggs[i].weight;
                    solution(hold + 1, cnt + (eggs[i].durability <= 0 ? 1 : 0) + (eggs[hold].durability <= 0 ? 1 : 0));
                    eggs[i].durability += eggs[hold].weight;
                    eggs[hold].durability += eggs[i].weight;
                }
            }
        }
    }

}