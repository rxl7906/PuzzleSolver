/**
 * PointPosition.java - coordinate position for piece
 * 
 * File:
 * 	$Id: PointPosition.java,v 1.5 2014/08/07 12:49:02 rxl7906 Exp $:
 * 
 * Revisions:
 * 	$Log: PointPosition.java,v $
 * 	Revision 1.5  2014/08/07 12:49:02  rxl7906
 * 	only square chess board can be played
 * 	rectangles don't work
 *
 * 	Revision 1.4  2014/08/06 21:26:09  rxl7906
 * 	some functionality, debugging
 * 	commit
 *
 * 	Revision 1.3  2014/08/05 23:29:25  rxl7906
 * 	Chess pieces work
 *
 * 	Revision 1.2  2014/08/05 16:41:38  rxl7906
 * 	commit
 *
 * 	Revision 1.1  2014/07/31 11:31:02  rxl7906
 * 	initial commit
 *
 * 
 * @author Robin Li
 *
 */
public class PointPosition {
	
	private int x;
	private int y;
	
	/**
	 * PointPosition constructor
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 */
	public PointPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Returns coordinate's x value
	 * @return coordinate's x value
	 */
	public int getXCoordinate() {
		return this.x;
	}
	
	/**
	 * Returns coordinate's y value
	 * @return coordinate's y value
	 */
	public int getYCoordinate() {
		return this.y;
	}
	
	@Override
	public boolean equals(Object object) {
		PointPosition pp = (PointPosition)object;
		// check to see if the object position is equal to point position
		if (pp.getXCoordinate() == this.x && pp.getYCoordinate() == this.y) {
			return true;
		} else {
			return false;
		}
	}

/*	@Override
	public String toString() {
		return String.format("[%s,%s]", this.x, this.y);
	}*/
	
	@Override
	public int hashCode() {
		if (this.x < 0 || this.y < 0) {
			return -1;
		}
		return Integer.parseInt(String.valueOf(this.y) + String.valueOf(this.x));
	}
}