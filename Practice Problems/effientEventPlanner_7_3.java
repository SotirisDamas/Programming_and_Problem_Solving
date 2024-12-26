import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class effientEventPlanner_7_3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		int[][] times = new int[N][2];
		for(int i = 0;i< N;i++) {
			times[i][0] = sc.nextInt();
			times[i][1] = sc.nextInt();
		}
		
		Arrays.sort(times,new Comparator<int[]>(){
			@Override
			public int compare(int[] a, int[] b) {
				return Integer.compare(a[1], b[1]);
			}
		});
		int endTime = times[0][1];
		int count = 1;
		for(int i = 1;i < N;i++) {
			if(times[i][0]>= endTime) {
				count++;
				endTime = times[i][1];
			}
		}
		System.out.println(count);
	}

}
