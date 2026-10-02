	import java.util.Scanner;
public class Sudoku
	{		
	

			public static void main(String[] args)
			{
				System.out.println("Hello welcome to Sudoku!");
				Scanner userDifficulty = new Scanner(System.in);
				System.out.println("Select your difficulty");
				System.out.println("1. Easy      2. Medium      3. Hard      4. Impossible");
				int difficulty = userDifficulty.nextInt();
				int newDifficulty = 0;
				switch(difficulty)
				{
					case 1:
							{
								newDifficulty = 30;
								break;
							}
					case 2:
							{
								newDifficulty = 50;
								break;
							}
					case 3:
							{
								newDifficulty = 60;
								break;
							}
					case 4:
							{
								newDifficulty = 70;
								break;
							}
				}
				
				Board.createPuzzle(newDifficulty);

				while (!Board.isSolved())
				{
					
					Board.displayNumberBoard();
					Scanner scanner = new Scanner(System.in);
					System.out.print("Enter a move like B3 7 (0 erases, q quits): ");
					String input = scanner.nextLine().trim().toUpperCase();

					if (input.equals("Q"))
					{
						System.out.println("Thanks for playing!");
						return;
					}

					String[] answer = input.split(" ");
					if (answer.length != 2 || answer[0].length() != 2 || answer[1].length() != 1)
					{
						System.out.println("Use the format: B3 7");
						continue;
					}

					char columnLetter = answer[0].charAt(0);
					char rowDigit = answer[0].charAt(1);
					char valueDigit = answer[1].charAt(0);

					if (columnLetter < 'A' || columnLetter > 'I' || rowDigit < '1' || rowDigit > '9'
							|| valueDigit < '0' || valueDigit > '9')
					{
						System.out.println("Column must be A-I, row 1-9, and value 0-9.");
						continue;
					}

					int column = columnLetter - 'A';
					int row = rowDigit - '1';
					int value = valueDigit - '0';
					Board.placeNumber(row, column, value);
				}

				Board.displayNumberBoard();
				System.out.println("Congratulations, you solved it!");
			}
	}
