/**
 * Knight.java - class for Knight chess piece
 * 
 * File:
 * 	$Id: Knight.java,v 1.4 2014/08/06 21:33:26 rxl7906 Exp $:
 * 
 * Revisions:
 * 	$Log: Knight.java,v $
 * 	Revision 1.4  2014/08/06 21:33:26  rxl7906
 * 	test commit
 *
 * 	Revision 1.3  2014/08/06 21:26:08  rxl7906
 * 	some functionality, debugging
 * 	commit
 *
 * 	Revision 1.2  2014/08/05 23:29:24  rxl7906
 * 	Chess pieces work
 *@author - Robin Li
 *
 */

import java.util.*;

public class Knight extends ChessPiece{
	
	/**
	 * Knight constructor based on initialized inherited values
	 * @param name - Pawn
	 * @param x-length of board on x-axis
	 * @param y-length of board on y-axis
	 */
	public Knight(String ChessPieceName, int X, int Y) {
		super(ChessPieceName, X, Y);
	}

	/**
	 * getAllMoves - movement for Knight
	 * @param - starting - movement based on starting position
	 */
	@Override
	public HashSet<PointPosition> getAllMoves(PointPosition startingPos) {
		HashSet<PointPosition> possibleMoves = new HashSet<PointPosition>();
		int x = startingPos.getXCoordinate();
		int y = startingPos.getYCoordinate();
		
		// Knight moves
		// moves: up 2 , right 1 (north east direction)
		possibleMoves.add(new PointPosition(x+1, y-2));
		
		// moves: up 2 , left 1 (north west direction)
		possibleMoves.add(new PointPosition(x-1, y-2)); 
		
		// moves: down 2 , right 1 (south east direction)
		possibleMoves.add(new PointPosition(x+1, y+2));
		
		// moves: down 2 , left 1 (south west direction)
		possibleMoves.add(new PointPosition(x-1, y+2));
		
		// moves: up 1 , right 2 (north east direction)
		possibleMoves.add(new PointPosition(x+2, y-1)); 
		
		// moves: down 1 , right 2 (south east direction)
		possibleMoves.add(new PointPosition(x+2, y+1));
		
		// moves: up 1 , left 2 (north west direction)
		possibleMoves.add(new PointPosition(x-2, y-1)); 
		
		// moves: down 1 , left 2 (south west direction)
		possibleMoves.add(new PointPosition(x-2, y+1));
		
		super.withinBoard(possibleMoves);
		return possibleMoves;
	}
}