package Question;
public class OneWord extends Question{

	private static int OWQuestionID = 1;
	private int myQuestionID; 
	private final String myQuestion;
	private final String myOption;
	private final String myAnswer;
	private Database myQuestionBank;

	
	public OneWord() {
		myQuestionBank = Database.getInstance();
		setMyQuestionID();
		myQuestion = setMyQuestion();
		myOption = setMyOption();
		myAnswer = setMyAnswer();
	}
	
	
	private void setMyQuestionID() {
		
		try {
			if (!myQuestionBank.isEntryAvailable(OWQuestionID, "OneWord")) {
				OWQuestionID = 1;
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		myQuestionID = OWQuestionID++;
	}
	
	@Override
	public int getMyQuestionID() {
		return myQuestionID;
	}
	
	private String setMyQuestion() {
		return myQuestionBank.fetch("question", myQuestionID, "OneWord");
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
		return myQuestionBank.fetch("answer", myQuestionID, "OneWord");
	}
	
	@Override
	public String getMyAnswer() {
		return myAnswer;
	}

	
//	private String queryDatabase(final String field) {
//		
//	    String result = "";
//		try {
//			final ResultSet rs = Database.QUESTIONBANK.executeQuery
//					("select " + field + " from OneWord where id = " + myQuestionID);
//			result = rs.getString(field);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
		
}
