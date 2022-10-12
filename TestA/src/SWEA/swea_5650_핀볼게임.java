package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class swea_5650_핀볼게임 {
	static int N, map[][];
	static ArrayList<Point> wormHole[];
	static int startX, startY;

	static int dx[] = { 1, 0, -1, 0 }; // 하좌상우
	static int dy[] = { 0, -1, 0, 1 }; // 하좌상우
	static int ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_5650.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			wormHole = new ArrayList[11];
			for (int i = 6; i <= 10; i++) {
				wormHole[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] >= 6) {
						wormHole[map[i][j]].add(new Point(i, j));
					}
				}
			}
			
			ans = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] == 0) {
						for(int d=0; d<4; d++) {
							go(i, j, d);
						}
					}
				}
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void go(int startX, int startY, int dir) {
		int x = startX;
		int y = startY;
		int cnt = 0;
		
		while (true) {
			int nx = 0;
			int ny = 0;
			int num = map[x][y];
			
			if (num >= 1 && num <= 5) { // 블록일때
				cnt++;
				switch (num) {
				case 1:
					if (dir == 0)
						dir = 3;
					else if (dir == 1)
						dir = 2;
					else if (dir == 2)
						dir = 0;
					else if (dir == 3)
						dir = 1;
					break;
				case 2:
					if (dir == 0)
						dir = 2;
					else if (dir == 1)
						dir = 0;
					else if (dir == 2)
						dir = 3;
					else if (dir == 3)
						dir = 1;
					break;
				case 3:
					if (dir == 0)
						dir = 2;
					else if (dir == 1)
						dir = 3;
					else if (dir == 2)
						dir = 1;
					else if (dir == 3)
						dir = 0;
					break;
				case 4:
					if (dir == 0)
						dir = 1;
					else if (dir == 1)
						dir = 3;
					else if (dir == 2)
						dir = 0;
					else if (dir == 3)
						dir = 2;
					break;
				case 5:
					if (dir == 0)
						dir = 2;
					else if (dir == 1)
						dir = 3;
					else if (dir == 2)
						dir = 0;
					else if (dir == 3)
						dir = 1;
					break;
				}
			}
			
			nx = x + dx[dir];
			ny = y + dy[dir];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N) { // 벽일때
				dir = (dir + 2) % 4;
				cnt++;
				nx = x;
				ny = y;
			}
			
			if ((nx == startX && ny == startY) || map[nx][ny] == -1) {
				ans = Math.max(ans, cnt);
				break;
			}
			
			if (map[nx][ny] >= 6) {
				int wormNum = map[nx][ny];
				if (wormHole[wormNum].get(1).x == nx && wormHole[wormNum].get(1).y == ny) {
					nx = wormHole[wormNum].get(0).x;
					ny = wormHole[wormNum].get(0).y;
				} else {
					nx = wormHole[wormNum].get(1).x;
					ny = wormHole[wormNum].get(1).y;
				}
			}
			
			x = nx;
			y = ny;
		}
	}

	static class Point {
		int x, y, dir;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}