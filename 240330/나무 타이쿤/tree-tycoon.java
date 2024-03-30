import java.util.*;

public class Main {

    static class point{
        int x;
        int y;
        int add;

        point(int x, int y){
            this.x = x;
            this.y = y;
        }

        point(int x, int y, int add){
            this.x = x;
            this.y = y;
            this.add = add;
        }
    }

    static int di[] = {0,0,-1,-1,-1,0,1,1,1};
    static int dj[] = {0,1,1,0,-1,-1,-1,0,1};

    static int DI[] = {-1,-1,1,1};
    static int DJ[] = {-1,1,-1,1};

    static boolean visit[][];

    static ArrayList<point> addlist;

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

            addlist = new ArrayList<>();

            visit = new boolean[n][n];

            int dir = sc.nextInt();
            
            int can = sc.nextInt();

            // 특수 영양제를 이동 규칙에 따라 이동시킵니다.
            int cnt = nutri.size();

            while(cnt > 0){
                
                cnt--;

                point po = nutri.poll();
                
                int x = (n + po.x + di[dir] * (can % n)) % n;
                int y = (n + po.y + dj[dir] * (can % n)) % n;
                // int y = (po.y + dj[dir] * can +n* can)%n;

                area[x][y]++;

                nutri.offer(new point(x, y));
            } // while


            // 이동 시킨 특수 영양제를 투입한다. 그 자리 나무 수 +1과 true;
            while(!nutri.isEmpty()){

                point po = nutri.poll();

                int check = 0;

                for(int k=0;k<4;k++){

                int nexti = DI[k] + po.x;
                int nextj = DJ[k] + po.y;

                if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < n && area[nexti][nextj] > 0){
                    check++;
                }
                    }

                addlist.add(new point(po.x, po.y, check));

                visit[po.x][po.y] = true;


            }
            
                for(int i=0;i<addlist.size();i++){
                    point po = addlist.get(i);

                    // System.out.println("x : " + po.x + "y :" + po.y + "add : " + po.add);

                    area[po.x][po.y] += po.add;
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

            // System.out.println(nutri.size());

            // System.out.println(area[0][3]);

        
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