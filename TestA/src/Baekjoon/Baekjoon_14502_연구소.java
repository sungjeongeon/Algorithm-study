package Baekjoon;
//baekjoon 14502 연구소
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Baekjoon_14502_연구소 {
	static int N, M;
	static int virus[][];
	static int numbers[];
	static boolean visited[];
	static ArrayList<Point> b = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String split[] = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		 
		virus = new int[N][M];
		for(int i=0; i<N; i++) {
			split = br.readLine().split(" ");
			for(int j=0; j<M; j++) {
				virus[i][j] = Integer.parseInt(split[j]);
				if(virus[i][j] == 0) {
					b.add(new Point(i, j));
				}
			}
		}
		visited = new boolean[b.size()];
		numbers = new int[b.size()];
	}
	
	static void comb(int cnt, int start) {
		if(cnt == 3) {
			System.out.println(Arrays.toString(numbers));
		}
		for(int i=start; i<b.size(); i++) {
			numbers[cnt] = i;
			comb(cnt+1, i+1);
		}
	}
	
	static class Point{
		int x,y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
}