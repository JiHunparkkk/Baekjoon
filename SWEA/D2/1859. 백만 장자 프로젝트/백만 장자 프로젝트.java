import java.util.Scanner;
import java.io.FileInputStream;
import java.io.*;
import java.util.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n;
            long answer=0L;
            n=sc.nextInt();
            int[] arr = new int[n];
            
            for(int i=0;i<n;i++){
             	arr[i]=sc.nextInt();   
            }
            int max=0;
            for(int i=n-1;i>=0;i--){
             	   if(max<arr[i])
                       max = arr[i];
                answer+=max-arr[i];
            }
            
            System.out.println("#"+test_case+" "+answer); 
        }
	}
}