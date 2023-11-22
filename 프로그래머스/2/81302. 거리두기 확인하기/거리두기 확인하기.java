class Solution {
    
    public static int[] dx = {-1,0,1,0};
    public static int[] dy = {0,1,0,-1};
    public static int[][] visited = new int[5][5];
    public static int result;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i=0;i<5;i++){
            char[][] place = new char[5][5];
            for(int j=0;j<5;j++){
                place[j] = places[i][j].toCharArray();
            }
            
            result = 1;
            for(int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                    if(place[j][k]=='P'){
                        visited[j][k]=1;
                        dfs(place,j,k,0);
                        visited[j][k]=0;
                    }
                }
            }
            answer[i] = result;
        }
        
        return answer;
    }
    
    public static void dfs(char[][] place, int x, int y,int count){
        if(place[x][y]=='X'){
            return;
        }
        
        if(place[x][y]=='P' && visited[x][y]==0){
            result = 0;
            return;
        }
        
        if(count<2){
            for(int i=0;i<4;i++){
                int nx = dx[i]+x;
                int ny = dy[i]+y;

                if(0<= nx && nx< 5 && 0<=ny && ny<5){
                    dfs(place,nx,ny,count+1);
                }
            }
        }
    }
}