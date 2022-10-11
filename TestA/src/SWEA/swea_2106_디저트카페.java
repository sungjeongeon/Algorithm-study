package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class swea_2106_디저트카페 {
	static int N, map[][];
	static boolean visited[][];
	static boolean dessert[];
	static int dx[] = {-1, 1, 1, -1}; //우상, 우하, 좌하, 좌
	static int dy[] = {1, 1, -1, -1};
	
	static int ans;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_2106.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			map = new int[N][N];
			ans = 0;
			for(int i=0; i<N; i++) {
				String split[] = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}
			
			for(int i=1; i<N-1; i++) {//위아래칸, 오른쪽 두칸 확
				for(int j=0; j<N-2; j++) {
					visited = new boolean[N][N];
					dessert = new boolean[101];
					
					visited[i][j] = true;
					dessert[map[i][j]] = true;
					dfs(i,j, i, j, 0, 1);
				}
			}
			
			if(ans == 0) ans = -1;
			System.out.println("#" + tc + " " + ans);
		}
	}
	private static void dfs(int x, int y, int fX, int fY, int dir, int cnt) {
		for(int d=dir; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
			
			if(nx == fX && ny == fY && cnt > 2) {
				ans = Math.max(ans, cnt);
				return;
			}
			
			if(!visited[nx][ny] && !dessert[map[nx][ny]]) {
				visited[nx][ny] = true;
				dessert[map[nx][ny]] = true;
				dfs(nx, ny, fX, fY, d, cnt+1);
				visited[nx][ny] = false;
				dessert[map[nx][ny]] = false;
			}
		}
		
	}
}
