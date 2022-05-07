public class Board 
{
    // The "final" keyword means something is immutable and cannot be changed after declaration. 
    // private final char DEFAULT_TOKEN = ' ';
    private final char PLAYER1_TOKEN = 'X';
    private final char PLAYER2_TOKEN = 'O';

    // State of the tic tac toe board
    // note: everything to the right of the '=' could be done in constructor instead
    //       This is a style choice.
    private char[][] board = {{'1','2','3'},{'4','5','6'},{'7','8','9'}};

    /* BS for testing if the board is won
    private final byte[][] boardTests = 
    {
        {146,80,49},
        {,,},
        {,,}
    };
    
    //Test 1 = Left col
    //Test 2 = Middle col
    //Test 3 = Right col
    //Test 4 = Top row
    //Test 5 = Middle row
    //Test 6 = Bottom row
    //Test 7 = Diagonal going down and right
    //Test 8 = Diagonal goinf down and left
    */

    // Constructor
    Board()
    {
        // for(int row = 0; row < 3; row++)
        // {
        //     for(int col = 0; col < 3; col++)
        //     {
        //         board[row][col] = DEFAULT_TOKEN;
        //     }
        // }
    }

    // You may choose to change the return type if you like
    public void takeTurn(boolean isP1,int col,int row)
    {
        if (isP1){
            board[col][row]=PLAYER1_TOKEN;
        }
        else{
            board[col][row]=PLAYER2_TOKEN;
        }
    }

    public boolean isGameOver()
    {
        return testAllBoardSpace()!=-1;
    }

    public int testAllBoardSpace()
    {
        if (board[0][0]==board[1][0] && board[0][0]==board[2][0]){
            return whichPlayer(board[0][0]);
        }
        else if (board[0][1]==board[1][1] && board[0][1]==board[2][1]){
            return whichPlayer(board[0][1]);
        }
        else if (board[0][2]==board[1][2] && board[0][2]==board[2][2]){
            return whichPlayer(board[0][2]);
        }
        else if (board[0][0]==board[0][1] && board[0][0]==board[0][2]){
            return whichPlayer(board[0][0]);
        }
        else if (board[1][0]==board[1][1] && board[1][0]==board[1][2]){
            return whichPlayer(board[1][0]);
        }
        else if (board[2][0]==board[2][1] && board[2][0]==board[2][2]){
            return whichPlayer(board[2][0]);
        }
        else if (board[0][0]==board[1][1] && board[0][0]==board[2][2]){
            return whichPlayer(board[0][0]);
        }
        else if (board[0][2]==board[1][1] && board[0][2]==board[2][0]){
            return whichPlayer(board[0][2]);
        }
        return -1;
    }

    private int whichPlayer(char c)
    {
        if (c==PLAYER1_TOKEN){
            return 1;
        }
        return 2;
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
