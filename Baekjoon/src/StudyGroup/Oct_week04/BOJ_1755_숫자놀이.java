package StudyGroup.Oct_week04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BOJ_1755_숫자놀이 {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	String en[] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    	
    	int M = sc.nextInt();
    	int N = sc.nextInt();
    	
    	ArrayList<String> list = new ArrayList<>();
    	
    	for(int i=M; i<=N; i++) {
    		if(i >= 10) {
        		int num1 = i / 10;
        		int num2 = i % 10;
        		
        		list.add(en[num1] + " " + en[num2]);
        	} else {
        		list.add(en[i]);
        	}
    	}
    	
    	Collections.sort(list);
    	
    	ArrayList<Integer> list2 = new ArrayList<>();
    	for(int i=0; i<list.size(); i++) {
    		if(list.get(i).contains(" ")) { //10의 자리 
    			String split[] = list.get(i).split(" ");
    			int num = 0;
    			for(int j=0; j<=9; j++) {
    				if(split[0].equals(en[j])) {
    					num += 10* j;
    				}
    				if(split[1].equals(en[j])) {
    					num += j;
    				}
    			}
    			list2.add(num);
    		}else {
    			for(int j=0; j<=9; j++) {
    				if(list.get(i).equals(en[j])) {
    					list2.add(j);
    					break;
    				}
    			}
    		}
    	}
    	
    	for(int i=0; i<list2.size(); i++) {
    		if(i % 10 == 9) {
    			System.out.println(list2.get(i) + " ");
    		}else {
    		System.out.print(list2.get(i) + " ");
    		}
    	}
    	
    }
}
