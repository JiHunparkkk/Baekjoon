class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        StringBuilder sb = new StringBuilder();

        int[] left = {3, 0};
        int[] right = {3, 2};

        for (int number : numbers) {
            int index = 3;
            int moving = 4;
            switch (number) {
                case 1:
                    index--;
                case 4:
                    index--;
                case 7:
                    index--;
                    left[0] = index;
                    left[1] = 0;
                    sb.append("L");
                    break;

                case 3:
                    index--;
                case 6:
                    index--;
                case 9:
                    index--;
                    right[0] = index;
                    right[1] = 2;
                    sb.append("R");
                    break;

                case 2:
                    moving--;
                case 5:
                    moving--;
                case 8:
                    moving--;
                case 0:
                    moving--;

                    int leftDist = Math.abs(moving - left[0]) + Math.abs(1 - left[1]);
                    int rightDist = Math.abs(moving - right[0]) + Math.abs(1 - right[1]);
                    if (leftDist < rightDist) {
                        left[0] = moving;
                        left[1] = 1;
                        sb.append("L");
                    } else if (leftDist > rightDist) {
                        right[0] = moving;
                        right[1] = 1;
                        sb.append("R");
                    } else {
                        if (hand.equals("left")) {
                            left[0] = moving;
                            left[1] = 1;
                            sb.append("L");
                        } else {
                            right[0] = moving;
                            right[1] = 1;
                            sb.append("R");
                        }
                    }

                    break;
            }
        }
        answer = sb.toString();

        return answer;
    }
}