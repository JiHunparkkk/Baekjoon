import java.util.Scanner;
 
public class Solution{
     
    private static int N,L,answer;
    private static Taste[] arr;
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
         
        for(int test_case = 1; test_case <= T; test_case++)
        {
             
            N = sc.nextInt();
            L = sc.nextInt();
            arr = new Taste[N];
             
            for(int i=0;i<N;i++) {
                arr[i] = new Taste(sc.nextInt(), sc.nextInt());
            }
            answer=0;
            solution(0,0,0,0);
             
            System.out.println("#"+test_case+" "+answer);
        }
    }
     
    private static void solution(int start,int flag,int calories,int score) {
        if(calories<=L) {
            answer = Math.max(answer, score);
        }
         
        for(int i=start;i<N;i++) {
            if((flag & 1<<i) == 0) {
                solution(i+1, flag | 1<<i, calories+arr[i].cal, score+arr[i].score);
            }
        }
    }
}
 
class Taste{
    int score;
    int cal;
     
    public Taste(int score, int cal) {
        this.score = score;
        this.cal = cal;
    }
}