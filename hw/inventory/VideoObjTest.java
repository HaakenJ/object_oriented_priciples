/*
 * Kramer Johnson
 * CPSC 5011 02, Winter 2021, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

package inventory;

import static org.junit.Assert.*;

import org.junit.Test;

public class VideoObjTest {

	@Test
	public void testConstructorAndAttributes() {
		String title1 = "XX";
		String director1 = "XY";
		String title2 = " XX ";
		String director2 = " XY ";
		int year = 2002;

		VideoObj v1 = new VideoObj(title1, year, director1);
		assertSame(title1, v1.title());
		assertEquals(year, v1.year());
		assertSame(director1, v1.director());

		VideoObj v2 = new VideoObj(title2, year, director2);
		assertEquals(title1, v2.title());
		assertEquals(director1, v2.director());
	}

	@Test
	public void testConstructorExceptionYear() {
		try {
			new VideoObj("X", 1800, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj("X", 5000, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj("X", 1801, "Y");
			new VideoObj("X", 4999, "Y");
		} catch (IllegalArgumentException e) {
			fail();
		}
	}

	@Test
	public void testConstructorExceptionTitle() {
		try {
			new VideoObj(null, 2002, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj("", 2002, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
		try {
			new VideoObj(" ", 2002, "Y");
			fail();
		} catch (IllegalArgumentException e) { }
	}

	@Test
	public void testConstructorExceptionDirector() {
		try {
			VideoObj v = new VideoObj("Die Monster Die!", 1965, null);
			fail();
		} catch (IllegalArgumentException e) {}
		try {
			VideoObj v = new VideoObj("Die Monster Die!", 1965, "");
			fail();
		} catch (IllegalArgumentException e) {}
		try {
			VideoObj v = new VideoObj("Die Monster Die!", 1965, " ");
			fail();
		} catch (IllegalArgumentException e) {}
	}

	@Test
	public void testHashCode() {
		assertEquals
		(-1869722747, new VideoObj("None", 2009, "Zebra").hashCode());
		assertEquals
		(2057189520, new VideoObj("Blah", 1954, "Cante").hashCode());
	}

	@Test
	public void testEquals() {
		VideoObj v1 = new VideoObj("Die Monster Die!", 1965, "Daniel Haller");
		VideoObj v2 = new VideoObj("Die Monster Die!", 1965, "Daniel Haller");
		VideoObj v3 = new VideoObj("Die Monster Die!", 1965, "Daniel Haller");
		VideoObj v4 = new VideoObj("Cat People", 1942, "Jacques Tourneur");

		// Reflexivity
		assertTrue(v1.equals(v1));
		// Symmetry
		assertTrue(v1.equals(v2));
		assertTrue(v2.equals(v1));
		// Transitivity
		assertTrue(v1.equals(v2));
		assertTrue(v2.equals(v3));
		assertTrue(v1.equals(v3));
		// Consistency
		assertTrue(v1.equals(v2));
		assertTrue(v2.equals(v1));
		// Non-null
		assertFalse(v1.equals(null));

		assertFalse(v1.equals(v4));
		assertFalse(v4.equals(v1));
	}

	@Test
	public void testCompareTo() {
		VideoObj v1 = new VideoObj("Die Monster Die!", 1965, "Daniel Haller");
		VideoObj v2 = new VideoObj("Die Monster Die!", 1965, "Daniel Haller");
		VideoObj v3 = new VideoObj("Die Monster Die!", 1964, "Daniel Haller");
		VideoObj v4 = new VideoObj("Cat People", 1942, "Jacques Tourneur");

		// Reflexivity
		assertEquals(0, v1.compareTo(v1));
		// Symmetry
		assertEquals(v1.compareTo(v2), -(v2.compareTo(v1)));
		// Transitivity
		assertEquals(-1, v4.compareTo(v3));
		assertEquals(-1, v3.compareTo(v1));
		assertEquals(-1, v4.compareTo(v1));
		// Consistency
		assertEquals(0, v1.compareTo(v2));
		assertEquals(v1.compareTo(v4), v2.compareTo(v4));

		// Compared to equals
		assertEquals((v1.compareTo(v2) == 0), v1.equals(v2));
	}

	@Test
	public void testToString() {
		String s = new VideoObj("A", 2000, "B").toString();
		assertEquals( "A (2000) : B", s );
		s = new VideoObj(" A ", 2000, " B ").toString();
		assertEquals( "A (2000) : B", s );
	}

}
