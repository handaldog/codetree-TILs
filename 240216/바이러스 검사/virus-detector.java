import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        
        // Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long answer = 0L;
        
        long custom[] = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++){
            custom[i] = Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int team = Integer.parseInt(st.nextToken());
        int teamone = Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++){
            if(custom[i] <= team){
                answer++;
            }
            else if(custom[i] > team){
                custom[i] -= team;

                answer += custom[i]/teamone+1;

                if(custom[i]%teamone > 0){
                    answer++;
                }
                
            }
        }

        System.out.println(answer);

    }
}