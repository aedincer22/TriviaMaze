package Question;
import java.util.*;
public class OneWord extends Question{

	private static int OWQuestionID = 1;
	private static final Database DATABASE = Database.getInstance();
	private static final List<String> ONEWORD_QUESTIONS = DATABASE.getOneWordQuestions(); 
	private static final List<String> ONEWORD_ANSWERS = DATABASE.getOneWordAnwsers();
	private int myQuestionID; 
	private final String myQuestion;
	private final String myOption;
	private final String myAnswer;
	

	
	public OneWord() {
		setMyQuestionID();
		myQuestion = setMyQuestion();
		myOption = setMyOption();
		myAnswer = setMyAnswer();
	}
	
	private void setMyQuestionID() {
		if (OWQuestionID == ONEWORD_QUESTIONS.size()) {
			OWQuestionID = 1;
		}
		myQuestionID = OWQuestionID++;
	}
	@Override
	public int getMyQuestionID() {
		return myQuestionID;
	}
	
	private String setMyQuestion() {
		return ONEWORD_QUESTIONS.get(myQuestionID);
	}
	
	@Override
	public String getMyQuestion() {
		return myQuestion;
	}

	private String setMyOption() {
		return "";
	}
	
	@Override
	public String getMyOptions() {
		return myOption;
	}
	
	private String setMyAnswer() {
		return ONEWORD_ANSWERS.get(myQuestionID);
	}
	
	@Override
	public String getMyAnswer() {
		return myAnswer;
	}

}
