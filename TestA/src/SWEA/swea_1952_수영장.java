package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea_1952_수영장 {
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_1952.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int T = Integer.parseInt(st.nextToken());
		
		for(int tc=1; tc<=T; tc++) {
			int[] pays = new int[4]; //1일, 1달, 3달, 1년
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<4; i++) {
				pays[i] = Integer.parseInt(st.nextToken());
			}
			int[] days = new int[12+1]; //12달
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<=12; i++) {
				days[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] dp = new int[12+1];
			for(int i=1; i<=12; i++) {
				int DAY = dp[i-1] + pays[0]*days[i];					 //1일
				int MONTH = dp[i-1] + pays[1];							 //1달
				int MONTH3 = (i>=3)?dp[i-3] + pays[2]:Integer.MAX_VALUE; //3달
				int YEAR = (i==12)?pays[3]:Integer.MAX_VALUE; 	  	  	 //1년
				dp[i]=Math.min(DAY, Math.min(MONTH, Math.min(MONTH3, YEAR)));
			}
			sb.append("#").append(tc).append(" ").append(dp[12]).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}
}
