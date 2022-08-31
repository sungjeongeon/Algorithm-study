package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
//baekjoon 20058 마법사 상어와 파이어스톰
//못풀겠다 .............
public class Baekjoon_20058_마법사상어 {
	static int N, Q;
	static int ice[][], copyice[][];
	static int n;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String split[] = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		Q = Integer.parseInt(split[1]);
		
		int NN = (int) Math.pow(2, N); //2의 n승
		ice = new int[NN][NN];
		copyice = new int[NN][NN];
		
		for(int i=0; i<NN; i++) {
			split = br.readLine().split(" ");
			for(int j=0; j<NN; j++) {
				ice[i][j] = Integer.parseInt(split[j]);
				copyice[i][j] = ice[i][j]; //ice배열 복사
			}
		}
		
		split = br.readLine().split(" ");
		for(int i=0; i<Q; i++) { //마법
			n = Integer.parseInt(split[i]);;
			cut(0, 0, (int) Math.pow(2, N)); //격자 나누기
		}
	}

	private static void cut(int r, int c, int size) {
		if(size == (int) Math.pow(2, n)) {
			rotate(r, c, size);
			return;
		}
		int half = size/2;
		cut(r, c, half); //좌상
		cut(r, c+half, half); //우상
		cut(r+half, c, half); //좌하
		cut(r+half, c+half, half); //우하
	}

	private static void rotate(int r, int c, int size) {
		int temp[][] = new int[size][size];
		for(int x=0; x<size; x++) {
			for(int y=0; y<size; y++) {
				temp[r+y][c+size-x-1]=ice[r+x][c+y];
			}
		}
		for(int i=r; i<r+size; i++) {
			for(int j=c; j<c+size; j++) {
				System.out.print(temp[i][j]);
			}
			System.out.println();
		}
	}
	
	
}
