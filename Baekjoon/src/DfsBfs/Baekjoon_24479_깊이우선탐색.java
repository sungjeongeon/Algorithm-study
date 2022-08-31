package DfsBfs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// 알고리즘 수업 - 깊이 우선 탐색
public class Baekjoon_24479_깊이우선탐색 {
	static int N, M, R, cnt;
	static int visited[];
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	
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
		dfs(R);
		
		for(int i=1; i<visited.length; i++) {
			System.out.println(visited[i]);
		}
	}
	
	static void dfs(int start) {
		visited[start] = cnt;
		for(int i=0; i<graph.get(start).size(); i++) {
			int x = graph.get(start).get(i);
			if(visited[x] == 0) {
				cnt++;
				dfs(x);
			}
		}
	}
}
//반례
//1 0 0 2 4 3