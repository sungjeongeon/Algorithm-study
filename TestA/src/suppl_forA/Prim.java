package suppl_forA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Prim {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/prim_input.txt"));
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] g = new int[N][N];
		boolean[] v = new boolean[N];
		int[] minEdge = new int[N]; 
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				g[i][j] = sc.nextInt();
			}
			minEdge[i] = Integer.MAX_VALUE;
		}

		int result=0, cnt=0;
		minEdge[0] = 0;
		for(int i=0; i<N; i++) {
			int minVertext = -1;
			int min = Integer.MAX_VALUE;
			for(int j=0; j<N; j++) {
				if(!v[j] && min>minEdge[j]) {
					minVertext=j;
					min=minEdge[j];
				}
			}
			v[minVertext] = true;
			result += min;
			if(cnt++ == N-1) break;
			for(int j=0; j<N; j++) {
				if(!v[j] && g[minVertext][j]!=0 && minEdge[i]>g[minVertext][j]) {
				}
			}
		}
		System.out.println(result);
		sc.close();
	}
}
