package StudyGroup.Oct_week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17090_미로탈출하기 {
	static int N, M;
	static char map[][];
	static boolean visited[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			String split[] = br.readLine().split("");
			for(int j=0; j<M; j++) {
				map[i][j] = split[j].charAt(0);
			}
		}
		
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(dfs(i, j)) {
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}

	private static boolean dfs(int i, int j) {
		boolean result = false;
		
		if(i<0 || i>=N || j<0 || j>=M) return true;
		
		if(map[i][j] == 'T') return true;
		if(map[i][j] == 'F') return false;
		if(visited[i][j]) return false;
		
		visited[i][j] = true;
		if(map[i][j] == 'U') {
			result = dfs(i-1, j);
		}else if(map[i][j] == 'R') {
			result = dfs(i, j+1);
		}else if(map[i][j] == 'D') {
			result = dfs(i+1, j);
		}else if(map[i][j] == 'L') {
			result = dfs(i, j-1);
		}
		
		map[i][j] = result ? 'T' : 'F';
		return result;
		
	}
}
