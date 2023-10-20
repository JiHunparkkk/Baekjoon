import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		
		System.out.println(sequence(num));
	}
	
	public static int sequence(int num) {
		int count=0;
		
		if(num<100)
			return num;
		else {
			while(num>100) {
				if(num==1000)
					num=999;
				int one = num%10;
				int ten = (num/10)%10;
				int hun = (num/100);
			
				if((hun-ten)==(ten-one)) {
					count++;
				}
				num--;
			}//while
			return count+99;
		}//else
	}//sequence function
}
	
