import java.util.*;

class Solution {
    private static class Evidence {
        int a, b;
        double ratio;
        
        public Evidence(int a, int b) {
            this.a = a;
            this.b = b;
            this.ratio = (double) a / b; // A/B 비율 계산
        }
    }

    public int solution(int[][] info, int n, int m) {
        List<Evidence> evidences = new ArrayList<>();
        
        for (int[] item : info) {
            evidences.add(new Evidence(item[0], item[1]));
        }

        // 정렬: 비율 내림차순, 같은 비율이면 B 내림차순
        evidences.sort((o1, o2) -> {
            if (Double.compare(o2.ratio, o1.ratio) == 0) {
                return Integer.compare(o2.b, o1.b); // B 내림차순
            }
            return Double.compare(o2.ratio, o1.ratio); // 비율 내림차순
        });

        boolean[] visited = new boolean[evidences.size()];
        int a = 0, b = 0;

        // 먼저 B를 채우기
        for (int i = 0; i < evidences.size(); i++) {
            Evidence item = evidences.get(i);
            if (m > b + item.b) {
                b += item.b;
                visited[i] = true;
            }
        }

        // 남은 A를 채우기
        for (int i = 0; i < evidences.size(); i++) {
            if (visited[i]) continue;
            Evidence item = evidences.get(i);
            if (n > a + item.a) {
                a += item.a;
                visited[i] = true;
            }
        }

        // 모든 항목을 선택할 수 없으면 -1 반환
        for (boolean v : visited) {
            if (!v) return -1;
        }
        return a;
    }
}
