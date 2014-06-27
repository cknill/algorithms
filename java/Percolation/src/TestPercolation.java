
import static org.junit.Assert.*;

import org.junit.Test;



public class TestPercolation {

	@Test
	public void testPercolation() {
		Percolation perc = new Percolation(3);
		assertNotEquals(perc, null);
	}

	@Test
	public void testOpen() {
		Percolation perc = new Percolation(20);
		perc.open(1, 1);
		assertTrue("Site(1,1) must be open", perc.isOpen(1, 1));
		assertFalse("Site(1,2) must not be open", perc.isOpen(1, 2));
		assertFalse("Site(1,2) must not be filled", perc.isFull(1, 2));
		assertFalse("Site (2,1) must not be open", perc.isOpen(2, 1));
		assertFalse("Site (2,1) must not be filled", perc.isFull(2, 1));
		assertFalse("Model must not percolate", perc.percolates());
	}

	@Test
	public void testIsOpen() {
		Percolation perc = new Percolation(20);
		perc.open(1, 2);
		assertTrue("Site(1,2) must be open", perc.isOpen(1, 2));
	}

	@Test
	public void testIsFull() {
		Percolation perc = new Percolation(20);
		perc.open(1, 1);
		assertTrue("Site(1,1) must be filled", perc.isFull(1, 1));
	}

	@Test
	public void testPercolates() {
		Percolation perc = new Percolation(20);
		for (int i = 1; i != 21; i++)
			perc.open(i, 2);
		assertTrue("Model must percolate", perc.percolates());
	}

}
