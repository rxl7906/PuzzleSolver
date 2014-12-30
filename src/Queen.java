/**
 * Queen.java - class for Queen chess piece
 * 
 * File:
 * 	$Id: Queen.java,v 1.4 2014/08/06 21:33:26 rxl7906 Exp $:
 * 
 * Revisions:
 * 	$Log: Queen.java,v $
 * 	Revision 1.4  2014/08/06 21:33:26  rxl7906
 * 	test commit
 *
 * 	Revision 1.3  2014/08/06 21:26:09  rxl7906
 * 	some functionality, debugging
 * 	commit
 *
 * 	Revision 1.2  2014/08/05 23:29:25  rxl7906
 * 	Chess pieces work
 * @author - Robin Li
 *
 */

import java.util.*;

public class Queen extends ChessPiece{
	
	/**
	 * Queen constructor based on initialized inherited values
	 * @param name - Queen
	 * @param x-length of board on x-axis
	 * @param y-length of board on y-axis
	 */
	public Queen(String ChessPieceName, int X, int Y) {
		super(ChessPieceName, X, Y);
	}

	/**
	 * getAllMoves - movement for Queen
	 * @param - starting - movement based on starting position
	 */
	@Override
	public HashSet<PointPosition> getAllMoves(PointPosition startingPos) {
		
		// Queen has diagonal movements inherited from ChessPiece.java
		HashSet<PointPosition> possibleMoves = super.diagonalMovements(startingPos);
		
		// Queens has vertical and diagonal movements inherited from ChessPiece.java
		possibleMoves.addAll(super.VerticalHorizontalMovements(startingPos));
		return possibleMoves;
	}
}