
/**
 * @author cknill
 * @date 06.25.2014
 */

/**
 * This models the Percolation problem.
 */
public final class Percolation
{
	/**
	 * Constructs a new percolation model of size <b>N</b>. The model's
	 * sites will all be in a blocked state. The model will not percolate.
	 * 
	 * @param N The desired size of the lattice
	 * 
	 * */
	public Percolation(int N)
	{
		dim = N;
		size = dim*dim + 2;
		BOTTOM = size - 1;
		sitesOpened = initArray();
		model = new WeightedQuickUnionUF(size);
	}
	
	// Allocates a byte array on the heap
	// The first and last element are open.
	private byte[] initArray()
	{
		// a byte size array: snicker, snicker
		byte[] byteArray = new byte[size];
		for (int i = 0; i != byteArray.length; ++i)
		{
			byteArray[i] = BLOCKED;
		}
		
		byteArray[TOP] = OPEN;
		byteArray[BOTTOM] = OPEN;
		
		return byteArray;
	}
	
	/**
	 * 
	 * Opens a site located at the coordinates (i,j)
	 * 
	 * @param row The row coordinate
	 * @param col The column coordinate
	 * 
	 * @return void
	 * 
	 * @throws IndexOutOfBoundsException
	 * 
	 * 
	 */
	public void open(int row, int col)
	{
		if (isOutOfBounds(row, col))
		{
			throw new IndexOutOfBoundsException();
		}

		int index = toIndex(row, col);
		sitesOpened[index] = OPEN;
		
		if (row == 1)
		{
			model.union(index, TOP);
		}
		
		if (row == dim)
		{
			model.union(index, BOTTOM);
		}

		linkAdjacentSites(row, col);
		
		
	}
	
	// A site is adjacent if it is above, below, left, or right of the given
	// row and column. Check for valid adjacent sites, and link them to
	// the site at (row, col)
	private void linkAdjacentSites(int row, int col)
	{
		boolean siteAboveValid = validAdjacent(row-1, col);
		boolean siteBelowValid = validAdjacent(row+1, col);
		boolean siteLeftValid = validAdjacent(row, col-1);
		boolean siteRightValid = validAdjacent(row, col+1);
		
		if (siteAboveValid)
		{
			model.union(toIndex(row-1, col), toIndex(row, col));
		}
		
		if (siteBelowValid)
		{
			model.union(toIndex(row+1, col), toIndex(row, col));
		}
		
		if (siteLeftValid)
		{
			model.union(toIndex(row, col-1), toIndex(row, col));
		}
		
		if (siteRightValid)
		{
			model.union(toIndex(row, col+1), toIndex(row, col));
		}
		
	}
	
	// A valid adjacent site is within bounds and open.
	private boolean validAdjacent(int row, int col)
	{
		return (!isOutOfBounds(row, col) && isOpen(row, col));
	}
	
	// An index is out of bounds if it is less than one or greater than N*N
	private boolean isOutOfBounds(int row, int col)
	{
		return (row < 1 || row > dim || col < 1 || col > dim);
	}
	
	/**
	 * 
	 * Tests whether a site located at coordinates (row, col) has been opened.
	 * 
	 * @param row The row coordinate
	 * @param col The column coordinate
	 * 
	 * @return <code>true</code> if the site at (row, col) has been opened,
	 * <code>false</code> if the site has not been opened.
	 * 
	 * @throws IndexOutOfBoundsException
	 * 
	 * */
	public boolean isOpen(int row, int col)
	{
		
		if (isOutOfBounds(row, col))
		{
			throw new IndexOutOfBoundsException();
		}

		return sitesOpened[toIndex(row, col)] == OPEN;
	}
	
	// Convert (row, col) to an array index.
	private int toIndex(int row, int col)
	{
		return dim*(row - 1) + col;
	}
	
	/**
	 * Tests whether there is a path of adjacent open sites from the top of
	 * the grid to the site at (row, col).
	 * 
	 * @param row The row coordinate.
	 * @param col The column coordinate.
	 * 
	 * @return <code>true</code> if there is a path from top to site (row, col),
	 * <code>false</code> if there is no such path.
	 * 
	 * @throws IndexOutOfBoundsException
	 * */
	public boolean isFull(int row, int col)
	{
		
		if (isOutOfBounds(row , col))
		{
			throw new IndexOutOfBoundsException();
		}

		return model.connected(toIndex(row, col), TOP);
	}
	
	/**
	 * Tests whether there is a path of adjacent open sites from the top of
	 * the lattice to the bottom of the lattice.
	 * 
	 * @return <code>true</code> when there is a path from top to bottom,
	 * otherwise <code>false</code>.
	 * */
	public boolean percolates()
	{
		return model.connected(TOP, BOTTOM);
	}

	private final int dim;
	private final int size;
	private final byte BLOCKED = 0;
	private final byte OPEN = 1;
	private final int TOP = 0;
	private final int BOTTOM;
	private byte[] sitesOpened;
	private WeightedQuickUnionUF model;
	
	public static void main(String[] args)
	{
		System.out.println("FooBarBaz!");
	}

}
