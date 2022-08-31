
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class swea_2117_홈방범서비스 {
	static int[][] map;
	static int ans;
	static int N, M; // 맵크기, 한집당 지불비용
	
	static int[] di = {0,0,1,-1};
	static int[] dj = {1,-1,0,0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		int TC = sc.nextInt();
		for(int tc=1; tc<=TC; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			} // end input
			
			ans = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					bfs(i, j);
				}
			}
			System.out.println("#"+tc+" "+ans);
		}
	}

	static void bfs(int starti, int startj) {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		
		queue.add(new Point(starti, startj));
		visit[starti][startj] = true;
		
		int K = 1;
		int house = 0; // K를 늘려가면서 몇개의 집에 서비스하는지 갯수 누적.
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s=0; s<size; s++) {
				Point now = queue.poll();
				if(map[now.i][now.j]==1) { // 이번 영역에 가정집 있다 ! 
					house++;
				}
				for(int d=0; d<4; d++) { // 현재영역 주변영역. 이 다음 K에 계산되게 됨.
					int nexti = now.i + di[d];
					int nextj = now.j + dj[d];
					if(nexti<0 || nexti>=N || nextj<0 || nextj>=N) continue;
					
					if(!visit[nexti][nextj]) {
						queue.add(new Point(nexti, nextj));
						visit[nexti][nextj]=true;
					}
				}
			}
			int cost = K*K + (K-1)*(K-1); // 운영비
			int income = house*M; // 수입
			if(cost <= income) { // 적자는 안났네.
				ans = Math.max(ans, house);
			}
			K++;
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
