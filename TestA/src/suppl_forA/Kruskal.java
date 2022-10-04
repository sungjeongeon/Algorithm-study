package suppl_forA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Kruskal {
	static int N;
	static int[][] edges;
	static int[] p;
	
	static void make() {
		p = new int[N];
		for(int i=0; i<N; i++) p[i] = i;
	}
	
	static int find(int a) {
		if(a==p[a]) return a;
		return p[a] = find(p[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot==bRoot) return false;
		p[bRoot] = aRoot;
		return true;
	}
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("res/kruskal_input.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int E = sc.nextInt();
		edges=new int[E][3];
		
		for(int e=0; e<E; e++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int weight = sc.nextInt();
			edges[e] = new int[] {from, to, weight};
		}
		Arrays.sort(edges, (o1, o2) -> Integer.compare(o1[2], o2[2]));
		make();
		int result=0, cnt=0;
		for(int[] edge:edges) {
			if(union(edge[0], edge[1])) {
				result+=edge[2];
				if(cnt++ == N-1) break;
			}
		}
		System.out.println(result);
		sc.close();
	}
}
