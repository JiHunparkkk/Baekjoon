import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N, d, k, c;
        int[] table, visited;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        table = new int[N];
        visited = new int[d + 1];

        for (int i = 0; i < N; i++) {
            table[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        for (int i = 0; i < k; i++) {
            if (visited[table[i]] == 0) {
                cnt++;
            }
            visited[table[i]]++;
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            //갯수 확인
            if (answer <= cnt) {
                answer = cnt;
                if (visited[c] == 0) {  //쿠폰 적용
                    answer++;
                }
            }

            //슬라이딩 이동, 시작값의 갯수 빼고, 중복된 메뉴가 없으면 갯수 감소
            visited[table[i]]--;
            if (visited[table[i]] == 0) {
                cnt--;
            }

            //슬라이딩 이동할 값이 선택한 메뉴에 없으면 갯수 추가
            if (visited[table[(i + k) % N]] == 0) {
                cnt++;
            }
            visited[table[(i + k) % N]]++;
        }

        System.out.println(answer);
    }

}
