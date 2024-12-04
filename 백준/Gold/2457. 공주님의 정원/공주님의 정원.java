import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static class Date {
        int month, day;

        public Date(int month, int day) {
            this.month = month;
            this.day = day;
        }

        @Override
        public boolean equals(Object o) {
            if(this == o) return true;
            if(o == null || getClass() != o.getClass()) return false;
            Date date = (Date) o;
            return month == date.month && day == date.day;
        }

        @Override
        public int hashCode() {
            return 31 * month + day;
        }

        public int compareTo(Date other) {
            if(month == other.month) return Integer.compare(day, other.day);
            return Integer.compare(month, other.month);
        }
    }

    private static class Flower implements Comparable<Flower>{
        Date startAt;
        Date endAt;

        public Flower(Date startAt, Date endAt) {
            this.startAt = startAt;
            this.endAt = endAt;
        }

        @Override
        public int compareTo(Flower flower) {
            if (startAt == flower.startAt) {
                return endAt.compareTo(flower.endAt);
            }
            return startAt.compareTo(flower.startAt);
        }
    }

    private static int N;
    private static Flower[] flowers;

    public static void main(String[] args) throws IOException {
        init();
        int answer = solution();
        System.out.println(answer);
    }

    private static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        flowers = new Flower[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int startMonth = Integer.parseInt(st.nextToken());
            int startDay = Integer.parseInt(st.nextToken());
            int endMonth = Integer.parseInt(st.nextToken());
            int endDay = Integer.parseInt(st.nextToken());

            flowers[i] = new Flower(new Date(startMonth, startDay), new Date(endMonth, endDay));
        }
    }

    private static int solution() {
        Arrays.sort(flowers);

        int answer = 0;
        int idx = 0;
        Date startDate = new Date(3, 1);
        Date endDate = new Date(0, 0);

        while(endDate.compareTo(new Date(11, 30)) <= 0) {
            boolean flag = false;
            for (int i = idx; i < N; i++) {
                if(flowers[i].startAt.compareTo(startDate) > 0) break;
                if(flowers[i].endAt.compareTo(endDate) > 0) {
                    endDate = flowers[i].endAt;
                    idx = i + 1;
                    flag = true;
                }
            }

            if (flag) {
                startDate = endDate;
                answer++;
            } else {
                return 0;
            }
        }

        return answer;
    }

}