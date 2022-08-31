package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
/*9
3+8*7-9*2*/
public class Baekjoon_16637_괄호추가하기 {
	static int N;
	static ArrayList<Integer> num;
	static ArrayList<Character> op;
	static int ans;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		num = new ArrayList<>();
		op = new ArrayList<>();
		
		String split = br.readLine();
		for(int i=0; i<N; i++) {
			if(i%2 == 0) {
				num.add(split.charAt(i) - '0');
			}else {
				op.add(split.charAt(i));
			}
		}
		ans = Integer.MIN_VALUE;
		dfs(num.get(0), 0);
		
		System.out.println(ans);
	}

	private static void dfs(int result, int opIdx) {
		if(opIdx >= op.size()) {
			ans = Math.max(ans, result);
			return;
		}
		
		//괄호 없는 경우
		int x1 = calc(result, num.get(opIdx+1), op.get(opIdx));
		dfs(x1, opIdx+1);
		
		if(opIdx+1 < op.size()) {
			//괄호 있는 경우
			int x2 = calc(num.get(opIdx+1), num.get(opIdx+2), op.get(opIdx+1));
			dfs(calc(result, x2, op.get(opIdx)), opIdx+2);
		}
	}

	private static int calc(int num1, int num2, char op) {
		if(op == '+') {
			return num1 + num2;
		}else if(op == '-') {
			return num1 - num2;
		}else {
			return num1 * num2;
		}
	}
}

