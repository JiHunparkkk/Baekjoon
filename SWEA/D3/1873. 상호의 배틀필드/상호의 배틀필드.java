import java.util.Scanner;

public class Solution {

    private static int h, w, n;
    private static char[][] board;
    private static char[] command;
    private static Tank tank;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            h = sc.nextInt();
            w = sc.nextInt();
            board = new char[h][w];

            //맵 구성 요소 입력
            for (int i = 0; i < h; i++) {
                String input = sc.next();
                for (int j = 0; j < w; j++) {
                    board[i][j] = input.charAt(j);
                }
            }

            //명령어 입력
            n = sc.nextInt();
            command = new char[n];
            String input = sc.next();
            for (int i = 0; i < n; i++) {
                command[i] = input.charAt(i);
            }

            //게임 시작
            solution();

            System.out.print("#" + test_case + " ");

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
        }
    }

    //게임 시작
    private static void solution() {

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (isDirection(board[i][j])) { //탱크 위치, 방향 설정
                    tank = new Tank(i, j, board[i][j]);
                    break;
                }
            }
        }
        //명령 시작
        for (int i = 0; i < n; i++) {
            playCommand(command[i]);
        }

        //명령 끝, 현재 탱크 방향 저장
        saveNowDir();
    }

    //명령 시작
    private static void playCommand(char cmd) {
        switch (cmd) {
            case 'U':
                tank.setDir('^');
                tank.move();
                break;
            case 'D':
                tank.setDir('v');
                tank.move();
                break;
            case 'L':
                tank.setDir('<');
                tank.move();
                break;
            case 'R':
                tank.setDir('>');
                tank.move();
                break;
            case 'S':
                tank.shoot();
                break;
        }
    }

    private static boolean isDirection(char ch) {
        return ch == '^' || ch == 'v' || ch == '<' || ch == '>';
    }

    private static void saveNowDir() {
        int nowDir = tank.dir;
        int x = tank.x;
        int y = tank.y;

        switch (nowDir) {
            case 1:
                board[x][y] = '^';
                break;
            case 2:
                board[x][y] = 'v';
                break;
            case 3:
                board[x][y] = '<';
                break;
            case 4:
                board[x][y] = '>';
                break;
        }
    }

    //탱크 객체
    static class Tank {
        int x, y, dir;  //x,y좌표, dir은 바라보는 방향

        public Tank(int x, int y, char dir) {
            this.x = x;
            this.y = y;
            setDir(dir);
        }

        public void setDir(char dir) {  //바라보는 방향 설정
            switch (dir) {
                case '^':
                    this.dir = 1;
                    break;
                case 'v':
                    this.dir = 2;
                    break;
                case '<':
                    this.dir = 3;
                    break;
                case '>':
                    this.dir = 4;
                    break;
            }
        }

        public void move() {    //방향에 맞게 이동
            int[] ndr = setNextPoint();
            int nx = x + ndr[0];
            int ny = y + ndr[1];

            if (validRange(nx, ny) && board[nx][ny] == '.') {
                board[x][y] = '.';  //탱크 방향 상태 화살표 초기화
                x += ndr[0];
                y += ndr[1];
            }
        }

        public void shoot() {
            int[] ndr = setNextPoint();
            int nx = x + ndr[0];
            int ny = y + ndr[1];

            while (validRange(nx, ny) && board[nx][ny] != '#') {
                if (board[nx][ny] == '*') {
                    board[nx][ny] = '.';
                    break;
                } else {
                    nx += ndr[0];
                    ny += ndr[1];
                }
            }
        }

        private int[] setNextPoint() {
            int[] ndr = new int[2];
            switch (dir) {
                case 1:
                    ndr[0] = -1;
                    break;
                case 2:
                    ndr[0] = 1;
                    break;
                case 3:
                    ndr[1] = -1;
                    break;
                case 4:
                    ndr[1] = 1;
                    break;
            }
            return ndr;
        }

        private boolean validRange(int nx, int ny) {
            return nx >= 0 && ny >= 0 && nx < h && ny < w;
        }
    }
}

