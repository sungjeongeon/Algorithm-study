package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_2112_보호필름 {
	static int D, W, K;
	static int arr[][], temp[][];
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String split[] = br.readLine().split(" ");
			D = Integer.parseInt(split[0]);
			W = Integer.parseInt(split[1]);
			K = Integer.parseInt(split[2]);
			
			arr = new int[D][W];
			temp = new int[D][W];
			for(int i=0; i<D; i++) {
				split = br.readLine().split(" ");
				for(int j=0; j<W; j++) {
					arr[i][j] = temp[i][j] = Integer.parseInt(split[j]);
				}
			}
			
			subset(0, 0);
		}
	}

	private static void subset(int cnt, int idx) {
		if(idx == D) {
		}
		
		subset(cnt, idx+1);
		
		for(int i=0; i<W; i++) {
			arr[idx][i] = 0;
		}
		subset(cnt+1, idx+1);
		
		 //되돌리기
		for(int i=0; i<W; i++) {
			temp[idx][i] = arr[idx][i];
		}
				
		for(int i=0; i<W; i++) {
			arr[idx][i] = 1;
		}
		subset(cnt+1, idx+1);
		
		 //되돌리기
		for(int i=0; i<W; i++) {
			temp[idx][i] = arr[idx][i];
		}
	}
	
	
}
