package DfsBfs;
//baekjoon 1697 숨바꼭질
// 5 17
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_1697_숨바꼭질 {
	static int N, M;
	static int[] cnt;
	static Queue<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String split[] = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		cnt = new int[100001];
		
		if(N==M) {
			System.out.println(0);
		} else {
			bfs(N);
		}
	}
	
	public static void bfs(int n) {
		q.offer(n);
		cnt[n] = 1;
		while(!q.isEmpty()) {
			int now = q.poll();
			for(int i=0; i<3; i++) {
				int next;
				if(i == 0) {
					next = now + 1;
				} else if(i == 1) {
					next = now - 1;
				} else {
					next = now * 2;
				}
				
				if(next == M) {
					System.out.println(cnt[now]);
					return;
				}
				
				if(next >=0 && next < 100001 && cnt[next] == 0) {
					q.offer(next);
					cnt[next] = cnt[now] + 1;
				}
			}
			
		}
	}
}
