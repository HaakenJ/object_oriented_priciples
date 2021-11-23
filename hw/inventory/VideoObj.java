/*
 * Kramer Johnson
 * CPSC 5011 02, Winter 2021, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

package inventory;

/**
 * Immutable Data Class for video objects.
 * Comprises a triple: title, year, director.
 *
 * <p><b>Class Type:</b> Immutable Data Class</p>
 * <p><b>Object Invariant:</b></p>
 *   Title is non-null, no leading or final spaces, not empty string.
 * <p><b>Object Invariant:</b></p>
 *   Year is greater than 1800, less than 5000.
 * <p><b>Object Invariant:</b></p>
 *   Director is non-null, no leading or final spaces, not empty string.
 *
 * @author Kramer Johnson
 */
final class VideoObj implements Comparable<VideoObj> {

	/** <p><b>Invariant:</b> non-null, no leading or final spaces, not empty string </p>*/
	private final String title;

	/** <p><b>Invariant:</b> greater than 1800, less than 5000 </p>*/
	private final int year;

	/** <p><b>Invariant:</b> non-null, no leading or final spaces, not empty string </p>*/
	private final String director;

	/**
	 * Initialize all object attributes.
	 * Title and director are "trimmed" to remove leading and final space.
	 * @param title    the title of the movie
	 * @param year     the year the movie was released
	 * @param director the director of the movie
	 * @throws IllegalArgumentException if any object invariant is violated.
	 */
	VideoObj(String title, int year, String director) {
		if (title == null || title.trim().equals(""))
			throw new IllegalArgumentException("Invalid title provided.");

		if (year <= 1800 || year >= 5000)
			throw new IllegalArgumentException("Invalid year provided.");

		if (director == null || director.trim().equals(""))
			throw new IllegalArgumentException("Invalid director provided.");
		title = title.trim();
		director = director.trim();
		this.title = title;
		this.year = year;
		this.director = director;
	}

	/**
	 * Returns the value of the director attribute.
	 * @return the value of the director attribute.
	 */
	public String director() {
		return director;
	}

	/**
	 * Return the value of the title attribute.
	 * @return the value of the title attribute.
	 */
	public String title() {
		return title;
	}

	/**
	 * Return the value of the year attribute.
	 * @return the value of the year attribute.
	 */
	public int year() {
		return year;
	}

	/**
	 * Compare the attributes of this object with those of thatObject.
	 * @param thatObject the Object to be compared.
	 * @return deep equality test between this and thatObject.
	 */
	@Override
	public boolean equals(Object thatObject) {
		if (!(thatObject instanceof VideoObj))
			return false;
		VideoObj v = (VideoObj) thatObject;
		return this.title.equals(v.title) && this.director.equals(v.director) && this.year == v.year;
	}

	/**
	 * Return a hash code value for this object using the algorithm from Bloch:
	 * fields are added in the following order: title, year, director.
	 * @return a hash code for this object
	 */
	@Override
	public int hashCode() {
		int result = title.hashCode();
		result = 31 * result + year;
		result = 31 * result + director.hashCode();
		return result;
	}

	/**
	 * Compares the attributes of this object with those of thatObject, in
	 * the following order: title, year, director.
	 * @param thatObject the VideoObj to be compared.
	 * @return a negative integer, zero, or a positive integer as this
	 *  object is less than, equal to, or greater than that object.
	 */
	@Override
	public int compareTo(VideoObj thatObject) {
		int result = String.CASE_INSENSITIVE_ORDER.compare(this.title,
				thatObject.title);
		if (result == 0)
			result = String.CASE_INSENSITIVE_ORDER.compare(this.director,
					thatObject.director);
		if (result == 0)
			result = Integer.compare(this.year, thatObject.year);
		return result;
	}

	/**
	 * Return a string representation of the object in the following format:
	 * <code>"title (year) : director"</code>.
	 * @return A string displaying this video's information
	 */
	@Override
	public String toString() {
		return title +
				" (" +
				year + ") : " +
				director;
	}

}