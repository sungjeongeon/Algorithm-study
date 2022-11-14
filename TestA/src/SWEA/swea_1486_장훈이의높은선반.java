package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//햄버거 다이어트
public class swea_1486_장훈이의높은선반 {
	static int N, B, S;
	static int arr[];
	static boolean isSelected[];
	static int ans;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String split[] = br.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			B = Integer.parseInt(split[1]);
			
			arr = new int[N];
			S = 0;
			ans = Integer.MAX_VALUE;
			split = br.readLine().split(" ");
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(split[i]);
				S += arr[i];
			}
			isSelected = new boolean[N];
			
			subset(0);
			//subset(0,0);
			System.out.println("#" + tc + " " + ans);
		}
	}

	private static void subset(int idx) {
		if(idx == N) {
			int sum = 0;
			for(int i=0; i<N; i++) {
				if(isSelected[i]) {
					sum += arr[i];
				}
			}
			if(sum >= B) {
				sum -= B;
				ans = Math.min(sum, ans);
			}
			return;
		}
		
		isSelected[idx] = true;
		subset(idx+1);
		
		isSelected[idx] = false;
		subset(idx+1);
		
	}
	
//	private static void subset(int idx, int sum) {
//		if(sum >= B) {
//			sum -= B;
//			ans = Math.min(sum, ans);
//			return;
//		}
//		if(idx == N) {
//			if(sum >= B) {
//				sum -= B;
//				ans = Math.min(sum, ans);
//			}
//			return;
//		}
//		
//		subset(idx+1, sum+arr[idx]);
//		subset(idx+1, sum);
//	}
}
