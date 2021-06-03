package Question;

import java.util.*;

public class TrueOrFalse extends Question{

	private static final Database DATABASE = Database.getInstance();
	private static final List<String> TRUEORFALSE_QUESTIONS = DATABASE.getTrueOrFalseQuestions();
	private static final List<String> TRUEORFALSE_ANSWERS = DATABASE.getTrueOrFalseAnswers();
	private static int TFQuestionID = 0;
	
	private int myQuestionID; 
	private final String myQuestion;
	private final String myOption;
	private final String myAnswer;
	
	public static void main(String [] args) {
		Question q = new TrueOrFalse();
		System.out.println(q.getMyQuestionID());
		System.out.println(q);
		
		Question q1 = new TrueOrFalse();
		System.out.println(q1.getMyQuestionID());
		System.out.println(q1);
		
		Question q2 = new TrueOrFalse();
		System.out.println(q2.getMyQuestionID());
		System.out.println(q2);
		
		Question q3 = new TrueOrFalse();
		System.out.println(q3.getMyQuestionID());
		System.out.println(q3);
	}

	//constructor
	public TrueOrFalse() {
		setMyQuestionID();
		myQuestion = setMyQuestion();
		myOption = setMyOption();
		myAnswer = setMyAnswer();
		
	}

	private void setMyQuestionID() {
		if (TFQuestionID == TRUEORFALSE_QUESTIONS.size()) {
			TFQuestionID = 0;
		}
		myQuestionID = TFQuestionID++;
	}
	
	@Override
	public int getMyQuestionID() {
		return myQuestionID;
	}
	
	/**
	 * Get the question from the database and set it to the current Question object
	 * @return
	 */
	private String setMyQuestion() {
		return TRUEORFALSE_QUESTIONS.get(myQuestionID);

	}
	
	@Override
	public String getMyQuestion() {
		return myQuestion;
	}

	/**
	 * For True or False type of questions there are only two options
	 * @return String: Options
	 */
	private String setMyOption() {
		return "True or False";
	}
	
	@Override
	public String getMyOptions() {
		return myOption;
	}
	
	/**
	 * Get the answer from the database for the Question object.
	 * @return String: Answer
	 */
	private String setMyAnswer() {
		return TRUEORFALSE_ANSWERS.get(myQuestionID);

	}
	
	@Override
	public String getMyAnswer() {
		return myAnswer;
	}

	
}
