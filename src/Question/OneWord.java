package Question;
import java.sql.*;
import static Utility.Database.DATABASE;
public class OneWord extends Question{

	private static int OWQuestionID = 1;
	private int myQuestionID; 
	private final String myQuestion;
	private final String myOption;
	private final String myAnswer;
	
	//private static Statement DATABASE = setup();
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		for (int i = 0; i < 5; i++) {
//			Question q = new OneWord();
//			System.out.println(q.getMyQuestion());
//			System.out.println(q.getMyOptions());
//			System.out.println(q.getMyAnswer());
//			System.out.println("---------------");
//		}		
//		TrueOrFalse.close();
//		
//	}
	public OneWord() {
		setMyQuestionID();
		myQuestion = setMyQuestion();
		myOption = setMyOption();
		myAnswer = setMyAnswer();
	}
	
	
	private void setMyQuestionID() {
		
		try {
			ResultSet rs = DATABASE.executeQuery("select * from OneWord where id =" + OWQuestionID);
			if (rs.next() == false) {
				OWQuestionID = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		myQuestionID = OWQuestionID++;
	}
	
	@Override
	public int getMyQuestionID() {
		return myQuestionID;
	}
	
	private String setMyQuestion() {
		return queryDatabase("question");
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
		return queryDatabase("answer");
	}
	
	@Override
	public String getMyAnswer() {
		return myAnswer;
	}

	
	private String queryDatabase(final String field) {
		
	    String result = "";
		try {
			final ResultSet rs = DATABASE.executeQuery
					("select " + field + " from OneWord where id = " + myQuestionID);
			result = rs.getString(field);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
		
}
