
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class swea_1227 {
	static Point start, end;
	static int[][] map;
	static int ans;
	
	static int[] di = {0,0,1,-1};
	static int[] dj = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = 10;
		
		for(int tc=1; tc<=TC; tc++) {
			br.readLine(); // testcase 번호가 쓸데없이 입력으로 들어오네;
			
			map = new int[100][100];
			ans = 0;
			
			for(int i=0; i<100; i++) {
				String line = br.readLine();
				for(int j=0; j<100; j++) {
					map[i][j] = line.charAt(j) - '0';
					if(map[i][j] == 2) { // 출발지
						start = new Point(i, j);
					}
					if(map[i][j] == 3) { // 도착지(0으로만 이동하니까 도착지 3을 안가;; 0으로 바꿔놓자 좀 가라고.)
						end = new Point(i, j);
						map[i][j] = 0;
					}
				}
			}
			
			dfs(start);
			//bfs(start);
			
			System.out.println("#"+tc+" "+ans);
		}
	}
	
	
	static void bfs(Point start) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(start);
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			
			map[now.i][now.j] = 9;
			if(now.i == end.i && now.j == end.j) {
				ans=1;
				return;
			}
			
			for(int d=0; d<4; d++) {
				int nexti = now.i + di[d];
				int nextj = now.j + dj[d];
				if(nexti>=0 && nexti<100 && nextj>=0 && nextj<100 && map[nexti][nextj]==0) {
					queue.add(new Point(nexti, nextj));
				}
			}
		}
	}


	static void print() {
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				System.out.printf("%2d", map[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------------------------");
	}
	
	static void dfs(Point now) {
		print();
		map[now.i][now.j] = 9;
		
		if(now.i == end.i && now.j == end.j) {
			ans = 1;
		}
		
		for(int d=0; d<4; d++) {
			int nexti = now.i + di[d];
			int nextj = now.j + dj[d];
			
			if(nexti>=0 && nexti<100 && nextj>=0 && nextj<100 && map[nexti][nextj]==0) {
				dfs(new Point(nexti,nextj));
			}			
		}
	}
	static class Point{
		int i, j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
}
