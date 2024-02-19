import java.util.*;

public class Main {

    static class work{
        int time;
        int price;

        work(int time, int price){
            this.time = time;
            this.price = price;
        }
    }

    static work career[];
    static int result[];
    static int n;
    static int answer;
    

    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        career = new work[n];

        for(int i=0;i<n;i++){
            int time = sc.nextInt();
            int price = sc.nextInt();
            answer = Math.max(answer, price);
            career[i] = new work(time, price);
        }

        // System.out.println("2: " + answer);

        for(int i=2;i<=n;i++){
            result = new int [i];
            comb(0,0,i);

           
        }
        System.out.println(answer);

    }

    public static void comb(int idx, int start, int cnt){
        if(idx == cnt){
            int add = 0;
            boolean workvisit[] = new boolean[20];
            for(int i=0;i<idx;i++){
                for(int j=result[i];j<result[i] + career[result[i]].time;j++){
                    if(workvisit[j])return;
                    workvisit[j] = true;                    
                }
                
                add += career[result[i]].price;
            }
            answer = Math.max(answer, add);
            return;
        }

        for(int i=start;i<n;i++){
            result[idx] = i;
            comb(idx+1, i+1, cnt);
        }
    }
}