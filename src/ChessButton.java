import javax.swing.*;

/**
 * ChessButton.java - Provides positional information
 * 
 * File:
 * 	$Id: ChessButton.java,v 1.2 2014/08/07 13:58:48 rxl7906 Exp $:
 * 
 * Revisions:
 * 	$Log: ChessButton.java,v $
 * 	Revision 1.2  2014/08/07 13:58:48  rxl7906
 * 	commit
 *
 * 	Revision 1.1  2014/08/05 16:41:37  rxl7906
 * 	commit
 *:
 * @author Robin Li
 *
 */
public class ChessButton extends JButton {
	
	PointPosition position;
	
	/**
	 * Chess Button constructor takes in x and y coordinates
	 * @param x - x coordinate of button
	 * @param y - y coordinate of button
	 */
	public ChessButton(int x, int y) {
		this.position = new PointPosition(x, y);
	}
	
	/*
	 * Returns the position of the button
	 * @return - the position of the button
	 */
	public PointPosition getPosition() {
		return position;
	}
}
