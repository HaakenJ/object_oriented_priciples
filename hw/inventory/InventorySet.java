/*
 * Kramer Johnson
 * CPSC 5011 02, Winter 2021, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

package inventory;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

/**
 * An Inventory implemented using a <code>HashMap&lt;Video, Record&gt;</code>.
 * Keys are Videos; Values are Records.
 *
 * <p><b>Class Type:</b> Mutable Collection of Records</p>
 * <p><b>Object Invariant:</b></p>
 *   Every key and value in the map is non-<code>null</code>.
 * <p><b>Object Invariant:</b></p>
 *   Each value <code>r</code> is stored under key <code>r.video</code>.
 *
 * @author Kramer Johnson
 */
final class InventorySet {

	/** <p><b>Invariant:</b> <code>_data != null</code> </p>*/
	private final Map<VideoObj, Record> data = new HashMap<>();

	/**
	 * Default constructor.
	 */
	InventorySet() { }

	/**
	 * Returns the number of records present in the Inventory
	 * @return the number of Records.
	 */
	public int size() {
		return data.size();
	}

	/**
	 * Returns a copy of the record for a given Video; if not present, return <code>null</code>.
	 *  @return copy of the record for a given Video; if not present, return <code>null</code>.
	 */
	public Record get(VideoObj v) {
		if (!data.containsKey(v)) return null;
		return data.get(v).copy();
	}

	/**
	 * Return a copy of the records as a collection.
	 * Neither the underlying collection, nor the actual records are returned.
	 * @return a copy of the records in inventory as a collection
	 */
	public Collection<Record> toCollection() {
		ArrayList<Record> result = new ArrayList<>();
		for (Record r: data.values()) {
			result.add(r.copy());
		}
		return result;
	}

	/**
	 * Add or remove copies of a video from the inventory.
	 * If a video record is not already present (and change is
	 * positive), a record is created.
	 * If a record is already present, <code>numOwned</code> is
	 * modified using <code>change</code>.
	 * If <code>change</code> brings the number of copies to be zero,
	 * the record is removed from the inventory.
	 * @param video the video to be added.
	 * @param change the number of copies to add (or remove if negative).
	 * @throws IllegalArgumentException if video null, change is zero,
	 *  if attempting to remove more copies than are owned, or if
	 *  attempting to remove copies that are checked out.
	 * <p><b>Postcondition:</b> changes the record for the video</p>
	 */
	public void addNumOwned(VideoObj video, int change) {
		if (video == null)
			throw new IllegalArgumentException("Video cannot be null.");
		if (change == 0)
			throw new IllegalArgumentException("Change cannot be zero.");
		if (!data.containsKey(video) && change > 0) {
			data.put(video, new Record(video, change, 0, 0));
		}
		else if (data.containsKey(video)) {
			Record record = data.get(video);
			if (change > 0) {
				record.numOwned += change;
			}
			else {
				if (record.numOwned + change < 0)
					throw new IllegalArgumentException("You cannot remove more copies than you own.");
				if (record.numOwned - record.numOut - change < 0)
					throw new IllegalArgumentException("You cannot remove copies that are checked out.");
				if (record.numOwned + change == 0)
					data.remove(video);
				else
					record.numOwned += change;
			}
		}
		else
			throw new IllegalArgumentException("You cannot remove more copies than you own.");
	}

	/**
	 * Check out a video.
	 * @param video the video to be checked out.
	 * @throws IllegalArgumentException if video has no record or numOut
	 * equals numOwned.
	 * <p><b>Postcondition:</b> changes the record for the video</p>
	 */
	public void checkOut(VideoObj video) {
		if (!data.containsKey(video))
			throw new IllegalArgumentException("The video has no record.");
		Record record = data.get(video);
		if (record.numOwned == record.numOut)
			throw new IllegalArgumentException("All copies of this video are checked out.");

		record.numOut++;
		record.numRentals++;
	}

	/**
	 * Check in a video.
	 * @param video the video to be checked in.
	 * @throws IllegalArgumentException if video has no record or numOut
	 * non-positive.
	 * <p><b>Postcondition:</b> changes the record for the video</p>
	 */
	public void checkIn(VideoObj video) {
		if (!data.containsKey(video))
			throw new IllegalArgumentException("The video has no record.");
		Record record = data.get(video);
		if (record.numOut <= 0)
			throw new IllegalArgumentException("There are no copies of this video checked out.");

		record.numOut--;
	}

	/**
	 * Remove all records from the inventory.
	 * <p><b>Postcondition:</b> <code>size() == 0</code></p>
	 */
	public void clear() {
		data.clear();
	}

	/**
	 * Return the contents of the inventory as a string.
	 */
	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append("Database:\n");
		for (Record r : data.values()) {
			buffer.append("  ");
			buffer.append(r);
			buffer.append("\n");
		}
		return buffer.toString();
	}

}