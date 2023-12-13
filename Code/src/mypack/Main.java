package mypack;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * A program that is designed to convert prefix expressions to post-fix using
 * the LIFO data structure stack. The java built in stack is not used, and my
 * built stack class is used and overrides the java class.
 * 
 * @author Brandon L Morrow
 * @date 09/30/2022
 */
public class Main {

	public static <T> void main(String[] args) {

		try {
			long startTime = System.nanoTime(); // Used to track time for O() estimation

			// Check command args length
			if (args.length > 0) {

				FileReader inputStream = null;
				FileWriter outputStream = null;

				Main main = new Main(); // Instance of main to use methods
				Stack<Character> inputStack = new Stack<Character>(); // Input stream stack
				Stack<String> postFixStack = new Stack<String>(); // Reverse postfix Sack
				Stack<String> outputStack = new Stack<String>(); // Output stream class

				try {
					inputStream = new FileReader(args[0]); // input file command line input
					outputStream = new FileWriter(args[1]); // output file command line input

					// This input stream reads each line character by character and
					// converts it to a postfix expression. It then validates it is a valid
					// postfix expression and if so it returns the expression.
					int c;
					while ((c = inputStream.read()) != -1) {
						char ch = (char) c;

						if (ch != '\n') {
							inputStack.push(ch);
							while (inputStack.peek() == ' ') {
								inputStack.pop();
							}
						}
						// When there is a new line convert the past line/prefix expression
						if (ch == '\n') {
							String postFixString = main.preToPostString(inputStack);
							postFixStack.push(postFixString);
						}
					} // End inputStream

					// Need to flip the string stack since it is in reverse
					while (postFixStack.isEmpty() == false) {
						outputStack.push(postFixStack.pop());
					}
					// Will now have the correct element at top of the stack to write to file
					while (outputStack.isEmpty() == false) {
						outputStream.write(outputStack.pop() + '\n');
					}

					// Displays the time the program took to run
					long endTime = System.nanoTime();
					long totalTime = endTime - startTime;
					System.out.println("Total program time in ms: " + (totalTime / 1000000));
				} finally {
					if (inputStream != null)
						inputStream.close();
					if (outputStream != null)
						outputStream.close();
				}
			} // Below is error checking
			if (args.length < 1) {
				System.err.print("Check your args length for command line arguments!!");
			}
		} catch (FileNotFoundException fnfe) {
			System.err.println("File not found try again!");
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			System.err.println("IO Error other than file not found!");
			ioe.printStackTrace();
		} catch (Exception e) {
			System.err.print("Error see stack trace!");
			e.printStackTrace();
		}
	}// End main()

	/**
	 * A method that converts a prefix character stack to a postfix string
	 * 
	 * @param stack<Character>
	 * @return String convertedString
	 */
	private String preToPostString(Stack<Character> stack) {

		String convertedString;
		Stack<String> conversionStack = new Stack<String>();// Initialize conversion stack

		// While the character stack is not empty
		while (stack.isEmpty() == false) {

			// If a blank space remove from the stack
			if (stack.peek() == ' ')
				stack.pop();

			// If else converts the stack and places into string
			if (isOperator(stack.peek())) {

				String stringA = conversionStack.pop();
				String stringB = conversionStack.pop();
				String temp = stringA + stringB + stack.pop();
				conversionStack.push(temp);

			} else {// If not an operator convert element to string and push to stack
				String popString = "" + stack.pop();
				conversionStack.push(popString);
			}
		}
		convertedString = conversionStack.pop();

		// Tests if the string is in the correct form!
		while (conversionStack.peek() == " ") {
			conversionStack.pop();
		}
		// If stack still has an element after the postfix element was pop'd
		if (conversionStack.peek() == null) {
			conversionStack.pop();
			return "Invalid prefix!";
		}
		return convertedString;
	}

	/**
	 * A method that tests if an input value is an operator
	 * 
	 * @param value from char stack
	 * @return true if operator; false else
	 */
	private boolean isOperator(Character value) {
		if (value == null) // Error checking if blank
			return false;
		switch (value) {
		case ('/'):
		case ('*'):
		case ('+'):
		case ('-'):
		case ('$'):
		case ('^'):
			return true;
		}
		return false;
	}
}// End Main()
