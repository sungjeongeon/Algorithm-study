package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class swea_7793_오나의여신님 {
	static Queue<Point> sq; //수연이 이동을 위한 큐
	static Queue<Point> vq; //악마 확장을 위한 큐
	
	static int dx[] = {0, -1, 0, 1};
	static int dy[] = {-1, 0, 1, 0};
	
	static int N, M;
	static char map[][];
	static boolean visited[][];
	
	static String ans = "GAME OVER";
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_7793.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String split[]= br.readLine().split(" ");
			
			N = Integer.parseInt(split[0]);
			M = Integer.parseInt(split[1]);
			
			map = new char[N][M];
			
			sq = new ArrayDeque<>();
			vq = new ArrayDeque<>();
			
			//visited = new boolean[N][M]
			for(int i=0; i<N; i++) {
				split = br.readLine().split("");
				for(int j=0; j<M; j++) {
					map[i][j] = split[j].charAt(0);
					if(map[i][j] == 'S') {
						//수연이의 위치
						sq.offer(new Point(i, j, 0));
					}else if(map[i][j] == '*') {
						//악마의 위치
						vq.offer(new Point(i, j, 0));
						map[i][j] = '0';
					}
				}
			}
			
			bfs();
			
			bfs2();
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void bfs() {
		while(!vq.isEmpty()) {
			Point cur = vq.poll();
			for(int d=0; d<4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				//경계값을 벗어나거나 돌이라면 
				if(nx<0 || nx>=N || ny<0 || ny>=M || map[nx][ny] == 'X') continue;
				
				if(map[nx][ny] == '.' || map[nx][ny] == 'S') {
					vq.offer(new Point(nx, ny, cur.depth+1));
					map[nx][ny] = (char) ((cur.depth+1) + '0');
				}
			}
		}
		
	}

	private static void bfs2() {
		while(!sq.isEmpty()) {
			Point cur = sq.poll();
			for(int d=0; d<4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				//경계값을 벗어나거나 돌이라면 
				if(nx<0 || nx>=N || ny<0 || ny>=M || map[nx][ny] == 'X') continue;
				
				if(map[nx][ny] == 'D') {
					ans = Integer.toString(cur.depth);
					break;
				}
				if((map[nx][ny]-'0') < cur.depth+1) {
					sq.offer(new Point(nx, ny, cur.depth+1));
				}
			}
		}
	}
	static class Point {
		int x, y, depth;

		public Point(int x, int y, int depth) {
			super();
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
}
