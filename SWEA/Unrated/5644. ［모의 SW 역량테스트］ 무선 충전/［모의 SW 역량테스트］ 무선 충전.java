import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

    private static int A, answer;
    private static BC[] bc;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int M = sc.nextInt();
            A = sc.nextInt();

            int[] userA = {0, 0};
            int[] userB = {9, 9};
            int[] userA_dir = new int[M];
            int[] userB_dir = new int[M];

            for (int i = 0; i < M; i++) {
                userA_dir[i] = sc.nextInt();
            }
            for (int i = 0; i < M; i++) {
                userB_dir[i] = sc.nextInt();
            }

            bc = new BC[A];
            for (int i = 0; i < A; i++) {
                int y = sc.nextInt() - 1;
                int x = sc.nextInt() - 1;
                int c = sc.nextInt();
                int p = sc.nextInt();
                bc[i] = new BC(x, y, c, p);
            }

            answer = 0;
            for (int i = -1; i < M; i++) {
                move(userA, userA_dir, i);
                move(userB, userB_dir, i);
                charge(userA, userB);
            }

            System.out.println("#" + test_case + " " + answer);
        }
    }

    private static void move(int[] user, int[] user_dir, int time) {
        if (time == -1) {
            return;
        }

        int x = user[0];
        int y = user[1];

        int dir = user_dir[time];
        switch (dir) {
            case 1:
                x += -1;
                break;
            case 2:
                y += 1;
                break;
            case 3:
                x += 1;
                break;
            case 4:
                y += -1;
                break;
        }

        user[0] = x;
        user[1] = y;
    }

    private static void charge(int[] userA, int[] userB) {
        List<BC> ap = new ArrayList<>();
        List<BC> bp = new ArrayList<>();
        for (int i = 0; i < A; i++) {
            if (isChargeable(userA, bc[i])) {
                ap.add(bc[i]);
            }
        }

        for (int i = 0; i < A; i++) {
            if (isChargeable(userB, bc[i])) {
                bp.add(bc[i]);
            }
        }

        Collections.sort(ap);
        Collections.sort(bp);

        int result = 0;
        if (ap.isEmpty() && bp.isEmpty()) {
            result = 0;
        } else if (ap.isEmpty() && !bp.isEmpty()) {
            result = bp.get(0).p;
        } else if (!ap.isEmpty() && bp.isEmpty()) {
            result = ap.get(0).p;
        } else {
            int sum = 0;
            for (int i = 0; i < ap.size(); i++) {
                for (int j = 0; j < bp.size(); j++) {
                    if (isSame(ap.get(i), bp.get(j))) {
                        sum = Math.max(sum, ap.get(i).p);
                    } else {
                        sum = Math.max(sum, ap.get(i).p + bp.get(j).p);
                    }
                }
            }
            result = sum;
        }
        answer += result;

    }

    private static boolean isChargeable(int[] user, BC bc) {
        int d = Math.abs(user[0] - bc.x) + Math.abs(user[1] - bc.y);

        if (d <= bc.c) {
            return true;
        }
        return false;
    }

    private static boolean isSame(BC bc1, BC bc2) {
        return bc1.x == bc2.x && bc1.y == bc2.y;
    }
}

class BC implements Comparable<BC> {
    int x, y, c, p;

    public BC(int x, int y, int c, int p) {
        this.x = x;
        this.y = y;
        this.c = c;
        this.p = p;
    }

    @Override
    public int compareTo(BC o) {
        return o.p - this.p;
    }


}