package Question;
public class TrueOrFalse extends Question{

	private static int TFQuestionID = 1;
	private int myQuestionID; 
	private final String myQuestion;
	private final String myOption;
	private final String myAnswer;
	private final Database myQuestionBank;

	//constructor
	public TrueOrFalse() {
		myQuestionBank = Database.getInstance();
		setMyQuestionID();
		myQuestion = setMyQuestion();
		myOption = setMyOption();
		myAnswer = setMyAnswer();
		
	}
	
	/**
	 * Set the Question ID, such that each ID represents a particular entry in the database. 
	 */
	private void setMyQuestionID() {
		
		try {
			if (!myQuestionBank.isEntryAvailable(TFQuestionID, "TrueOrFalse")) {
				TFQuestionID = 1;
			}
		} catch (final Exception e) {
			e.printStackTrace();
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
		return myQuestionBank.fetch("question", myQuestionID, "TrueOrFalse");

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
		return myQuestionBank.fetch("answer", myQuestionID, "TrueOrFalse");

	}
	
	@Override
	public String getMyAnswer() {
		return myAnswer;
	}

	
}
