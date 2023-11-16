import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int answer = 0;

        while (n > 0) {
            if (n < 3) {
                answer = -1;
                break;
            }
            if (n % 5 == 0 || n % 5 == 3) {
                answer += n / 5;
                n %= 5;
                answer += n / 3;
                n %= 3;
            } else {
                answer++;
                n -= 3;
            }

        }

        System.out.println(answer);
    }
}
