import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int answer=0,cnt=0;
        int[] x = new int[n+1];

        for (int i = 2; i * i < n + 1; i++) {
            for (int j = i; j < n + 1; j = j + i) {
                if(j!=i)
                    x[j] = 1;
            }
        }

        for (int i = 2; i < n + 1; i++) {
            if(x[i]==0) cnt++;
        }

        int[] y = new int[cnt];
        for (int i = 2,j=0; i < n+1; i++) {
            if(x[i]==0)
                y[j++] = i;
        }


        int lp = 0, rp = 0,sum=0;
        for (; rp < cnt; rp++) {
            if (sum < n) {
                sum += y[rp];
            }
            while (sum >= n) {
                if(sum==n){
                    answer++;
                }
                sum -= y[lp];
                lp++;
            }
        }

        System.out.println(answer);
    }
}
