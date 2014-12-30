/**
 * Chess.java - Chess Puzzle GUI front end. Provides a 
 * graphical display of the current board, initially based
 * on the input file. Chess pieces are displayed using
 * letters. 
 * 
 * FURTHER INSTRUCTIONS:
 * How to move: First click on the puzzle piece, on the board
 * from its current location and click on the spot the piece
 * will move to. 
 * Button that allows the user to step to the next best move
 * by clicking on the "Next Best Move" button.
 * Keeps track of current number of moves made in the game. 
 * Reset Button allows the user to reset the game to the 
 * starting board. Move count resets.
 * Message box indicates when it is the end of the game.
 * How to play button pops a dialog box how to play the game.
 * 
 * 
 * File:
 * 	$Id: Chess.java,v 1.1 2014/08/08 15:46:31 rxl7906 Exp $:
 * 
 * Revisions:
 * 	$Log: Chess.java,v $
 * 	Revision 1.1  2014/08/08 15:46:31  rxl7906
 * 	Final Commit before submission
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
 * 	Revision 1.2  2014/08/05 16:41:39  rxl7906
 * 	commit
 *
 * 	Revision 1.1  2014/08/04 23:23:21  rxl7906
 * 	Chess GUI initial commit
 *:
 * 
 * @author Robin Li
 *
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Chess extends JFrame implements Observer {
	
	private ChessModel chessModel;
	private JTextArea moveCountDisplay;
	private JTextArea gameMessage;
	private ChessButton[][] buttons;

	/**
	 * ChessGUI constructor - produces frame, GridLayout with button panels and
	 * sets up the chess pieces
	 * 
	 * @param chessModel - model(back end) for the GUI (front end)
	 */
	public Chess(ChessModel chessModel) {
		
		this.chessModel = chessModel;
		
		// allows model to notify GUI of updates
		chessModel.addObserver(this);

		// set up GUI
		this.moveCountDisplay = new JTextArea("Move: " + chessModel.getMoveCount());
		this.gameMessage = new JTextArea("");
		this.gameMessage.setForeground(Color.GREEN);
		this.buttons = new ChessButton[chessModel.getBoardYLength()][chessModel.getBoardXLength()];

		//create top panel
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(1,3));
		Font font1 = new Font("Top", Font.PLAIN,34);
		this.moveCountDisplay.setFont(font1);
		this.gameMessage.setFont(font1);
		//top.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 3));
		top.add(this.moveCountDisplay);
		top.add(this.gameMessage);
		top.setVisible(true);
		
		// set up buttons
		JButton reset = new JButton("Reset");
		JButton nextBestMove = new JButton("Next Best Move");
		JButton howToPlay = new JButton("How To Play");
		Font font3 = new Font("Bottom",Font.PLAIN,34);
		reset.setFont(font3);
		reset.addActionListener(new ButtonListener(reset, this));
		nextBestMove.setFont(font3);
		nextBestMove.addActionListener(new ButtonListener(nextBestMove, this));
		howToPlay.setFont(font3);
		howToPlay.addActionListener(new ButtonListener(howToPlay, this));

		// generate chess buttons
		for (int i = 0; i < chessModel.getBoardYLength(); i++) {
			for (int j = 0; j < chessModel.getBoardXLength(); j++) {
				ChessButton button = new ChessButton(j, i);
				//button.setBackground(new Color(5, 5, 5));
				Font font2 = new Font("Center",Font.BOLD,34);
				button.setFont(font2);
				button.setForeground(Color.BLACK);
				//button.setBackground(Color.BLUE);
				button.setBorderPainted(false);
				button.setContentAreaFilled(false);
				button.setOpaque(true);
				button.setVisible(true);
				button.addActionListener(new ButtonListener(button, this));
				buttons[i][j] = button;
			}
		}

		// create center panel
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(chessModel.getBoardYLength(), chessModel.getBoardXLength(), 15, 15));
		//center.setLayout(new GridLayout(chessModel.getBoardYLength(), chessModel.getBoardXLength(),15, 15));
		//center.setVisible(true);
		for (int i = 0; i < chessModel.getBoardYLength(); i++) {
			for (int j = 0; j < chessModel.getBoardXLength(); j++) {
				center.add(this.buttons[i][j]);
			}
		}

		// create bottom panel
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
		bottom.add(reset);
		bottom.add(nextBestMove);
		bottom.add(howToPlay);
		bottom.setVisible(true);

		// add everything to the frame
		this.setLayout( new BorderLayout() );
		this.add(top, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);

		// set up the frame
		this.setTitle("Solitare Chess: Robin Li rxl7906");
		this.setSize(chessModel.getBoardXLength()*200, chessModel.getBoardYLength()*200);
		this.setLocation(1000,1000);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessModel.reset();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		HashMap<PointPosition, ChessPiece> chessBoard = chessModel.getChessBoard();
		
		// reset button values
		for (ChessButton[] row : this.buttons) {
			for (ChessButton button : row) {
				//button.setBackground(Color.BLUE);
				button.setText("");
			}
		}
		
		// set text based on new model
		for (PointPosition pp : chessBoard.keySet()) {
			this.buttons[pp.getYCoordinate()][pp.getXCoordinate()].setText(chessBoard.get(pp).getName());
		}
		
		this.moveCountDisplay.setText("Move: " + chessModel.getMoveCount());
		
		// check for end of game
		if (chessBoard.size() == 1) {
			this.gameMessage.setText("You Win!");
		}
		
		if (this.chessModel.resetSize() == 0) {
			this.originalColor();
		}
	}
	
	/**
	 * Updates background color back to original state
	 */
	protected void originalColor() {
		int i = 0;
		int j = 0;
		for (ChessButton[] row : this.buttons) {
			for (ChessButton button : row) {
				if ((i+j) % 2 == 0) {
				button.setBackground(new Color(50,50,50));
				button.setBackground(Color.WHITE);
				/*System.out.println("White");
				System.out.println(i);
				System.out.println(j);*/
				j +=1;
				} else if ( (i+j) % 2 == 1){
					button.setBackground(new Color(50,50,50));
					button.setBackground(Color.BLUE);
					/*System.out.println("Blue");
					System.out.println(i);
					System.out.println(j);*/
					j +=1;
				}
			}
			i += 1;
			j = 0;
		}
	}

	/**
	 * main - initializes ChessGUI
	 * 
	 * @param args - takes chessBoard text file
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java Chess input-file");
			System.exit(0);
		}
		// Takes in chessBoard text file, insert into chessModel 
		// to initialize board, then display GUI
		File newFile = new File(args[0]);
		ChessModel chessModel = new ChessModel(newFile);
		new Chess(chessModel);
		/*if (args.length != 1) {
			System.out.println("Usage: java Chess input-file");
			System.exit(1);
		}
		try {
			System.out.println("test");
			File newFile = new File(args[0]);
			System.out.println("test");
			ChessModel chessModel = new ChessModel(newFile);
			new ChessGUI(chessModel);
		} catch (FileNotFoundException e) {
			System.out.println(args[0] + " no found.i");
		}
		// Takes in chessBoard text file, insert into chessModel 
		// to initialize board, then display GUI
		//File newFile = new File(args[0]);
		//ChessModel chessModel = new ChessModel(newFile);
		//new ChessGUI(chessModel);
*/	}

	/**
	 * Each button has an event listener in the GUI.
	 */
	private class ButtonListener implements ActionListener {
		private JButton button;
		private Chess chessGUI;

		/**
		 * Gives functionality to JButtons when activated.
		 * 
		 * @param button - JButton made for GUI display
		 * 		  chessGUI - ChessGUI
		 */
		public ButtonListener(JButton button, Chess chessGUI) {
			this.button = button;
			this.chessGUI = chessGUI;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// check what type of button is pressed
			
			// if button pressed is "reset"
			if (e.getActionCommand().equals("Reset")){
				chessModel.reset();
				gameMessage.setText("");
			} 
			// if button pressed was a chess button
			else if (button instanceof ChessButton) { 
				// add chess piece
				if (chessModel.addPiece(((ChessButton) button).getPosition())) {
					chessGUI.originalColor();
					button.setBackground(Color.LIGHT_GRAY);
				}
			// if button pressed was "Next Best Move"
			} else if (button.getText().equals("Next Best Move")){
				if (gameMessage.getText().equals("You Win!") ) {
					gameMessage.setText("");
				}
				else if (!chessModel.nextBestMove(chessModel)) {
					gameMessage.setText("Invalid Move");
				}
				
			} else if (e.getActionCommand().equals("How To Play")){ 
				// pop up message: How To Play
				JOptionPane.showMessageDialog(chessGUI, 
						"Based on normal chess rules, each chess piece has their own movements."
						+ "To move a chess piece, click on the grid box of the chess piece, and \n"
						+ "click to another destination. To capture, select the chess piece you want"
						+ "to attack with and click on the chess piece you wish to capture. If you \n"
						+ "are unsure where to move, the 'Next Best Move' will generate the best move."
						+ "To win the game, you must have one chess piece standing." 
						,"How to play",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
