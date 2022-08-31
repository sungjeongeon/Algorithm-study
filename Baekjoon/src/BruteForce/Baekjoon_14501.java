package BruteForce;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Baekjoon_14501 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
		int N = Integer.parseInt(br.readLine());
		int T[] = new int[N+3];
		int P[] = new int[N+3];
		int D[] = new int[N+3];
		
		for(int i=0; i<N; i++) {
			String[] split = br.readLine().split(" ");
			T[i] = Integer.parseInt(split[0]);
			P[i] = Integer.parseInt(split[1]);
		}
		
		int index;
		for(int i=0; i<=N; i++) {
			index = i + T[i];
			if(index <= N) {
				for(int j=N; j>=index; j--) {
					if(D[j] < P[i] + D[i]) {
						D[j] = P[i] + D[i];
					}
				}
			}
		}
		System.out.println(D[N]);
	}
}
