class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                find(n, i, computers, visited);
                answer++;
            }
        }
        
        return answer;
    }
    
    private static void find(int n, int x, int[][] computers, boolean[] visited) {
        for(int i = 0; i < n; i++) {
            if(i == x) continue;
            if(computers[x][i] == 1 && !visited[i]) {
                visited[i] = true;
                find(n, i, computers, visited);
            } 
        }
    }
}