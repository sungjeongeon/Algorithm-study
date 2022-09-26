package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_1952_수영장_dfs {
	static int min, pays[], days[];
	
	static void dfs(int idx, int sum) {
		if(idx>=12) {
			min=Math.min(min, sum);
			return;
		}
		dfs(idx+1, sum+days[idx]*pays[0]);
		dfs(idx+1, sum+pays[1]);
		dfs(idx+3, sum+pays[2]);
	}
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_1952.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			pays = new int[4]; //1일, 1달, 3달, 1년
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<4; i++) {
				pays[i] = Integer.parseInt(st.nextToken());
			}
			days = new int[12]; //12달
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<12; i++) {
				days[i] = Integer.parseInt(st.nextToken());
			}
			
			min=pays[3];
			dfs(0,0);
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}
}
