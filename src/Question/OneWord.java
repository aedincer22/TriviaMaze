package Question;
import java.util.*;
public class OneWord extends Question{

	private static int OWQuestionID = 0;
	private static final Database DATABASE = Database.getInstance();
	private static final List<String> ONEWORD_QUESTIONS = DATABASE.getOneWordQuestions(); 
	private static final List<String> ONEWORD_ANSWERS = DATABASE.getOneWordAnwsers();
	private int myQuestionID; 
	private final String myQuestion;
	private final String myOption;
	private final String myAnswer;
	
	public static void main(String [] args) {
		Question q = new OneWord();
		System.out.println(q.getMyQuestionID());
		System.out.println(q);
		
		Question q1 = new OneWord();
		System.out.println(q1.getMyQuestionID());
		System.out.println(q1);
		
		Question q2 = new OneWord();
		System.out.println(q2.getMyQuestionID());
		System.out.println(q2);
		
		Question q3 = new OneWord();
		System.out.println(q3.getMyQuestionID());
		System.out.println(q3);
	}
	
	public OneWord() {
		setMyQuestionID();
		myQuestion = setMyQuestion();
		myOption = setMyOption();
		myAnswer = setMyAnswer();
	}
	
	private void setMyQuestionID() {
		if (OWQuestionID == ONEWORD_QUESTIONS.size()) {
			OWQuestionID = 0;
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
