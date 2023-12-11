public class Board {
    // The "final" keyword means something is immutable and cannot be changed after declaration. 
    private final char DEFAULT_TOKEN = '_';
	private int boardWidth;
	private int boardHeight;
	private int boardWinLength;
	private char[] players =  {DEFAULT_TOKEN, 'X', 'O'};

	private int turn = 1;

    // State of the tic tac toe board
    // note: everything to the right of the '=' could be done in constructor instead
    //       This is a style choice.
    private char[][] board = new char[3][3];

    // Constructor
	/**
	 * Width, height, and win length initialized to 3
	 */
	Board()
	{
		initBoard(3, 3, 3);
	}
	/**
	 * Initialize a Tic-Tac-Toe board
	 * @param width board width size
	 * @param height board height size
	 * The number of pieces in a needed in a run to win is  run length to win is the minimum of the width and height
	 */
	Board(int width, int height) {

		initBoard(width, height, Math.min(width, height));
	}
	/**
	 * 
	 * @param width board with size
	 * @param height board height size
	 * @param winLength number of pieces in a run needed to win
	 */
    Board(int width, int height, int winLength)
    {
		initBoard(width, height, winLength);
	}
	void initBoard(int width, int height, int winLength) {
		boardWidth = width;
		boardHeight = height;
		boardWinLength = winLength;
		for(int row = 0; row < width; row++)
		{
			for(int col = 0; col < height; col++)
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
		// If getWinner does not return default token, someone won
		return DEFAULT_TOKEN != getWinner();
	}
	
	public char getWinner() {
		for (int i = 0; i < boardHeight; ++i)
		{
			int runCount = 0;
			char runToken = DEFAULT_TOKEN;
			for (int j = 0; j < boardWidth; ++j)
			{
				runCount = (board[i][j] == runToken) ? (runCount + 1) : 1;
				runToken = board[i][j];
				if (runCount >= boardWinLength & runToken != DEFAULT_TOKEN)
				{
					return runToken;
				}
			}
		}
		for (int i = 0; i < boardWidth; ++i)
		{
			int runCount = 0;
			char runToken = DEFAULT_TOKEN;
			for (int j = 0; j < boardHeight; ++j)
			{
				runCount = (board[j][i] == runToken) ? (runCount + 1) : 1;
				runToken = board[j][i];
				if (runCount >= boardWinLength & runToken != DEFAULT_TOKEN)
				{
					return runToken;
				}
			}
		}
		return DEFAULT_TOKEN;

	}

    public String displayBoard()
    {
        StringBuilder builder = new StringBuilder();

        for(int row = 0; row < boardHeight; row++)
        {
            for(int col = 0; col < boardWidth; col++)
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
		// If turn reaches the end of the player list, reset to zero, then increment
		turn = turn % (players.length - 1) + 1;
		return turn;
	}
}
