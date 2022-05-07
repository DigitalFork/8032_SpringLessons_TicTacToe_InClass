public class Board 
{
    private final char DEFAULT_TOKEN = '#';
    // State of the tic tac toe board
    // note: everything to the right of the '=' could be done in constructor instead
    //       This is a style choice.
    private char[][] board = new char[3][3];

    // Constructor
    Board()
    {
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                board[row][col] = DEFAULT_TOKEN;
            }
        }
    }

    public String displayBoard()
    {
        StringBuilder builder = new StringBuilder();

        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                builder.append(board[row][col]);
            }
            builder.append('\n');
        }

        return builder.toString();
    }
}
