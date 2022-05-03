import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TicTacToe 
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("Hello, World!");

        Board board = new Board();

        // A BufferedReader allows you to take in input strings from the terminal
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        // An example of how to use BufferedReader:
        //String tempString = reader.readLine();
        //System.out.println(tempString);

        System.out.println(board.displayBoard());
    }
}
