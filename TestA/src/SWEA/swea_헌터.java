package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class swea_헌터 {
	static int N;
	static ArrayList<Point> monsters;
	static ArrayList<Point> clients;
	static boolean Mvisited[]; 
	static boolean Cvisited[]; 
	static int min;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			int map[][] = new int[N+1][N+1];
			
			monsters = new ArrayList<>();
			clients = new ArrayList<>();
			for(int i=1; i<N+1; i++) {
				String split[] = br.readLine().split(" ");
				for(int j=1; j<N+1; j++) {
					map[i][j] = Integer.parseInt(split[j-1]);
					if(map[i][j]>0) {
						monsters.add(new Point(i,j,map[i][j]));
					}
					if(map[i][j]<0) {
						clients.add(new Point(i,j,map[i][j]));
					}
				}
			}
			
			min = Integer.MAX_VALUE;
			Mvisited = new boolean[monsters.size()+1];
			Cvisited = new boolean[clients.size()+1];
			dfs(0, 0, 1,1);
			
			System.out.println("#" + tc + " " + min);
		}
	}
	
	private static void dfs(int cnt, int dist, int x, int y) {
		if(dist>=min) return;
		if(cnt == monsters.size()*2) {
			min = Math.min(min, dist);
			return;
		}
		
		for(Point monster : monsters) {
			if(Mvisited[monster.num]) continue;
			
			int d = Math.abs(monster.x - x) + Math.abs(monster.y - y);
			Mvisited[monster.num] = true;
			dfs(cnt+1, dist+d, monster.x, monster.y);
			Mvisited[monster.num] = false;
		}
		
		for(Point client : clients) {
			int n = Math.abs(client.num);
			if(!Mvisited[n] || Cvisited[n]) continue;
			
			int d = Math.abs(client.x - x) + Math.abs(client.y - y);
			Cvisited[n] = true;
			dfs(cnt+1, dist+d, client.x, client.y);
			Cvisited[n] = false;
		}
		
	}

	static class Point {
		int x, y, num;

		public Point(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
		
	}
}
