/**
 * King.java - class for King chess piece
 * 
 * File:
 * 	$Id: King.java,v 1.4 2014/08/06 21:33:26 rxl7906 Exp $:
 * 
 * Revisions:
 * 	$Log: King.java,v $
 * 	Revision 1.4  2014/08/06 21:33:26  rxl7906
 * 	test commit
 *
 * 	Revision 1.3  2014/08/06 21:26:09  rxl7906
 * 	some functionality, debugging
 * 	commit
 *
 * 	Revision 1.2  2014/08/05 23:29:24  rxl7906
 * 	Chess pieces work
 *@author - Robin Li
 *
 */

import java.util.*;

public class King extends ChessPiece{
	
	/**
	 * King constructor based on initialized inherited values
	 * @param name - King
	 * @param x-length of board on x-axis
	 * @param y-length of board on y-axis
	 */
	public King(String ChessPieceName, int X, int Y) {
		super(ChessPieceName, X, Y);
	}

	/**
	 * getAllMoves - movement for King
	 * @param - starting - movement based on starting position
	 */
	@Override
	public HashSet<PointPosition> getAllMoves(PointPosition startingPos) {
		HashSet<PointPosition> possibleMoves = new HashSet<PointPosition>();
		int x = startingPos.getXCoordinate();
		int y = startingPos.getYCoordinate();
		
		//King moves
		// moves: up 1 , left 1
		possibleMoves.add(new PointPosition(x-1, y-1));
		
		// moves: up 1 , right 1
		possibleMoves.add(new PointPosition(x+1, y-1));
		
		// moves: down 1 , left 1
		possibleMoves.add(new PointPosition(x-1, y+1));
		
		// moves: down 1 , right 1
		possibleMoves.add(new PointPosition(x+1, y+1));
		
		// moves: up 1
		possibleMoves.add(new PointPosition(x, y-1));
		
		// moves: down 1
		possibleMoves.add(new PointPosition(x, y+1));
		
		// moves: right 1
		possibleMoves.add(new PointPosition(x+1, y));
		
		// moves: left 1
		possibleMoves.add(new PointPosition(x-1, y));
		
		super.withinBoard(possibleMoves);
		return possibleMoves;
	}
}