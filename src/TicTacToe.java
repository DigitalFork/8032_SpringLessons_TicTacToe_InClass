import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TicTacToe 
{

    /*

    Requirements:
    ✅ there can be two players
    ✅ players take turns (i.e. player 0 goes, then player 1 goes, etc)
    ✅ players input their turns in the terminal while the main loop is running
    ✅ there can be a winner
    ✅ there can be a tie
    ✅ all fields in the Board class are private
    */

    public static void main(String[] args) throws Exception
    {
        System.out.println("Hello, World!");

        Board board = new Board(5,5,4);

		Bot myBot = new Bot();

		boolean botEnabled = false;

        // A BufferedReader allows you to take in input strings from the terminal
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // This is the main game loop
        while(!board.isGameOver())
        {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            System.out.println(board.displayBoard());
			if(board.getTurn() > 1 & botEnabled)
			{
				int move = myBot.Think(board);
				board.takeTurn(move);
			}
			else
			{

				System.out.println("Player " + board.getPlayerChar(board.getTurn()) + ", make your move");
				boolean valid = false;
				while (!valid)
				{
					try
					{
						String tempString = reader.readLine();
						board.takeTurn(tempString);
						valid = true;
					}
					catch (Exception e)
					{
						System.out.print("\033[2A\033[1G\033[0J");
						System.out.println("Player " + board.getPlayerChar(board.getTurn()) + ", please give a valid move (one of the numbers on the board).");
					}
				}
			}

        }
		System.out.print("\033[H\033[2J");
		System.out.flush();
		System.out.println(board.displayBoard());

		if (board.getWinner() != board.getDefaultToken() & board.getWinner() != '\0')
		{
			System.out.println("Congratulations player " + board.getWinner() + ", you won!");
		}
		else
		{
			System.out.println("The game was a tie!");
		}
    }
}
