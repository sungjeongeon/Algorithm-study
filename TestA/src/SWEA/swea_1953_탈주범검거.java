/*package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_1953_탈주범검거 {
	static int dx[] = {-1, 0, 0, 1}; //상좌우하
	static int dy[] = {0, -1, 1, 0}; 
	
	static String[] type= {null, "0312", "03", "12","02", "32", "31", "01"};
	static int N, M, R, C, L, map[][];
	static boolean[][] v;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_1953.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map=new int[N][M];
			v=new boolean[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			//for(int[] a:map) System.out.println(Arrays.toString(a));
			sb.append("#" + tc + " " + bfs(R,C,1) + "\n");
		}
		
		
		System.out.println(sb);
	}

	static int bfs(int x, int y, int time) {
		int ans = 0;
		Queue<Point> q = new LinkedList<>();
		v[x][y] = true;
		q.offer(new Point(x,y, time));
		ans++;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if(cur.time == L) break;
			String curType = type[map[cur.x][cur.y]];
			
			for(int c=0; c<curType.length(); c++) {
				int d = curType.charAt(c)-'0';
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
				
				if(map[nx][ny] != 0 && !v[nx][ny] && type[map[nx][ny]].contains(Integer.toString(3-d))) {
					v[nx][ny] = true;
					q.offer(new Point(nx,ny, time+1));
					ans++;
				}
			}
		}
		return ans;
	}
	
	static class Point {
		int x, y, time;

		public Point(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
	}
}*/
package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_1953_탈주범검거 {
	static int di[] = {-1, 0, 0, 1}; //상좌우하
	static int dj[] = {0, -1, 1, 0}; 
	
	static String[] type= {null, "0312", "03", "12","02", "32", "31", "01"};
	static int N, M, R, C, L, map[][];
	static boolean[][] v;
	
	public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("res/input_swea_1953.txt"));
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();
        StringTokenizer st=new StringTokenizer(br.readLine()," ");
        int T=Integer.parseInt(st.nextToken());
        
        for(int tc=1; tc<=T; tc++){
            st=new StringTokenizer(br.readLine()," ");
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
            R=Integer.parseInt(st.nextToken());
            C=Integer.parseInt(st.nextToken());
            L=Integer.parseInt(st.nextToken());
            map=new int[N][M];
            v=new boolean[N][M];
            for(int i=0; i<N; i++){
                st=new StringTokenizer(br.readLine()," ");
                for(int j=0; j<M; j++){
                    map[i][j]=Integer.parseInt(st.nextToken());
                }
            }
            //for(int[] a:map) System.out.println(Arrays.toString(a));System.out.println();
            sb.append("#").append(tc).append(" ").append(bfs(R,C,1)).append("\n");
        }        
        System.out.print(sb.toString());
        br.close();
    }
	
	 static int bfs(int i,int j,int time){
        int ans=0;
        ArrayDeque<int[]> q=new ArrayDeque<>();
        v[i][j]=true;
        q.offer(new int[]{i,j,time});
        ans++;
        while(!q.isEmpty()){
            int[] ij=q.poll();
            i=ij[0];
            j=ij[1];
            time=ij[2];
            if(time==L) break;
            String cur=type[map[i][j]];
            for(int c=0; c<cur.length(); c++){
                int d=cur.charAt(c)-'0';
                int ni=i+di[d];
                int nj=j+dj[d];
                if(0<=ni&&ni<N && 0<=nj&&nj<M
                        && map[ni][nj]!=0
                        && !v[ni][nj]
                        && type[map[ni][nj]].contains(""+(3-d))){//Integer.toString(3-d)
                    v[ni][nj]=true;
                    q.offer(new int[]{ni,nj,time+1});
                    ans++;
                }
            }
        }
        return ans;
    }
}