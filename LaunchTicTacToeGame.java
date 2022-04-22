import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class TicTacToe {
    static ArrayList<Integer> playerPosition = new ArrayList<>();
    static ArrayList<Integer> cpuPosition = new ArrayList<>();

    //Display the tic-tac-toe grid
    public void display(char[][] grid) {
	      for(char[] c : grid) {
	          System.out.println(c);
	      }
    }

    //Insert 'X' on the grid if Player and 'O' if CPU
    public void insert(char[][] grid, int position, String playerType) {
	      char c;

	      //Insert 'X' if it is a Player
	      if(playerType.equals("player")) {
	          c = 'X';
	          playerPosition.add(position);
	      }
	      //Insert 'O' if it is a CPU
	      else {
	          c = 'O';
	          cpuPosition.add(position);
	      }

	      //Insert 'X' or 'O' at the selected position
	      switch(position) {
	          case 1:
	              grid[0][0] = c;
	              break;
	          case 2:
	              grid[0][2] = c;
	              break;
	          case 3:
	              grid[0][4] = c;
	              break;
            case 4:
                grid[2][0] = c;
                break;
            case 5:
                grid[2][2] = c;
                break;
            case 6:
                grid[2][4] = c;
                break;
            case 7:
                grid[4][0] = c;
                break;
            case 8:
                grid[4][2] = c;
                break;
            case 9:
                grid[4][4] = c;
                break;
            default:
                System.out.println("Invalid position. Select a position between (1-9):");
	      }
    }

    //Conditions required to win the game
    public void winningCondition() {
	      List<Integer> topRow = Arrays.asList(1,2,3); //top row matches
	      List<Integer> midRow = Arrays.asList(4,5,6); //middle row matches
	      List<Integer> botRow = Arrays.asList(7,8,9); //bottom row matches
	      List<Integer> leftCol = Arrays.asList(1,4,7); //left column matches
	      List<Integer> midCol = Arrays.asList(2,5,8); //middle column matches
	      List<Integer> rightCol = Arrays.asList(3,6,9); //right column matches
	      List<Integer> diag1 = Arrays.asList(1,5,9); //left to right diagonal matches
	      List<Integer> diag2 = Arrays.asList(3,5,7); //right to left diagonal matches

	      //Add the list of winning conditions to the ArrayList
	      ArrayList<List<Integer>> winningCond = new ArrayList<>();
	      winningCond.add(topRow);
	      winningCond.add(midRow);
	      winningCond.add(botRow);
	      winningCond.add(leftCol);
	      winningCond.add(midCol);
	      winningCond.add(rightCol);
	      winningCond.add(diag1);
	      winningCond.add(diag2);

	      //Loop through the ArrayList to check for the winning condition
	      for(List<Integer> i : winningCond) {

	          //Check the winning conditions for the Player
	          if(playerPosition.containsAll(i)) {
		            System.out.println("Player has won the game");
		            System.exit(0);
	          }
	          //Check the winning conditions for the CPU
	          else if(cpuPosition.containsAll(i)) {
		            System.out.println("CPU has won the game");
		            System.exit(0);
	          }
        }

	      /* The above for-each loop have to finish running before checking if it is a draw
	      Otherwise, any winning conditions after topRow will become a draw if size is 9 */

	      //At this point, Player and CPU have not fulfilled the winning conditions
	      //If the sum of Player moves + CPU moves equals 9, it is a draw
	      if(playerPosition.size()+cpuPosition.size()==9) {
	          System.out.println("It is a draw");
	          System.exit(0);
	      }
    }
}

public class LaunchTicTacToeGame {
    public static void main(String[] args) {
	      Scanner keyboard = new Scanner(System.in);

	      //Draw the tic tac toe grid
	      char[][] grid = {{' ','|',' ','|',' '}
			                  ,{'-','+','-','+','-'}
			                  ,{' ','|',' ','|',' '}
			                  ,{'-','+','-','+','-'}
			                  ,{' ','|',' ','|',' '}};

	      TicTacToe t = new TicTacToe();
	      t.display(grid);

	      while(true) {
	          System.out.println("Player, please select a position (1-9):");
	          int ppos = keyboard.nextInt();

	          //Check if the position selected by the Player has already been taken
	          while(TicTacToe.playerPosition.contains(ppos) || TicTacToe.cpuPosition.contains(ppos)) {

		            //If position already taken, ask the Player to select another position
		            System.out.println("Position taken. Select another position (1-9):");
		            ppos = keyboard.nextInt();
	          }

	          t.insert(grid, ppos, "player");
	          t.display(grid);
	          t.winningCondition();
	          System.out.println();

	          //Generate a random position between 1-9 for the CPU
	          Random rand = new Random();
	          int cpos = rand.nextInt(1,9);
	          System.out.println("CPU selected position " + cpos);

	          //Check if the position selected by the CPU has already been taken
	          while(TicTacToe.playerPosition.contains(cpos) || TicTacToe.cpuPosition.contains(cpos)) {

		            //If position already taken, generate another position between 1-9 for the CPU
		            System.out.println("Position taken. Select another position (1-9):");
		            cpos = rand.nextInt(1,9);
		            System.out.println("CPU selected position " + cpos);
	          }
	          t.insert(grid, cpos, "cpu");
	          t.display(grid);
	          t.winningCondition();
	          System.out.println();
	      }
    }
}
