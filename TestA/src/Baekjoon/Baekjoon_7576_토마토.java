package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_7576_토마토 {
	static int N, M;
	static int tomato[][];
	static boolean visited[][];
	static Queue<Point> q = new LinkedList<>();
	
	static int dx[] = {0,1,0,-1};
	static int dy[] = {1,0,-1,0};
	
	static int ans = 0;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		String split[] = br.readLine().split(" ");
		
		M = Integer.parseInt(split[0]);
		N = Integer.parseInt(split[1]);
		
		tomato = new int[N][M];
		visited = new boolean[N][M];
		boolean flag = true;
		for(int i=0; i<N; i++) {
			split = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				tomato[i][j] = Integer.parseInt(split[j]);
				if(tomato[i][j] == 1) {
					q.add(new Point(i, j));
				}
				if(tomato[i][j] == 0) {
					flag = false;
				}
			}
		}
		
		if(flag) {
			System.out.println(0);
		}else {
			bfs();
			
			boolean flag2 = true;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(tomato[i][j] == 0) {
						flag2 = false;
					}
				}
			}
			
			if(!flag2) System.out.println(-1);
			else System.out.println(ans-1);
		}
	}
	
	private static void bfs() {
		while(!q.isEmpty()) {
			
			int size= q.size();
			ans++;
			for(int s=0; s<size; s++) {
				Point cur = q.poll();
				
				for(int d=0; d<4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					if(nx<0 || nx>=N || ny<0 || ny>=M || tomato[nx][ny] == -1) continue;
				
					if(!visited[nx][ny]) {
						tomato[nx][ny] = 1;
						visited[nx][ny] = true;
						q.offer(new Point(nx,ny));
					}
				}
			}
		}
	}

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
}
