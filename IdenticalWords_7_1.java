

import java.util.Scanner;

public class IdenticalWords_7_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		sc.nextLine();
		
		String[] words = new String[N];
		for(int i = 0;i < N;i++) {
			words[i] = sc.nextLine();
		}
		sc.close();
		char[] l = {'a','b','c','d','e'};
		StringBuilder target = new StringBuilder();
		int countChanges = 0;
		
		for(int j = 0;j < M;j++) {
			int[] freq = new int[5];
			
			for(int i = 0;i < N;i++) {
				char c = words[i].charAt(j);
				freq[c - 'a']++;	
			}
			
			char best = l[0];
			int max = freq[0];
			for(int k = 1;k < 5;k++) {
				if(freq[k] > max) {
					max = freq[k];
					best = l[k];
				}else if(freq[k] == max) {
					if(l[k] < best) {
						best = l[k];
					}
				}
			}
			target.append(best);
			countChanges += (N - max);
		}
		System.out.println(target);
		System.out.println(countChanges);

	}

}
