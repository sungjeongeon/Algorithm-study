package StudyGroup.Oct_week04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14267_회사문화1 {
	static int N, M;
	static ArrayList<Integer>[] employee;
	static int dp[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //회사의 직원 
		M = Integer.parseInt(st.nextToken()); //최초의 칭찬횟
		
		employee = new ArrayList[N+1];
		for(int i=0; i<=N; i++) {
			employee[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num == -1) continue;
			employee[num].add(i); //직원의 직속부하 추
		}
		
		dp = new int[N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			dp[n] += w;
		}
		
		dfs(1);
		
		for(int i=1; i<N+1; i++) {
			System.out.print(dp[i] + " ");
		}
	}
	static void dfs(int start) {
		for(int next : employee[start]) {
			dp[next] += dp[start]; //부하에게 상사의 칭찬정도만큼 더
			dfs(next);
		}
	}
}
