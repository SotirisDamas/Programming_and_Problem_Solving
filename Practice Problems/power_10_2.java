import java.util.Scanner;

public class power_10_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = Integer.parseInt(sc.nextLine());
		int n = Integer.parseInt(sc.nextLine());
		sc.close();
		int res = power(x,n);
		System.out.println(res);
		
	}
	public static int power(int x,int n) {
		if(n == 0) return 1;
		return x* power(x,n-1);
	}
}
