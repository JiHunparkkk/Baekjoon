import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static int size=0;
    public static int start=0;
    public static int[] queue;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        queue = new int[n];

        while(n-->0){
            st = new StringTokenizer(br.readLine(), " ");

            switch(st.nextToken()){
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(pop()).append("\n");
                    break;
                case "size":
                    sb.append(size()).append("\n");
                    break;
                case "empty":
                    sb.append(empty()).append("\n");
                    break;
                case "front":
                    sb.append(front()).append("\n");
                    break;
                case "back":
                    sb.append(back()).append("\n");
                    break;
            }// switch
        }// while

        System.out.println(sb);

    }// main

    public static void push(int num){
        queue[size++] = num;
    }

    public static int pop(){
        if(start==size) {
            return -1;
        }
        int rst = queue[start];
        queue[start] = 0;
        start++;
        return rst;
    }

    public static int size(){
        return size-start;
    }

    public static int empty() {
        if(start==size){
            return 1;
        }else{
            return 0;
        }
    }

    public static int front(){
        if(start==size){
            return -1;
        }else{
            return queue[start];
        }
    }

    public static int back(){
        if(start==size){
            return -1;
        }else{
            return queue[size-1];
        }
    }
}
