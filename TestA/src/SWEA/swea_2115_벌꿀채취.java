package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea_2115_벌꿀채취 {
	static int N,M,C,maxBenefit;
    static int map[][];
    public static void main(String[] args) throws IOException{
    	System.setIn(new FileInputStream("res/input_swea_2115.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
             
            map = new int[N][N];
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println("#"+t+" "+combination());
        }
    }
     
    private static int combination() {
        int result=0;
        int max1=0;
        int max2=0;
         
        for(int i=0;i<N;i++) {
            for(int j=0;j<=N-M;j++) {
                maxBenefit=0;
                getMaxHoney(i,j,0,0,0);
                max1 = maxBenefit;
                 
                 
                max2=0;
                maxBenefit=0;               
                for(int j2=j+M;j2<=N-M;j2++) {
                    getMaxHoney(i,j2,0,0,0);
                    max2 = Math.max(max2,maxBenefit);
                }
                 
                for(int i2=i+1;i2<N;i2++) {
                    for(int k=0;k<=N-M;k++) {
                        getMaxHoney(i2,k,0,0,0);
                        max2 = Math.max(max2,maxBenefit);
                    }
                }
                result = Math.max(result,max1+max2);
            }
        }
        return result;
    }
     
    private static void getMaxHoney(int x, int y, int cnt, int sum, int benefit) {
         
        if(sum>C) return;
         
        if(cnt==M) {
            maxBenefit = Math.max(maxBenefit,benefit);
            return;
        }
         
        getMaxHoney(x,y+1,cnt+1,sum+map[x][y],benefit+(map[x][y]*map[x][y]));
        getMaxHoney(x,y+1,cnt+1,sum,benefit);
    }
}