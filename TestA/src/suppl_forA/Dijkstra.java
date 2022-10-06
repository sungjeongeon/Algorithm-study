package suppl_forA;

import java.io.*;
import java.util.*;

public class Dijkstra {
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/dijkstra_input.txt"));
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		int[][] g=new int[N][N];
		boolean[] v=new boolean[N];
		int[] dist=new int[N]; //다익스트라
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				g[i][j]=sc.nextInt();
			}
			dist[i]=Integer.MAX_VALUE;
		}
		int /*result=0,*/ cnt=0;
		dist[0]=0;
		for(int i=0; i<N; i++){
			int minVertext=-1;
			int min=Integer.MAX_VALUE;
			for(int j=0; j<N; j++){
				if(!v[j] && min>dist[j]){
					        minVertext=j;
					        min=dist[j];
				}
			}
			v[minVertext]=true;
			//result+=min;
			if(minVertext == -1) break;
			//if(cnt++ == N-1) break;
			for(int j=0; j<N; j++){
				if(!v[j] && g[minVertext][j]!=0 && dist[j]>min+g[minVertext][j]){
					                               dist[j]=min+g[minVertext][j];					
				}
			}
		}
		System.out.println(dist[N-1]);
		sc.close();
	}
}
