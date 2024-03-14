import java.util.*;

public class Main {

    static class point{
        int num;
        int x;
        int y;

        point(int num, int x, int y){
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    // 북 동 남 서
    static int di[] = {-1,0,1,0};
    static int dj[] = {0,1,0,-1};

    static int n,m, other, me, min = 10000000;

    static int result[]; 
    static boolean visit[][];
    static int area[][];

    static ArrayList<point> cctvlist;

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        area = new int[n][m];

        cctvlist = new ArrayList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                area[i][j] = sc.nextInt();

                if(area[i][j] != 0 && area[i][j] != 6){
                    cctvlist.add(new point(area[i][j], i, j));
                }
                if(area[i][j] == 6)other++;
            }
        }

        result = new int[cctvlist.size()];

        permu(0,cctvlist.size());
        System.out.println(min);


    }

    public static void permu(int idx, int cctvcnt){
        if(idx == cctvcnt){

            visit = new boolean[n][m];
            me = 0;

            for(int i=0;i<idx;i++){
                visit[cctvlist.get(i).x][cctvlist.get(i).y] = true;
                dfs(cctvlist.get(i), result[i]);
            }

            // System.out.println(me);

            min = Math.min(min, n*m - (other + cctvlist.size() + me));

            return;
        }
        for(int k=0;k<4;k++){
            result[idx] = k;
            permu(idx+1, cctvcnt);
        }
    }

    public static void dfs(point cctv, int dir){
        int cctvnum = cctv.num;

        if(cctvnum == 1){
            if(dir == 0)go(cctv.x, cctv.y, 0);
            else if(dir == 1)go(cctv.x, cctv.y, 1);
            else if(dir == 2)go(cctv.x, cctv.y, 2);
            else if(dir == 3)go(cctv.x, cctv.y, 3);
        }
        else if(cctvnum == 2){
            if(dir%2 == 0){
                go(cctv.x, cctv.y, 1);
                go(cctv.x, cctv.y, 3);
                
            }
            else if(dir%2 == 1){
                go(cctv.x, cctv.y, 0);
                go(cctv.x, cctv.y, 2);
                
            }
        }
        else if(cctvnum == 3){
            if(dir == 0){
                go(cctv.x, cctv.y, 0);
                go(cctv.x, cctv.y, 1);
            }
            else if(dir == 1){
                go(cctv.x, cctv.y, 1);
                go(cctv.x, cctv.y, 2);
            }
            else if(dir == 2){
                go(cctv.x, cctv.y, 2);
                go(cctv.x, cctv.y, 3);
            }
            else if(dir == 3){
                go(cctv.x, cctv.y, 3);
                go(cctv.x, cctv.y, 0);
            }
        }
        else if(cctvnum == 4){
            if(dir == 0){
                go(cctv.x, cctv.y, 0);
                go(cctv.x, cctv.y, 1);
                go(cctv.x, cctv.y, 2);
            }
            else if(dir == 1){
                go(cctv.x, cctv.y, 1);
                go(cctv.x, cctv.y, 2);
                go(cctv.x, cctv.y, 3);
            }
            else if(dir == 2){
                go(cctv.x, cctv.y, 2);
                go(cctv.x, cctv.y, 3);
                go(cctv.x, cctv.y, 0);
            }
            else if(dir == 3){
                go(cctv.x, cctv.y, 3);
                go(cctv.x, cctv.y, 0);
                go(cctv.x, cctv.y, 1);
            }
        }
        else if(cctvnum == 5){
                go(cctv.x, cctv.y, 0);
                go(cctv.x, cctv.y, 1);
                go(cctv.x, cctv.y, 2);
                go(cctv.x, cctv.y, 3);
        }
    }
    public static void go(int x, int y, int dir){

        int nexti = x + di[dir];
        int nextj = y + dj[dir];

        if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < m){
            if(area[nexti][nextj] == 6)return;
            else if(visit[nexti][nextj] || area[nexti][nextj] > 0){
                go(nexti,nextj, dir);
            }
            else{
                visit[nexti][nextj] = true;
                me++;
                go(nexti, nextj, dir);
            }
            
        }
        
    }
}