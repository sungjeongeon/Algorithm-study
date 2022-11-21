package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//등산로 
public class swea_1949_등산로조성_2 {
	static int N, K, map[][];
	static boolean visited[][];
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	static int maxLength;
    public static void main(String[] args) throws IOException {
    	System.setIn(new FileInputStream("res/input_swea_1949.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String split[] = br.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			K = Integer.parseInt(split[1]);
			
			map = new int[N][N];
			visited  = new boolean[N][N];
			maxLength = Integer.MIN_VALUE;
			
			int max = -1;
			for(int i=0; i<N; i++) {
				split = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
					if(max < map[i][j]) max = map[i][j];
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == max) {
						visited[i][j] = true;
						dfs(new Point(i, j, map[i][j]), 1, false);
						visited[i][j] = false;
					}
				}
			}
			
			System.out.println("#" + tc + " " + maxLength);
		}
	}

	private static void dfs(Point start, int length, boolean isOK) {
		if(length > maxLength) maxLength = length;
		
		for(int d=0; d<4; d++) {
			int nx = start.x + dx[d];
			int ny = start.y + dy[d];
			
			if(nx<0 || nx>=N || ny<0 || ny>=N || visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			
			if(start.height > map[nx][ny]) {
				dfs(new Point(nx, ny, map[nx][ny]), length+1, isOK);
			} else if (!isOK && map[nx][ny] - K < start.height) {
				dfs(new Point(nx, ny, start.height-1), length+1, true);
			}
			
			visited[nx][ny] = false;
			
		}
	}
	
	static class Point {
		int x, y, height;
		public Point(int x, int y, int height) {
			super();
			this.x = x;
			this.y = y;
			this.height = height;
		}
	}
}