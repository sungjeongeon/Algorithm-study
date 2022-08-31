package Backtracking;
// baekjoon 로또

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_6603 {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int arr[];
	static int ans[];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String split[] = br.readLine().split(" ");
			if(Integer.parseInt(split[0])==0) break;
			N = Integer.parseInt(split[0]);
			arr = new int [N];
			ans = new int [6];
			for(int i=1; i<=N; i++) {
				arr[i-1] = Integer.parseInt(split[i]);
			}
			comb(0,0);
			sb.append("\n");
		}
		System.out.println(sb);
		
	}
	private static void comb(int cnt, int start) {
		if(cnt == 6) {
			for(int i=0;i<6; i++) {
				sb.append(ans[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		//가능한 모든 수에 대해 시도
		for(int i=start; i<N; i++) {
			ans[cnt] = arr[i];
			//다음수 뽑으러 가기
			comb(cnt+1, i+1);
		}
		
	}
}
