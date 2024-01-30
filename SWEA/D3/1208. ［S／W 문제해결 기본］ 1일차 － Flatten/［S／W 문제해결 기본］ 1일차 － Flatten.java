import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        
		int T = 10;
		
		for(int test_case = 1 ; test_case<=T ; test_case++) {
			int n = sc.nextInt();
			int[] box = new int[100];
			
			for(int i=0;i<100;i++) {
				box[i] = sc.nextInt();
			}
			
			Arrays.sort(box);
			
			for(int i=0;i<n;i++) {
				box[0]+=1;
				box[99]-=1;
				
				int j=1,k=0;
				while(box[k]>box[j]) {
					change(box, j++, k++);
				}
				
				j=98;
				k=99;
				while(box[k]<box[j]) {
					change(box, j--, k--);
				}
			}
			
			int answer = box[99] - box[0];
			System.out.println("#"+test_case+" " + answer);
		}
	}
	
	private static void  change(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
