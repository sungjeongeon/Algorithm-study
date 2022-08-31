package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_14503_로봇청소기 {
	static int N, M;
	static int map[][];
	static boolean visited[][];
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String split[] = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		split = br.readLine().split(" ");
		int r = Integer.parseInt(split[0]);
		int c = Integer.parseInt(split[1]);
		int d = Integer.parseInt(split[2]);

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			split = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		visited = new boolean[N][M];
		ans = 0;
		visited[r][c] = true;

		dfs(r, c, d);
		System.out.println(ans);
	}

	private static void dfs(int x, int y, int dir) {
		if(map[x][y] == 1) return;
		else if(map[x][y] == 0) {
			map[x][y] = 2;
			ans++;
		}
			
		for(int i=0; i<4; i++) {
			dir = (dir+3) % 4; //왼쪽으로 회전
			int nx = x + dx[dir]; 
			int ny = y + dy[dir];
			
			if(map[nx][ny] > 0) continue;
			dfs(nx,ny,dir);
			return;
		}
			
		dfs(x-dx[dir],y-dy[dir],dir);
		return;
	}



	static class Point {
		int x, y, dir;

		public Point(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
		
	}
}
