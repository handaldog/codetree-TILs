import java.util.*;

public class Main {

    static int result[] = new int [3];
    static int n,m,ffire, secuwall;
    static int area[][];
    static boolean visit[][];
    static boolean wall[][];
    static ArrayList<Integer> fire = new ArrayList<>();

    static int di[] = {0,0,1,-1};
    static int dj[] = {1,-1,0,0};

    static int max = -1;


    public static void main(String[] args) {
       
       Scanner sc = new Scanner(System.in);

       n = sc.nextInt();
       m = sc.nextInt();

       area = new int [n][m];
       wall = new boolean[n][m];
       

       for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            area[i][j] = sc.nextInt();
            if(area[i][j] == 2){
                fire.add((i*m)+j);
                wall[i][j] = true;
                }
            if(area[i][j] == 1){
                secuwall++;
                wall[i][j] = true;
            }
            }
       }

       comb(0,0);
       System.out.println(max);


    }
    public static void comb(int start, int idx){
        if(idx == 3){
            ffire = 0;
            
            for(int i=0;i<3;i++){
                wall[result[i]/m][result[i]%m] = true;
            }

            visit = new boolean[n][m];

            for(int i=0;i<fire.size();i++){
                ffire++;
                visit[fire.get(i)/m][fire.get(i)%m] = true;
                dfs(fire.get(i)/m, fire.get(i)%m);
            }

            max = Math.max(max, (n*m) - (ffire + 3 + secuwall));
            
            for(int i=0;i<3;i++){
                wall[result[i]/m][result[i]%m] = false;
            }
            
            return;
        }

        for(int k=0;k<n*m;k++){
            if(wall[k/m][k%m])continue;
            result[idx] = k;
            comb(k+1, idx+1);
        }
    }

    public static void dfs(int i, int j){
        for(int k=0;k<4;k++){
            int nexti = i + di[k];
            int nextj = j + dj[k];

            if(nexti >= 0 && nextj >=0 && nexti < n && nextj < m 
            && !visit[nexti][nextj] && !wall[nexti][nextj]){
                visit[nexti][nextj] = true;
                ffire++;
                dfs(nexti, nextj);
            }
        }
    }
}