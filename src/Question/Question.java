package Question;

import java.util.Random;
public abstract class Question {
	
	
	//Example on how to use the Question class
	public static void main(String [] args) {
		int choice = 0;
		
		
		for (int i = 0; i < 16; i++) {
			Random rand = new Random();
			choice = rand.nextInt(3);
			
			//Based on some conditions we need to create different type of questions
			Question question;
			if (choice ==  0) {
				question = Question.create("MultipleChoice");
			} else if (choice == 1) {
				question = Question.create("TrueOrFalse");
			} else {
				question = Question.create("OneWord");
			}
			System.out.println(question);
			//System.out.println(question.getMyAnswer());
			System.out.println("_______________");
		}
		Question.close();
		
	}
	
	
	/**
	 * Makes a random decision on which type of question needs to be created.
	 * @return Question
	 */
	public static Question createRandomQuestion() {
		Random rand = new Random();
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
		
		final Question question;
		if (theQuestionType.equals("MultipleChoice")) {
			question = new MultipleChoice();
		} else if (theQuestionType.equals("TrueOrFalse")) {
			question = new TrueOrFalse();
		} else if (theQuestionType.equals("OneWord")){
			question = new OneWord();
		} else {
			throw new IllegalArgumentException("The type of question asked is invalid");
		}
		return question;
	}
	
	/**
	 * Close the Database connection
	 */
	public static void close() {
		Database.close();
	}
	
	/**
	 *  Get's the ID of the Question.
	 *  @return QuestionID
	 **/
	public abstract int getMyQuestionID();
	
	/**
	 * Get the question of the current Question object. 
	 * @return String: Question
	 */
	public abstract String getMyQuestion();
	
	/**
	 * Get the answer of the current Question object
	 * @return String: Answer
	 */
	public abstract String getMyAnswer();
	
	/**
	 * Get the options of the current Question object 
	 * @return String: Options
	 */
	public abstract String getMyOptions();
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(this.getMyQuestion()).append("\n")
		.append(this.getMyOptions());
		
		return str.toString();
	}
	
}
