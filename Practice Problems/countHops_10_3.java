

import java.util.Arrays;
import java.util.Scanner;

public class countHops_10_3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();
		int[] memo = new int[n+1];
		Arrays.fill(memo, -1);
		
		int res = countWays(n, memo);
		System.out.println(res);
	}
	
	public static int countWays(int n, int[] memo) {
		if (n == 0) return 1;
        if (n < 0) return 0;
        
        if(memo[n] != -1) return memo[n];
		
		memo[n] = countWays(n-1,memo) +countWays(n-2, memo)+ countWays(n-3, memo);
		return memo[n];
	}
}

