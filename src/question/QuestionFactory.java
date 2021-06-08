/*
 * QuestionFactory
 * Version Spring 2021
 * 06/06/2021
 */

package Question;

import java.util.Random;

/**
 * QuestionFactory creates question based on the needs. A user can either pass
 * in specifically or just use a (random) method to create a question.
 * 
 * @author Ruchik Chaudhari, Shirwa Ahmed, and Yongzhao Ye
 * @version Spring 2021
 *
 */
public class QuestionFactory {

	/**
	 * A class variable which keeps track of the random choice selected. The
	 * variable is helpful to test the createRandomQuestion.
	 */
	private static int randomSelection = 1;

	/**
	 * Makes a random decision on which type of question needs to be created.
	 * 
	 * @return question
	 */
	public static Question createRandomQuestion() {
		final Random rand = new Random();
		int choice = rand.nextInt(3);
		randomSelection = choice;
		String[] choose = { "MultipleChoice", "TrueOrFalse", "OneWord" };
		return create(choose[choice]);
	}

	/**
	 * @return the random selection made.
	 */
	public static int getRandomSelection() {
		return randomSelection;
	}

	/**
	 * Creates a type of question based on the argument passed.
	 * 
	 * @param theQuestionType
	 * @return question
	 */
	public static Question create(final String theQuestionType) {
		final QuestionBank questionBank = QuestionBank.getInstance();
		final Question question;
		if (theQuestionType.equals("MultipleChoice")) {
			question = questionBank.getMultipleChoiceQuestion();
		} else if (theQuestionType.equals("TrueOrFalse")) {
			question = questionBank.getTrueOrFalseQuestion();
		} else if (theQuestionType.equals("OneWord")) {
			question = questionBank.getOneWordQuestion();
		} else {
			throw new IllegalArgumentException("The type of question asked is invalid");
		}
		return question;
	}

}
