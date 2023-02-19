import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        String str = br.readLine();

        //공백,문장의 끝,<>등을 만나면 문장을 뒤집기 위해 첫 단어의 위치를 기록할 j 선언
        for(int i=0, j=0;i<str.length();i++){
            
            if(str.charAt(i)=='<'){
                while(str.charAt(i)!='>'){
                    sb.append(str.charAt(i));
                    i++;
                    j=i+1;//아래 else if로직에서 공백부터 j시작 위치까지 단어를 넣으므로 마지막 i의 위치가(i++하였으므로) '>'이므로 j=i+1을 넣어준다
                }
                sb.append(">");
                
            }else if(str.charAt(i)==' ' || str.length()-1==i || str.charAt(i+1)=='<'){  //str.charAt(i+1)은 잘못하면 범위를 벗어나게 되지만 앞의 두 조건에 다 걸러짐
                
                int tmp;   
                if(str.charAt(i)==' ') 
                    tmp = i-1;  //공백부터 뒤집을 문자를 넣으면 안되므로 공백 전 문자의 위치를 저장
                else
                    tmp = i;    //if문 조건 자체가 문장의 끝은 마지막 문자에 i가 위치, '<'을 만났을때는 i+1을 해주었으므로 tmp=i를 해줘야 함.

                while(tmp!=j-1){
                    sb.append(str.charAt(tmp));
                    tmp--;
                }

                if(str.charAt(i)==' ' || str.length()-1==i)
                    sb.append(" ");

                j=i+1;
            }
        }//for

        System.out.println(sb);

    }//main
}//class
