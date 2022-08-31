package BruteForce;

import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon_2309 {
	public static void main(String[] args) {
	
		Scanner	sc = new Scanner(System.in);
		
		int[] nine = new int[9];
		int sum = 0;
		
		for(int i=0; i<nine.length; i++) {
			nine[i] = sc.nextInt();
			sum += nine[i];
		}
		Arrays.sort(nine);
		
		int a=0, b=0;
		for(int i=0; i<nine.length-1; i++) {
			for(int j=i+1; j<nine.length; j++) {
				if(sum - nine[i] - nine[j] == 100) {
					a = i;
					b = j;
					break;
				}
			}
		}
		
		for(int i=0; i<nine.length; i++) {
			if(i==a || i==b) continue;
			else {
				System.out.println(nine[i]);
			}
		}
	}
		
}
