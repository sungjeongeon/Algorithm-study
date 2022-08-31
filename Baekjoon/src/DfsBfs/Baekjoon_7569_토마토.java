package DfsBfs;
//baekjoon 7569 토마토
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_7569_토마토 {
	static int M, N, H;
	static int tomato[][][];
	static Queue<Coor> q = new LinkedList<>();
	static int dx[] = {1, -1, 0, 0, 0, 0}; //위, 아래, 왼쪽, 오른쪽, 앞, 뒤
	static int dy[] = {0, 0, 0, 0, 1, -1};
	static int dz[] = {0, 0, 1, -1, 0, 0};
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String split[] = br.readLine().split(" ");
		M = Integer.parseInt(split[0]);
		N = Integer.parseInt(split[1]);
		H = Integer.parseInt(split[2]);
		
		tomato = new int[H][N][M];
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				split = br.readLine().split(" ");
				for(int k=0; k<M; k++) {
					tomato[i][j][k] = Integer.parseInt(split[k]);
					if(tomato[i][j][k] == 1) {
						q.add(new Coor(i,j,k));
					}
				}
			}
		}
		
		bfs();
		
		int ans = tomato[0][0][0];
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<M; k++) {
					if(tomato[i][j][k] == 0) {
						System.out.println(-1);
						return;
					}
					ans = Math.max(ans, tomato[i][j][k]);
				}
			}
		}
		System.out.println(ans-1);
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			Coor tom = q.poll();
			
			for(int d=0; d<6; d++) {
				Coor tom2 = new Coor(tom.x + dx[d], tom.y + dy[d], tom.z + dz[d]);
				
				if(tom2.x<0 || tom2.x>=H || tom2.y<0 || tom2.y>=N || tom2.z<0 || tom2.z>=M) continue;
				
				if(tomato[tom2.x][tom2.y][tom2.z] == 0) {
					tomato[tom2.x][tom2.y][tom2.z] = tomato[tom.x][tom.y][tom.z]+1;
					q.offer(tom2);
				}
			}
			
		}
		
	}
}

class Coor {
	int x, y, z;
	public Coor(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
