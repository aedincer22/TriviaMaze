/*
 * Question Class
 * Version Spring 2021
 * 06/06/2021
 */
package question;

import java.util.*;

/**
 * Question class creates instances which holds, question, options and answer.
 * 
 * @author Ruchik Chaudhari, Shirwa Ahmed, and Yongzhao Ye
 * @version Spring 2021
 *
 */
public class Question {

	/**
	 * The question.
	 */
	private final String myQuestion;

	/**
	 * The options.
	 */
	private final String myOptions;

	/**
	 * The answer.
	 */
	private final String myAnswer;

	/**
	 * The parameterized constructor takes a question, option and answer strings and
	 * initiates all the fields of the class.
	 * 
	 * @param theQuestion
	 * @param theOptions
	 * @param theAnswer
	 */
	public Question(final String theQuestion, final String theOptions, final String theAnswer) {
		Objects.requireNonNull(theQuestion);
		Objects.requireNonNull(theOptions);
		Objects.requireNonNull(theAnswer);
		myQuestion = theQuestion;
		myOptions = theOptions;
		myAnswer = theAnswer;
	}

	/**
	 * @return Question.
	 */
	public String getQuestion() {
		return myQuestion;
	}

	/**
	 * @return Answer.
	 */
	public String getAnswer() {
		return myAnswer;
	}

	/**
	 * @return Options.
	 */
	public String getOptions() {
		return myOptions;
	}

	/**
	 * @return The string format of Question Class.
	 */
	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder();
		str.append(this.getQuestion()).append("\n").append(this.getOptions());

		return str.toString();
	}

}
