package Question;
public class MultipleChoice extends Question{

	private static int MCQuestionID = 1;
	private int myQuestionID; 
	private final String myQuestion;
	private final String myOption;
	private final String myAnswer;
	private final Database myQuestionBank;
	
	
	public MultipleChoice() {
		myQuestionBank = Database.getInstance();
		setMyQuestionID();
		myQuestion = setMyQuestion();
		myOption = setMyOption();
		myAnswer = setMyAnswer();
		
	}
	
	
	private void setMyQuestionID() {		
		try {
			if (!myQuestionBank.isEntryAvailable(MCQuestionID, "MultipleChoice")) {
				MCQuestionID = 1;
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		myQuestionID = MCQuestionID++;
	}
	
	@Override
	public int getMyQuestionID() {
		return myQuestionID;
	}
	
	private String setMyQuestion() {
		return myQuestionBank.fetch("question", myQuestionID, "MultipleChoice");
	}
	
	@Override
	public String getMyQuestion() {
		return myQuestion;
	}

	private String setMyOption() {
		return myQuestionBank.fetch("options", myQuestionID, "MultipleChoice");
	}
	
	@Override
	public String getMyOptions() {
		return myOption;
	}
	
	private String setMyAnswer() {
		return myQuestionBank.fetch("answer", myQuestionID, "MultipleChoice");
	}
	
	@Override
	public String getMyAnswer() {
		return myAnswer;
	}

		
}
