/**
 * Tetrad.java  4/30/2014
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

import java.awt.Color;

// Represents a Tetris piece.
public class Tetrad
{
	private Block[] blocks;	// The blocks for the piece.

	// Constructs a Tetrad.
	public Tetrad(BoundedGrid<Block> grid)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}


	// Postcondition: Attempts to move this tetrad deltaRow rows down and
	//						deltaCol columns to the right, if those positions are
	//						valid and empty.
	//						Returns true if successful and false otherwise.
	public boolean translate(int deltaRow, int deltaCol)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Postcondition: Attempts to rotate this tetrad clockwise by 90 degrees
	//                about its center, if the necessary positions are empty.
	//                Returns true if successful and false otherwise.
	public boolean rotate()
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}


	// Precondition:  The elements of blocks are not in any grid;
	//                locs.length = 4.
	// Postcondition: The elements of blocks have been put in the grid
	//                and their locations match the elements of locs.
	private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Precondition:  The elements of blocks are in the grid.
	// Postcondition: The elements of blocks have been removed from the grid
	//                and their old locations returned.
	private Location[] removeBlocks()
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}

	// Postcondition: Returns true if each of the elements of locs is valid
	//                and empty in grid; false otherwise.
	private boolean areEmpty(BoundedGrid<Block> grid, Location[] locs)
	{
		throw new RuntimeException("INSERT MISSING CODE HERE");
	}
}
