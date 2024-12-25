

import java.util.Scanner;
import java.util.Stack;

public class box_loading_9_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] orderS = sc.nextLine().split(" ");
		int n = orderS.length;
		int[] order = new int[n];
		for(int i = 0;i<n;i++) {
			order[i] = Integer.parseInt(orderS[i]);
		}
		sc.close();
		
		Stack<Integer> stack = new Stack<>();
		int i = 0; // index of order
		int boxOrder = 1;
		while(boxOrder <= n) {
			if(boxOrder == order[i]) {
				i++;
			}else {
				stack.push(boxOrder);
			}
			
			while(!stack.isEmpty() && stack.peek() == order[i]) {
				i++;
				stack.pop();
			}
			boxOrder++;
		}
		System.out.println(i);

	}

}
