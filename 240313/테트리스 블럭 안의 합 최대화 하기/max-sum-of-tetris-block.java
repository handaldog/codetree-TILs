import java.util.*;

public class Main {

    static int di[] = {0,0,1,-1};
    static int dj[] = {1,-1,0,0};

    static int otheri[] = {-1,0,1,0};
    static int otherj[] = {0,1,0,-1};

    static int area[][];
    static boolean visit[][];
    static int result[] = new int [3];

    static int n, m;
    static int max = -1;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        area = new int [n][m];
        visit = new boolean [n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                area[i][j] = sc.nextInt();
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                visit[i][j] = true;
                dfs(i,j,1,area[i][j]);
                visit[i][j] = false;
                comb(i,j,0,0,area[i][j]);
            }
        }

        System.out.println(max);




    }
    public static void dfs(int i, int j, int cnt, int sum){
        if(cnt == 4){
            // 최대값
            max = Math.max(sum, max);
            return;
        }

        for(int k=0;k<4;k++){
            int nexti = i + di[k];
            int nextj = j + dj[k];

            if(nexti >=0 && nextj >=0 && nexti < n && nextj < m && !visit[nexti][nextj]){
                visit[nexti][nextj] = true;
                dfs(nexti, nextj, cnt+1, sum+area[nexti][nextj]);
                visit[nexti][nextj] = false;
            }
        }
    }

    public static void comb(int x, int y, int start, int idx, int res){
        if(idx == 3){
            for(int i=0;i<3;i++){
                int nexti = x + otheri[result[i]];
                int nextj = y + otherj[result[i]];

                if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < m){
                    res += area[nexti][nextj];
                }
                else{
                    return;
                }
                max = Math.max(max, res);
            }
            
            return;
        }
        for(int k=start;k>4;k++){
            result[idx] = k;
            comb(x,y,k+1, idx+1,res);
        }
    }
}