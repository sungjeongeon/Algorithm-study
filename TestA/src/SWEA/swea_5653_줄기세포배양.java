package SWEA;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class swea_5653_줄기세포배양 {
	static int n, m, k;
	static int[][] arr;
	static List<Pair> list;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int test = in.nextInt();
		list = new ArrayList<>();
		for (int i = 1; i <= test; i++) {
			list.clear();

			n = in.nextInt();
			m = in.nextInt();
			k = in.nextInt();
			arr = new int[n + k][m + k];
			for (int j = (k / 2); j < (k / 2) + n; j++) {
				for (int z = (k / 2); z < (k / 2) + m; z++) {
					arr[j][z] = in.nextInt();
					if (arr[j][z] != 0)
						list.add(new Pair(j, z, arr[j][z], arr[j][z], k, 0));
				}
			}
			solve();
			
			int result = 0;
			for (int j = 0; j < arr.length; j++) {
				for (int z = 0; z < arr[0].length; z++) {
					if (arr[j][z] != 0 && arr[j][z] != -1)
						result++;
				}
			}
			System.out.println("#" + i + " " + result + "\n");
		}
	}

	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	private static void solve() {
		PriorityQueue<Pair> queue = new PriorityQueue<Pair>(list);

		while (!queue.isEmpty()) {
			Pair t = queue.poll();
			if (t.state == 0 && t.flag == 1) {
				arr[t.x][t.y] = -1;
				continue;
			}
			if (t.time == 0) {
				continue;
			}
			if (t.state == 0) {
				queue.add(new Pair(t.x, t.y, t.k, t.k, t.time, 1));
			} else {
				queue.add(new Pair(t.x, t.y, t.k, t.state - 1, t.time - 1, t.flag));
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int tx = t.x + dir[i][0];
				int ty = t.y + dir[i][1];
				if (tx < 0 || ty < 0 || tx >= n + k || ty >= m + k) {
					continue;
				}
				if (arr[tx][ty] != 0) {
					continue;
				}
				arr[tx][ty] = t.k;
				queue.add(new Pair(tx, ty, t.k, t.k, t.time - 1, 0));
			}
		}
	}
}

class Pair implements Comparable<Pair> {
	public int x;
	public int y;
	public int k;
	public int state; // 변하는 생명력
	public int time;
	public int flag; // 0이 되었을 때, 번식 했나 유무

	public Pair(int x, int y, int k, int state, int time, int flag) {
		this.x = x;
		this.y = y;
		this.k = k;
		this.state = state;
		this.time = time;
		this.flag = flag;
	}

	@Override
	public int compareTo(Pair o) {
		return o.time - this.time;
	}
}
