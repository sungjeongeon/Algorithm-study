package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea_2115_벌꿀채취 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_2115.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String split[] = br.readLine().split(" ");
			int N = Integer.parseInt(split[0]);
			int M = Integer.parseInt(split[1]);
			int C = Integer.parseInt(split[2]);
			
			int map[][] = new int[N][N];
			
			for(int i=0; i<N; i++) {
				split = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N-M; j++) {
					int arr[] = new int[M];
					int sum = 0;
					for(int k=0; k<M; k++) {
						arr[k] = map[i][j+k];
						sum += arr[k];
					}
					if(sum > C) {
					} else {
						
					}
				}
			}
			
		}
		
	}
}
