import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    private static class Room {
        int index;
        int price;

        public Room(int num, int price) {
            this.index = num;
            this.price = price;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static int N, M;
    private static Room[] rooms;
    private static List<Integer> numbers = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        init();
        solution();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        rooms = new Room[N];
        for (int i = 0; i < N; i++) {
            int price = Integer.parseInt(st.nextToken());
            rooms[i] = new Room(i, price);
        }
        M = Integer.parseInt(br.readLine());
    }

    //자릿수 우선
    //우선 1 번째와 0번째를 조합하여 자릿수를 확인한다.
    //그 다음 2번째와 0번째를 조합하여 자릿수를 확인한다.
    //확인하다가 자릿수가 줄어든다면, 멈춘다.
    //마지막 자릿수부터 하나씩 넣어보면서 자릿수가 변하지 않는 선에서 최댓값을 구한다.
    private static void solution() {
        //가장 낮은 비용 및 인덱스가 1이상인 번호를 찾아서 최대 자릿수를 찾는다.
        //자릿수에 변화 없이 가장 큰값을 찾아야함
        if (rooms.length == 1) {
            System.out.println(0);
            return;
        }

        Room[] originRooms = new Room[N];
        for (int i = 0; i < N; i++) {
            originRooms[i] = rooms[i];
        }
        Arrays.sort(rooms, Comparator.comparingInt(o -> o.price));

        Room lower = findLower();   //제일 작은 값 찾기
        int money = findDigits(lower);  //제일 큰 자릿수 구하기

        Arrays.sort(rooms, Comparator.comparingInt(o -> o.index));

        for (int i = 0, roomsIdx = N - 1; i < numbers.size() && roomsIdx >= 0; ) {
            //높은 자릿수부터 값을 바꿔서 확인
            money += originRooms[numbers.get(i)].price;

            if (money >= rooms[roomsIdx].price) {
                money -= rooms[roomsIdx].price;
                numbers.set(i, rooms[roomsIdx].index);
                i++;
            } else {
                roomsIdx--;
                money -= originRooms[numbers.get(i)].price;
            }
        }

        if (numbers.size() == 0) {
            System.out.println(0);
            return;
        }

        for (Integer number : numbers) {
            System.out.print(number);
        }
        System.out.println();
    }

    private static Room findLower() {
        Room lower = rooms[0];
        if (rooms[0].index == 0) {
            lower = rooms[1];
        }
        return lower;
    }

    private static int findDigits(Room lower) {
        int money = M - lower.price;
        if (money >= 0) {
            numbers.add(lower.index);
        }

        while ((money -= rooms[0].price) >= 0) {
            numbers.add(rooms[0].index);
        }

        return money + rooms[0].price;
    }
}