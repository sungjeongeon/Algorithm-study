package StudyGroup.Sep_week03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon_21758_꿀따기 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int honey[] = new int[N+1];
		int sum[] = new int[N+1];
		
		String split[] = br.readLine().split(" ");
		
		for(int i=1; i<=N; i++) {
			honey[i] = Integer.parseInt(split[i-1]);
			sum[i] = honey[i] + sum[i-1];
		}
		
		int ans = 0;
		
		//벌 벌 벌통
		for(int i=2; i<=N-1; i++) {
			int tmp = sum[N] - honey[1] - honey[i] + sum[N] - sum[i];		
			ans = Math.max(ans, tmp);
		}
		
		//벌 벌통 벌
		for(int i=2; i<=N-1; i++) {
			int tmp = sum[N-1]-sum[i-1]+sum[i]-sum[1];
			ans = Math.max(ans, tmp);
		}
		
		//벌통 벌 벌 
		for(int i=2; i<=N-1; i++) {
			int tmp = sum[N-1] - honey[i] + sum[i-1];
			ans = Math.max(ans, tmp);
		}
		System.out.println(ans);
	}
}
