package BruteForce;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_3085 {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		//StringBuilder sb = new StringBuilder();

		// StringTokenize 객체 선언
		// StringTokenizer st = null;

		int n = Integer.parseInt(in.readLine());

		//candy 배열 받기
		String[][] candy = new String[n][n];
		for(int i=0; i<n; i++) {
			String[] a = in.readLine().split("");
			for(int j=0; j<a.length; j++) {
				candy[i][j] = a[j];
			}
		}

		int count = 1, max = 0;


		for(int i=0; i<n; i++) {
			for(int j=0; j<n-1; j++) {
				swap(candy, i, j, i, j+1); //swap 행
				for(int x=0; x<n; x++) {
					for(int y=0; y<n-1; y++) {
						if(candy[x][y].equals(candy[x][y+1])) count += 1; //행탐색
						else count = 1;
						if(max < count) max = count;
					}
					count = 1;
				}
				for(int x=0; x<n; x++) {
					for(int y=0; y<n-1; y++) {
						if(candy[y][x].equals(candy[y+1][x])) count += 1; //열탐색
						else count = 1;
						if(max < count) max = count;
					}
					count = 1;
				}
				swap(candy, i, j+1, i, j); //다시 제자리

				swap(candy, j, i, j+1, i); //swap 열
				for(int x=0; x<n; x++) {
					for(int y=0; y<n-1; y++) {
						if(candy[x][y].equals(candy[x][y+1])) count += 1; //행탐색
						else count = 1;
						if(max < count) max = count;
					}
					count = 1;
				}
				for(int x=0; x<n; x++) {
					for(int y=0; y<n-1; y++) {
						if(candy[y][x].equals(candy[y+1][x])) count += 1; //열탐색
						else count = 1;
						if(max < count) max = count;
					}
					count = 1;
				}
				swap(candy, j+1, i, j, i); //다시 제자리
			}
		}

		System.out.println(max);
	}

	public static void swap(String[][] array, int i1, int i2, int j1, int j2) {
		String temp = array[i1][i2];
		array[i1][i2] = array[j1][j2];
		array[j1][j2] = temp;
	}
}