class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        //플로이드 와샬로, 각 모든 정점에서 다른 모든 정점으로의 최단거리 계산
        //그리고 특정 노드를 선택 후, 해당 노드에서 목적지로의 최솟값 계산 + S에서 특정 노드 거리 계산 => 최솟값이 정답
        
        int[][] dist = new int[n + 1][n + 1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == j) continue;
                dist[i][j] = 100000000;
            }
        }
        
        for(int i = 0; i < fares.length; i++) {
            int aa = fares[i][0];
            int bb = fares[i][1];
            int cc = fares[i][2];

            dist[aa][bb] = cc;
            dist[bb][aa] = cc;
        }
        
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(dist[i][k] == 100000000 || dist[k][j] == 100000000) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        
        for(int i = 1; i <= n; i++) {
            //i 노드 선택
            //i 노드에서 a/b로의 최단 거리 계산
            int result = dist[i][a] + dist[i][b];
            
            //S에서 i까지 거리 계산
            result += dist[s][i];
            
            //합의 최솟값 계산
            answer = Math.min(answer, result);
        }
        
        return answer;
    }
}