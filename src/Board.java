

public class Board {
    // The "final" keyword means something is immutable and cannot be changed after declaration. 
    private final char DEFAULT_TOKEN = '_';
	private int boardWidth;
	private int boardHeight;
	private int boardWinLength;
	private char[] players =  {'X', 'O'};
	private int turn = 0;

    // State of the tic tac toe board
    // note: everything to the right of the '=' could be done in constructor instead
    //       This is a style choice.
    private char[][] board = new char[3][3];

    /**
	 * Initialize board with width, height, and run length to win of 3
	 */
    Board()
	{

		initBoard(3, 3, 3);
	}
	/**
	 * Initialize game board
	 * @param width the width of the board
	 * @param height the height of the board<pre>
	 * </pre>The run length to win is the lesser of the width and height
	 */
	Board(int width, int height)
	{

		initBoard(width, height, Math.min(width, height));
	}
	/**
	 * Initialize game board
	 * @param width the width of the board
	 * @param height the height of the board
	 * @param winLength the minimum run length needed to win
	 */
	Board(int width, int height, int winLength)
    {
		initBoard(width, height, winLength);
	}
	void initBoard (int width, int height, int winLength)
	{
		boardWidth = width;
		boardHeight = height;
		boardWinLength = winLength;

		for(int row = 0; row < width; ++row)
		{
			for(int col = 0; col < height; ++col)
			{
				board[row][col] = DEFAULT_TOKEN;
			}
		}

	}

    // You may choose to change the return type if you like
    public void takeTurn(String input) throws NumberFormatException, IndexOutOfBoundsException
    {
        
		int square = Integer.parseInt(input) - 1;
		if (square < 0 | square >= boardWidth * boardHeight) {
			throw new IndexOutOfBoundsException(square);
		}

		// Integer quotient of given square divided by the width of a row 
		int row = square / boardWidth;
		// The given square modulo the width of the board
		int col = square % boardWidth;
		if (board[row][col] == DEFAULT_TOKEN)
		{
			board[row][col] = players[turn];
		}
		else
		{
			throw new IndexOutOfBoundsException();
		}
		changeTurn();
    }

    public boolean isGameOver()
    {
		// IntToBoardChar rowRun = (i,j) -> board[i][j];
		// IntToBoardChar colRun = (i,j) -> board[j][i];

        // for (int row = 0; row < boardHeight; ++row)
		// {

		// 	char runToken = testRun(boardWidth, row);
		// }
		// for (int col = 0; col < boardWidth; ++col)
		// 	for (int row = 1; row < boardHeight; ++row)
		// 	{
				
		// 	}
		// {
			
		// }
        return false;
    }

    public String displayBoard()
    {
        StringBuilder builder = new StringBuilder();

        for(int row = 0; row < boardHeight; ++row)
        {
            for(int col = 0; col < boardWidth; ++col)
            {
				char boardChar = board[row][col];
				
				builder.append(' ');
				
				if (boardChar == DEFAULT_TOKEN)
				{
					builder.append(row * boardWidth + col + 1);
				}
				else
				{
					builder.append(boardChar);
				}
				builder.append(' ');

				// Add seperation if 
				if (col < boardWidth - 1)
				{
					builder.append('|');
				}

				/*
				 *  1 | 2 | 3
				 * ———|———|———
				 *  X | O | 6
				 * ———|———|———
				 *  O | 7 | 8
				 */
            }
            builder.append('\n');
			if (row < boardHeight - 1) {
				builder.append("---|---|---\n");
			}
        }

        return builder.toString();
    }
	
	public int getTurn() {
		return turn;
	}
	public char getTurnChar() {
		return players[turn];
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public int changeTurn() {
		turn = (turn + 1) % players.length;
		return turn;
	}

	private char testRun(int iterations, int index, IntToBoardChar getChar)
	{
		char runToken = DEFAULT_TOKEN;
		int runCount = 0;
		for (int j = 0; j < iterations; ++j)
		{
			runCount = (getChar.op(index, j) == DEFAULT_TOKEN) ? runCount + 1 : 1;
			runToken = getChar.op(i);
			if (runCount >= boardWinLength)
			{
				return runToken;
			}
		}
		return '_';
	}
	private interface IntToBoardChar {
		public char op(int i, int j);
	}
}
