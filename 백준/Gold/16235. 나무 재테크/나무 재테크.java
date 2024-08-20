import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Tree implements Comparable<Tree> {
        int x, y, age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(age, o.age);
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, M, K;
    private static int[][] givenFood;
    private static int[][] currentFood;
    private static Queue<Tree> deadTree;
    private static PriorityQueue<Tree> trees;

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        givenFood = new int[N + 1][N + 1];
        currentFood = new int[N + 1][N + 1];
        deadTree = new ArrayDeque<>();
        trees = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                givenFood[i][j] = Integer.parseInt(st.nextToken());
                currentFood[i][j] = 5;
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, z));
        }
    }

    private static void solution() {
        while (K-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }
        findLiveTrees();
    }

    private static void spring() {
        //나이가 증가된 나무를 따로 보관 후 마지막에 저장
        List<Tree> growthTrees = new ArrayList<>();

        while (!trees.isEmpty()) {
            Tree poll = trees.poll();
            int x = poll.x;
            int y = poll.y;
            int curAge = poll.age;

            if (currentFood[x][y] >= curAge) {
                currentFood[x][y] -= curAge;
                growthTrees.add(new Tree(x, y, curAge + 1));
            } else {
                deadTree.add(new Tree(x, y, curAge));
            }
        }

        trees.addAll(growthTrees);
    }

    private static void summer() {
        while (!deadTree.isEmpty()) {
            Tree poll = deadTree.poll();
            int x = poll.x;
            int y = poll.y;
            int curAge = poll.age;
            currentFood[x][y] += curAge / 2;
        }
    }

    private static void fall() {
        //하나씩 뽑아서, 5로 나뉘어지는 것만 재생성
        List<Tree> newTrees = new ArrayList<>();

        for (Tree tree : trees) {
            if (tree.age % 5 == 0) {
                newTrees.add(new Tree(tree.x, tree.y, tree.age));
            }
        }

        for (Tree newTree : newTrees) {
            reproduce(newTree.x, newTree.y);
        }
    }

    private static void reproduce(int x, int y) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx > 0 && ny > 0 && nx <= N && ny <= N) {
                trees.add(new Tree(nx, ny, 1));
            }
        }
    }

    private static void winter() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                currentFood[i][j] += givenFood[i][j];
            }
        }
    }

    private static void findLiveTrees() {
        System.out.println(trees.size());
    }
}