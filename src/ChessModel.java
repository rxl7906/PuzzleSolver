/**
 * ChessModel.java - Model to hold data, part of the Model-View-Controller Design
 * 
 * File:
 * 	$Id: ChessModel.java,v 1.6 2014/08/07 13:58:48 rxl7906 Exp $:
 * 
 * Revisions:
 * 	$Log: ChessModel.java,v $
 * 	Revision 1.6  2014/08/07 13:58:48  rxl7906
 * 	commit
 *
 * 	Revision 1.5  2014/08/07 13:50:19  rxl7906
 * 	Functionality working
 * 	doing more tests
 *
 * 	Revision 1.3  2014/08/06 21:26:09  rxl7906
 * 	some functionality, debugging
 * 	commit
 *
 * 	Revision 1.2  2014/08/05 23:29:24  rxl7906
 * 	Chess pieces work
 *
 * 	Revision 1.1  2014/08/05 16:41:39  rxl7906
 * 	commit
 *:
 * 
 * @author Robin Li
 *
 */
import java.io.*;
import java.util.*;

public class ChessModel extends Observable implements Puzzle<HashMap<PointPosition, ChessPiece>> {
	
	private HashMap<PointPosition, ChessPiece> chessBoard;
	private HashMap<Character, ChessPiece> chessPiece;
	private Solver<HashMap<PointPosition, ChessPiece>> solver = new Solver<HashMap<PointPosition, ChessPiece>>();
	private File startingChessBoard;
	private int x;
	private int y;
	private int moveCount;
	private ArrayList<PointPosition> killStack;
	//private boolean debug = false;

	/**
	* ChessModel constructor
	* 
	* @param startinChessBoard - file input that contains chessBoard configuration
	*/
	public ChessModel(File startingChessBoard) {
		//this.debugger("Initialize Chess Model");
		this.chessBoard = new HashMap<PointPosition, ChessPiece>();
		this.startingChessBoard = startingChessBoard;
		this.killStack = new ArrayList<PointPosition>();
		this.reset();
		}

	
		/**
		* isGoal(HashMap<PointPosition, ChessPiece> config) - checks if only
		* one chess piece is on the board.
		* 
		* @return true - there exists one chess piece on the board, you win!
		* 		  false - otherwise
		*/
		@Override
		public boolean isGoal(HashMap<PointPosition, ChessPiece> config) {
			//this.debugger("Check if " + config + " is the solution");
			if (config.size() == 1) {
				//this.debugger("Solution found");
				return true;
			} else {
				return false;
			}
			}
	
		/**
		* addPiece(PointPosition pp) - if chess piece is at a given point, add it 
		* to the stack. If more than two chess pieces in one spot, check for a 
		* capture.
		* 
		* @param pp - point selected by user
		* @return true - there is a piece there
		* 		  false - check for capture. 
		*/ 
		public boolean addPiece(PointPosition pp) {
			
			boolean bool = false;
			//this.debugger("Point " + pp);
			
			// check is chess board has chess piece at this point
			if (this.chessBoard.containsKey(pp)) {
				bool = true;
				//this.debugger(pp + " exists, add to killStack");
				this.killStack.add(pp);
			
				// check if there are two pieces in one spot
				if (this.killStack.size() == 2) {
					//this.debugger("Undo stack size == 2");
					PointPosition first = this.killStack.get(0);
					PointPosition second = this.killStack.get(1);
			
					// if the piece at point first and all its possible moves based on
					// that point has the second point then that's a capture. 
					if (this.chessBoard.get(first).getAllMoves(first).contains(second)) {
						//this.debugger("Piece at " + first + " can capture piece at + second");
						
						// first piece captures second piece
						this.chessBoard.remove(second);
						
						// replace the second piece with the first piece
						this.chessBoard.put(second, this.chessBoard.get(first));
						// remove the first piece from the board (at the original position)
						// since the first piece moved to the second piece position
						this.chessBoard.remove(first); 
						this.moveCount++;
						this.killStack.clear();
						bool = false;
					} else {
						//this.debugger("No kill, changing focus to second");
						// if this is not a kill the second becomes the first
						this.killStack.remove(0);
					}
				}
			}
			this.setChanged();
			this.notifyObservers(this.chessBoard);
			return bool;
			}

		
		
		/**
		* getMoveCount() - gets the move count
		* @return - current number of moves made
		*/
		public int getMoveCount() {
			return moveCount;
			}

		/**
		* getBoardXLength() - gets the x-length of the board
		* @return x-length of the board
		*/
		public int getBoardXLength() {
			return this.x;
			}

		/**
		* getBoardYLength() - gets the y-length of the board
		* @return y-length of the board
		*/
		public int getBoardYLength() {
			return this.y;
			}

		/**
		* Returns the size of the undo stack.
		* @return Size of undo stack.
		*/
		public int resetSize() {
			return this.killStack.size();
			}

		/**
		* getChessBoard() - gets the current chess board
		* @return - positions of all the chess pieces
		*/
		public HashMap<PointPosition, ChessPiece> getChessBoard() {
			return this.chessBoard;
			}

