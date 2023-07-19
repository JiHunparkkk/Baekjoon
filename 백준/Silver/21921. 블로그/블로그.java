import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, x, max = 0, period_result = 0;
        n = sc.nextInt();
        x = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int lp = 0, cnt = 0, sum = 0;
        for (int i = 0; i < x; i++) {
            sum += arr[i];
        }
        for (int rp = x; rp < n; rp++) {
            if (max < sum) {
                max = sum;
            }
            sum += arr[rp];
            sum -= arr[lp];
            lp++;
        }
        if (max < sum) {
            max = sum;
        }

        lp = cnt = sum = 0;
        for (int i = 0; i < x; i++) {
            sum += arr[i];
        }

        for (int rp = x; rp < n; rp++) {
            if (max == sum) {
                period_result++;
            }
            sum += arr[rp];
            sum -= arr[lp];
            lp++;
        }
        if (max == sum) {
            period_result++;
        }

        if(max==0)
            System.out.println("SAD");
        else{
            System.out.println(max);
            System.out.println(period_result);
        }

    }
}
