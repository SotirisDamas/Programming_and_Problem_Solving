import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class spaceBattleSimulation_6_3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] str = sc.nextLine().split(" ");
		sc.close();
		
		int N = str.length;
		if(N==0) {
			System.out.println(0);
            return;
		}
		
		int[] ships = new int[N];
		for(int i =0;i<N; i++) {
			ships[i] = Integer.parseInt(str[i]);
		}
			
		ArrayList<Integer> rfleets = new ArrayList<>();
		
		for(int s : ships) {
			boolean destroyed = false;
			if(s > 0) {
				rfleets.add(s);
			} else {
				while(!rfleets.isEmpty() && rfleets.get(rfleets.size()-1) > 0) {
					int lastRF = rfleets.get(rfleets.size()- 1);
					
					if(lastRF + s >0) { 
						destroyed = true;
						break;
					}else if(lastRF + s < 0) {
						rfleets.remove(rfleets.size() - 1);
					}else {
						rfleets.remove(rfleets.size() - 1);
						destroyed = true;
						break;
					}
				}
				if(!destroyed) rfleets.add(s);
			}		
		}
		if(rfleets.isEmpty()) {
			System.out.println(0);
		}else {
			List<String> fl = new ArrayList<>();
			for(int i = 0;i< rfleets.size();i++) {
				fl.add(rfleets.get(i).toString());
				
			//for (int fleet : rfleets) {
	               //fl.add(String.valueOf(fleet));
			}
			String s = String.join(" ",fl);
			System.out.print(s);
		}		
		
	}
}