		/**
		* nextBestMove(ChessModel chessModel) - Run Solver.java to yield "next best move"
		* for the current state of the board and updates the board
		* @param chessModel - initialized chessModel
		* @return true - if there is a solution 
		* 		  false - if there is not solution
		*/
		public boolean nextBestMove(ChessModel chessModel) {
			
			// run solver and set board to the second step
			try {
				this.chessBoard = this.solver.Solve(chessModel).get(1);
				this.moveCount++;
				this.setChanged();
				this.notifyObservers(this.chessBoard);
				return true;
			} catch (NullPointerException e) {
				return false;
			}
			}


		@Override
		public ArrayList<HashMap<PointPosition, ChessPiece>> getNeighbors(HashMap<PointPosition, ChessPiece> chessConfig) {
			//this.debugger("Get the neighbors for " + chessConfig);
			ArrayList<HashMap<PointPosition, ChessPiece>> neighbors = new ArrayList<HashMap<PointPosition, ChessPiece>>();
			
			// check the first position
			for (PointPosition first : chessConfig.keySet()) {
				HashSet<PointPosition> possibleMoves = chessConfig.get(first).getAllMoves(first);
				//this.debugger("Move piece at " + first + " are " + possibleMoves);
				// check for captures
				for (PointPosition second : chessConfig.keySet()) {
					//this.debugger("Check for captures at " + second);
					if (possibleMoves.contains(second)) {
						//this.debugger("Captured");
						HashMap<PointPosition, ChessPiece> neighbor = new HashMap<PointPosition, ChessPiece>();
						// make neighbor copy of chessConfig
						neighbor.putAll(chessConfig);
						// remove captured piece
						neighbor.remove(second);
						// move first to the location of second
						neighbor.put(second, chessConfig.get(first));
						// remove first piece
						neighbor.remove(first);
			
						neighbors.add(neighbor);
					}
				}
			}
			return neighbors;
			}

		@Override
		public HashMap<PointPosition, ChessPiece> getStart() {
			//this.debugger("Giving solver current board " + this.chessBoard);
			return this.chessBoard;
			}

		/**
		* readFile(File startingFile) - read
		* @param startingFile - chess board input text file from command line
		*/
		private void readInputFile(File startingFile) {
			try {
				FileReader readFile = new FileReader(startingFile);
				BufferedReader inputFile = new BufferedReader(readFile);
				String[] nextLine = inputFile.readLine().split(" ");
				char current;
			
				this.y = Integer.parseInt(nextLine[0]);
				this.x = Integer.parseInt(nextLine[1]);
				this.chessPiece = this.assignAbbreviation();
			
				// checking for the chess pieces
				for (int i = 0; i < this.y; i++) {
					//this.debugger("Parse line " + i);
					nextLine = inputFile.readLine().split(" ");
					for (int j = 0; j < this.x; j++) {
						current = nextLine[j].charAt(0);
						if (this.chessPiece.containsKey(current)) {
							//this.debugger("Found piece");
							this.chessBoard.put(new PointPosition(j, i),
									this.chessPiece.get(current));
						}
					}
				}
				inputFile.close();
			
			} catch (FileNotFoundException e) {
				//System.err.println("FileNotFoundException: Input File not found in directory.");
				System.err.println(startingFile + " not found.i");
				System.exit(0);
			} catch (IOException e) {
				System.err.println("IO Exception");
				System.exit(0);
			}
			}

		/**
		* assignAbbreviation() - gives each chess piece generated, its letter assignment
		* @return piecesHashMap - map of all the chess pieces with the letter assignment
		* as the key and the value as the chess piece
		*/
		private HashMap<Character, ChessPiece> assignAbbreviation() {
			//this.debugger("Generate piece abbreviation");
			HashMap<Character, ChessPiece> piecesHashMap = new HashMap<Character, ChessPiece>();
			piecesHashMap.put(new Character('K'), new King("King", this.x, this.y));
			piecesHashMap.put(new Character('Q'), new Queen("Queen", this.x, this.y));
			piecesHashMap.put(new Character('R'), new Rook("Rook", this.x, this.y));
			piecesHashMap.put(new Character('B'), new Bishop("Bishop", this.x, this.y));
			piecesHashMap.put(new Character('N'), new Knight("Knight", this.x, this.y));
			piecesHashMap.put(new Character('P'), new Pawn("Pawn", this.x, this.y));
			return piecesHashMap;
			}

		/**
		* Returns everything to starting positions.
		*/
		public void reset() {
			//this.debugger("Reseting board");
			this.moveCount = 0;
			this.chessBoard.clear();
			this.readInputFile(this.startingChessBoard);
			this.killStack.clear();
			this.setChanged();
			this.notifyObservers(this.chessBoard);
			}

		/**
		* debugger(String message) - debugging
		* @param message String to be written to log.
		*//*
		private void debugger(String message) {
			if (this.debug) {
				System.out.println(message);
			}
			}

		*//**
		* toggleDebug() - turn debugger on or off
		*//*
		public void toggleDebug() {
			if (this.debug) {
				this.debug = false;
			} else {
				this.debug = true;
			}
			}*/
}

