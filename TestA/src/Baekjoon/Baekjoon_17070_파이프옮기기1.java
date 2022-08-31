package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Baekjoon_17070_파이프옮기기1 {
	static int N;
	static int arr[][];
	static int ans;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		ans = 0;
		
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			String split[] = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(split[j]);
			}
		}
		
		dfs(0, 1, 0);
		System.out.println(ans);
	}

	private static void dfs(int x, int y, int dir) {
		if (x == N-1 && y == N-1) {
			ans++;
			return;
		}

		switch (dir) {
		case 0: // 가로방향일때
			// 가로이동
			if (y + 1 < N && arr[x][y + 1] == 0) {
				dfs(x, y + 1, 0);
			}
			// 대각선이동
			if (x + 1 < N && y + 1 < N && arr[x][y + 1] == 0 && arr[x + 1][y] == 0 && arr[x + 1][y + 1] == 0) {
				dfs(x + 1, y + 1, 2);
			}
			break;
		case 1: // 세로방향일때
			// 세로이동
			if (x + 1 < N && arr[x + 1][y] == 0) {
				dfs(x + 1, y, 1);
			}
			// 대각선이동
			if (x + 1 < N && y + 1 < N && arr[x][y + 1] == 0 && arr[x + 1][y] == 0 && arr[x + 1][y + 1] == 0) {
				dfs(x + 1, y + 1, 2);
			}
			break;
		case 2:
			if (x + 1 < N && arr[x + 1][y] == 0) {
				dfs(x + 1, y, 1);
			}
			if (y + 1 < N && arr[x][y + 1] == 0) {
				dfs(x, y + 1, 0);
			}
			if (x + 1 < N && y + 1 < N && arr[x][y + 1] == 0 && arr[x + 1][y] == 0 && arr[x + 1][y + 1] == 0) {
				dfs(x + 1, y + 1, 2);
			}
			break;
		}

	}
}
