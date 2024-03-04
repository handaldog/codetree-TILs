import java.util.*;

public class Main {

    static class point{
        int x;
        int y;
        int costs;

        point(int x, int y, int costs){
            this.x = x;
            this.y = y;
            this.costs = costs;
        }

        point(int x, int y){
            this.x = x;
            this.y = y;
            
        }
    }
    static long min = 10000000L;
    static int n,m;
    static long distance;
    static int result[];
    static int local[][];
    static boolean localcopy[][];
    static ArrayList<point> hospital = new ArrayList<>();
    static ArrayList<point> person = new ArrayList<>();

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

         n = sc.nextInt();
         m = sc.nextInt();

        local = new int [n][n];
        

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                local[i][j] = sc.nextInt();
                if(local[i][j] == 2){
                    hospital.add(new point(i,j));
                }
                if(local[i][j] == 1){
                    person.add(new point(i,j,0));
                }
            }
        }

        result = new int [m];

        comb(0,0);

        System.out.println(min);

    }

    public static void comb(int start, int idx){
        if(idx == m){
            distance = 0;
            localcopy = new boolean[n][n];
            for(int i=0;i<m;i++){
                localcopy[hospital.get(result[i]).x][hospital.get(result[i]).y] = true;
            }

            for(int i=0;i<person.size();i++){
                bfs(person.get(i).x, person.get(i).y,0);
        }

        min = Math.min(min, distance);
            return;
        }
        for(int i=start;i<hospital.size();i++){
            result[idx] = i;
            comb(i+1, idx+1);
        }
    }

    public static void bfs(int x, int y, int costs){
        
        Queue<point> que = new LinkedList<>();

        boolean visit[][] = new boolean[n][n];

        que.offer(new point(x,y,costs));

        int di[] = {0,0,1,-1};
        int dj[] = {1,-1,0,0};

        while(!que.isEmpty()){

        point po = que.poll();

        visit[po.x][po.y] = true;

          for(int k=0;k<4;k++){
            int nexti = po.x + di[k];
            int nextj = po.y + dj[k];

            if(nexti >=0 && nextj >=0 && nexti < n && nextj < n && !visit[nexti][nextj]){
                if(local[nexti][nextj] == 2 && localcopy[nexti][nextj]){
                    distance += po.costs+1;
                    if(distance >= min){
                        return;
                    }
                    return;
                }
                else{
                    visit[nexti][nextj] = true;
                    que.offer(new point(nexti,nextj, po.costs+1));
                    }
                }
            }  
        }
        //return;
        
    }
}