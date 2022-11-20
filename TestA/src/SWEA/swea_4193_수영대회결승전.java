package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class swea_4193_수영대회결승전 {
	static int N, map[][];
	static int dx[] = {0, -1, 0, 1};
	static int dy[] = {1, 0, -1, 0};
	static boolean visited[][];
	static int endX, endY;
	static int ans;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				String split[] = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}
			
			String split[] = br.readLine().split(" ");
			int startX = Integer.parseInt(split[0]);
			int startY = Integer.parseInt(split[1]);
			
			split = br.readLine().split(" ");
			endX = Integer.parseInt(split[0]);
			endY = Integer.parseInt(split[1]);

			if(startX == endX && startY == endY) {
				System.out.println("#" + tc + " " + 0);
				continue;
			}
			
			System.out.println("#" + tc + " " + (bfs(startX, startY, 0) ? ans : -1));
		}
	}

	private static boolean bfs(int x, int y, int t) {
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(x, y, t));
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if(cur.x == endX && cur.y == endY) {
				ans = cur.time;
				return true;
			}
			
			for(int d=0; d<4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				if(map[nx][ny] == 1 || visited[nx][ny]) continue;
				
				if(map[nx][ny] == 2) {
					if(cur.time % 3 == 2) {
						visited[nx][ny] = true;
						q.offer(new Point(nx, ny, cur.time+1));
					}else {
						visited[cur.x][cur.y] = true;
						q.offer(new Point(cur.x, cur.y, cur.time+1));
					}
				}else {
					visited[nx][ny] = true;
					q.offer(new Point(nx, ny, cur.time+1));
 				}
			}
		}
		return false;
	}
	
	static class Point {
		int x, y, time;

		public Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}
