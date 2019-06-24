package mathParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Parser {

	public static int parseExpression(String expression, Map<String, Integer> values) {
		String expressionT = expression.trim();
		String newExpression = expressionT;
		String[] parts = expressionT.split("(?=[/*+-])|(?<=[/*+-])");
		List<String> partsList = Arrays.asList(parts);
		List<Integer> indexOfMult = new ArrayList<>();
		List<Integer> indexOfSum = new ArrayList<>();
		List<Integer> indexOfSub = new ArrayList<>();
		List<Integer> indexOfDiv = new ArrayList<>();

		for (int i = 0; i < parts.length; i++) {
			if (parts[i].contains("*")) {
				indexOfMult.add(i);
			} else if (parts[i].contains("/")) {
				indexOfDiv.add(i);
			} else if (parts[i].contains("+")) {
				indexOfSum.add(i);
			} else if (parts[i].contains("-")) {
				indexOfSub.add(i);
			}
		}

		int result = 0, mult = 1;
		for (int index : indexOfMult) {

			mult = values.get(parts[index - 1]) * values.get(parts[index + 1]);
			result += mult;
			System.out.println(
					parts[index - 1] + "*" + parts[index + 1] + ", " + newExpression.substring(index - 1, index + 1));

		}
		/*
		 * System.out.println(newExpression); for (int index : indexOfDiv) {
		 * result += values.get(parts[index - 1]) / values.get(parts[index +
		 * 1]); } for (int index : indexOfSum) { result +=
		 * values.get(parts[index - 1]) + values.get(parts[index + 1]); } for
		 * (int index : indexOfSub) { result += values.get(parts[index - 1]) -
		 * values.get(parts[index + 1]); }
		 */
		return result;

	}

	public static int[] getIndexOfChar(String expression) {
		String exp = expression.trim();
		int i = 0;

		while (i < exp.length()) {

		}
		return new int[] {};
	}
	/*
	 * public static int parseExpression(String expression, Map<String, Integer>
	 * values) {
	 * 
	 * String[] multiplication = expression.split("\\*"); //String[] divition =
	 * expression.split("\\/"); String[] sum =expression.split("\\+"); String[]
	 * subtraction = expression.split("\\-");
	 * 
	 * int result = 0; for(String num:sum){ if(num.contains("-")){ int sub=0;
	 * for(String numSub:num.split("\\-")){ sub-=values.get(numSub.trim()); }
	 * result+=sub; } /*if(num.contains("*")){ int mult=1; for(String
	 * numMult:num.split("\\*")){ mult*=values.get(numMult.trim()); }
	 * result+=mult; }
	 *//*
		 * else result+=values.get(num.trim()); }
		 * 
		 * 
		 * return result; }
		 */

	public static Map<String, Integer> parseValues(String[] values) {
		Map<String, Integer> parsedValues = new HashMap<>();
		return parsedValues;
	}

	public static Map<String, Integer> areCorrectValues(List<String> listOfValues) {
		Map<String, Integer> correctValues = new HashMap<>();
		for (int i = 0; i < listOfValues.size() - 1; i++) {
			String valueTrim = listOfValues.get(i).trim();
			String number = valueTrim.split("=")[1];
			if (valueTrim.contains("=") && isInteger(number))
				correctValues.put(valueTrim.split("=")[0], Integer.parseInt(number));
		}
		return correctValues;
	}

	public static boolean isInteger(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter please an algebraic expression:");
		String expression = in.nextLine();
		List<String> listOfValues = new ArrayList<>();
		System.out.println("Enter the values of every variable, when you finish enter $");
		listOfValues.add(in.nextLine());
		while (!listOfValues.contains("$")) {
			listOfValues.add(in.nextLine());
		}
		System.out.println("The result is:");
		System.out.println(parseExpression(expression, areCorrectValues(listOfValues)));

	}

}
