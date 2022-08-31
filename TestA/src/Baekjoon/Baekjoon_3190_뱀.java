package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class Baekjoon_3190_뱀 {
	static int board[][];
	static int N;
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	
	static ArrayList<Point> SecDir = new ArrayList<>();
	static Queue<Point> snake = new LinkedList<>();
	static int L;
	static int sec[];
	static char dir[];
	static int ans;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); //보드의 크기
		int K = Integer.parseInt(br.readLine()); //사과의 개수
		
		board = new int[N][N];
		for(int i=0; i<K; i++) {
			String split[] = br.readLine().split(" ");
			int x =Integer.parseInt(split[0]) -1;
			int y= Integer.parseInt(split[1]) -1;
			board[x][y] = -1; //사과는 -1로 표시
		}
		
		L = Integer.parseInt(br.readLine()); //뱀의 변환 횟수
		for(int i=0; i<L; i++) {
			String split[] = br.readLine().split(" ");
			int sec = Integer.parseInt(split[0]);
			int dir = split[1].charAt(0)=='D'?1:-1;
			SecDir.add(new Point(sec,dir));
		}
		
		board[0][0] = 1; 
		int time=0, idx=0;
		int curdir = 0;
		Point cur = new Point(0,0);
		snake.add(new Point(0,0));
		
		while(true){
            time++;
            
            int nx = cur.x + dx[curdir];
            int ny = cur.y + dy[curdir];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N || board[nx][ny] == 1) break;

            if(board[nx][ny] != -1){
                Point tail = snake.poll();
                board[tail.x][tail.y] = 0;
            }
            
            cur = new Point(nx,ny);
            snake.add(new Point(nx,ny));
            board[nx][ny] = 1;

            if(idx < L && SecDir.get(idx).x == time){
                curdir = (curdir + SecDir.get(idx).y) % 4;
                curdir = curdir == -1 ? 3 : curdir;
                idx++;
            }
        }
		
		System.out.println(time);
	}
	
	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
