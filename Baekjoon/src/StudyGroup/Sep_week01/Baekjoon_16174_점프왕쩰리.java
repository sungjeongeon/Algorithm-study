package StudyGroup.Sep_week01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_16174_점프왕쩰리 {
	static int N;
	static int map[][];
	static int dx[] = {0, 1};
	static int dy[] = {1, 0};
	static boolean visited[][];
	static boolean flag = false;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			String split[] = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}
		
		bfs(0,0);
		
		if(flag) {
			System.out.println("HaruHaru");
		}else {
			System.out.println("Hing");
		}
	}

	private static void bfs(int i, int j) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(i,j, map[i][j]));
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if(map[cur.x][cur.y] == -1) {
				flag = true;
				break;
			}
			
			for(int d=0; d<2; d++) {
				int nx = cur.x + dx[d]*cur.cnt;
				int ny = cur.y + dy[d]*cur.cnt;
				
				if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
				
				if(!visited[nx][ny]) {
					visited[nx][ny] = true;
					q.add(new Point(nx,ny,map[nx][ny]));
				}
			}
		}
		
	}
	
	static class Point {
		int x, y, cnt;

		public Point(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
