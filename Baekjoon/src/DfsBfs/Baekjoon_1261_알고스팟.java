package DfsBfs;
//BJ 1261 알고스팟
//다시 풀어보기
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Baekjoon_1261_알고스팟 {
	static int M, N; //가로, 세로
	static int maze[][]; //미로 배열
	static boolean visited[][]; //방문 확인
	static PriorityQueue<PointCnt> q = new PriorityQueue<>();//벽을 부순 개수를 오름차순 정렬
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	static int ans = Integer.MAX_VALUE;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String split[] = br.readLine().split(" ");
		M = Integer.parseInt(split[0]); //가로
		N = Integer.parseInt(split[1]); //세로
		
		maze = new int[N][M];
		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			split = br.readLine().split("");
			for(int j=0; j<M; j++) {
				maze[i][j] = Integer.parseInt(split[j]);
			}
		}
	
		bfs();
		System.out.println(ans);
	}
	private static void bfs() {
		q.offer(new PointCnt(0,0,0));
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			PointCnt cur = q.poll();
			
			if(cur.x == N-1 && cur.y == M-1) { //도착했으면
				ans = Math.min(cur.cnt, ans);
				return;
			}
			
			for(int d=0; d<4; d++) { //4방향 탐색
				int testX = cur.x + dx[d];
				int testY = cur.y + dy[d];
				
				if(testX<0 || testX>=N || testY<0 || testY>=M) continue; //범위체크
				
				if(maze[testX][testY]==0 && !visited[testX][testY]) {
					q.offer(new PointCnt(testX, testY, cur.cnt));
					visited[testX][testY] = true;
				}
				if(maze[testX][testY] == 1 && !visited[testX][testY]) {
					q.offer(new PointCnt(testX, testY, cur.cnt+1));
					visited[testX][testY] = true;
				}
			}
		}
		
	}
}

class PointCnt implements Comparable<PointCnt> {
	int x, y, cnt;

	public PointCnt(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
	
	@Override
    public int compareTo(PointCnt o) {
        return cnt - o.cnt;
    }
}