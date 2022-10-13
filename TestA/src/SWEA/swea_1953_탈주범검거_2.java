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
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_1953_탈주범검거_2 {
	static int N, M, R, C, L, map[][], cnt;
	static int dx[] = {-1, 0, 1, 0}; //상 우 하 좌
	static int dy[] = {0, 1, 0, -1}; //0 1 2 3
	
	static boolean visited[][];
	static String type[] = {null, "0123", "02", "13", "01", "12", "23", "03"};
	public static void main(String[] args) throws Exception{
       System.setIn(new FileInputStream("res/input_swea_1953.txt"));
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = null;
       
       int T = Integer.parseInt(br.readLine());
       for(int tc=1; tc<=T; tc++) {
    	   st = new StringTokenizer(br.readLine());
    	   N = Integer.parseInt(st.nextToken()); //세로
    	   M = Integer.parseInt(st.nextToken()); //가로
    	   R = Integer.parseInt(st.nextToken()); //맨홀의 세로위치
    	   C = Integer.parseInt(st.nextToken()); //맨홀의 가로위치
    	   L = Integer.parseInt(st.nextToken()); //탈출후 소요된시간
    	   
    	   map = new int[N][M];
    	   visited = new boolean[N][M];
    	   for(int i=0; i<N; i++) {
    		   st = new StringTokenizer(br.readLine());
    		   for(int j=0; j<M; j++) {
    			   map[i][j] = Integer.parseInt(st.nextToken());
    		   }
    	   }
    	       	   
    	   System.out.println("#" + tc + " " + bfs(R, C, 1));
       }
    }

	private static int bfs(int r, int c, int time) {
		Queue<Point> q = new ArrayDeque<>();
		int ans = 1;
		q.offer(new Point(r, c, map[r][c], 0));
		visited[r][c] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int s=0; s<size; s++) {
				Point cur = q.poll();
				
				if(cur.time == L) break;
				
				for(int d=0; d<type[cur.type].length(); d++) {
					int curDir = type[cur.type].charAt(d)-'0';
					int nx = cur.x + dx[curDir];
					int ny = cur.y + dy[curDir];
					
					if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
					
					if(map[nx][ny] != 0 && !visited[nx][ny] && type[map[nx][ny]].contains(Integer.toString((curDir+2)%4))) {
						q.offer(new Point(nx, ny, map[nx][ny], time+1));
						visited[nx][ny] = true;
						ans++; //갈 수 있는 장소
					}
				}
			}
			time++;
		}
		return ans;
	}
	
	static class Point {
		int x, y, type, time;

		public Point(int x, int y, int type, int time) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
			this.time = time;
		}
	}
}