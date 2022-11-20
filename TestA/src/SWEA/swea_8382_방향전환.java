package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class swea_8382_방향전환 {
	static int x2, y2;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			String split[] = br.readLine().split(" ");
			int x1 = Integer.parseInt(split[0]);
			int y1 = Integer.parseInt(split[1]);
			x2 = Integer.parseInt(split[2]);
			y2 = Integer.parseInt(split[3]);
			
			min = Integer.MAX_VALUE;
			
			turn(x1, y1, false, 0); //가로 시작
			turn(x1, y1, true, 0); //세로 시작
			
			System.out.println("#" + tc + " " + min);
		}
	}

	private static void turn(int x, int y, boolean rc, int cnt) {
		if(x == x2 && y == y2) {
			min = Math.min(min, cnt);
			return;
		}
		
		if(!rc) {
			if(y>y2) {
				turn(x,y-1, true, cnt+1);
			}else {
				turn(x,y+1, true, cnt+1);
			}
		}else {
			if(x>x2) {
				turn(x-1,y, false, cnt+1);
			}else {
				turn(x+1,y, false, cnt+1);
			}
		}
	}
}
