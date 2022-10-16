package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea_1949_등산로조성 {
	static int N, K;
	static int maxLength;
	static int map[][];
	static boolean visited[][];
	static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { -1, 0, 1, 0 };
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_1949.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String split[] = br.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			K = Integer.parseInt(split[1]);
			
			map = new int[N][N];
			visited = new boolean[N][N];
			maxLength = Integer.MIN_VALUE;
			
			int max = Integer.MIN_VALUE;
			for(int i=0; i<N; i++) {
				split= br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					int intJ = Integer.parseInt(split[j]);
					if(intJ > max) max = intJ;
					map[i][j] = intJ;
				}
			}
			
			// 산봉우리의 좌표값으로 dfs 실행
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == max) dfs(new Point(i,j, map[i][j]),1,false);
				}
			}
			//System.out.println(max);
			//for(int[] a : map) System.out.println(Arrays.toString(a));
			
			sb.append("#" + tc + " " + maxLength + "\n");
		}
		System.out.print(sb);
	}
	
	private static void dfs(Point start, int length, boolean isK) {
		if (length > maxLength) maxLength = length;
		
		//visited[start.x][start.y] = true;
		
		for(int d=0; d<4; d++) {
			int nx = start.x + dx[d];
			int ny = start.y + dy[d];
			
			if(nx<0 || nx>=N || ny<0 || ny>= N || visited[nx][ny]) continue;
			
			visited[nx][ny] = true;
			
			if(map[nx][ny] < start.height) {
				//System.out.println(map[nx][ny]);
				dfs(new Point(nx,ny,map[nx][ny]), length+1, isK);
				
			} else if (!isK && map[nx][ny] - start.height < K){
				dfs(new Point(nx,ny,start.height-1), length+1, true);
			}
			visited[nx][ny] = false;
		}
		//visited[start.x][start.y] = false;
	}

	static class Point{
		int x, y, height;

		public Point(int x, int y, int height) {
			super();
			this.x = x;
			this.y = y;
			this.height = height;
		}
	}
}