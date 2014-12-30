import java.util.*;

/*
 * Water.java
 * 
 * File: 
 * 		$Id: Water.java,v 1.7 2014/08/05 16:41:37 rxl7906 Exp $
 * 
 * Revisions:
 * 		$Log: Water.java,v $
 * 		Revision 1.7  2014/08/05 16:41:37  rxl7906
 * 		commit
 *
 * 		Revision 1.6  2014/07/17 21:45:10  rxl7906
 * 		final commit
 *
 * 
 * Project Part 2: Water
 * 
 * Water.java - runs from inputs from command line with
 * minimum of two arguments: desired amount of water and
 * capacity of first jug. 
 * Water implements the puzzle interface as a arraylist<integer>
 * 
 * @author Robin Li rxl7906@rit.edu
 * 			Lakshay Khatter
 */
public class Water implements Puzzle<ArrayList<Integer>> {
	
	private int gallonGoal;
	private ArrayList<Integer> jugs;
	
	//constructor for the water puzzle
	public Water(int gallonGoal, ArrayList<Integer> jugs) {
		this.gallonGoal = gallonGoal;
		this.jugs = jugs;
	}
	
	public static void main(String[] args) {
		
		// ArrayList<ArrayList<Integer>> is the water solution
		ArrayList<ArrayList<Integer>> puzzle_Solution = new ArrayList<ArrayList<Integer>>();
		
		// must take minimum of 2 arguments
		if (args.length >= 2) {
			//make empty list of jugs
			ArrayList<Integer> jugs = new ArrayList<Integer>(args.length - 1);
			
			//make a new jug for each jug argument
			for (int i = 1; i < args.length; i++) {
				jugs.add(Integer.parseInt(args[i]));
			}
			
			//set the gallon goal
			int gallonGoal = Integer.parseInt(args[0]);
			

			//initialize water object to be solved
			Puzzle<ArrayList<Integer>> puzzle_Water = new Water(gallonGoal,jugs);
			puzzle_Solution = new Solver<ArrayList<Integer>>().Solve(puzzle_Water);
			//ArrayList<ArrayList<Integer>> puzzle_Solution = puzzle_Solver.get_Solution();
			
			//puzzle_Solution = new Solver<ArrayList<Integer>>().Solver(new Water( gallonGoal, jugs));
			if (puzzle_Solution != null) {
				
				// print out the Steps 1 to N
				for (int i = 0; i < puzzle_Solution.size(); i++){
					System.out.print("Step " + i + ": ");
					
					// get the jug values
					for (int k = 0; k < puzzle_Solution.get(i).size(); k++) {
						System.out.print(puzzle_Solution.get(i).get(k));
						System.out.print(" ");
					}
					System.out.print("\n");
				}
			}
			else {
				
				// set a boolean value to false
				boolean solution = false;
				
				// checks to see if the gallonGoal was found
				// finds what index jug is the goal
				for (int i = 1; i < args.length; i++) {
					if (args[0] == args[i]) {
						solution = true;
						break;
					}
				}
				
				if (solution == true) {
					// print Step 0
					String step0 = "Step 0: ";
					for (int i = 1; i < args.length; i++) {
						step0 += args[i];
					}
					System.out.println(step0);
				} else {
					System.out.println("No solution.");
				}
				
			}
			
		}
		else{
			System.out.println("Usage: java Water amount jug1 jug2 ...");
		}
	}
	
	/**
	 * initJugs initializes the jugs to be zero
	 * @return ArrayList<Integer> initJugs
	 */
	public ArrayList<Integer> getStart(){
		// initialize jugs to zero
		ArrayList<Integer> init = new ArrayList<Integer>();
			for(int i = 0; i < jugs.size() ; i++) {
				init.add(0);
			}
			return init;
		}

	/**
	 * isGoal checks to see if the goalConfig is the goal
	 * @param ArrayList<Integer> goalConfig
	 * @returns true or false if the goalConfig has the gallonGoal
	 */
	public boolean isGoal(ArrayList<Integer> config){
		if (config.contains(this.gallonGoal)){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * initConfig - resets neighbor config to initial state
	 * @param orig
	 * @return
	 */
	public ArrayList<Integer> initConfig(ArrayList<Integer> init) {
		ArrayList<Integer> resetConfig = new ArrayList<Integer>();
		for(Integer i : init) {
			resetConfig.add(i);	
		}
		return resetConfig;
	}
	/**
	 * getNeighbors finds an arraylist of neighbors
	 * @param config
	 * @return ArrayList<ArrayList<Integer>> neighbors
	 */
	public ArrayList<ArrayList<Integer>> getNeighbors(ArrayList<Integer> config) {
		
		//create data structure arraylist<arraylist<integer>> of neighbors
		ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>();
		
		//for each config reset it to its original state
		ArrayList<Integer> oneConfig = initConfig(config);
		
		// get neighbors of the config
		for (int i = 0; i < config.size(); i++) {

			//reset config
			if (oneConfig.size() != config.size() ) 
				oneConfig = initConfig(config);
			
			// set current to the first element removed
			Integer current = oneConfig.remove(i);
			
			// fill a single jug up to complete capacity
			if (current != jugs.get(i)) {
				oneConfig.add(i,jugs.get(i));
				neighbors.add(oneConfig);
				oneConfig = initConfig(config);
				current = oneConfig.remove(i);
			}
			
			// empty a single jug by dumping all water
			if (current != 0) {
				oneConfig.add(i,0);
				neighbors.add(oneConfig);
				oneConfig = initConfig(config);
				current = oneConfig.remove(i);
			}
			
			// pour contents of any one jug into another
			for (int k = 0; k < config.size() - 1; k++) {
				if (oneConfig.size() == config.size()){
					current = oneConfig.remove(i);
				}
				if (oneConfig.get(k) != 0 && current != jugs.get(i)) {
					Integer pour = oneConfig.remove(k);
					int sum = 0;
					int left = 0;
					if (pour + current <= jugs.get(i)) {
						sum = pour + current;
					} 
					else {
						sum = jugs.get(i);
						left = (pour + current) - jugs.get(i);
					}
					// add 
					oneConfig.add(k, left);
					oneConfig.add(i, sum);
					if (pour != left) {
						neighbors.add(oneConfig);
						oneConfig = initConfig(config);
						current = oneConfig.remove(i);
					}
				} else {
					oneConfig = initConfig(config);
				}
			}
		}
		return neighbors;
	}
}

