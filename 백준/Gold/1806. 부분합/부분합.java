import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, s, answer = Integer.MAX_VALUE;
        n = sc.nextInt();
        s = sc.nextInt();
        int[] x = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
        }

        int lp = 0,sum=0,len=0,rp=0;
        boolean flag=true;
        for (; rp < n; rp++) {
            if (sum < s) {
                sum+=x[rp];
            }
            while (sum >= s) {
                flag=false;
                len = rp - lp + 1;
                if (answer > len) {
                    answer = len;
                }
                sum -= x[lp];
                lp++;
            }
        }

        if(flag)
            System.out.println(0);
        else
            System.out.println(answer);
    }
}
