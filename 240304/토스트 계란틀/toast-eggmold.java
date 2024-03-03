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
    static int di[] = {0,1,-1,0};
    static int dj[] = {1,0,0,-1};
    static int n,l,r;
    static int eggbox[][];
   
    static boolean visit[][];
    static int result;
    static ArrayList<point> list;

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
            
            int check = 0;
            visit = new boolean[n][n];
            
            // 1. 돌면서 0이 아니면 상하좌우 bfs 가기.
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(!visit[i][j]){
                        visit[i][j] = true;
                        bfs(i,j);

                        if(list.size() > 1)check++;

                        // System.out.println("result : " + result);

            // 계산한값 덮어씌우기
            for(int h=0;h<list.size();h++){
                // System.out.println("x : " + list.get(h).x + "y : " + list.get(h).y);
                eggbox[list.get(h).x][list.get(h).y] = result;
                    }

                    }
                }
            }

            if(check == 0)break;
            else{
               answer++; 
            }
            
            
        }
        System.out.println(answer);
        
    }
    public static void bfs(int i, int j){

    list = new ArrayList<>();

    list.add(new point(i,j));

    Queue<point> que = new LinkedList<>();

    que.offer(new point(i, j));

    int cnt = 1;
    int sum = eggbox[i][j];

    while(!que.isEmpty()){

        point po = que.poll();

       for(int k=0;k<4;k++){
            int nexti = po.x + di[k];
            int nextj = po.y + dj[k];
                if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < n && !visit[nexti][nextj]){
                    int dif = Math.abs(eggbox[po.x][po.y] - eggbox[nexti][nextj]);
                        if(dif >= l && dif <= r){                         
                            que.offer(new point(nexti,nextj));
                            visit[nexti][nextj] = true;
                            list.add(new point(nexti, nextj));
                            sum += eggbox[nexti][nextj];
                            cnt++;
                        }
                }
        } 
    }
    
    result = sum/cnt;
     
    }
}