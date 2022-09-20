package StudyGroup.Sep_week03;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_12761_돌다리 {
	static int A, B, N, M;
	static Queue<Integer> q;
	static int arr[] = new int[100001];
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String split[] = br.readLine().split(" ");
		A = Integer.parseInt(split[0]);
		B = Integer.parseInt(split[1]);
		N = Integer.parseInt(split[2]);
		M = Integer.parseInt(split[3]);
		
		bfs();
	}

	private static void bfs() {
		q = new LinkedList<>();
		q.add(N);
		arr[N] = 1;
		
		while(!q.isEmpty()) {
			N = q.poll();
			if(N == M) break;
			
			if(N+1 <= 100000 && arr[N+1]==0) {
				q.add(N+1);
				arr[N+1] = arr[N] + 1;
			}
			if(N-1 >= 0 && arr[N-1]==0) {
				q.add(N-1);
				arr[N-1] = arr[N] + 1;
			}
			if(N+A <= 100000 && arr[N+A]==0) {
				q.add(N+A);
				arr[N+A] = arr[N] + 1;
			}
			if(N*A <= 100000 && arr[N*A]==0) {
				q.add(N*A);
				arr[N*A] = arr[N] + 1;
			}
			if(N-A >= 0 && arr[N-A]==0) {
				q.add(N-A);
				arr[N-A] = arr[N] + 1;
			}
			if(N+B <= 100000 && arr[N+B]==0) {
				q.add(N+B);
				arr[N+B] = arr[N] + 1;
			}
			if(N*B <= 100000 && arr[N*B]==0) {
				q.add(N*B);
				arr[N*B] = arr[N] + 1;
			}
			if(N-B >= 0 && arr[N-B]==0) {
				q.add(N-B);
				arr[N-B] = arr[N] + 1;
			}
		}
		System.out.println(arr[M]-1);
	}
}
