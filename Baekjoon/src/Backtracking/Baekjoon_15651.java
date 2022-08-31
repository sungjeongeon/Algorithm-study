package Backtracking;
//baekjoon 15651

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Baekjoon_15651  {
	static int N, R;
	static int[] numbers, input;
	static StringBuffer sb = new StringBuffer();
	//nCr : n개의 입력받은 수 중 r개를 모두 뽑아 순서없이 나열한것(1<=r<=n)
	public static void main(String[] args) throws Exception{
		
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		
		String split[] = in.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		R = Integer.parseInt(split[1]);
		
		input = new int[N];
		numbers = new int[R];
		
		for(int i=0; i<N;i++) {
			input[i]= i+1; //1부터 N까지 수 input에 담기
		}
		comb(0);
		System.out.println(sb);
	}
	private static void comb(int cnt) {
		if(cnt == R) {
			for(int i=0;i<numbers.length; i++) {
				sb.append(numbers[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		//가능한 모든 수에 대해 시도(input 배열의 가능한 수 시도)
		for(int i=0; i<N; i++) {
			numbers[cnt] = input[i];
			//다음수 뽑으러 가기
			comb(cnt+1);
		}
		
	}
}
