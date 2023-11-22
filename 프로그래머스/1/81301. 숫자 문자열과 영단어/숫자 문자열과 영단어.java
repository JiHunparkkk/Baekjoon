class Solution {
    public int solution(String s) {
         int answer = 0;
        String[] number = {"zero", "one", "two", "three", "four", "five",
                           "six","seven", "eight", "nine"};

        for (int i = 0; i < 10; i++) {
            s = s.replaceAll(number[i], i + "");
        }

        for (int i = 0; i < s.length(); i++) {
            answer = answer * 10 + s.charAt(i) - '0';
        }

        return answer;
    }
}