/**
 * Tetris.java  4/30/2014
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

// Represents a Tetris game.
public class Tetris implements ArrowListener
{
	private BoundedGrid<Block> grid;	// The grid containing the Tetris pieces.
	private BlockDisplay display;		// Displays the grid.
	private Tetrad activeTetrad;		// The active Tetrad (Tetris Piece).

	// Constructs a Tetris Game
	public Tetris()
	{
		//throw new RuntimeException("INSERT MISSING CODE HERE");
		grid = new BoundedGrid<Block>(20, 10);
		display = new BlockDisplay(grid);
		display.setTitle("Tetris");
		display.setArrowListener(this);
		
		activeTetrad = new Tetrad(grid);
		display.showBlocks();
	}

	// Play the Tetris Game
	public void play()
	{
		//throw new RuntimeException("INSERT MISSING CODE HERE");
		while (true) {
			sleep(1);
			boolean successfullyDropped = activeTetrad.translate(1, 0);
			if (successfullyDropped == true) {
				display.showBlocks();
			} else {
				activeTetrad = new Tetrad(grid);
			}
		}
	}
	
	// Event listeners for the arrow keys
	public void upPressed() {
		activeTetrad.rotate();
		display.showBlocks();
	}
	public void downPressed() {
		activeTetrad.translate(1, 0);
		display.showBlocks();
	}
	public void leftPressed() {
		activeTetrad.translate(0, -1);
		display.showBlocks();
	}
	public void rightPressed() {
		activeTetrad.translate(0, 1);
		display.showBlocks();
	}
	public void spacePressed() {
		
	}

	// Precondition:  0 <= row < number of rows
	// Postcondition: Returns true if every cell in the given row
	//                is occupied; false otherwise.
	private boolean isCompletedRow(int row)
	{
		//throw new RuntimeException("INSERT MISSING CODE HERE");
		for (int col = 0; col < grid.getNumCols(); col++) {
			if (grid.get(new Location(row, col)) == null) {
				return false;
			}
		}
		return true;
	}

	// Precondition:  0 <= row < number of rows;
	//                The given row is full of blocks.
	// Postcondition: Every block in the given row has been removed, and
	//                every block above row has been moved down one row.
	private void clearRow(int row)
	{
		//throw new RuntimeException("INSERT MISSING CODE HERE");
		int cols = grid.getNumCols();
		for (int removingCol = 0; removingCol < cols; removingCol++) {
			grid.get(new Location(row, removingCol)).removeSelfFromGrid();
		}
	}

	// Postcondition: All completed rows have been cleared.
	private void clearCompletedRows()
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Sleeps (suspends the active thread) for duration seconds.
	private void sleep(double duration)
	{
		final int MILLISECONDS_PER_SECOND = 1000;

		int milliseconds = (int)(duration * MILLISECONDS_PER_SECOND);

		try
		{
			Thread.sleep(milliseconds);
		}
		catch (InterruptedException e)
		{
			System.err.println("Can't sleep!");
		}
	}


	// Creates and plays the Tetris game.
	public static void main(String[] args)
	{
		//throw new RuntimeException("INSERT MISSING CODE HERE");
		// Testing code here
		Tetris newGame = new Tetris();
		newGame.play();
	}
}
