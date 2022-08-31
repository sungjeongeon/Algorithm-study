package Baekjoon;
//baekjoon 16234 인구이동
//다시 풀어보기

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_16234_인구이동 {
	static int N, L, R;
	static int arr[][];
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	static ArrayList<Point> people;
	static boolean visited[][];
	static int answer;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String split[] = br.readLine().split(" ");
		N = Integer.parseInt(split[0]); //NxN크기의 땅
		L = Integer.parseInt(split[1]); //L명이상
		R = Integer.parseInt(split[2]); //R명이상
		 
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			split = br.readLine().split(" ");
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(split[j]);
			}
		}
		
		int result = 0;
		while(true) {
			boolean flag = false;
			visited = new boolean[N][N]; //방문 초기화
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j]) {
						int sum = bfs(i,j);
						if(people.size() > 1) { //인구수를 2군데 이상 바꿀 수 있다면
							change(sum);
							flag = true;
						}
					}
				}
			}
			if(!flag) {
				System.out.println(result);
				return;
			} else {
				result++;
			}
		}
	}

	private static int bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		people = new ArrayList<>(); //인구수 바꿀 수 있는 좌표 저장
		
		q.add(new Point(x,y));
		visited[x][y] = true;
	
		int sum = arr[x][y];
		people.add(new Point(x,y));
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nextX = cur.x + dx[d];
				int nextY = cur.y + dy[d];
				
				if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;
				
				int dist = Math.abs(arr[nextX][nextY]-arr[cur.x][cur.y]);
				if(!visited[nextX][nextY] && dist >= L && dist <= R) {
					q.add(new Point(nextX, nextY));
					visited[nextX][nextY] = true;
					people.add(new Point(nextX, nextY));
					sum += arr[nextX][nextY];
				}
			}
		}
		return sum;
	}
	
	//인구수 이동하기
	private static void change(int sum) {
		int avg = sum / people.size();
		for(int i=0; i<people.size(); i++) {
			arr[people.get(i).x][people.get(i).y] = avg;
		}
	}
	
	//좌표값 클래스
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
}
