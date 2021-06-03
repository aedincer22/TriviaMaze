package Question;

import java.util.Random;
public abstract class Question {
	
	
	//Example on how to use the Question class
	public static void main(String [] args) {
		
		
		for (int i = 0; i < 16; i++) {
			Question q = QuestionFactory.createRandomQuestion();
			System.out.println(q);
			System.out.println(q.getMyAnswer());
		}
		
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
	
	
	/**
	 * @return The string format of Question Class.
	 */
	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder();
		str.append(this.getMyQuestion()).append("\n")
		.append(this.getMyOptions());
		
		return str.toString();
	}
	
}
