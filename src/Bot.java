public class Bot {
	private int player;

	public int Think (Board board)
	{
		player = board.getTurn();
		int[] moves = board.getMoves();
		System.out.println(moves);
		return moves[(moves.length + 1) / 2];
	}

	int MinMax(Board board, int depth)
	{
		switch (board.getWinner())
		{
			case -1:
				break;
			case 0:
				return 0;
			default:
				return 255 - depth;
		}
		int[] moves = board.getMoves();
		for (int move : moves)
		{
		}

		return 0;
	}
}
