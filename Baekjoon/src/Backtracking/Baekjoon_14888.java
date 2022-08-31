package Backtracking;
//baekjoon 14888

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Baekjoon_14888  {
	static int N;
	static int num[];
	static int op[];
	static int MAX = Integer.MIN_VALUE;
	static int MIN = Integer.MAX_VALUE;	
	
	static StringBuffer sb = new StringBuffer();
	//nCr : n개의 입력받은 수 중 r개를 모두 뽑아 순서없이 나열한것(1<=r<=n)
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		num = new int[N]; //N개의 수
		op = new int[4]; //연산자
		
		String split[] = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			num[i] = Integer.parseInt(split[i]);
		}
		
		split = br.readLine().split(" ");
		for(int i=0; i<4; i++) {
			op[i] = Integer.parseInt(split[i]);
		}
		
		Operator(num[0], 1);
		
		System.out.println(MAX);
		System.out.println(MIN);
	}
	
	public static void Operator(int number, int idx) {
		if(idx == N) {
			MAX = Math.max(MAX, number);
			MIN = Math.min(MIN, number);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(op[i] > 0) {
				op[i]--;
				if(i==0) {
					Operator(number + num[idx], idx+1);
				}
				else if(i==1) {
					Operator(number - num[idx], idx+1);
				}
				else if(i==2) {
					Operator(number * num[idx], idx+1);
				}
				else if(i==3) {
					Operator(number / num[idx], idx+1);
				}
				op[i]++;
			}
		}
	}
		
}
