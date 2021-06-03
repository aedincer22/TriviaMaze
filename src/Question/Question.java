package Question;
import java.util.*;

public class Question {
	
	private final String myQuestion;
	private final String myOptions;
	private final String myAnswer;
	
	
	//Example on how to use the Question class
	public static void main(String [] args) {
		
		
		for (int i = 0; i < 16; i++) {
			Question q = QuestionFactory.createRandomQuestion();
			System.out.println(q);
			System.out.println(q.getAnswer());
		}
		
	}
	
	public Question(final String theQuestion, final String theOptions, final String theAnswer) {
		Objects.requireNonNull(theQuestion);
		Objects.requireNonNull(theOptions);
		Objects.requireNonNull(theAnswer);
		myQuestion = theQuestion;
		myOptions = theOptions;
		myAnswer = theAnswer;
	}
	
	/**
	 * Get the question of the current Question object. 
	 * @return String: Question
	 */
	public String getQuestion() {
		return myQuestion;
	}
	
	/**
	 * Get the answer of the current Question object
	 * @return String: Answer
	 */
	public String getAnswer() {
		return myAnswer;
	}
	
	/**
	 * Get the options of the current Question object 
	 * @return String: Options
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
		str.append(this.getQuestion()).append("\n")
		.append(this.getOptions());
		
		return str.toString();
	}
	
}
