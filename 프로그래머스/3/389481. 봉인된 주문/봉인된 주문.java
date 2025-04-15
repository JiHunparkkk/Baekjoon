import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        
        Arrays.sort(bans, (o1, o2) -> {
            if(o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return Integer.compare(o1.length(), o2.length());
        });
        
        
        for(int i = 0; i < bans.length; i++) {
            String target = getStringFrom(n);
            
            if(bans[i].length() < target.length() || (bans[i].length() == target.length() && bans[i].compareTo(target) <= 0)) {
                n++;
            } else {
                return target;
            }
        }
        
        return getStringFrom(n);
    }
    
    private static String getStringFrom(long n) {
        StringBuilder sb = new StringBuilder();
        
        while(n > 0) {
            long remain = n % 26;
            n /= 26;
            if(remain == 0) {
                n--;
                sb.append("z");
            } else {
                sb.append((char)('a' + remain - 1));
            }
        }
        
        return sb.reverse().toString();
    }
}