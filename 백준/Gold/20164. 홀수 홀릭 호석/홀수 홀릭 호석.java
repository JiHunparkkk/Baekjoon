import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Num {
        int oddCnt;
        int digitCnt;

        public Num(int oddCnt, int digitCnt) {
            this.oddCnt = oddCnt;
            this.digitCnt = digitCnt;
        }
    }

    private static int maxCnt = Integer.MIN_VALUE;
    private static int minCnt = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        //1. 홀수 확인
        //2. 자릿수 확인 (1자리 수 이면 종료)
        //3-1. 3자리 이상이면 랜덤 자르기
        //3-2. 2자리면 그냥 자르기
        //4. 합치기 -> 다시 1번으로

        solution(n, 0);
        System.out.println(minCnt + " " + maxCnt);
    }

    private static void solution(int n, int cnt) {
        Num num = countDigit(n, cnt);

        if (num.digitCnt <= 1) {
            maxCnt = Math.max(maxCnt, num.oddCnt);
            minCnt = Math.min(minCnt, num.oddCnt);
            return;
        }

        if (num.digitCnt == 2) {
            solution(cutTwo(n), num.oddCnt);
        } else {
            //자를 수 있는 모든 경우의 수를 여기서 다 확인
            cutThree(n, num, 0, 0, new int[2]);
        }
    }

    private static Num countDigit(int n, int cnt) {
        String str = String.valueOf(n);
        int digitCnt = str.length();
        for (char ch : str.toCharArray()) {
            if((ch - '0') % 2 != 0) {
                cnt++;
            }
        }
        return new Num(cnt, digitCnt);
    }

    private static int cutTwo(int n) {
        return n % 10 + n / 10;
    }

    private static void cutThree(int n, Num num, int depth, int start, int[] cutPoint) {
        //자를 위치 2개를 찾기
        if(depth == 2) {
            //해당 위치 자르고 합친 뒤
            //solution 호출
            solution(cutAndSumThree(n, cutPoint), num.oddCnt);
            return;
        }

        //해당 위치 뒤에서 자른다고 생각
        for(int i = start; i < num.digitCnt - 1; i++) {
            cutPoint[depth] = i;
            cutThree(n, num, depth + 1, i + 1, cutPoint);
        }
    }

    private static int cutAndSumThree(int n, int[] cutPoint) {
        String str = String.valueOf(n);

        int num1 = Integer.parseInt(str.substring(0, cutPoint[0] + 1));
        int num2 = Integer.parseInt(str.substring(cutPoint[0] + 1, cutPoint[1] + 1));
        int num3 = Integer.parseInt(str.substring(cutPoint[1] + 1));

        return num1 + num2 + num3;
    }
}