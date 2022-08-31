package DfsBfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

//알고리즘 수업 - 너비 우선 탐색
public class Baekjoon_24444_너비우선탐색 {
	static int N, M, R, cnt;
	static int visited[];
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static Queue<Integer> q = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String split[] = br.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		R = Integer.parseInt(split[2]);
		visited = new int[N+1]; //정답
		
		for(int i=0;i<N+1;i++){
			graph.add(new ArrayList<Integer>());
	    }
		
		for(int i=0; i<M; i++) {
			split = br.readLine().split(" ");
			int u = Integer.parseInt(split[0]);
			int v = Integer.parseInt(split[1]);
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		for(int i=0; i<graph.size(); i++) {
			Collections.sort(graph.get(i));
		}

		cnt=1;
		bfs(R);
		
		for(int i=1; i<visited.length; i++) {
			System.out.println(visited[i]);
		}
	}
	
	static void bfs(int start) {
		q.offer(start);
		visited[start] = cnt++;
		
		while(!q.isEmpty()) {
			int x = q.poll();
			for(int i=0; i<graph.get(x).size(); i++) {
				int y = graph.get(x).get(i);
				if(visited[y] != 0) continue;
				 q.offer(y);
	             visited[y] = cnt++;
			}
		}
	}
}
