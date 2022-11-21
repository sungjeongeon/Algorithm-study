package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_4008_숫자만들기 {
	static int N, cnt[], op[], num[];
	static int min, max, ans;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			cnt = new int[4];
			num = new int[N];
			op = new int[N-1];
			
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<4; i++) {
				cnt[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			perm(0);
			
			ans = max - min;
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	private static void perm(int idx) {
		if(idx == N-1) {
			int result = cal();
			
			min = Math.min(min, result);
			max = Math.max(max, result);
		}
		for(int i=0; i<4; i++) {
			if(cnt[i]>0) {
				cnt[i]--;
				op[idx] = i;
				perm(idx+1);
				cnt[i]++;
			}
		}
	}

	private static int cal() {
		int result = num[0];
		int nidx = 1;
		
		for(int i=0; i<N-1; i++) {
			switch(op[i]) {
			case 0:
				result += num[nidx++];
				break;
			case 1:
				result -= num[nidx++];
				break;
			case 2:
				result *= num[nidx++];
				break;
			case 3:
				result /= num[nidx++];
				break;
			}
		}
		return result;
	}
}
