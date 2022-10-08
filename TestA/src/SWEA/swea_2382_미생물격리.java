package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class swea_2382_미생물격리 {
	static int N, M, K;
	static ArrayList<Micro> map[][];
	static Queue<Micro> q;
	static int dx[] = {-1, 0, 1, 0}; //상 우 하 좌
	static int dy[] = {0, 1, 0, -1}; 
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_2382.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String split[] = br.readLine().split(" ");
			N = Integer.parseInt(split[0]); //셀의 개수
			M = Integer.parseInt(split[1]); //격리 시간
			K = Integer.parseInt(split[2]); //미생물 군집의 개수
			
			
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
				
				if(dir == 1) dir = 0;
				else if(dir == 4) dir = 1;
				
				q.add(new Micro(x, y, num, dir));
			}
			
			for(int i=0; i<M; i++) {
				move();
			}
			
			int ans = 0;
			while(!q.isEmpty()) {
				Micro cur = q.poll();
				ans += cur.num;
			}
			System.out.println("#" + tc + " " + ans);
		}
		
	}
	
	private static void move() {
		
		while(!q.isEmpty()) {
			Micro cur = q.poll();
			
			int nx = cur.x + dx[cur.dir];
			int ny = cur.y + dy[cur.dir];
			
			if(nx<0 || nx>=N || ny<0 || ny>=N) continue;
			
			if(nx==0 || nx==N-1 || ny==0 || ny==N-1) { //약품이 칠해진 구역
				cur.dir = (cur.dir+2) % 4;
				cur.num = cur.num / 2;
			} 
			map[nx][ny].add(new Micro(nx, ny, cur.num, cur.dir));	
		}
		
		//맵에 있는 미생물 모두 넣기
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j].isEmpty()) continue;
				if(map[i][j].size() == 1){
					q.add(map[i][j].get(0));
				} else {
					int sum = 0;
					int max = 0, nx=0, ny=0, ndir=0;
					for(int k=0; k<map[i][j].size(); k++) {
						sum += map[i][j].get(k).num;
						if(max < map[i][j].get(k).num) {
							max = map[i][j].get(k).num;
							nx =  map[i][j].get(k).x;
							ny =  map[i][j].get(k).y;
							ndir =  map[i][j].get(k).dir;
						}
					}
					q.add(new Micro(nx, ny, sum, ndir));
				}
				map[i][j].clear();
			}
		}
	}

	static class Micro{
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