package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class swea_4008_숫자만들기_2 {
	static int N, cnt[], num[], op[];
	static int min, max;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_4008.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			String split[] = br.readLine().split(" ");
			
			cnt = new int[4];
			for(int i=0; i<4; i++) {
				cnt[i] = Integer.parseInt(split[i]); //연산자의 개수 + - * /
			}
			
			num = new int[N];
			split = br.readLine().split(" ");
			for(int i=0; i<N; i++) {
				num[i] = Integer.parseInt(split[i]); //숫자
			}
			
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			dfs(num[0], 1, N-1);
			
			sb.append("#" + tc + " " + (max-min) + "\n");
		}
		System.out.println(sb);
	}

	

	private static void dfs(int sum, int idx, int left) {
		if(left == 0) {
			if(sum > max) max = sum;
			if(sum < min) min = sum;
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(cnt[i] > 0) {
				cnt[i]--;
				dfs(cal(sum, num[idx], i), idx+1, left-1);
				cnt[i]++;
			}
		}
	}



	private static int cal(int n1, int n2, int op) {
		switch(op) {
		case 0:
			return n1 + n2;
		case 1:
			return n1 - n2;
		case 2:
			return n1 * n2;
		default:
			return n1 / n2;
		}
	}
}
