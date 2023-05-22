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
	private BoundedGrid<Block> grid;

	// Constructs a Tetrad.
	public Tetrad(BoundedGrid<Block> grid)
	{
		//throw new RuntimeException("INSERT MISSING CODE HERE");
		this.grid = grid;
		
		blocks = new Block[4];
		Color tetradColor;
		Location[] blockLocations;
		
		int tetradRandomizer = (int)(Math.random() * 7);
		
		if (tetradRandomizer == 0) { // Red "I"
			tetradColor = Color.RED;
			Location[] tempLocations = {new Location(0, 4), new Location(0, 3), new Location(0, 5), new Location(0, 6)};
			blockLocations = tempLocations;
		} else if (tetradRandomizer == 1) { // Gray "T"
			tetradColor = Color.GRAY;
			Location[] tempLocations = {new Location(0, 4), new Location(0, 3), new Location(0, 5), new Location(1, 4)};
			blockLocations = tempLocations;
		} else if (tetradRandomizer == 2) { // Cyan "O"
			tetradColor = Color.CYAN;
			Location[] tempLocations = {new Location(0, 4), new Location(0, 5), new Location(1, 4), new Location(1, 5)};
			blockLocations = tempLocations;
		} else if (tetradRandomizer == 3) { // Yellow "L"
			tetradColor = Color.YELLOW;
			Location[] tempLocations = {new Location(0, 3), new Location(0, 4), new Location (0, 5), new Location(1, 3)};
			blockLocations = tempLocations;
		} else if (tetradRandomizer == 4) { // Magenta "J"
			tetradColor = Color.MAGENTA;
			Location[] tempLocations = {new Location(0, 5), new Location(0, 4), new Location (0, 6), new Location(1, 6)};
			blockLocations = tempLocations;
		} else if (tetradRandomizer == 5) { // Blue "S"
			tetradColor = Color.BLUE;
			Location[] tempLocations = {new Location(0, 4), new Location(1, 3), new Location(1, 4), new Location(0, 5)};
			blockLocations = tempLocations;
		} else { // If tetradRadomizer is 6, then create a green "Z"
			tetradColor = Color.GREEN;
			Location[] tempLocations = {new Location(0, 4), new Location(0, 3), new Location(1, 4), new Location(1, 5)};
			blockLocations = tempLocations;
		} 
		
		for (int i = 0; i < blocks.length; i++) {
			Block currentBlock = new Block();
			currentBlock.setColor(tetradColor);
			blocks[i] = currentBlock;
		}
		
		
        	int sumOfDistances = 0;
        	int minSumOfDistances = Integer.MAX_VALUE;
        	int centerIndex = 0;
        
            
            	for (int j = 0; j < 4; j++) {
                	if (j != i) {
                    		int dx = blockLocations[j].row - blockLocations[i].row;
                    		int dy = blockLocations[j].col - blockLocations[i].col;
                    		sumOfDistances += Math.abs(dx) + Math.abs(dy);
                	}
            	}
            
   
            	if (sumOfDistances < minSumOfDistances) {
                	minSumOfDistances = sumOfDistances;
                	centerIndex = i;
        	}
        
       
       	 	Location temp = blockLocations[0];
       		blockLocations[0] = locations[centerIndex];
        	blockLocations[centerIndex] = temp;
		
		addToLocations(grid, blockLocations);
	}


	// Postcondition: Attempts to move this tetrad deltaRow rows down and
	//						deltaCol columns to the right, if those positions are
	//						valid and empty.
	//						Returns true if successful and false otherwise.
	public boolean translate(int deltaRow, int deltaCol)
	{
		//throw new RuntimeException("INSERT MISSING CODE HERE");
		Location[] newTetradLocations = new Location[blocks.length];
		for (int i = 0; i < blocks.length; i++) {
			Location blockLocation = blocks[i].getLocation();
			newTetradLocations[i] = new Location(blockLocation.getRow() + deltaRow, blockLocation.getCol() + deltaCol);
		}
		for (Location check : newTetradLocations) { // Checks if all of the new locations are in the grid
			if (grid.isValid(check) == false) {
				return false;
			}
		}
		
		Location[] originalTetradLocations = removeBlocks();
		
		boolean success = (areEmpty(grid, newTetradLocations));
		if (success) {
			addToLocations(grid, newTetradLocations);
		} else {
			addToLocations(grid, originalTetradLocations);
		}
		return success;
	}

	// Postcondition: Attempts to rotate this tetrad clockwise by 90 degrees
	//                about its center, if the necessary positions are empty.
	//                Returns true if successful and false otherwise.
	public boolean rotate()
	{
		//throw new RuntimeException("INSERT MISSING CODE HERE");
		Location[] newTetradLocations = new Location[blocks.length];
		
		int centerRow = blocks[0].getLocation().getRow();
		int centerCol = blocks[0].getLocation().getCol();
		for (int i = 0; i < blocks.length; i++) {
			Location blockLocation = blocks[i].getLocation();
			int blockRow = blockLocation.getRow();
			int blockCol = blockLocation.getCol();
			newTetradLocations[i] = new Location(centerRow - centerCol + blockCol, centerRow + centerCol - blockRow);
		}
		for (Location check : newTetradLocations) { // Checks if all of the new locations are in the grid
			if (grid.isValid(check) == false) {
				return false;
			}
		}
		
		Location[] originalTetradLocations = removeBlocks();
		
		boolean success = (areEmpty(grid, newTetradLocations));
		if (success) {
			addToLocations(grid, newTetradLocations);
		} else {
			addToLocations(grid, originalTetradLocations);
		}
		return success;
	}


	// Precondition:  The elements of blocks are not in any grid;
	//                locs.length = 4.
	// Postcondition: The elements of blocks have been put in the grid
	//                and their locations match the elements of locs.
	private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
	{
		//throw new RuntimeException("INSERT MISSING CODE HERE");
		for (int i = 0; i < locs.length; i++) {
			blocks[i].putSelfInGrid(grid, locs[i]);
		}
	}

	// Precondition:  The elements of blocks are in the grid.
	// Postcondition: The elements of blocks have been removed from the grid
	//                and their old locations returned.
	public Location[] removeBlocks()
	{
		//throw new RuntimeException("INSERT MISSING CODE HERE");
		Location[] oldLocations = new Location[blocks.length];
		for (int i = 0; i < blocks.length; i++) {
			oldLocations[i] = blocks[i].getLocation();
			blocks[i].removeSelfFromGrid(); // Sets the grid and location of the block to null
		}
		
		return oldLocations;
	}

	// Postcondition: Returns true if each of the elements of locs is valid
	//                and empty in grid; false otherwise.
	private boolean areEmpty(BoundedGrid<Block> grid, Location[] locs)
	{
		//throw new RuntimeException("INSERT MISSING CODE HERE");
		for (Location location : locs) {
			if (grid.get(location) != null) {
				return false;
			}
		}
		return true;
	}
}
