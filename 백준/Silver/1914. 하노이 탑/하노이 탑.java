import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	
	private static StringBuilder sb = new StringBuilder();
	private static int n;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		BigInteger result= new BigInteger("2");
		sb.append(result.pow(n).subtract(new BigInteger("1"))).append("\n");
		
		if(n<=20) {
			hanoi(n, 1, 2, 3);			
		}
		System.out.println(sb);
	}
	
	private static void hanoi(int m,int from, int temp, int end) {
		if(m==1) {
			sb.append(from+" "+end).append("\n");
			return;
		}

		hanoi(m-1, from, end, temp);
		sb.append(from+" "+end).append("\n");
		hanoi(m-1, temp, from, end);

	}
}
