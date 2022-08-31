package DfsBfs;
//baekjoon 7576 토마토
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.plaf.synth.SynthSplitPaneUI;
/*6 4
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 1*/
public class Baekjoon_7576_토마토 {
	static int M, N;
	static int tomato[][];
	static Queue<Point> q = new LinkedList<>();
	static int visited[];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String split[] = br.readLine().split(" ");
		M  = Integer.parseInt(split[0]); //가로칸 수
		N = Integer.parseInt(split[1]); //세로칸 수
		
		tomato = new int[N][M];
		for(int i=0; i<N; i++) {
			split = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				tomato[i][j] = Integer.parseInt(split[j]);
				if(tomato[i][j] == 1) q.add(new Point(i,j));
			}
		}
		
		bfs();
		int ans = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(tomato[i][j] == 0) {
					System.out.println(-1);
					return;
				}
				if(ans < tomato[i][j]) ans = tomato[i][j];
				//System.out.print(tomato[i][j] + " ");
			}
			//System.out.println();
		}
		System.out.println(ans-1);
	}
	
	static void bfs() {
		int dx[] = {1, 0, -1, 0};
		int dy[] = {0, 1, 0, -1};
		while(!q.isEmpty()) {
			Point tom = q.poll();
			for(int i=0; i<4; i++) {
				Point tom2 = new Point(tom.x+dx[i],tom.y+dy[i]);
				
				if(tom2.x<0 || tom2.x> N-1 || tom2.y<0 || tom2.y>M-1) {
					continue;
				}
				
				if(tomato[tom2.x][tom2.y] == 0) {
					tomato[tom2.x][tom2.y] = tomato[tom.x][tom.y] + 1;
					q.offer(tom2);
				}
			}
		
		}
	}
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
}