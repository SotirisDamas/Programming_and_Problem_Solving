package Practice1;

import java.util.Scanner;

public class islandCounter {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("give the dimensions of the grid");
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[][] grid = new int[n][m];
		for(int i=0;i<n; i++) {
			for(int j=0;j<m;j++) {
				grid[i][j] = sc.nextInt();
			}
		}
		if (grid == null || grid.length == 0) System.out.println(0);
		
		boolean[][] visited = new boolean[n][m];
		int count = 0;
		for (int i=0;i<n; i++) {
			for(int j=0;j<m;j++) {
				if (grid[i][j] == 1 && !visited[i][j]) {
					dfs(i, j, grid, visited);
					count++;
				}
			}
		}
		System.out.println(count);
	}
	private static void dfs(int row,int col,int[][] grid, boolean[][] visited) {
		visited[row][col]=true;
		// Check and move North (up)
		if(row-1>=0 && grid[row-1][col]==1 && !visited[row-1][col]) dfs(row-1,col,grid,visited);
		// Check and move South (down)
		if(row+1< grid.length && grid[row+1][col]==1 && !visited[row+1][col]) dfs(row+1,col,grid,visited);
		// Check and move West (left)
		if(col-1>= 0 && grid[row][col-1]==1 && !visited[row][col-1]) dfs(row,col-1,grid,visited);
		// Check and move East (right)
		if(col+1< grid[0].length && grid[row][col+1]==1 && !visited[row][col+1]) dfs(row,col+1,grid,visited);
	}

}