import java.util.Arrays;
import java.util.Scanner;

public class ChocolatePacking {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//System.out.println("Give pack's number");
		int n = sc.nextInt();
		//System.out.println("Give the number of chocolates in each pack");
		int[] chocolate = new int[n];
		for(int i = 0;i < n; i++) {
			chocolate[i] = sc.nextInt();
		}
		//System.out.println("give the number of boxes");
		int m = sc.nextInt();
		//System.out.println("give the capacity of each box");
		int[] capacity = new int[m];
		for(int i = 0;i<m;i++) {
			capacity[i] = sc.nextInt();
		}
		sc.close();
		int sumCho= 0;
		int sumCap = 0;
		for( int i = 0; i<n; i++) {
			sumCho+= chocolate[i];
		}
		for( int i = 0; i<m; i++) {
			sumCap+= capacity[i];
		}
		
		if (sumCho > sumCap) {
			System.out.println(-1);
			return;
		}
		if (sumCho == 0 || (sumCho == 0 && sumCap == 0)){
			System.out.println(0);
			return;
		}
		
		Arrays.sort(chocolate);
		Arrays.sort(capacity);
		
		int b = m-1; //box index
		int c = n-1; // chocolate index
		int boxes = 0;
		
		while(sumCho > 0) {
			if(chocolate[c]<= capacity[b]) {
				capacity[b] -= chocolate[c];
				sumCho-=chocolate[c];
				c--;
				//boxes++;
				
			}else{
				chocolate[c]-=capacity[b];
				sumCho-=capacity[b];
				capacity[b] = 0;
				//boxes++;
				b--;
			}
		}
		boxes = m-b;

		System.out.println(boxes + " boxes are needed");

	}

}