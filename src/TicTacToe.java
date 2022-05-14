import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class TicTacToe 
{

    /*

    Requirements:
    - there can be two players DONE
    - players take turns (i.e. player 0 goes, then player 1 goes, etc) DONE
    - players input their turns in the terminal while the main loop is running DONE
    - there can be a winner DONE
    - there can be a tie DONE
    - all fields in the Board class are private DONE
    */

    public static void main(String[] args) throws Exception
    {
        System.out.println("Hello, World!");

        Board board = new Board();

        // A BufferedReader allows you to take in input strings from the terminal
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // This is the main game loop
        boolean isP1 = true;
        while(!board.isGameOver())
        {
            System.out.println(board.displayBoard());
            // An example of how to use BufferedReader:
            String num = "";
            Boolean isAllowed = false;
            int row=0;
            int col=0;

            List<String> allowedInputs = Arrays.asList("1","2","3","4","5","6","7","8","9");
            while(!isAllowed)
            {
                System.out.println("Please enter a digit between 1 and 9 inclusive that hasn't already been taken");
                num = reader.readLine();

                if (allowedInputs.contains(num))
                {
                    row = (int)Math.floor((Integer.parseInt(num)-1))/3;
                    col = (Integer.parseInt(num)-1)%3;
                    if (board.isOpen(row, col))
                    {
                        isAllowed = true;
                    }
                }
            }


            board.takeTurn(isP1,row,col);
            isP1=!isP1;
        }

        System.out.println(board.displayBoard());
        int win = board.testAllBoardSpace();
        if (win==0)
        {
            System.out.println("Tie!!!");
        } 
        else
        {
            System.out.println(("Player "+board.testAllBoardSpace()+" WINS!"));
        }
        
    }
}
