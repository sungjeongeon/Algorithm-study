package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class swea_낚시터자리잡기 {
	static int N;
	static int numbers[];
	static boolean isSelected[];
	
	static int map[];
	static int InfoGate[][];
	static boolean visited[];
	static int ans;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			InfoGate = new int[3][2]; //게이트 인덱스, 게이트 사람수
			for(int i=0; i<3; i++) {
				String split[] = br.readLine().split(" ");
				for(int j=0; j<2; j++) {
					InfoGate [i][j] = Integer.parseInt(split[j]);
				}
			}
			ans = Integer.MAX_VALUE;
			numbers = new int[3];
			isSelected = new boolean[3];
			perm(0);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	private static void perm(int cnt) {//게이트 순서 
		if(cnt == 3) {
			//ans = Integer.MAX_VALUE;
			dfs(0, new int[N]);
		}
		
		for(int i=0; i<3; i++) {
			if(isSelected[i]) continue;
			numbers[cnt] = i;
			isSelected[i] = true;
			perm(cnt+1);
			isSelected[i] = false;
		}
	}
	private static void dfs(int dep, int[] map) {
		if(dep == 3) {
			int sumDis = 0;
			for(int i=0; i<N; i++) {
				if(map[i] != 0) 
					sumDis += Math.abs(map[i]-i)+1;
			}
			ans = Math.min(ans, sumDis);
			return;
		}
		
		int newMap[] = new int[N];
		for(int i=0; i<N; i++) {
			newMap[i] = map[i];
		}
		int gateIdx = InfoGate[numbers[dep]][0]-1;
		int gateFisher = InfoGate[numbers[dep]][1];
		
		int distance = 0;
		
		while(gateFisher > 1) {
			if(gateIdx + distance < N && newMap[gateIdx + distance] == 0) {
				newMap[gateIdx + distance] = gateIdx;
				gateFisher--;
			}
			if(gateIdx - distance >= 0 && newMap[gateIdx - distance] == 0) {
				newMap[gateIdx - distance] = gateIdx;
				gateFisher--;
			}
			distance++;
		}
		
		if(gateFisher == 0) { //낚시꾼이 0명이 됐을때
			dfs(dep+1, newMap);
		} else {
			distance = findMinDis(gateIdx, newMap);
			
			if(gateIdx + distance < N && newMap[gateIdx + distance] == 0) {
				int copy[] = new int [N];
				for(int i=0; i<N; i++) {
					copy[i] = newMap[i];
				}
				copy[gateIdx + distance] = gateIdx;
				dfs(dep+1, copy);
			}
			if(gateIdx - distance >= 0 && newMap[gateIdx - distance] == 0) {
				int copy[] = new int [N];
				for(int i=0; i<N; i++) {
					copy[i] = newMap[i];
				}
				copy[gateIdx - distance] = gateIdx;
				dfs(dep+1, copy);
			}
		}
	}
	private static int findMinDis(int gateIdx, int[] map) {
		int leftDis = 0;
		while(gateIdx-leftDis>=0 && map[gateIdx-leftDis] != 0) {
			leftDis++;
		}
		int rightDis = 0;
		while(gateIdx+rightDis<N && map[gateIdx+rightDis] != 0) {
			rightDis++;
		}
		int minDis = Math.min(leftDis, rightDis);
		int maxDis = Math.max(leftDis, rightDis);
		return (gateIdx+minDis<N && map[gateIdx+minDis]==0) || (gateIdx-minDis>=0 && map[gateIdx-minDis]==0) ? minDis : maxDis;
	}
	
	
}
