import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static char[] arr;
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine()," ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine()," ");
        arr = new char[C];
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);

        DFS(0, "");
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static void DFS(int index, String str) {
        if(str.length()==L){
            if (check(str)) {
                list.add(str);
                return;
            }
        }

        if(index>=C) return;

        DFS(index + 1, str + arr[index]);
        DFS(index + 1, str);
    }

    public static boolean check(String str) {
        int mo = 0, za = 0;

        for (char ch : str.toCharArray()) {
            if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u')
                mo++;
            else
                za++;
        }
        return (mo>=1 && za>=2);
    }
}
