/**
 * File
 * 	$Id: ChessPiece.java,v 1.7 2014/08/07 13:58:48 rxl7906 Exp $:
 * 
 * Revisions:
 * 	$Log: ChessPiece.java,v $
 * 	Revision 1.7  2014/08/07 13:58:48  rxl7906
 * 	commit
 *
 * 	Revision 1.6  2014/08/07 13:50:20  rxl7906
 * 	Functionality working
 * 	doing more tests
 *
 * 	Revision 1.5  2014/08/07 12:49:01  rxl7906
 * 	only square chess board can be played
 * 	rectangles don't work
 *
 * 	Revision 1.4  2014/08/06 21:26:09  rxl7906
 * 	some functionality, debugging
 * 	commit
 *
 * 	Revision 1.3  2014/08/05 23:29:24  rxl7906
 * 	Chess pieces work
 *
 * 	Revision 1.2  2014/08/05 16:41:37  rxl7906
 * 	commit
 *
 * 	Revision 1.1  2014/07/31 13:32:20  rxl7906
 * 	initial commit
 *:
 * 
 * @author Robin Li
 *
 */

import java.util.*;

public abstract class ChessPiece {
	
	private String name;
	private int x;
	private int y;
	private int numberCheck;
	private static int numberCount = 0;

	/**
	 * Chess Piece constructor - initialize chess piece
	 * 
	 * @param name - name of chess piece
	 * @param x - x-coordinate
	 * @param y - y-coordinate
	 */
	public ChessPiece(String ChessPieceName, int X, int Y) {
		this.name = ChessPieceName;
		this.x = X;
		this.y = Y;
		this.numberCheck = numberCount;
		numberCount += 1;
	}

	/**
	 * Returns name of Piece
	 * 
	 * @return Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Based on starting position, get all the possible valid moves for this
	 * chess piece
	 * @param starting - starting position
	 */
	public abstract HashSet<PointPosition> getAllMoves(PointPosition starting);

	/**
	 * Based on starting position, get all possible diagonal movements
	 * @param starting - starting position
	 * @return possible valid diagonal moves
	 */
	protected HashSet<PointPosition> diagonalMovements(PointPosition starting) {
		HashSet<PointPosition> possibleMoves = new HashSet<PointPosition>();
		int x = starting.getXCoordinate();
		int y = starting.getYCoordinate();

		// get lower right
		while (x < this.x && y < this.y) {
			possibleMoves.add(new PointPosition(x, y));
			x++;
			y++;
		}

		x = starting.getXCoordinate();
		y = starting.getYCoordinate();

		// get upper left
		while (x >= 0 && y >= 0) {
			possibleMoves.add(new PointPosition(x, y));
			x--;
			y--;
		}

		x = starting.getXCoordinate();
		y = starting.getYCoordinate();

		// get lower left
		while (x >= 0 && y < this.y) {
			possibleMoves.add(new PointPosition(x, y));
			x--;
			y++;
		}

		x = starting.getXCoordinate();
		y = starting.getYCoordinate();

		// get lower right
		while (x < this.x && y >= 0) {
			possibleMoves.add(new PointPosition(x, y));
			x++;
			y--;
		}

		possibleMoves.remove(starting); // remove current location

		return possibleMoves;
	}

	/**
	 * Based on starting position, get all vertical and horizontal movements
	 * 
	 * @param starting - starting position
	 * @return possible valid vertical horizontal movements
	 */
	protected HashSet<PointPosition> VerticalHorizontalMovements(PointPosition starting) {
		HashSet<PointPosition> possibleMoves = new HashSet<PointPosition>();
		int x = starting.getXCoordinate();
		int y = starting.getYCoordinate();

		// get north
		while (y >= 0) {
			possibleMoves.add(new PointPosition(starting.getXCoordinate(), y));
			y--;
		}

		x = starting.getXCoordinate();
		y = starting.getYCoordinate();

		// get south
		while (y < this.y) {
			possibleMoves.add(new PointPosition(starting.getXCoordinate(), y));
			y++;
		}

		x = starting.getXCoordinate();
		y = starting.getYCoordinate();

		// get east
		while (x < this.x) {
			possibleMoves.add(new PointPosition(x, starting.getYCoordinate()));
			x++;
		}

		x = starting.getXCoordinate();
		y = starting.getYCoordinate();

		// get west
		while (x >= 0) {
			possibleMoves.add(new PointPosition(x, starting.getYCoordinate()));
			x--;
		}

		possibleMoves.remove(starting); // remove current location

		return possibleMoves;
	}

	/**
	 * Checks set of moves to make sure the moves are valid (within on the chess board)
	 * @param set
	 *            Set of moves.
	 * @return Set of moves with all the out of bounds moves removed.
	 */
	protected HashSet<PointPosition> withinBoard(HashSet<PointPosition> moves) {
		// Hashset contains all the moves
		HashSet<PointPosition> allMoves = new HashSet<PointPosition>();
		for (PointPosition pp : moves) {
			if (pp.getXCoordinate() < 0 || pp.getXCoordinate() >= this.x || 
					pp.getYCoordinate() < 0 || pp.getYCoordinate() >= this.y) {
				allMoves.add(pp);
			}
		}
		
		// remove the moves that are invalid (off the board)
		for (PointPosition pp : allMoves) {
			moves.remove(pp);
		}
		return moves;
	}

/*	public String toString() {
		return name;
	}*/
	
	public int hashCode() {
		int code = 0;
		for (char c : this.name.toCharArray()) {
			code += (int) c;
		}
		return code * this.numberCheck;
	}
}