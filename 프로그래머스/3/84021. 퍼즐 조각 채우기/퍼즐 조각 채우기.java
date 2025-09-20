import java.util.*;

class Solution {

    //table에 있는 조각을 일단 다 찾고, 각 조각의 회전상태를 저장.
    //좌표? 방향?
    //1. 방향으로 해서 퍼즐 찾을 때 방향 및 개수 저장
    //2. 저장된 개수 크기만큼 임시 정사각형 생성, 저장된 방향으로 퍼즐 복사
    //3. 복사된 퍼즐 회전 후 다시 방향 저장 * 4
    //4. game_board에서 빈칸 찾으면, 방향 저장
    //5. 저장된 퍼즐들을 꺼내서 방향 일치하는지 확인
    //게임보드의 빈칸을 만났을 때 각 확인.

    private static class Puzzle {
        int count;
        List<Curl> curl = new ArrayList<>();

        public Puzzle(List<Curl> curl) {
            this.curl = new ArrayList<>(curl);
            this.count = curl.get(0).points.size();
        }

        public boolean isSame(Curl other) {
            return curl.contains(other);
        }
    }

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object object) {
            if (object == null || getClass() != object.getClass()) {
                return false;
            }
            Point point = (Point) object;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private static class Curl {
        Set<Point> points;

        public Curl(List<Point> points) {
            this.points = new HashSet<>(points);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;
            Curl other = (Curl) obj;
            return Objects.equals(points, other.points);
        }

        @Override
        public int hashCode() {
            return Objects.hash(points);
        }
    }

    private static int n;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static List<Puzzle> list = new ArrayList<>();

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        n = table.length;
        findPuzzles(table);

        boolean[][] visited = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(game_board[i][j] == 1 || visited[i][j]) continue;
                visited[i][j] = true;

                List<Point> target = new ArrayList<>();
                target.add(new Point(i, j));
                findBlank(game_board, visited, i, j, target);
                List<Point> newTarget = changePoints(target);

                for (int k = 0; k < list.size(); k++) {
                    Puzzle puzzle = list.get(k);

                    if(puzzle.isSame(new Curl(newTarget))) {
                        answer += puzzle.count;
                       list.remove(k);
                        break;
                    }
                }
            }
        }

        return answer;
    }

    private static void findPuzzles(int[][] table) {
        boolean[][] visited = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(table[i][j] == 0 || visited[i][j]) continue;
                visited[i][j] = true;
                List<Point> points = new ArrayList<>();
                points.add(new Point(i, j));
                findPuzzle(table, visited, i, j, points);
                makePuzzleCurl(points);
            }
        }
    }

    private static void findPuzzle(int[][] table, boolean[][] visited, int x, int y, List<Point> points) {
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isOut(nx, ny, table.length) || visited[nx][ny] || table[nx][ny] == 0) continue;
            visited[nx][ny] = true;
            points.add(new Point(nx, ny));
            findPuzzle(table, visited, nx, ny, points);
        }
    }

    private static void findBlank(int[][] table, boolean[][] visited, int x, int y, List<Point> points) {
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isOut(nx, ny, table.length) || visited[nx][ny] || table[nx][ny] == 1) continue;
            visited[nx][ny] = true;
            points.add(new Point(nx, ny));
            findBlank(table, visited, nx, ny, points);
        }
    }

    private static boolean isOut(int nx, int ny, int len) {
        return nx < 0 || nx >= len || ny < 0 || ny >= len;
    }

    private static void makePuzzleCurl(List<Point> points) {
        //기준좌표 변경
        List<Point> newPoints = changePoints(points);

        List<Curl> curl = new ArrayList<>();
        curl.add(new Curl(newPoints));

        if(points.size() == 1) {
            list.add(new Puzzle(curl));
            return;
        }

        int len = 6;
        int[][] temp = new int[len *  2 + 1][len * 2 + 1];
        for (Point p : newPoints) {
            temp[p.x][p.y] = 1;
        }

        //회전
        for(int i = 0; i < 3; i++) {
            rotate(temp);
            List<Point> rotateDir = findDir(temp);
            curl.add(new Curl(rotateDir));
        }
        list.add(new Puzzle(curl));
    }

    private static List<Point> changePoints(List<Point> points) {
        //시작좌표는 (6, 6)
        List<Point> newPoints = new ArrayList<>();
        int standardX = 6 - points.get(0).x;
        int standardY = 6 - points.get(0).y;

        for (int i = 0; i < points.size(); i++) {
            int x = points.get(i).x + standardX;
            int y = points.get(i).y + standardY;
            newPoints.add(new Point(x, y));
        }
        return newPoints;
    }

    private static void rotate(int[][] temp) {
        int[][] newTemp = new int[temp.length][temp.length];
        for(int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp.length; j++) {
                newTemp[i][j] = temp[j][temp.length - i - 1];
            }
        }

        for(int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp.length; j++) {
                temp[i][j] = newTemp[i][j];
            }
        }
    }

    private static List<Point> findDir(int[][] temp) {
        for(int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp.length; j++) {
                if(temp[i][j] == 1) {
                    List<Point> points = new ArrayList<>();
                    points.add(new Point(i, j));
                    boolean[][] visited = new boolean[temp.length][temp.length];
                    visited[i][j] = true;
                    findPuzzle(temp, visited, i, j, points);

                    return changePoints(points);
                }
            }
        }
        return null;
    }
}