import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	private static final int MAN = 1;
	private static final int WOMAN = 2;
	private static final int ON = 1;
	private static final int OFF = 0;
	
	private static int n;
	private static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int student = Integer.parseInt(br.readLine());
		for(int i=0;i<student;i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			manageSwitch(s, num);
		}
		
		for(int i=1;i<=n;i++) {
			System.out.print(arr[i-1]+" ");
			if(i%20 == 0) {
				System.out.println();
			}
		}
	}
	
	private static void manageSwitch(int s, int num) {
		if(s==MAN) {
			for(int i=0;i<n;i++) {
				if((i+1)%num==0) {
					changeStatus(i);
				}
			}
			return;
		}
		
		if(s==WOMAN) {
			num-=1;
			changeStatus(num);
			
			int left = num-1;
			int right = num+1;
			while(isRange(left, right)) {
				changeStatus(left--);
				changeStatus(right++);
			}
		}
	}
	
	private static void changeStatus(int i) {
		arr[i] = arr[i]==ON ? OFF : ON;
	}
	
	private static boolean isRange(int left,int right) {
		return left>=0 && right>=0 && left<n && right<n && arr[left]==arr[right];
	}
}
