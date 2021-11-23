/*
 * Kramer Johnson
 * CPSC 5011 02, Winter 2021, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

package inventory;

/**
 * Utility class for Inventory.  Fields are mutable and package-private.
 * Does not override <code>equals</code> or <code>hashCode</code>.
 *
 * <p><b>Class Type:</b> Mutable Data Class</p>
 */
final class Record {

	/**
	 * The video.
	 * <p><b>Invariant:</b> <code>video != null</code></p>
	 */
	VideoObj video;

	/**
	 * The number of copies of the video that are in the inventory.
	 * <p><b>Invariant:</b> <code>numOwned > 0</code></p>
	 */
	int numOwned;

	/**
	 * The number of copies of the video that are currently checked out.
	 * <p><b>Invariant:</b> <code>numOut <= numOwned</code></p>
	 */
	int numOut;

	/**
	 * The total number of times this video has ever been checked out.
	 * <p><b>Invariant:</b> <code>numRentals >= numOut</code></p>
	 */
	int numRentals;

	/**
	 * Initialize all object attributes.
	 * @param video      the video object of this record
	 * @param numOwned   the number of copies owned of this video
	 * @param numOut     the number of copies currently rented out
	 * @param numRentals the number of times this video has been rented
	 */
	Record(VideoObj video, int numOwned, int numOut, int numRentals) {
		this.video = video;
		this.numOwned = numOwned;
		this.numOut = numOut;
		this.numRentals = numRentals;
	}

	/**
	 * Returns a shallow copy of this record.
	 * @return a shallow copy of this record.
	 */
	public Record copy() {
		return new Record(video,numOwned,numOut,numRentals);
	}

	/**
	 * Returns a string representation of the object in the following format:
	 * @return a string representation of the object in the following format:
	 * <code>"video [numOwned,numOut,numRentals]"</code>.
	 */
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(video);
		buffer.append(" [");
		buffer.append(numOwned);
		buffer.append(",");
		buffer.append(numOut);
		buffer.append(",");
		buffer.append(numRentals);
		buffer.append("]");
		return buffer.toString();
	}

}