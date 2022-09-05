package StudyGroup.Sep_week01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon_17352_여러분의다리가되어드리겠습니다 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] graph = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<N-2; i++) {
			String split[] = br.readLine().split(" ");
			int a = Integer.parseInt(split[0]);
		    int b = Integer.parseInt(split[1]);
		    graph[a].add(b);
		    graph[b].add(a);
		}
		
		int flag[] = new int[300001];
		Queue<Integer> q = new LinkedList<>();

		q.add(1);
		flag[1] = 1; //flag 1인 마을과 0인 마을로 나누기
		sb.append(1 + " ");
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=0; i<graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				if(flag[next] == 0) {
					flag[next] = 1;
					q.add(next);
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(flag[i] == 0) {
				sb.append(i);
				break;
			}
		}
		
		System.out.println(sb);
	}
}
