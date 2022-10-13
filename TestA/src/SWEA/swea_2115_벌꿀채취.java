package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_2115_벌꿀채취 {
	static int N, M, C, ans, map[][], profit[][];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/input_swea_2115.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //벌통의 크기
			M = Integer.parseInt(st.nextToken()); //선택할 수 있는 벌통의 개수
			C = Integer.parseInt(st.nextToken()); //꿀을 채취할 수 있는 최대양
			
			profit = new int[N][N]; //꿀 담기
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<=N-M; j++) {
					//여기서 얻을 수 있는 최대 수익
					getHoney(i, j, 0, 0, 0);
				}
			}
			
			//일꾼 A 꿀채취
			for(int i=0; i<N; i++) {
				for(int j=0; j<=N-M; j++) {
					comb(i,j, 1, profit[i][j]);
				}
			}
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	private static void comb(int x, int y, int cnt, int sum) {
		if(cnt==2) {
			ans = Math.max(ans, sum);
			return;
		}
		
		//일꾼 B 꿀채취
		for(int i=x; i<N; i++) {
			for(int j=y+M; j<=N-M; j++) {
				comb(i, j, cnt+1, sum+profit[i][j]);
			}
			y= -M;
		}
	}
	private static void getHoney(int i, int j, int Mcnt, int Csum, int totalSum) {
		if(Csum > C) return;
		if(Mcnt == M) {
			//해당 구간에서 최대 수익
			profit[i][j-M] = Math.max(profit[i][j-M], totalSum);
			return;
		}
		
		//이 꿀은 채취
		getHoney(i, j+1, Mcnt+1, Csum+map[i][j], totalSum+map[i][j]*map[i][j]);
		//이 꿀은 넘어가!
		getHoney(i, j+1, Mcnt+1, Csum, totalSum);
	}
}