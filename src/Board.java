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

    // Constructor
    Board(){}

    public boolean isOpen(int row,int col){
        return Character.isDigit(board[row][col]);
    }
    // You may choose to change the return type if you like
    public void takeTurn(boolean isP1,int row,int col)
    {
        if (isP1){
            board[row][col]=PLAYER1_TOKEN;
        }
        else{
            board[row][col]=PLAYER2_TOKEN;
        }
    }

    public boolean isGameOver()
    {
        return testAllBoardSpace()!=-1;
    }

    public int testAllBoardSpace()
    {
        //Checks Col 1-3 then Row 1-3 then Diagonals
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

        //Checks board for tie
        for (char[] cArr:board){
            for (char c:cArr){
                if (Character.isDigit(c)){
                    return -1;
                }
            }
        }
        return 0;
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
