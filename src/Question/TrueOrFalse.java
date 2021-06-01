package Question;

import java.util.*;

public class TrueOrFalse extends Question{

	private static int TFQuestionID = 1;
	private static final Database DATABASE = Database.getInstance();
	private static final List<String> TRUEORFALSE_QUESTIONS = DATABASE.getTrueOrFalseQuestions();
	private static final List<String> TRUEORFALSE_ANSWERS = DATABASE.getTrueOrFalseAnswers();
	private int myQuestionID; 
	private final String myQuestion;
	private final String myOption;
	private final String myAnswer;
	

	//constructor
	public TrueOrFalse() {
		setMyQuestionID();
		myQuestion = setMyQuestion();
		myOption = setMyOption();
		myAnswer = setMyAnswer();
		
	}

	private void setMyQuestionID() {
		if (TFQuestionID == TRUEORFALSE_QUESTIONS.size()) {
			TFQuestionID = 1;
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
