public class Bot {
	public int Think (Board board)
	{
		int[] moves = board.getMoves();
		System.out.println(moves);
		return moves[(moves.length + 1) / 2];
	}
}
