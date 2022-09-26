package suppl_forA;

import java.util.Arrays;

// nPr, nCr, 2^n
public class PermCombSubs {
	static int N = 4, R=3, C=0;
	static int[] a = {1,2,3,4}, b=new int[R];
	static boolean[] v = new boolean[N];
	public static void main(String[] args) {
		//perm(0); //순열 4P3=4*3*2=24
		//comb(0,0); //조합 4C3=4!/3! = 4
		subs(0); //부분집합 s^4=16
		System.out.println(C);
	}

	private static void perm(int depth) {
		if(depth==R) {
			System.out.println(Arrays.toString(b));
			C++;
			return;
		}
		for(int i=0; i<N; i++) {
			if(v[i]) continue;
			v[i] = true;
			b[depth] = a[i];
			perm(depth+1);
			v[i] = false;
		}
	}
	
	private static void comb(int depth, int start) {
		if(depth==R) {
			System.out.println(Arrays.toString(b));
			C++;
			return;
		}
		for(int i=start; i<N; i++) {
			b[depth] = a[i];
			comb(depth+1, i+1);
			//comb(depth+1, i+1); //중복조합
		}
	}
	
	private static void subs(int depth) {
		if(depth==N) {
			for(int i=0; i<N; i++) {
				System.out.println(v[i] ? a[i] : "X");
			}
			System.out.println();
			return;
		}
		v[depth] = true;
		subs(depth+1);
		v[depth] = false;
		subs(depth+1);
	}

}
