import java.util.*;

public class Main {

    static class point{
        int x;
        int y;
        
        point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int checkbox[][];
    static int di[] = {0,1};
    static int dj[] = {1,0};
    static int n,l,r;
    static int eggbox[][];
    static int eggboxcopy[][];
    static boolean visit[][];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        eggbox = new int [n][n];

    
         l = sc.nextInt();
         r  = sc.nextInt();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                eggbox[i][j] = sc.nextInt();
            }
        }
        
        int answer = 0;
        while(true){

            eggboxcopy = new int [n][n];
            visit = new boolean[n][n];
            
            // 1. 돌면서 0이 아니면 상하좌우 bfs 가기.
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(eggboxcopy[i][j] == 0){
                        bfs(i,j);
                    }
                }
            }
            
            int midcheck = 0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(eggboxcopy[i][j] != 0){
                       eggbox[i][j] = eggboxcopy[i][j]; 
                       midcheck++;
                    }
                    

                }
            }

            if(midcheck == 0)break;
            else{
                answer++;
            }

            // 끝났어.
        }
        System.out.println(answer);
        
    }
    public static void bfs(int i, int j){
    Queue<point> que = new LinkedList<>();

    checkbox = new int [n][n];

    que.offer(new point(i, j));

    int cnt = 1;
    int sum = eggbox[i][j];

    while(!que.isEmpty()){

        point po = que.poll();

       for(int k=0;k<2;k++){
            int nexti = po.x + di[k];
            int nextj = po.y + dj[k];
                if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < n && !visit[nexti][nextj]){
                    int dif = Math.abs(eggbox[i][j] - eggbox[nexti][nextj]);
                        if(dif >= l && dif <= r){
                            // System.out.println("1 : " + dif);
                            que.offer(new point(nexti,nextj));
                            checkbox[nexti][nextj] = 1;
                            visit[nexti][nextj] = true;
                            sum += eggbox[nexti][nextj];
                            cnt++;
                        }
                }
        } 
    }
    int result =0; 
    try{
         result = sum/cnt;
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
    
    // 계산한값 덮어씌우기
    for(int h=0;h<n;h++){
        for(int b=0;b<n;b++){
            if(checkbox[h][b] == 1){
                eggboxcopy[h][b] = result;
            }
        }
    }

     
    }
}