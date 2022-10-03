package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class swea_4008_숫자만들기 {
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
				cnt[i] = Integer.parseInt(split[i]);
			}
			
			num = new int[N];
			op = new int[N-1];
			split = br.readLine().split(" ");
			for(int i=0; i<N; i++) {
				num[i] = Integer.parseInt(split[i]);
			}
			
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			
			perm(0);
			
			sb.append("#" + tc + " " + (max-min) + "\n");
		}
		System.out.println(sb);
	}

	private static void perm(int idx) {
		if(idx == N-1) {
			int result = cal();
			
			if(min > result) min = result;
			if(max < result) max = result;
			
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(cnt[i]>0) {
				cnt[i]--;
				op[idx] = i;
				perm(idx + 1);
				cnt[i]++;
			}
		}
	}

	private static int cal() {
		int result = num[0];
		int n = 1;
		
		for(int i=0; i<N-1; i++) {
			switch(op[i]) {
			case 0:
				result += num[n++];
				break;
			case 1:
				result -= num[n++];
				break;
			case 2:
				result *= num[n++];
				break;
			case 3:
				result /= num[n++];
				break;
			}
		}
		return result;
	}
}
