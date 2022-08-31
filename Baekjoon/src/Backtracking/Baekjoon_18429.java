package Backtracking;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_18429 {
	static int N, K;
	static int[] numbers, weight;
	static int[] copyweight;
	static boolean[] isSelected;
	static int ans;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String split[] = br.readLine().split(" ");
		N  = Integer.parseInt(split[0]);
		K = Integer.parseInt(split[1]);
		
		weight = new int[N];
		numbers = new int[N];
		isSelected = new boolean[N];
		
		split = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			weight[i] = Integer.parseInt(split[i]);
		}
		
		ans = 0; //정답 변수 초기화
		
		perm(0);
		System.out.println(ans);
		
		
	}
	
	private static void perm(int cnt) {
		if(cnt == N){
			int wsum = 500;
			int sum = 0;
			for(int i=0; i<N; i++) {
				wsum = wsum + weight[numbers[i]] - K; 
				if(wsum >= 500) sum++;
				else break;
			}
			if(sum==N) ans++;
			return;
		}
		
		//가능한 모든 수에 대해 시도(input배열의 모든 수 시도)
		for(int i=0; i<N; i++) {
			//시도하는 수가 선택되었는지 판단
			if(isSelected[i]) continue;
			//선택되지 않았다면 수를 사용
			numbers[cnt] = i;
			isSelected[i] = true;
			//다음수 뽑으러 가기
			perm(cnt+1);
			//사용했던 수에 대한 선택 되돌리기
			isSelected[i] = false;
		}
	}
}
