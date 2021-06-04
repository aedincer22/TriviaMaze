package Question;

import java.util.Random;

public class QuestionFactory {

	
	
	/**
	 * Makes a random decision on which type of question needs to be created.
	 * @return Question
	 */
	public static Question createRandomQuestion() {
		final Random rand = new Random();
		int choice = rand.nextInt(3);
		String [] choose = {"MultipleChoice", "TrueOrFalse", "OneWord"};
		return create(choose[choice]);
	}
	
	/**
	 * Creates a type of question based on the argument passed.
	 * @param theQuestionType
	 * @return Question
	 */
	public static Question create(final String theQuestionType) {
		final QuestionBank questionBank = QuestionBank.getInstance();
		final Question question;
		if (theQuestionType.equals("MultipleChoice")) {
			question = questionBank.getMultipleChoiceQuestion();
		} else if (theQuestionType.equals("TrueOrFalse")) {
			question = questionBank.getTrueOrFalseQuestion();
		} else if (theQuestionType.equals("OneWord")){
			question = questionBank.getOneWordQuestion();
		} else {
			throw new IllegalArgumentException("The type of question asked is invalid");
		}
		return question;
	}
	
	

}
