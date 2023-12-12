public class Board {
    // The "final" keyword means something is immutable and cannot be changed after declaration. 
    private final char DEFAULT_TOKEN = '_';
	private int boardWidth;
	private int boardHeight;
	private int boardBoxWidth;
	private int boardWinLength;
	private char[] players =  {DEFAULT_TOKEN, 'X', 'O'};

	private int turn = 1;

    // State of the tic tac toe board
    // note: everything to the right of the '=' could be done in constructor instead
    //       This is a style choice.
    private char[][] board;

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
		boardBoxWidth = String.valueOf(width * height).length();
		board = new char[height][width];

		for(int row = 0; row < height; ++row)
		{
			for(int col = 0; col < width; ++col)
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
		// If getWinner does not return null,
		// then someone won or tied, and the game is over, so return true
		return getWinner() != '\0';
	}
	
	public char getWinner()
	{
		char winToken = '\0';
		
		IntToBoardChar rowRun = (i,j) -> board[i][j];
        for (int row = 0; row < boardHeight; ++row)
		{
			char runToken = testRun(boardWidth, row, rowRun);
			if (runToken != DEFAULT_TOKEN)
			{
				winToken = runToken;
			}
			
		}
		
		IntToBoardChar colRun = (i,j) -> board[j][i];
		for (int col = 0; col < boardWidth; ++col)
		{
			char runToken = testRun(boardHeight, col, colRun);
			if (runToken != DEFAULT_TOKEN)
			{
				winToken = runToken;
			}
		}

		// Check diagonals starting from the left side and going either up or down to the right
		IntToBoardChar ascDiagonalRun = (i,j) -> board[i + j][j];
		IntToBoardChar descDiagonalRun = (i,j) -> board[boardHeight - i - j - 1][j];
		for (int startRow = 0; startRow <= boardHeight - boardWinLength; ++startRow)
		{
			char runAscToken = testRun(Math.min(boardWidth, boardHeight - startRow), startRow, ascDiagonalRun);
			if (runAscToken != DEFAULT_TOKEN & winToken == '\0')
			{
				winToken = runAscToken;
			}
			char runDescToken = testRun(Math.min(boardWidth, boardHeight - startRow), startRow, descDiagonalRun);
			if (runDescToken != DEFAULT_TOKEN & winToken == '\0')
			{
				winToken = runDescToken;
			}
		}
		// Check diagonals starting from the bottom or top and going either up or down to the right
		IntToBoardChar ascDiagonalRun2 = (i,j) -> board[j][i + j];
		IntToBoardChar descDiagonalRun2 = (i,j) -> board[boardHeight - j - 1][i + j];
		for (int startCol = 1; startCol < boardWidth; ++startCol)
		{
			char runAscToken = testRun(Math.min(boardWidth - startCol, boardHeight), startCol, ascDiagonalRun2);
			if (runAscToken != DEFAULT_TOKEN & winToken == '\0')
			{
				winToken = runAscToken;
			}
			char runDescToken  = testRun(Math.min(boardWidth - startCol, boardHeight), startCol, descDiagonalRun2);
			if(runDescToken != DEFAULT_TOKEN & winToken == '\0')
			{
				winToken = runDescToken;
			}
		}

		if (winToken != '\0')
		{
			return winToken;
		}
		boolean isTie = true;
		for (int row = 0; row < boardHeight; ++row)
		{
			for (int col = 0; col < boardWidth; ++col)
			{
				// If isTie still hasn't been set to false and the current board square isn't empty,
				// then isTie will stay true.
				// Otherwise, isTie will be set to false and can't be set back to true
				isTie = isTie & board[row][col] != DEFAULT_TOKEN;
			}
		}
		if (isTie)
		{
			return DEFAULT_TOKEN; 
		}
		else
		{
			return '\0';
		}

	}

    public String displayBoard()
    {
        StringBuilder builder = new StringBuilder();

		// Row is seperate from iterator because there are more display rows than board rows
		int row = 0;
        for(int i = 0; i < 2 * boardHeight - 1; ++i)
        {
			for(int col = 0; col < boardWidth; ++col)
			{
				// even rows are game pieces, odd rows are seperators
				if (i % 2 == 0) {

					char boardChar = board[row][col];
					
					builder.append(' ');
					
					if (boardChar == DEFAULT_TOKEN)
					{
						int squareVal = row * boardWidth + col + 1;
						for (int spaces = 0; spaces < boardBoxWidth - String.valueOf(squareVal).length(); spaces++)
						{
							builder.append(' ');
						}
						builder.append(squareVal);
					}
					else
					{
						for (int spaces = 0; spaces < boardBoxWidth - 1; spaces++)
						{
							builder.append(' ');
						}
						builder.append(boardChar);
					}
					builder.append(' ');
					
					// Add seperation if not at end of line
					
					/*
					*  1 | 2 | 3
					* ———|———|———
					*  X | O | 6
					* ———|———|———
					*  O | 7 | 8
					*/
				}
				else
				{
					for (int spaces = 0; spaces < boardBoxWidth + 2; spaces++)
					{
						builder.append("-");
					}
				}
				
				if (col < boardWidth - 1)
				{
					builder.append('|');
				}
            }
            builder.append('\n');

			if (i % 2 == 0)
			{
				row++;
			}
        }
		
        return builder.toString();
    }
	
	public int getTurn()
	{
		return turn;
	}
	public char getTurnChar()
	{
		return players[turn];
	}
	public void setTurn(int turn)
	{
		this.turn = turn;
	}
	public int changeTurn()
	{
		// If turn reaches the end of the player list, reset to zero, then increment
		turn = turn % (players.length - 1) + 1;
		return turn;
	}
	public char getDefaultToken() 
	{
		return DEFAULT_TOKEN;
	}
	
	private char testRun(int iterations, int fixedIndex, IntToBoardChar getChar)
	{
		char runToken = '\0';
		int runCount = 0;
		for (int j = 0; j < iterations; ++j)
		{
			runCount = (getChar.op(fixedIndex, j) == runToken) ? runCount + 1 : 1;
			runToken = getChar.op(fixedIndex, j);
			if (runCount >= boardWinLength)
			{
				return runToken;
			}
		}
		// if (runCount > 1 & runToken != DEFAULT_TOKEN & runToken != '\0')
		// {
		// 	System.out.println("Run count: " + runCount);
		// }
		if (runCount >= boardWinLength)
		{
			return runToken;
		}
		else
		{
			// If no run occured, return default token
			return DEFAULT_TOKEN;
		}
	}
	/**
	 * An interface holding a function that can be passed as a parameter
	 * This is used  for the testRun() function
	 */
	private interface IntToBoardChar {
		public char op(int i, int j);
	}
}
