import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] x = new int[30];
        int[] result = new int[2];

        for(int i=0;i<28;i++){
            x[Integer.parseInt(br.readLine())-1]++;
        }

        int m=0;
        for(int i=0;i<30;i++){
            if(x[i]==0){
                result[m++] = i+1;
            }
        }

        if(result[0]<result[1])
            System.out.println(result[0]+"\n"+result[1]);
        else
            System.out.println(result[1]+"\n"+result[0]);

    }
}
