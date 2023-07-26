import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        TreeSet<String> tree = new TreeSet<>();

        int lp = 0, rp = 0;
        for (int i = 0; i < str.length(); i++) {
            for (rp = i; rp < str.length(); rp++) {
                tree.add(str.substring(lp,rp+1));
            }
            lp++;
        }
        System.out.println(tree.size());
    }
}
