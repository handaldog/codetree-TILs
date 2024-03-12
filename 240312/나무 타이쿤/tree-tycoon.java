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

    static int di[] = {0,0,-1,-1,-1,0,1,1,1};
    static int dj[] = {0,1,1,0,-1,-1,-1,0,1};

    static int DI[] = {-1,-1,1,1};
    static int DJ[] = {-1,1,-1,1};

    static boolean visit[][];

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int m = sc.nextInt();

        int area[][] = new int [n][n];

        

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                area[i][j] = sc.nextInt();
            }
        }

        Queue<point> nutri = new LinkedList<>();

        nutri.add(new point(n-1, 0));
        nutri.add(new point(n-2, 0));
        nutri.add(new point(n-1, 1));
        nutri.add(new point(n-2, 1));

        for(int year=0;year<m;year++){

            visit = new boolean[n][n];

            int dir = sc.nextInt();
            
            int can = sc.nextInt();

            // 특수 영양제를 이동 규칙에 따라 이동시킵니다.
            int cnt = nutri.size();
            while(cnt > 0){
                
                cnt--;

                point po = nutri.poll();
                
                int x = di[dir] * can;
                int y = dj[dir] * can;

                int nextx = x + po.x;
                int nexty = y + po.y;

                if(nextx < 0){
                    int gop = 0;
                    gop += nextx/n;
                    if(gop%n > 0)gop++;

                    nextx += (gop*n);
                }
                else{
                    nextx = nextx%n;
                }

                if(nexty < 0){
                    int gop = 0;
                    gop += nexty/n;
                    if(gop%n > 0)gop++;

                    nexty += (gop*n);
                }
                else{
                    nextx = nextx%n;
                }

                nutri.offer(new point(nextx, nexty));
            } // while

            // 이동 시킨 특수 영양제를 투입한다. 그 자리 나무 수 +1과 true;
            while(!nutri.isEmpty()){

                point po = nutri.poll();

                int check = 0;

                area[po.x][po.y]++;

                for(int k=0;k<4;k++){

                int nexti = DI[k] + po.x;
                int nextj = DJ[k] + po.y;

                if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < n && area[nexti][nextj] > 0){
                    check++;
                }
                    }

                area[po.x][po.y] += check;

                visit[po.x][po.y] = true;


            }

            // 특수 영양제를 투입한 부분 제외하고 높이가 2인 이상의 나무 높이를 -2 하고 
            // 해당 위치에 약물을 놓는다

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(!visit[i][j] && area[i][j] >= 2){
                        area[i][j] -= 2;
                        nutri.offer(new point(i,j));
                    }
                }
            }

        }


        int answer = 0;

        for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    answer += area[i][j];
                }
            }

            System.out.println(answer);

        }

    }