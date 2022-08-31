package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea_프로세서연결하기 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int arr[][] = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String split[] = br.readLine().split(" ");
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(split[j]);
				}
			}
			
			
		}
	}
}
