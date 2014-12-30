/**
 * Pawn.java - class for Pawn chess piece
 * 
 * File:
 * 	$Id: Pawn.java,v 1.4 2014/08/06 21:33:26 rxl7906 Exp $:
 * 
 * Revisions:
 * 	$Log: Pawn.java,v $
 * 	Revision 1.4  2014/08/06 21:33:26  rxl7906
 * 	test commit
 *
 * 	Revision 1.3  2014/08/06 21:26:09  rxl7906
 * 	some functionality, debugging
 * 	commit
 *
 * 	Revision 1.2  2014/08/05 23:29:24  rxl7906
 * 	Chess pieces work
 * @author - Robin Li
 * 
 */

import java.util.*;

public class Pawn extends ChessPiece{
	
	/**
	 * Pawn constructor based on initialized inherited values
	 * @param name - Pawn
	 * @param x-length of board on x-axis
	 * @param y-length of board on y-axis
	 */
	public Pawn(String ChessPieceName, int X, int Y) {
		super(ChessPieceName, X, Y);
	}

	/**
	 * getAllMoves - movement for Pawn
	 * @param - starting - movement based on starting position
	 */
	@Override
	public HashSet<PointPosition> getAllMoves(PointPosition startingPos) {
		HashSet<PointPosition> possibleMoves = new HashSet<PointPosition>();
		int x = startingPos.getXCoordinate();
		int y = startingPos.getYCoordinate();
		
		// pawn captures to the left diagonal
		possibleMoves.add(new PointPosition(x-1, y-1)); 
		// pawn captures to the right diagonal
		possibleMoves.add(new PointPosition(x+1, y-1)); 
		
		super.withinBoard(possibleMoves);
		return possibleMoves;
	}
}