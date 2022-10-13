package SWEA;

import java.io.*;
import java.util.*;


public class swea_7793_오나의여신님_2{
	static int[] dx={1,0,-1,0};
	static int[] dy={0,1,0,-1};
	
	static int N, M;
	static char map[][];
	static  Queue<Point> q;
	static String ans;
	public static void main(String args[]) throws Exception{
		System.setIn(new FileInputStream("res/input_swea_7793.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();	
		//StringTokenizer st=new StringTokenizer(br.readLine()," ");
		
        int T=Integer.parseInt(br.readLine());
        
    	for(int tc=1; tc<=T; tc++){
	    	String split[] = br.readLine().split(" ");
	    	
	        N=Integer.parseInt(split[0]);
	        M=Integer.parseInt(split[1]);
	        
	        map=new char[N][M];
	        q=new ArrayDeque<>();
	        Point S=null;
	        
	        for(int i=0; i<N; i++){
	            map[i]=br.readLine().toCharArray();
	            for(int j=0; j<M; j++){
	                     if(map[i][j]=='S') S=new Point(i,j,1,1);//연수
	                else if(map[i][j]=='*') q.offer(new Point(i,j,0,1));//악마
	            }
	        }
	        q.offer(S);
	        
	        ans = "GAME OVER";
	        bfs();
	        
	        System.out.println("#" + tc + " " + ans);
    	}
    }
	
	private static void bfs() {
		 while(!q.isEmpty()){
	        	Point cur = q.poll();

     		for(int d=0; d<4; d++) {
 				int nx = cur.x + dx[d];
 				int ny = cur.y + dy[d];
 				
 				//경계값을 벗어나거나 돌이라면 
 				if(nx<0 || nx>=N || ny<0 || ny>=M || map[nx][ny] == 'X' || map[nx][ny] == '*') continue;
 				

 	        	if(cur.who == 0) { //악마일때
 	        		if(map[nx][ny] == 'D') continue;
 	        		map[nx][ny] = '*';
 	        		q.offer(new Point(nx, ny, cur.who, cur.cnt + 1));
 	        	} else {
 	        		if(map[nx][ny] == 'S') continue;
 	        		if(map[nx][ny] == 'D') {
 	        			ans = Integer.toString(cur.cnt);
 	        			return;
 	        		}
 	        		map[nx][ny] = 'S';
 	        		q.offer(new Point(nx, ny, cur.who, cur.cnt + 1));
 	        	}
     		}
		 }
	}

	static class Point {
		int x, y, who, cnt;

		public Point(int x, int y, int who, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.who = who;
			this.cnt = cnt;
		}
	}
}
