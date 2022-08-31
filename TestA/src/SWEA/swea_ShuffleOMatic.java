package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class swea_ShuffleOMatic {
	static int N;
	static int card[];
	static int min;
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			card = new int[N];
			String split[] = br.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				card[i] = Integer.parseInt(split[i]);
			}

			min = Integer.MAX_VALUE;
			dfs(0, card);
			if(min == Integer.MAX_VALUE) min = -1;
			System.out.println("#" + tc + " " + min);
		}
	}

	private static void dfs(int cnt, int[] card) {
		if(cnt>5) return;
		if(cnt>=min) return;
		
		if(isSorted(card)) {
			min = Math.min(min, cnt);
			return;
		}
		
		int Lcard[] = new int[N/2];
		int Rcard[] = new int[N/2];
		
		for(int i=0; i<N/2; i++) {
			Lcard[i] = card[i]; 
		}
		for(int i=N/2; i<N; i++) { 
			Rcard[i-N/2] = card[i]; 
		}
		
		for(int x=1; x<N; x++) {
			int[] next = x<N/2 ? shuffle(x,Lcard,Rcard) : shuffle(x-N/2,Rcard,Lcard);
			dfs(cnt+1, next);
		}
	}

	private static boolean isSorted(int[] card) {
		boolean isUp = true;
		boolean isDown = true;
		for(int i=0; i<N; i++) {
			if(card[i] != i+1) isUp = false;
			if(card[i] != N-i) isDown = false;
			
			if(!isUp && !isDown) return false;
		}
		return true;
	}

	private static int[] shuffle(int x, int[] Lcard, int[] Rcard) {
		int next[] = new int[N];
		int idx = 0;
		int LIdx = 0;
		int RIdx = 0;
		
		while(LIdx<N/2 - x) {
			next[idx++] = Lcard[LIdx++];
		}
		int order = 0;
		while(LIdx<N/2 && RIdx<N/2) {
			next[idx++] = order%2==0 ? Rcard[RIdx++] : Lcard[LIdx++]; 
			order++;
		}
		while(RIdx < N/2) {
			next[idx++] = Rcard[RIdx++];
		}
		return next;
	}

}
