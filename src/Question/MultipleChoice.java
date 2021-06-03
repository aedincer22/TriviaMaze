package Question;

import java.util.*;

public class MultipleChoice extends Question{

	private static final Database DATABASE =  Database.getInstance();;
	private static final List<String> MULTIPLECHOICE_QUESTIONS = DATABASE.getMultipleChoiceQuestions();
	private static final List<String> MULTIPLECHOICE_OPTIONS = DATABASE.getMultipleChoiceOptions();
	private static final List<String> MULTIPLECHOICE_ANSWERS = DATABASE.getMultipleChoiceAnswers();
	private static int MCQuestionID = 0;

	private int myQuestionID; 
	private final String myQuestion;
	private final String myOption;
	private final String myAnswer;
	

	
	
	public MultipleChoice() {
		setMyQuestionID();
		myQuestion = setMyQuestion();
		myOption = setMyOption();
		myAnswer = setMyAnswer();
	}
	
	private void setMyQuestionID() {
		if (MCQuestionID == MULTIPLECHOICE_QUESTIONS.size()) {
			MCQuestionID = 0;
		}
		myQuestionID = MCQuestionID++;
	}
	@Override
	public int getMyQuestionID() {
		return myQuestionID;
	}
	
	private String setMyQuestion() {
		return MULTIPLECHOICE_QUESTIONS.get(myQuestionID);
	}
	
	@Override
	public String getMyQuestion() {
		return myQuestion;
	}

	private String setMyOption() {
		return MULTIPLECHOICE_OPTIONS.get(myQuestionID);
	}
	
	@Override
	public String getMyOptions() {
		return myOption;
	}
	
	private String setMyAnswer() {
		return MULTIPLECHOICE_ANSWERS.get(myQuestionID);
	}
	
	@Override
	public String getMyAnswer() {
		return myAnswer;
	}

		
}
