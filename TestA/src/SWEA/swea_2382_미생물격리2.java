package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class swea_2382_미생물격리2 {		
	static int N, M, K;
	static ArrayList<Micro> map[][];
	static Queue<Micro> q;
	static int dx[] = {-1, 1, 0, 0};
	static int dy[] = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_2382.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String split[] = br.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			M = Integer.parseInt(split[1]);
			K = Integer.parseInt(split[2]);
			
			map = new ArrayList[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = new ArrayList<>();
				}
			}
			
			q = new ArrayDeque<>();
			for(int i=0; i<K; i++) {
				split = br.readLine().split(" ");
				int x = Integer.parseInt(split[0]);
				int y = Integer.parseInt(split[1]);
				int num = Integer.parseInt(split[2]);
				int dir = Integer.parseInt(split[3]);
				
				q.add(new Micro(x, y, num, dir-1));
				
			}
			
			for(int i=0; i<M; i++) {
				move();
			}
		}
	}
	
	private static void move() {
		while(!q.isEmpty()) {
			Micro cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if(nx<0 || nx>=N || ny<0 || ny>N) continue;
				
				if(nx==0 || nx==N-1 || ny==0 || ny==N-1) {
					
				}
			}
		}
	}

	static class Micro {
		int x, y, num, dir;
		public Micro(int x, int y, int num, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
		}
	}
}