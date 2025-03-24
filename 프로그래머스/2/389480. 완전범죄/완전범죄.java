import java.util.*;

class Solution {
    
    //모든 물건 훔쳐야됨
    //단, A가 최소가 되는쪽으로 유도
    //불가능하면 -1
    //정렬
        //A 내림차순, 같으면 B 오름차순 -> B부터 선택

    private static class Evidence implements Comparable<Evidence> {
        int a, b;
        
        public Evidence(int a, int b) {
            this.a = a;
            this.b = b;
        }
        
        //(3,1), (2,1)
        @Override
        public int compareTo(Evidence o1) {
            double originRatio = (double) this.a / this.b;
            double objectRatio = (double) o1.a / o1.b;
            
            if(Double.compare(originRatio, objectRatio) == 0) {
                return Integer.compare(o1.b, this.b);
            }
            return Double.compare(objectRatio, originRatio);
        }
    }
    
    private static List<Evidence> evidences = new ArrayList<>();
    
    public int solution(int[][] info, int n, int m) {
        
        for(int i = 0; i < info.length; i++) {
            evidences.add(new Evidence(info[i][0], info[i][1]));
        }
        
        Collections.sort(evidences);
        
        int answer = 0;
        for(int i = 0; i < evidences.size(); i++) {
            Evidence evidence = evidences.get(i);
            int a = evidence.a;
            int b = evidence.b;
            
            if(m > b) {
                m -= b;
            } else if(n > a) {
                n -= a;
                answer += a;
            } else {
                return -1;
            }
        }
        
        return answer;
    }
}