/*
 * Kramer Johnson
 * CPSC 5011 02, Winter 2021, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

package inventory;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class InventorySetTest {

	InventorySet s = new InventorySet();
	final VideoObj v1 = new VideoObj( "A", 2000, "B" );
	final VideoObj v1copy = new VideoObj( "A", 2000, "B" );
	final VideoObj v2 = new VideoObj( "B", 2000, "B" );

	@Test
	public void testAddNumOwned() {
		assertEquals( 0, s.size() );
		s.addNumOwned(v1, 1);     assertEquals( v1, s.get(v1).video );
		assertEquals( 1, s.get(v1).numOwned );
		s.addNumOwned(v1, 2);     assertEquals( 3, s.get(v1).numOwned );
		s.addNumOwned(v1, -1);    assertEquals( 2, s.get(v1).numOwned );
		s.addNumOwned(v2, 1);     assertEquals( 2, s.get(v1).numOwned );
		s.addNumOwned(v1copy, 1); assertEquals( 3, s.get(v1).numOwned );
		assertEquals( 2, s.size() );
		s.addNumOwned(v1, -3);
		assertEquals( 1, s.size() );
		try { s.addNumOwned(null, 1);   fail(); } catch ( IllegalArgumentException e ) {}
	}

	@Test
	public void testSize() {
		assertEquals( 0, s.size() );
		s.addNumOwned(v1,  1); assertEquals( 1, s.size() );
		s.addNumOwned(v1,  2); assertEquals( 1, s.size() );
		s.addNumOwned(v2,  1); assertEquals( 2, s.size() );
		s.addNumOwned(v2, -1); assertEquals( 1, s.size() );
		s.addNumOwned(v1, -3); assertEquals( 0, s.size() );
		try { s.addNumOwned(v1, -3); fail(); } catch ( IllegalArgumentException e ) {}
		assertEquals( 0, s.size() );
	}

	@Test
	public void testCheckOutCheckIn() {
		try { s.checkOut(v1); fail(); } catch (IllegalArgumentException e) {}
		try { s.checkIn(v1); fail(); } catch (IllegalArgumentException e) {}
		s.addNumOwned(v1, 2);
		s.checkOut(v1);
		assertEquals(1, s.get(v1).numRentals);
		assertEquals(1, s.get(v1).numOut);
		s.checkOut(v1);
		assertEquals(2, s.get(v1).numRentals);
		assertEquals(2, s.get(v1).numOut);
		try { s.checkOut(v1); fail(); } catch (IllegalArgumentException e) {}

		s.checkIn(v1);
		assertEquals(2, s.get(v1).numRentals);
		assertEquals(1, s.get(v1).numOut);
		s.checkIn(v1);
		assertEquals(0, s.get(v1).numOut);
		try { s.checkIn(v1); fail(); } catch (IllegalArgumentException e) {}
	}

	@Test
	public void testClear() {
		s.addNumOwned(v1, 5);
		s.addNumOwned(v2, 6);
		assertEquals(2, s.size());
		s.clear();
		assertEquals(0, s.size());
	}

	@Test
	public void testGet() {
		assertNull(s.get(v1));
		s.addNumOwned(v1, 3);
		assertEquals(3, s.get(v1).numOwned);
		Record r = s.get(v1);
		r.numOwned = 1;
		assertEquals(1, r.numOwned);
		assertEquals(3, s.get(v1).numOwned);
	}

	@Test
	public void testToCollection() {
		s.addNumOwned(v1, 3);

		ArrayList<Record> records = new ArrayList<>(s.toCollection());

		assertEquals(1, s.size());
		assertEquals(1, records.size());
		assertEquals(3, s.get(v1).numOwned);
		assertEquals(3, records.get(0).numOwned);
		records.get(0).numOwned++;
		assertEquals(3, s.get(v1).numOwned);
		assertEquals(4, records.get(0).numOwned);
	}
}
