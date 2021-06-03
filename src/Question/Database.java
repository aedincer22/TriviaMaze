//test
//sh
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
 * Database for the Questions.
 * @author Ruchik Chaudhari
 *
 */
public class Database {

	private static final Statement DATABASE = createDatabase();
	private static Database QUESTIONBANK;
	private static List<String>MULTIPLECHOICE_QUESTIONS;
	private static List<String>MULTIPLECHOICE_OPTIONS;
	private static List<String>MULTIPLECHOICE_ANSWERS;
	private static List<String>ONEWORD_QUESTIONS;
	private static List<String>ONEWORD_ANSWERS;
	private static List<String>TRUEORFALSE_QUESTIONS;
	private static List<String>TRUEORFALSE_ANSWERS;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database db = new Database();
		System.out.println(db.getOneWordQuestions());
		System.out.println(db.getOneWordAnwsers());
		System.out.println(db.getTrueOrFalseQuestions());
		System.out.println(db.getTrueOrFalseAnswers());
		//System.out.println(MULTIPLECHOICE_QUESTIONS);

	}


	/**
	 * Private Constructor
	 */
	private Database() {
		setup();
		final int initialQuestionID = 1;
		MULTIPLECHOICE_QUESTIONS = setList(initialQuestionID, "MultipleChoice", "question");
		MULTIPLECHOICE_OPTIONS = setList(initialQuestionID, "MultipleChoice","option");
		MULTIPLECHOICE_ANSWERS = setList(initialQuestionID, "MultipleChoice","answer");
		ONEWORD_QUESTIONS = setList(initialQuestionID, "OneWord","question");
		ONEWORD_ANSWERS = setList(initialQuestionID, "OneWord","answer");
		TRUEORFALSE_QUESTIONS = setList(initialQuestionID, "TrueOrFalse", "question");
		TRUEORFALSE_ANSWERS = setList(initialQuestionID, "TrueOrFalse", "answer");
		close();
	}

	/**
	 * Returns the instance of the single Database object.
	 * @return
	 */
	public static Database getInstance() {

		if (QUESTIONBANK == null) {
			QUESTIONBANK = new Database();
			return QUESTIONBANK;
		}
		return QUESTIONBANK;
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
	 * Adds three tables in the database. Currently there are three tables to 
	 * store one word, true or false and multiple choice questions in separate tables.
	 */
	private void addTables() {
		//create new tables 
		try {
			//if a table exists then delete the previous one and make a new one
			DATABASE.executeUpdate("drop table if exists OneWord");
			DATABASE.executeUpdate("create table OneWord (id integer, question string, answer string)");
			DATABASE.executeUpdate("drop table if exists MultipleChoice");
			DATABASE.executeUpdate("create table MultipleChoice (id integer, question string, option string, answer string)");
			DATABASE.executeUpdate("drop table if exists TrueOrFalse");
			DATABASE.executeUpdate("create table TrueOrFalse (id integer, question string, answer string)");
		} catch (final SQLException e) {
			System.err.println("An error occured while creating tables.");
			e.printStackTrace();
		}
	}

	/**
	 * Reads the data from the file name given and transfers it to the 
	 * table name given. The methods also takes a boolean value which 
	 * to confirm if the question has options because each question always
	 * has a question and an answer but not all questions have options. 
	 * @param fileName
	 * @param tableName
	 * @param optionsNeeded
	 */
	private void addQuestions(final String fileName, String tableName, final boolean optionsNeeded) {

		int questionId = 1;
		try {
			final File myFile = new File(fileName);
			final Scanner myReader = new Scanner(myFile);
			//create a query from the line
			while (myReader.hasNextLine()) {
				final String question = myReader.nextLine();
				final String query; 
				//if there are options available
				if (optionsNeeded) {
					//then read the options first
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
	 * @param theTableName
	 * @param theQuery
	 */
	private void insertQuery(final String theTableName, final String theQuery) {
		try {
			DATABASE.executeUpdate("insert into " + theTableName + " values" + theQuery);
		} catch (final SQLException e) {
			System.err.println("An error occured while inserting data into the table.");
			e.printStackTrace();
		}
	}

	/**
	 * Creates a connection with the database.
	 * @return
	 */
	private static Statement createDatabase() {
		Connection connection = null;
		Statement statement = null;
		try {
			//Create the database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:TriviaMazeQuestions.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}		
		return statement;
	}

	/**
	 * Reads all the column value from the given 
	 * table name and field into a list.
	 * @param theQuestionID
	 * @param theTableName
	 * @param theFieldName
	 * @return List<String>
	 */
	private List<String> setList(int theQuestionID, final String theTableName, final String theFieldName){

		final List<String> list = new ArrayList<>();
		while (isEntryAvailable(theQuestionID, theTableName)) {
			list.add(fetch(theFieldName, theQuestionID, theTableName));
			theQuestionID++;
		}
		return list;
	}

	/**
	 * Fetches the entry from the table with the given information.
	 * @param field
	 * @param theQuestionID
	 * @param theTable
	 * @return String
	 */
	private String fetch(final String field, final int theQuestionID, final String theTable) {

		String result = "";
		try {
			final ResultSet rs = DATABASE.executeQuery
					("select " + field + " from " + theTable + " where id = " + theQuestionID);
			result = rs.getString(field);
		} catch (final SQLException e) {
			System.err.println("An error occured while fetcing the entry from the database.");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Checks if the entry with the question id exists in the table or not
	 * @param theQuestionID
	 * @param theTable
	 * @return boolean
	 */
	private boolean isEntryAvailable(final int theQuestionID, final String theTable) {
		boolean result = true;
		try {
			final ResultSet rs = DATABASE.executeQuery("select * from " + theTable + " where id =" + theQuestionID);
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
			connection = DATABASE.getConnection();
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e){
			System.err.println(e.getMessage());
		}
	}

	/**
	 * @return Return a copy of the list of multiple choice questions.
	 */
	public List<String> getMultipleChoiceQuestions(){
		return new ArrayList<String>(MULTIPLECHOICE_QUESTIONS);
	}

	/**
	 * @return Return a copy of the list of multiple choice options.
	 */
	public List<String> getMultipleChoiceOptions(){
		return new ArrayList<String>(MULTIPLECHOICE_OPTIONS);
	}

	/**
	 * @return Return a copy of the list of multiple choice answers.
	 */
	public List<String> getMultipleChoiceAnswers(){
		return new ArrayList<String>(MULTIPLECHOICE_ANSWERS);
	}

	/**
	 * @return Return a copy of the list of one word questions.
	 */
	public List<String> getOneWordQuestions(){
		return new ArrayList<String>(ONEWORD_QUESTIONS);
	}

	/**
	 * @return Return a copy of the list of one word answers.
	 */
	public List<String> getOneWordAnwsers(){
		return new ArrayList<String>(ONEWORD_ANSWERS);
	}

	/**
	 * @return Return a copy of the list of true or false questions.
	 */
	public List<String> getTrueOrFalseQuestions(){
		return new ArrayList<String>(TRUEORFALSE_QUESTIONS);
	}

	/**
	 * @return Return a copy of the list of true or false answers.
	 */
	public List<String> getTrueOrFalseAnswers(){
		return new ArrayList<String>(TRUEORFALSE_ANSWERS);
	}




}
