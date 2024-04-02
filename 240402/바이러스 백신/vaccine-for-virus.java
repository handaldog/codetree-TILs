import java.util.*;

public class Main {

    static class point{
        int x;
        int y;
        int time;

        point(int x, int y){
            this.x = x;
            this.y = y;
        }
        point(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int di[] = {0,0,1,-1};
    static int dj[] = {1,-1,0,0};

    static boolean flag = true;

    static int n,m, realtime, answer = 1000;
    static ArrayList<point> hospital = new ArrayList<>();
    static int result[];
    static int area[][];
    static int areacopy[][];
    static boolean visit[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n  = sc.nextInt();
        m  = sc.nextInt();

        area = new int [n][n];
        areacopy = new int [n][n];
        result = new int [m];

        for(int i=0;i<n;i++){
          Arrays.fill(areacopy[i], 100);
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                area[i][j] = sc.nextInt();
                if(area[i][j] == 2){
                    hospital.add(new point(i,j));
                }
            }
        }

        comb(0,0);
        if(answer == 1000){
            System.out.println(-1);
        }
        else {

        System.out.println(answer);
        }
    }

    public static void comb(int start, int idx){
        if(idx == m){
            realtime = 0;
            flag = true;
            for(int i=0;i<n;i++){
                Arrays.fill(areacopy[i], 100);
            }
            for(int h=0;h<idx;h++){
                point po = hospital.get(result[h]);
                // System.out.println(po.x + " " + po.y);

                visit = new boolean[n][n];
                areacopy[po.x][po.y] = 0;

                bfs(po.x,po.y);
            }
            // for(int c=0;c<n;c++){
            //     for(int q=0;q<n;q++){
            //         System.out.print(areacopy[c][q] + "    ");
            //     }
            // System.out.println();

            // }
            // System.out.println("-----------------");
            check();
            if(flag){
            // System.out.println(realtime);
                answer = Math.min(realtime, answer);
            }

            return;
        }

        for(int i=start;i<hospital.size();i++){
            result[idx] = i;
            comb(i+1, idx+1);
        }
    }

    public static void bfs(int x, int y){
        Queue<point> que = new LinkedList<>();
        que.add(new point(x,y,0));

        while(!que.isEmpty()){

        point po = que.poll();

        for(int k=0;k<4;k++){
            int nexti = po.x + di[k];
            int nextj = po.y + dj[k];

            if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < n 
            && area[nexti][nextj] != 1 && !visit[nexti][nextj]){
                que.add(new point(nexti, nextj, po.time +1));
                areacopy[nexti][nextj] = Math.min(areacopy[nexti][nextj], po.time + 1); 
                visit[nexti][nextj] = true;
                
            }
        }

        }
    }

    public static void check(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(area[i][j] == 0 && areacopy[i][j] == 100){
                    flag = false;
                    return;
                }
                if(area[i][j] == 0 && areacopy[i][j] < 100){
                realtime = Math.max(realtime, areacopy[i][j]);

                }
                
            }
        }
    }
}