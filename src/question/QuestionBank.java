/*
 * QuestionBank Class
 * Version Spring 2021
 * 06/06/2021
 */
package Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * This class reads questions from three different tables in the database. The
 * three different tables store multiple choice, true or false and one word
 * questions. The questions are read and stored in the arrays, and provided to
 * client as needed.
 * 
 * @author Ruchik Chaudhari, Shirwa Ahmed, and Yongzhao Ye
 * @version Spring 2021
 *
 */
public class QuestionBank {

	/**
	 * The Statement object.
	 */
	private static final Statement STATEMENT = createStatement();
	/**
	 * Intitial id, from of all the questions and then the id starts incrementing.
	 */
	private static final int INITIAL_QUESTION_ID = 1;
	/**
	 * The single instance of the class.
	 */
	private static QuestionBank myQuestionBank;

	/**
	 * List of all the multiple choice questions from the database.
	 */
	private final List<Question> multipleChoiceQuestions;

	/**
	 * List of all the true or false questions from the database.
	 */
	private final List<Question> trueOrFalseQuestions;
	/**
	 * List of all the one word questions from the database.
	 */
	private final List<Question> oneWordQuestions;
	/**
	 * The index which keeps track of the questions in the multiple choice list.
	 */
	private int multipleChoiceIndex = 0;
	/**
	 * The index which keeps track of the questions in the true of false list.
	 */
	private int trueOrFalseIndex = 0;
	/**
	 * The index which keeps track of the questions in the one word list.
	 */
	private int oneWordIndex = 0;

	/**
	 * Private Constructor, which sets up the database.
	 */
	private QuestionBank() {
		setup();
		multipleChoiceQuestions = setList("MultipleChoice");
		trueOrFalseQuestions = setList("TrueOrFalse", "True or False");
		oneWordQuestions = setList("OneWord", "");
		close();
	}

	/**
	 * Returns the unique instance of the Database object.
	 * 
	 * @return
	 */
	public static QuestionBank getInstance() {

		if (myQuestionBank == null) {
			myQuestionBank = new QuestionBank();
			return myQuestionBank;
		}
		return myQuestionBank;
	}

	/**
	 * Sets up the database by adding tables and questions to the tables.
	 */
	private void setup() {
		addTables();
		addQuestions("TrueOrFalse.txt", "TrueOrFalse", false);
		addQuestions("OneWordQA.txt", "OneWord", false);
		addQuestions("MultipleChoice.txt", "MultipleChoice", true);
	}

	/**
	 * Adds three tables in the database. Currently there are three tables to store
	 * one word, true or false and multiple choice questions in separate tables.
	 */
	private void addTables() {
		// create new tables
		try {
			// if a table exists then delete the previous one and make a new one
			STATEMENT.executeUpdate("drop table if exists OneWord");
			STATEMENT.executeUpdate("create table OneWord (id integer, question string, answer string)");
			STATEMENT.executeUpdate("drop table if exists MultipleChoice");
			STATEMENT.executeUpdate(
					"create table MultipleChoice (id integer, question string, options string, answer string)");
			STATEMENT.executeUpdate("drop table if exists TrueOrFalse");
			STATEMENT.executeUpdate("create table TrueOrFalse (id integer, question string, answer string)");
		} catch (final SQLException e) {
			System.err.println("An error occured while creating tables.");
			e.printStackTrace();
		}
	}

