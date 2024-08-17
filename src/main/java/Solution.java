import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

	static boolean isAnotherDirectionHasPeople;
	static Integer nowtime = 0;
	static Integer lastDirection;
	static ArrayList<Person> personLine;
	/*
	 * Complete the 'getTimes' function below.
	 *
	 * The function is expected to return an INTEGER_ARRAY. The function accepts
	 * following parameters: 1. INTEGER_ARRAY time 2. INTEGER_ARRAY direction
	 */

	public static List<Integer> getTimes(List<Integer> time, List<Integer> direction) {
		// Write your code here
		ArrayList<Integer> sortTimePoint;
		personLine = getPersonLine((ArrayList<Integer>) time, (ArrayList<Integer>) direction);

		lastDirection = 1; // leave first

		sortTimePoint = getSort(time);

		Integer timePoint = sortTimePoint.get(0);

		nowtime = timePoint;
		lastDirection = 1;
		// one direction go
		goByOneDirectionContinuously(sortTimePoint, 0);

		ArrayList<Integer> finalResultTime = transToFinalList(personLine);
		return finalResultTime;
	}

	public static void goByOneDirectionContinuously(ArrayList<Integer> sortTimePoint, Integer loops) {

		for (Integer i = loops; i < sortTimePoint.size(); i++) { // 某時間點 例如 0 秒時
			ArrayList<Integer> indexOfLineAtThisTime = indexOfAll(sortTimePoint.get(i));

			if (nowtime < sortTimePoint.get(i) - 1) {
				nowtime = sortTimePoint.get(i);
			}
			goAtSameTime(indexOfLineAtThisTime, sortTimePoint.get(i));

			if (nowtime != 0 && (i + 1 < sortTimePoint.size()) && nowtime >= (sortTimePoint.get(i + 1))) {
				goByOneDirectionContinuously(sortTimePoint, i + 1);
			} else if (loops != 0) {
				return;
			}
			// 同方向沒有連續等待的人
			lastDirection = lastDirection == 1 ? 0 : 1;

			if (nowtime < sortTimePoint.get(i) - 1) {
				nowtime = sortTimePoint.get(i);
			}

			goAtSameTime(indexOfLineAtThisTime, sortTimePoint.get(i));

			if ((i + 1 < sortTimePoint.size()) && nowtime >= (sortTimePoint.get(i + 1))) {
				goByOneDirectionContinuously(sortTimePoint, i + 1);
			} else if (loops != 0) {
				return;
			}

		}

	}

	public static void goAtSameTime(ArrayList<Integer> indexOfLineAtThisTime, Integer timePoint) {

		isAnotherDirectionHasPeople = false;

		for (Integer indexOfThisTime : indexOfLineAtThisTime) {

			if (personLine.get(indexOfThisTime).getDirection() == lastDirection
					&& personLine.get(indexOfThisTime).getFinishTime() == null) {

				personLine.get(indexOfThisTime).setFinishTime(nowtime);
				lastDirection = personLine.get(indexOfThisTime).getDirection();

				nowtime++;
			} else {
				isAnotherDirectionHasPeople = true;
			}
		}
	}

	public static ArrayList<Integer> transToFinalList(ArrayList<Person> personLine) {

		ArrayList<Integer> finalList = new ArrayList<Integer>();
		for (Person pn : personLine) {
			finalList.add(pn.getFinishTime());
		}

		return finalList;
	}

	public static ArrayList<Person> getPersonLine(ArrayList<Integer> time, ArrayList<Integer> direction) {
		ArrayList<Person> personLine = new ArrayList<Person>();
		Result result = new Result();

		for (Integer i = 0; i < time.size(); i++) {
			Person person = result.new Person(i, time.get(i), direction.get(i));

			personLine.add(person);
		}

		return personLine;
	}

	public class Person {

		Integer direction;
		Integer indexNumber;
		Integer comingTime;
		Integer finishTime;

		Person(Integer indexNumber, Integer comingTime, Integer direction) {
			this.indexNumber = indexNumber;
			this.comingTime = comingTime;
			this.direction = direction;
		}

		public Integer getDirection() {
			return direction;
		}

		public void setDirection(Integer direction) {
			this.direction = direction;
		}

		public Integer getIndexNumber() {
			return indexNumber;
		}

		public void setIndexNumber(Integer indexNumber) {
			this.indexNumber = indexNumber;
		}

		public Integer getComingTime() {
			return comingTime;
		}

		public void setComingTime(Integer comingTime) {
			this.comingTime = comingTime;
		}

		public Integer getFinishTime() {
			return finishTime;
		}

		public void setFinishTime(Integer finishTime) {
			this.finishTime = finishTime;
		}

	}

	public static ArrayList<Integer> getSort(List<Integer> list) {

		TreeSet<Integer> set = new TreeSet(list); // ascending
		ArrayList<Integer> al = new ArrayList<Integer>();

		al.addAll(set);
		return al;
	}

	static ArrayList<Integer> indexOfAll(Object obj) {
		ArrayList<Integer> indexList = new ArrayList<Integer>();
		for (int i = 0; i < personLine.size(); i++)
			if (obj.equals(personLine.get(i).getComingTime()))
				indexList.add(i);
		return indexList;
	}

}

// Q1
public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// BufferedWriter bufferedWriter = new BufferedWriter(new
		// FileWriter(System.getenv("OUTPUT_PATH")));

		int timeCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> time = IntStream.range(0, timeCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).map(String::trim).map(Integer::parseInt).collect(toList());

		int directionCount = Integer.parseInt(bufferedReader.readLine().trim());

		List<Integer> direction = IntStream.range(0, directionCount).mapToObj(i -> {
			try {
				return bufferedReader.readLine().replaceAll("\\s+$", "");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}).map(String::trim).map(Integer::parseInt).collect(toList());

		List<Integer> result = Result.getTimes(time, direction);

		// bufferedWriter.write(result.stream().map(Object::toString).collect(joining("\n"))
		// + "\n");

		System.out.print(result);
		bufferedReader.close();
		// bufferedWriter.close();
	}
}
