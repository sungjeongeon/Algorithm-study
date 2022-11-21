package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_4727_견우와직녀  {
	static int N, M, map[][];
	static boolean visited[][][];
	static int dx[] = {0, -1, 0, 1};
	static int dy[] = {1, 0, -1, 0};
	static int ans;
	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			ans = Integer.MAX_VALUE;
			
			map = new int[N][N];
			visited = new boolean[N][N][2];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			findCross();
			bfs();
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	private static void findCross() {		
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c]==0) {	// 절벽일 때
					cnt=0;
					// 좌상/ 좌하/ 우상/ 우하
					if((r-1>=0 && map[r-1][c]==0) || (r+1<N && map[r+1][c]==0)) cnt++;
					if((c-1>=0 && map[r][c-1]==0) || (c+1<N && map[r][c+1]==0)) cnt++;
					if(cnt==2) map[r][c]=-1;
				}
			}
		}
	}

	private static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if(cur.x == N-1 && cur.y == N-1) {
				ans = Math.min(ans, cur.time);
				return;
			}
			
			for(int d=0; d<4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny][cur.k]) continue;
				
				if(map[nx][ny] == 0 && cur.k == 0) {//다음위치가 절벽이고 새 오작교를 설치한적이 없는 경우 
					if((cur.time+1) % M == 0) { // 주기가 맞다면 설치한다
						q.offer(new Point(nx, ny, cur.time+1, cur.k+1));
					}else {
						q.offer(new Point(cur.x, cur.y, cur.time+1, cur.k));
					}
				}
				
				if(map[nx][ny] > 1 && map[cur.x][cur.y] == 1) { //다음위치가 오작교이고 현재위치가 땅일
					if((cur.time+1) % map[nx][ny] == 0) { //주기가 맞으면 다음위치 
						q.offer(new Point(nx, ny, cur.time+1, cur.k));
					}else {
						q.offer(new Point(cur.x, cur.y, cur.time+1, cur.k)); //대기를 위해 현재 
					}
				}
				if(map[nx][ny] == 1) { //다음위치가 땅일
					visited[nx][ny][cur.k] = true;
					q.offer(new Point(nx, ny, cur.time+1, cur.k));
				}
			}	
		}
	}
	
	static class Point {
		int x, y, time, k;
		public Point(int x, int y, int time, int k) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
			this.k = k;
		}
	}
}