	/**
	 * Reads the data from the file name given and transfers it to the table name
	 * given. The methods also takes a boolean value to confirm if the question has
	 * options because each question always has a question and an answer but not all
	 * questions have options.
	 * 
	 * @param fileName
	 * @param tableName
	 * @param optionsNeeded
	 */
	private void addQuestions(final String fileName, String tableName, final boolean optionsNeeded) {

		int questionId = INITIAL_QUESTION_ID;
		try {
			final File myFile = new File(fileName);
			final Scanner myReader = new Scanner(myFile);
			// create a query from the line
			while (myReader.hasNextLine()) {
				final String question = myReader.nextLine();
				final String query;
				// if there are options available
				if (optionsNeeded) {
					// then read the options first
					final String options = myReader.nextLine();
					final String answer = myReader.nextLine();
					query = String.format("(%d, '%s', '%s', '%s')", questionId, question, options, answer);
				} else {
					final String answer = myReader.nextLine();
					query = String.format("(%d, '%s', '%s')", questionId, question, answer);
				}
				insertQuery(tableName, query);
				questionId++;
			}
			myReader.close();
		} catch (final FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insert the entry into the table name given.
	 * 
	 * @param theTableName
	 * @param theQuery
	 */
	private void insertQuery(final String theTableName, final String theQuery) {
		try {
			STATEMENT.executeUpdate("insert into " + theTableName + " values" + theQuery);
		} catch (final SQLException e) {
			System.err.println("An error occured while inserting data into the table.");
			e.printStackTrace();
		}
	}

	/**
	 * Creates a connection with the database.
	 * 
	 * @return Statement
	 */
	private static Statement createStatement() {
		Connection connection = null;
		Statement statement = null;

		try {
			// Create the database connection
			connection = DriverManager.getConnection("jdbc:sqlite:TriviaMazeQuestions.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return statement;
	}

	/**
	 * Reads all the questions from the database from the given table name and
	 * stores it in a list.
	 * 
	 * @param theQuestionID
	 * @param theTableName
	 * @param theFieldName
	 * @return List<String>
	 */
	private List<Question> setList(final String theTableName) {

		final String questionFieldName = "question";
		final String optionsFieldName = "options";
		final String answerFieldName = "answer";
		int questionId = INITIAL_QUESTION_ID;

		final List<Question> list = new ArrayList<>();
		while (isEntryAvailable(questionId, theTableName)) {
			final String question = fetch(questionId, questionFieldName, theTableName);
			final String options = fetch(questionId, optionsFieldName, theTableName);
			final String answer = fetch(questionId, answerFieldName, theTableName);
			final Question multiplChoiceQuestion = new Question(question, options, answer);
			list.add(multiplChoiceQuestion);
			questionId++;
		}
		return list;
	}

	/**
	 * Reads all the questions from the database from the given table name and
	 * stores it in a list.
	 * 
	 * @param theQuestionID
	 * @param theTableName
	 * @param theFieldName
	 * @return List<String>
	 */

	private List<Question> setList(final String theTableName, final String customOptions) {

		final String questionFieldName = "question";
		final String answerFieldName = "answer";
		int questionId = INITIAL_QUESTION_ID;

		final List<Question> list = new ArrayList<>();
		while (isEntryAvailable(questionId, theTableName)) {
			final String question = fetch(questionId, questionFieldName, theTableName);
			final String options = customOptions;
			final String answer = fetch(questionId, answerFieldName, theTableName);
			final Question q = new Question(question, options, answer);
			list.add(q);
			questionId++;
		}
		return list;
	}

	/**
	 * Fetches the entry from the table with the given information.
	 * 
	 * @param field
	 * @param theQuestionID
	 * @param theTable
	 * @return String
	 */
	private String fetch(final int theQuestionID, final String field, final String theTable) {

		String result = "";
		try {
			final ResultSet rs = STATEMENT
					.executeQuery("select " + field + " from " + theTable + " where id = " + theQuestionID);
			result = rs.getString(field);
		} catch (final SQLException e) {
			System.err.println("An error occured while fetcing the entry from the database.");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Checks if the entry with the question id exists in the table or not
	 * 
	 * @param theQuestionID
	 * @param theTable
	 * @return boolean
	 */
	private boolean isEntryAvailable(final int theQuestionID, final String theTable) {
		boolean result = true;
		try {
			final ResultSet rs = STATEMENT.executeQuery("select * from " + theTable + " where id =" + theQuestionID);
			if (rs.next() == false) {
				result = false;
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Closes the connection with the database.
	 */
	private void close() {
		Connection connection = null;
		try {
			connection = STATEMENT.getConnection();
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @return Return a copy of the list of multiple choice questions.
	 */
	public List<Question> getMultipleChoiceQuestions() {
		return new ArrayList<Question>(multipleChoiceQuestions);
	}

	/**
	 * @return A multiple choice question.
	 */
	public Question getMultipleChoiceQuestion() {
		if (multipleChoiceIndex >= multipleChoiceQuestions.size()) {
			multipleChoiceIndex = 0;
		}
		return multipleChoiceQuestions.get(multipleChoiceIndex++);
	}

	/**
	 * @return A list of one word questions.
	 */
	public List<Question> getOneWordQuestions() {
		return new ArrayList<Question>(oneWordQuestions);
	}

	/**
	 * @return A one word question.
	 */
	public Question getOneWordQuestion() {
		if (oneWordIndex >= oneWordQuestions.size()) {
			oneWordIndex = 0;
		}
		return oneWordQuestions.get(oneWordIndex++);
	}

	/**
	 * @return Return a copy of the list of true or false questions.
	 */
	public List<Question> getTrueOrFalseQuestions() {
		return new ArrayList<Question>(trueOrFalseQuestions);
	}

	/**
	 * @return true or false question.
	 */
	public Question getTrueOrFalseQuestion() {
		if (trueOrFalseIndex >= trueOrFalseQuestions.size()) {
			trueOrFalseIndex = 0;
		}
		return trueOrFalseQuestions.get(trueOrFalseIndex++);
	}

}
