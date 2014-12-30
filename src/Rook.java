/**
 * Rook.java - class for Rook chess piece
 * 
 * File:
 * 	$Id: Rook.java,v 1.4 2014/08/06 21:33:26 rxl7906 Exp $:
 * 
 * Revisions:
 * 	$Log: Rook.java,v $
 * 	Revision 1.4  2014/08/06 21:33:26  rxl7906
 * 	test commit
 *
 * 	Revision 1.3  2014/08/06 21:26:08  rxl7906
 * 	some functionality, debugging
 * 	commit
 *
 * 	Revision 1.2  2014/08/05 23:29:24  rxl7906
 * 	Chess pieces work
 *
 *@author - Robin Li
 */

import java.util.*;

public class Rook extends ChessPiece{
	
	/**
	 * Rook constructor based on initialized inherited values
	 * @param name - Rook
	 * @param x-length of board on x-axis
	 * @param y-length of board on y-axis
	 */
	public Rook(String ChessPieceName, int X, int Y) {
		super(ChessPieceName, X, Y);
	}

	/**
	 * getAllMoves - movement for Rook 
	 * @param - starting - movement based on starting position
	 */
	@Override
	public HashSet<PointPosition> getAllMoves(PointPosition startingPos) {
		
		// Rook has vertical and horizontal movements inherited from ChessPiece.java
		return super.VerticalHorizontalMovements(startingPos);
	}
}
