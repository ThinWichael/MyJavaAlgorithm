package Quiz.Leetcode.Algorithm.Hard;

import java.util.HashSet;
import java.util.Set;

/*489. Robot Room Cleaner
 * Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.
 * */

//1. The input is only given to initialize the room and the robot's position internally. You must solve this problem "blindfolded". 
// In other words, you must control the robot using only the mentioned 4 APIs, without knowing the room layout and the initial robot's position.
//2. The robot's initial position will always be in an accessible cell.
//3. The initial direction of the robot will be facing up.
//4. All accessible cells are connected, which means the all cells marked as 1 will be accessible by the robot.
//5. Assume all four edges of the grid are all surrounded by wall.

// O (4*r*c)

public class Quiz489_RobotCleaner {

	final int[][] Dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // position to right, down , left , up
//	int degree = 0; // 0 , 90, 180 , 270 , 360 // not necessary

	public Quiz489_RobotCleaner() {
		// TODO Auto-generated constructor stub
	}

	public void cleanRoom(Robot robot) {
		Set<String> visited = new HashSet<String>();

		dfs(robot, visited, 0, 0, 0);

		for(String point: visited) {
			System.out.println("clean point :" + point);
		}
	}

	public void dfs(Robot robot, Set<String> visited, int r, int c, int curDir) {
		// set refresh
		visited.add(r + "-->" + c); // any distinct number bigger than room size
		robot.clean();

		// four direction dfs
		for (int j = 0; j < 4; j++) {
			int nextDir = (curDir + j) % 4; // key program point !!!
			int nextR = r + Dirs[nextDir][0];
			int nextC = c + Dirs[nextDir][1];

			if (!visited.contains(nextR + "-->" + nextC) && robot.move()) { // move() would actually move to next grid
																			// or return false
				dfs(robot, visited, nextR, nextC, nextDir);
				// go back
				robot.turnRight();
				robot.turnRight();
				robot.move();
				robot.turnRight(); // turn back
				robot.turnRight();
			}

			robot.turnRight(); // to next direction
		}
	}

//	public void checkTurn(int row, int column, Robot robot) {
//		// go right , make sure degree is 90
//		if (row == 0 && column == 1) {
//			switch (degree) {
//			case 0:
//				break;
//			case 90:
//				break;
//			case 180:
//				break;
//			case 270:
//				break;
//			}
//		}
	// other direction ...

//	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// to be test !!

	}

}

//class Robot01 implements Robot {
//
//	static int[][] room = null;
////	static int[] nowPoint = { 0, 0 }; // for a clean robot, the (x,y) coordinate is not necessary
//
//	public static void initailize(int[][] room, int[] start) throws Exception {
//		room = room;
//		if (room.length > nowPoint[0] && room[0].length > nowPoint[1]) { // this edge case is for grid. For robot, move
//																			// would give false when it be blocked.
////			nowPoint = start;
//		} else {
//			throw new Exception("start point is out of range");
//		}
//
//	}
//
//	public boolean move() {
//		if (room[nowPoint[0]][nowPoint[1]] != 0) { // 0 is no obstacle
//			return false;
//		}
//		return true;
//	}
//
//	@Override
//	public void turnLeft() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void turnRight() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void clean() {
//		// TODO Auto-generated method stub
//
//	}
//}

interface Robot {
	int[][] room = null;
	int[] nowPoint = { 0, 0 };

	// returns true if next cell is open and robot moves into the cell.
	// returns false if next cell is obstacle and robot stays on the current cell.
	boolean move();

	// Robot will stay on the same cell after calling turnLeft/turnRight.
	// Each turn will be 90 degrees.
	void turnLeft();

	void turnRight();

	// Clean the current cell.
	void clean();
}
