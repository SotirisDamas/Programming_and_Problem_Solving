package Practice1;

import java.util.Scanner;

public class doubleBinarySearch_9_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		int[][] mat = new int[m][n];
		for(int i = 0;i < m;i++) {
			for(int j = 0;j < n;j++) {
				mat[i][j] = sc.nextInt();
			}
		}
		int target = sc.nextInt();
		sc.close();
		int row = -1,col = -1;
		int rowLow = 0;
		int rowHigh = m-1;
		while(rowLow<=rowHigh) {
			int midRow = (rowLow+rowHigh)/2;
			if(mat[midRow][0] <=target && mat[midRow][n-1]>= target) {
				int colLow = 0;
				int colHigh = n-1;
				while(colLow <= colHigh) {
					int midCol = (colLow + colHigh)/2;
					if(mat[midRow][midCol] == target) {
						row = midRow;
						col = midCol;
						break;
					}else if(target > mat[midRow][midCol]) {
						colLow = midCol + 1;
					}else {
						colHigh = midCol - 1;
					}
				}
				break;
			}else if(target > mat[midRow][n-1]) {
				rowLow = midRow + 1;
			}else {
				rowHigh = midRow - 1;
			}
		}
		System.out.println(row + " " +col);
	}

}
