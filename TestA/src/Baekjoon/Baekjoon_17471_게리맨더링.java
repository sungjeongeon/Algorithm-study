package Baekjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_17471_게리맨더링 {
	static int N;
	static int pSum[];
	static boolean isSelected[];
	static ArrayList<Integer>[] graph;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		pSum = new int[N+1]; //인구수
		
		graph = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		String split[] = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			pSum[i] = Integer.parseInt(split[i]);
		}
		
		for(int i=0; i<N; i++) {
			split = br.readLine().split(" ");
			int n = Integer.parseInt(split[0]);
			for(int j=1; j<n+1; j++) {
				graph[i].add(Integer.parseInt(split[j])-1);
			}
		}
		
		isSelected = new boolean[N];
		powerset(0);
		
		if(ans==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
		
	}
	private static void powerset(int cnt) {
		if(cnt == N-1) {
			ArrayList<Integer> area1 = new ArrayList<>();
			ArrayList<Integer> area2 = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				if(isSelected[i]) {
					area1.add(i);
				}else area2.add(i);
			}
			
			if(area1.size()==0 || area2.size()==0) return; //각 선거구는 적어도 하나의 구역을 포함해야 한다.
			
			if(isPossible(area1) && isPossible(area2)) {
				int sum1 = 0;
				for(int i=0; i<area1.size(); i++) {
					sum1 += pSum[area1.get(i)];
				}
				int sum2 = 0;
				for(int i=0; i<area2.size(); i++) {
					sum2 += pSum[area2.get(i)];
				}
				
				ans = Math.min(ans, Math.abs(sum1-sum2));
			}
			return;
		}
		
		isSelected[cnt] = true;
		powerset(cnt+1);
		isSelected[cnt] = false;
		powerset(cnt+1);
	}
	
	private static boolean isPossible(ArrayList<Integer> area) { //bfs
		boolean visit[] = new boolean[N];
		Queue<Integer> q = new LinkedList<>();
		q.add(area.get(0));
		visit[area.get(0)] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=0; i<graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				if(visit[next]) continue;
				if(area.contains(next)) {
					q.add(next);
					visit[next] = true;
				}
			}
		}
		for (int i = 0; i < area.size(); i++) {
			if(!visit[area.get(i)]) return false;//false인 애가 있다면 연결이 안되었다는 뜻
		}
		return true;
	}
}
