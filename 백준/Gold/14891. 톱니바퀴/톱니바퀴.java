import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[4][8]; // 4개의 자석, 8개의 날
        for (int i = 0; i < 4; i++) {
            String input = br.readLine();
            for (int j = 0; j < 8; j++) {
                arr[i][j] = input.charAt(j) - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int num, dir;
            num = Integer.parseInt(st.nextToken()) - 1; // num은 0부터 시작
            dir = Integer.parseInt(st.nextToken());

            // 회전
            // 2, 6 번 날이 붙어있음
            int[] selected = checkMagnet(arr, num, dir);
            curlMagnet(arr, selected);
        }

        // 점수 계산
        int answer = calScore(arr);
        System.out.println(answer);
    }

    // 회전시키려는 자석 양쪽의 인접점을 확인
    private static int[] checkMagnet(int[][] arr, int num, int dir) {
        int[] select = new int[4]; // 4개의 자석, 0 이면 회전 x, 1이면 시계, -1이면 반시계
        select[num] = dir;

        // 왼쪽 확인
        for (int i = num; i > 0; i--) {
            if (arr[i][6] != arr[i - 1][2]) { // 현재 왼쪽과, 다음꺼의 오른쪽 확인
                select[i - 1] = -select[i];
            } else {
                break;
            }
        }

        // 오른쪽 확인
        for (int i = num; i < 3; i++) {
            if (arr[i][2] != arr[i + 1][6]) { // 현재 오른쪽과, 다음꺼의 왼쪽 확인
                select[i + 1] = -select[i];
            } else {
                break;
            }
        }

        return select;
    }

    private static void curlMagnet(int[][] arr, int[] selected) {
        for (int i = 0; i < 4; i++) {
            if (selected[i] == 0) {
                continue;
            }

            if (selected[i] == 1) {
                clockwise(arr[i]);
            } else {
                counterClockwise(arr[i]);
            }
        }
    }

    private static void clockwise(int[] magnet) {
        int len = magnet.length;
        int tmp = magnet[len - 1];

        for (int i = len - 1; i > 0; i--) {
            magnet[i] = magnet[i - 1];
        }
        magnet[0] = tmp;
    }

    private static void counterClockwise(int[] magnet) {
        int len = magnet.length;
        int tmp = magnet[0];

        for (int i = 0; i < len - 1; i++) {
            magnet[i] = magnet[i + 1];
        }
        magnet[len - 1] = tmp;
    }

    private static int calScore(int[][] arr) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            if (arr[i][0] == 1) {
                result += Math.pow(2, i);
            }
        }
        return result;
    }
}