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
    private int[][] board;

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
		board = new int[height][width];

		for(int row = 0; row < height; ++row)
		{
			for(int col = 0; col < width; ++col)
			{
				board[row][col] = 0;
			}
		}

	}

    // You may choose to change the return type if you like
    protected void takeTurn(String input) throws NumberFormatException, IndexOutOfBoundsException
    {
        // Ensure input is valid
		int square = Integer.parseInt(input) - 1;
		if (square < 0 | square >= boardWidth * boardHeight)
		{
			throw new IndexOutOfBoundsException(square);
		}

		int row = square / boardWidth;
		int col = square % boardWidth;
		if (board[row][col] == 0)
		{
			board[row][col] = turn;
		}
		else
		{
			throw new IndexOutOfBoundsException();
		}
		changeTurn();
    }
	protected void takeTurn(int square) throws IndexOutOfBoundsException
	{
		if (square < 0 | square >= boardWidth *  boardHeight)
		{
			throw new IndexOutOfBoundsException();
		}
		int row = square / boardWidth;
		int col = square % boardWidth;
		if (board[row][col] == 0)
		{
			board[row][col] = turn;
		}
		else
		{
			throw new IndexOutOfBoundsException();
		}
		changeTurn();
	}

    public boolean isGameOver()
    { 
		// If getWinner isn't null, then someone won or tied, so return true
		return getWinner() != -1;
	}
	
	public int getWinner()
	{
		int winToken = -1;
		
		GetRunToken rowRun = (i,j) -> board[i][j];
        for (int row = 0; row < boardHeight; ++row)
		{
			int runToken = testRun(boardWidth, row, rowRun);
			if (runToken > 0 & winToken == -1)
			{
				winToken = runToken;
			}
			
		}
		
		GetRunToken colRun = (i,j) -> board[j][i];
		for (int col = 0; col < boardWidth; ++col)
		{
			int runToken = testRun(boardHeight, col, colRun);
			if (runToken > 0 & winToken == -1)
			{
				winToken = runToken;
			}
		}

		// Check diagonals starting from the left side and going either up or down to the right
		GetRunToken ascDiagonalRun = (i,j) -> board[i + j][j];
		GetRunToken descDiagonalRun = (i,j) -> board[boardHeight - i - j - 1][j];
		for (int startRow = 0; startRow <= boardHeight - boardWinLength; ++startRow)
		{
			int runAscToken = testRun(Math.min(boardWidth, boardHeight - startRow), startRow, ascDiagonalRun);
			if (runAscToken > 0 & winToken == -1)
			{
				winToken = runAscToken;
			}
			int runDescToken = testRun(Math.min(boardWidth, boardHeight - startRow), startRow, descDiagonalRun);
			if (runDescToken > 0 & winToken == -1)
			{
				winToken = runDescToken;
			}
		}
		// Check diagonals starting from the bottom or top and going either up or down to the right
		GetRunToken ascDiagonalRun2 = (i,j) -> board[j][i + j];
		GetRunToken descDiagonalRun2 = (i,j) -> board[boardHeight - j - 1][i + j];
		for (int startCol = 1; startCol < boardWidth; ++startCol)
		{
			int runAscToken = testRun(Math.min(boardWidth - startCol, boardHeight), startCol, ascDiagonalRun2);
			if (runAscToken > 0 & winToken == -1)
			{
				winToken = runAscToken;
			}
			int runDescToken  = testRun(Math.min(boardWidth - startCol, boardHeight), startCol, descDiagonalRun2);
			if(runDescToken > 0 & winToken == -1)
			{
				winToken = runDescToken;
			}
		}

		if (winToken != -1)
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
				isTie = isTie & board[row][col] > 0;
			}
		}
		if (isTie)
		{
			return 0; 
		}
		else
		{
			return -1;
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

					int boardToken = board[row][col];
					
					builder.append(' ');
					
					if (boardToken == 0)
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
						builder.append(players[boardToken]);
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
	public void setTurn(int turn)
	{
		this.turn = turn;
	}
	public char getPlayerChar(int idx)
	{
		return players[idx];
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
	
	public int[] getMoves()
	{
		int numMoves = 0;
		for(int i = 0; i < boardWidth * boardHeight; ++i)
		{
			// If the board square is empty, increase number of moves
			numMoves += (board[i / boardWidth][i % boardWidth] == DEFAULT_TOKEN) ? 1 : 0;
		}
		int[] moves = new int[numMoves];
		int moveNum = 0;
		for(int i = 0; i < boardWidth * boardHeight; ++i)
		{
			if (board[i / boardWidth][i % boardWidth] == DEFAULT_TOKEN)
			{
				moves[moveNum] = i;
				++moveNum;
			}
		}
		return moves;
		
	}


	private int testRun(int iterations, int fixedIndex, GetRunToken getToken)
	{
		int runToken = -1;
		int runCount = 0;
		for (int j = 0; j < iterations; ++j)
		{
			runCount = (getToken.op(fixedIndex, j) == runToken) ? runCount + 1 : 1;
			runToken = getToken.op(fixedIndex, j);
			if (runCount >= boardWinLength)
			{
				return runToken;
			}
		}
		if (runCount >= boardWinLength)
		{
			return runToken;
		}
		else
		{
			// If no run occured, return default token
			return 0;
		}
	}
	/**
	 * An interface holding a function that can be passed as a parameter<pre>
	 * </pre>This is used  for the testRun() function
	 */
	private interface GetRunToken {
		public int op(int i, int j);
	}
}
