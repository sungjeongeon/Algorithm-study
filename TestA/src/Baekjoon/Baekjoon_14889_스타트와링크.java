package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//baekjoon 14889 스타트와 링크
public class Baekjoon_14889_스타트와링크 {
	static int N;
	static int team[][];
	static int select[]; 
	static int select2[];
	static boolean visited[];
	static int ans;
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		team = new int[N][N];
		select = new int[N/2];
		select2 = new int[N/2];
		ans = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			String split[] = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				team[i][j] = Integer.parseInt(split[j]);
			}
		}
		
		comb(0,0);
		System.out.println(ans);
	}

	public static void comb(int cnt, int start) { // N개의 식재료 조합
		if(cnt == N/2) {
			visited = new boolean[N];
			for(int i=0; i<select.length; i++) {
				visited[select[i]] = true;
				//System.out.println(select[i]);
			}
			
			int idx = 0;
			for(int i=0; i<visited.length; i++) {
				if(visited[i] == false) {
					select2[idx++] = i;
				}
			}
			
			int sum = 0;
			int sum2 = 0;
			
			for(int i=0; i<N/2-1; i++) {
				for(int j=i+1; j<N/2; j++) {
					sum += Math.abs(team[select[i]][select[j]]) + Math.abs(team[select[j]][select[i]]);
				}
			}
			
			for(int i=0; i<N/2-1; i++) {
				for(int j=i+1; j<N/2; j++) {
					sum2 += Math.abs(team[select2[i]][select2[j]]) + Math.abs(team[select2[j]][select2[i]]);
				}
			}
			
			ans = Math.min(Math.abs(sum-sum2), ans);
			return;
		}

		for(int i=start; i<N; i++) {
			select[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
}
