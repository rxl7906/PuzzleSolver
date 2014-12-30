/**
 * Bishop.java - class for Bishop chess piece
 * 
 * File:
 * 	$Id: Bishop.java,v 1.4 2014/08/06 21:33:26 rxl7906 Exp $:
 * 
 * Revisions:
 * 	$Log: Bishop.java,v $
 * 	Revision 1.4  2014/08/06 21:33:26  rxl7906
 * 	test commit
 *
 * 	Revision 1.3  2014/08/06 21:26:09  rxl7906
 * 	some functionality, debugging
 * 	commit
 *
 * 	Revision 1.2  2014/08/05 23:29:25  rxl7906
 * 	Chess pieces work
 *@author - Robin Li
 *
 */

import java.util.*;

public class Bishop extends ChessPiece{
	
	/**
	 * Bishop constructor based on initialized inherited values
	 * @param name - Bishop
	 * @param x-length of board on x-axis
	 * @param y-length of board on y-axis
	 */
	public Bishop(String ChessPieceName, int X, int Y) {
		super(ChessPieceName, X, Y);
	}

	/**
	 * getAllMoves - movement for Bishop
	 * @param - starting - movement based on starting position
	 */
	@Override
	public HashSet<PointPosition> getAllMoves(PointPosition startingPos) {
		
		// Bishop has diagonal movements inherited from ChessPiece.java
		return super.diagonalMovements(startingPos);
	}
}