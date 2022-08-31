package Backtracking;
//baekjoon 16987 계란으로 계란치기 
//다시풀어보기

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Baekjoon_16987  {
	static int N;
	static int egg[][];
	static int max;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		egg = new int[N][2];
		max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			String SW[] = br.readLine().split(" ");
			egg[i][0] = Integer.parseInt(SW[0]);
			egg[i][1] = Integer.parseInt(SW[1]);
		}
		
		hitegg(0);
		System.out.println(max);
	}
	
	static void hitegg(int depth) {
		//마지막 계란인 경우
		if(depth == N) {
			int count = 0;
			for(int i=0;i <N; i++) {
				if(egg[i][0] <= 0) {
					count++;
				}
			}
			max = Math.max(max, count);
			return;
		}
		
		//내구성이 0이하라면 다음오로
		if(egg[depth][0] <= 0) {
			hitegg(depth+1);
		}else {
			boolean broken=false; //깰 계란이 있는지 확인
			
			for(int i=0; i<N; i++) {
				if(i==depth||egg[i][0]<=0)
					continue;
				broken=true; //깰 계란이 있음
				
				egg[i][0] -= egg[depth][1];
				egg[depth][0] -= egg[i][1];
				hitegg(depth+1);
				egg[i][0] += egg[depth][1];
				egg[depth][0] += egg[i][1];
			}
			if(broken==false)
				hitegg(depth+1);
		}
	}
}
