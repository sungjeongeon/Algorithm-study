package DfsBfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_1245_농장관리 {
	static int N, M;
	static int map[][];
	static int dx[] = {0, 1, 0, -1, -1, -1, 1, 1};
	static int dy[] = {1, 0, -1, 0, -1, 1, 1, -1};
	static Queue<Point> q;
	static int ans;
	static boolean visited[][];
	static boolean flag;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String split[] = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			split = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}
		
		visited = new boolean[N][M];
		flag = true;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j]) {
					dfs(i,j);
					if(flag) ans++;
					flag=true;
				}
			}
		}
		System.out.println(ans);
	}
	private static void dfs(int x, int y) {
		visited[x][y] = true;
		
		for(int d=0; d<8; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx<0 || nx>=N || ny<0 || ny>=M) continue;
			
			if(map[nx][ny] > map[x][y]) flag=false;
			if(!visited[nx][ny] && map[nx][ny] == map[x][y]) {
				dfs(nx,ny);
			}
		}
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
}
