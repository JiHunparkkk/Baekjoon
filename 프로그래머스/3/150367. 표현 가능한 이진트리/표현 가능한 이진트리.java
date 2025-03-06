class Solution {

    private static int result;
    
    public int[] solution(long[] numbers) {

        int[] answer = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            String number = Long.toBinaryString(numbers[i]);
            int maxLength = calMaxLength(number.length());
                
            while(number.length() < maxLength) {
                number = "0" + number;
            }
            
            result = 1;
            if(number.charAt(number.length() / 2) == '0') {
                result = 0;
            } else {
                checkRoot(number.length() / 2, number, (number.length() + 1) / 4, false);
            }
            answer[i] = result;
        }
        
        return answer;
    }
    
    private static int calMaxLength(int length) {
        int target = 0;
        int cnt = 0;
        while(target < length) {
            target += (int)Math.pow(2, cnt);
            cnt++;
        }
        return target;
    }
    
    private static void checkRoot(int mid, String number, int plus, boolean flag) {
        int leftChild = mid - plus;
        int rightChild = mid + plus;

        if(mid < 0 || mid >= number.length()) return;
        if(leftChild < 0 || leftChild >= number.length() || rightChild < 0 || rightChild >= number.length()) return;
        // System.out.println(leftChild + ", " + mid +", " + rightChild);
        
        if(number.charAt(mid) == '0') {
            if (number.charAt(leftChild) == '0' && number.charAt(rightChild) == '0') {
                // checkRoot(leftChild, number, plus / 2, true);
                // checkRoot(leftChild, number, plus / 2, true);
                // return;
            } else {
                result = 0;
            }
        }
        
        if(leftChild % 2 == 0 || rightChild % 2 == 0) return;
                
        checkRoot(leftChild, number, plus / 2, flag);
        checkRoot(rightChild, number, plus / 2, flag);
    }
    
}