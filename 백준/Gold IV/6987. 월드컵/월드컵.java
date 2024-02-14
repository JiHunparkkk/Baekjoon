import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main {
	
	private static int answer;
	private static int[][] origin;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		for(int i=0;i<4;i++) {
			st = new StringTokenizer(br.readLine());
			origin = new int[6][3];
			boolean flag = true;
			answer=0;

			
			for(int j=0;j<6;j++) {
				for(int k=0;k<3;k++) {
					origin[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(isValidSum()) {
				solution(0, 1);				
			}
			
			System.out.print(answer+" ");
		}
		
		
	}
	
	private static void solution(int now ,int next) {
		
		if(isOut(now)) {
			return;
		}
		
		if(next==6) {
			now+=1;
			next=now+1;
		}
		
		if(now==5) {
			if(origin[now][0]==0 &&origin[now][1]==0 &&origin[now][2]==0)
			answer=1;
			return;
		}
		
		
		
		origin[now][0]--;
		origin[next][2]--;
		solution(now, next+1);
		origin[now][0]++;
		origin[next][2]++;

		
		origin[now][1]--;
		origin[next][1]--;
		solution(now, next+1);
		origin[now][1]++;
		origin[next][1]++;
		
		origin[now][2]--;
		origin[next][0]--;
		solution(now, next+1);
		origin[now][2]++;
		origin[next][0]++;
		
	}
	
	private static boolean isOut(int now) {
		return origin[now][0]<0 || origin[now][1]<0 || origin[now][2]<0;
	}
	
	private static boolean isValidSum() {
		for(int i=0;i<6;i++) {
			int sum =0;
			for(int j=0;j<3;j++) {
				sum+=origin[i][j];
			}
			if(sum!=5)
				return false;
		}
		return true;
	}
}
