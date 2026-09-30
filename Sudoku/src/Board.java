import java.util.*;
public class Board
	{
		static int [][] board = new int[9][9];
		static int [][] solution = new int[9][9];
		static boolean [][] starter = new boolean[9][9];
		
		static boolean fillBoard(int[][] grid) 
			{
		    for (int row = 0; row < 9; row++) 
		    	{
		        for (int col = 0; col < 9; col++) 
		        	{
		            if (grid[row][col] == 0) 
		            	{
		                List<Integer> nums = new ArrayList<>();
		                for (int numbers = 1; numbers <= 9; numbers++) nums.add(numbers);
		                Collections.shuffle(nums);
		                for (int number : nums) 
		                	{
		                    if (isValid(grid, row, col, number)) 
		                    	{
		                        grid[row][col] = number;
		                        if (fillBoard(grid)) 
		                        	{
		                        	return true;
		                        	}
		                        grid[row][col] = 0;
		                    	}
		                	}
		                return false;
		            	}
		        	}
		    	}
		    return true;
			}
		public static void createPuzzle(int cellsToRemove)
			{
				for (int row = 0; row < 9; row++)
				{
					for (int column = 0; column < 9; column++)
					{
						solution[row][column] = 0;
					}
				}
				fillBoard(solution);
		 
				for (int row = 0; row < 9; row++)
				{
					for (int column = 0; column < 9; column++)
					{
						board[row][column] = solution[row][column];
					}
				}
		 
				int cellsRemoved = 0;
				while (cellsRemoved < cellsToRemove)
				{
					int row = (int)(Math.random() * 9);
					int column = (int)(Math.random() * 9);
					if (board[row][column] != 0)
					{
						board[row][column] = 0;
						cellsRemoved++;
					}
				}
		 
				for (int row = 0; row < 9; row++)
				{
					for (int column = 0; column < 9; column++)
					{
						starter[row][column] = board[row][column] != 0;
					}
				}
			}
		public static boolean placeNumber(int row, int column, int value)
			{
				if (starter[row][column])
				{
					System.out.println("That cell is a starting clue and cannot be changed.");
					return false;
				}
				if (value == 0)
				{
					board[row][column] = 0;
					return true;
				}
				if (!isValid(board, row, column, value))
				{
					System.out.println("Illegal move: that number is already in the row, column, or box.");
					return false;
				}
				board[row][column] = value;
				return true;
			}
		static boolean isValid(int[][] grid, int row, int col, int value) 
			{
		    for (int i = 0; i < 9; i++) 
		    	{
		        if (i != col && grid[row][i] == value)
		        	{
		        	return false;
		        	}
		        if (i != row && grid[i][col] == value)
		        	{
		        	return false;
		        	}
		    	}
		    int r0 = row / 3 * 3, c0 = col / 3 * 3;
		    for (int row1 = r0; row1 < r0 + 3; row1++)
		    	
		        for (int col1 = c0; col1 < c0 + 3; col1++) 
		        	{
		            if ((row1 != row || col1 != col) && grid[row1][col1] == value)
		            	{
		            	return false;
		            	}
			}
		    return true;
		}
		public static void displayNumberBoard()
			{
				System.out.println();
				System.out.println("    A B C   D E F   G H I");
				System.out.println("  +-------+-------+-------+");
				for (int row = 0; row < 9; row++)
				{
					String line = (row + 1) + " | ";
					for (int column = 0; column < 9; column++)
					{
						if (board[row][column] == 0)
						{
							line += ". ";
						}
						else
						{
							line += board[row][column] + " ";
						}
						if (column % 3 == 2)
						{
							line += "| ";
						}
					}
					System.out.println(line);
					if (row % 3 == 2)
					{
						System.out.println("  +-------+-------+-------+");
					}
				}
				System.out.println();
			}
		public static boolean isSolved()
			{
				for (int row = 0; row < 9; row++)
				{
					for (int column = 0; column < 9; column++)
					{
						if (board[row][column] == 0 || !isValid(board, row, column, board[row][column]))
						{
							return false;
						}
					}
				}
				return true;
			}
	
	}
