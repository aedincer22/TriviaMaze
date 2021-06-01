package Question;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;



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
	
	private Database() {
		setup();
		int index = 1;
		MULTIPLECHOICE_QUESTIONS = setList(index, "MultipleChoice", "question");
		MULTIPLECHOICE_OPTIONS = setList(index, "MultipleChoice","option");
		MULTIPLECHOICE_ANSWERS = setList(index, "MultipleChoice","answer");
		ONEWORD_QUESTIONS = setList(index, "OneWord","question");
		ONEWORD_ANSWERS = setList(index, "OneWord","answer");
		TRUEORFALSE_QUESTIONS = setList(index, "TrueOrFalse", "question");
		TRUEORFALSE_ANSWERS = setList(index, "TrueOrFalse", "answer");
		close();
	}
	
	public static Database getInstance() {
		
		if (QUESTIONBANK == null) {
			QUESTIONBANK = new Database();
			return QUESTIONBANK;
		}
		return QUESTIONBANK;
	}
	

	public List<String> getMultipleChoiceQuestions(){
		return MULTIPLECHOICE_QUESTIONS;
	}
	
	public List<String> getMultipleChoiceOptions(){
		return MULTIPLECHOICE_OPTIONS;
	}
	
	public List<String> getMultipleChoiceAnswers(){
		return MULTIPLECHOICE_ANSWERS;
	}
	
	public List<String> getOneWordQuestions(){
		return ONEWORD_QUESTIONS;
	}
	
	public List<String> getOneWordAnwsers(){
		return ONEWORD_ANSWERS;
	}
	
	public List<String> getTrueOrFalseQuestions(){
		return TRUEORFALSE_QUESTIONS;
	}
	
	public List<String> getTrueOrFalseAnswers(){
		return TRUEORFALSE_ANSWERS;
	}
	
	
	private static void addQuestions(final String fileName, String tableName, boolean optionsNeeded) {
		
		int id = 1;
	    try {
	        File myObj = new File(fileName);
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String question = myReader.nextLine();
	          String query;
	          
	          if (optionsNeeded) {
	        	  String options = myReader.nextLine();
	        	  String answer = myReader.nextLine();
	        	  query = String.format("(%d, '%s', '%s', '%s')", id, question, options, answer);
	          } else {
	        	  String answer = myReader.nextLine();
	        	  query = String.format("(%d, '%s', '%s')", id, question, answer);
	          }
	          try {
				DATABASE.executeUpdate("insert into " + tableName + " values" + query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          id++;
	        }
	        
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
	
	private List<String> setList(int id, final String tableName, final String field){
		
		List<String> list = new ArrayList<>();
		while (isEntryAvailable(id, tableName)) {
			list.add(fetch(field, id, tableName));
			id++;
		}
		return list;
	}
	private static void addTables() {
		try {
			DATABASE.executeUpdate("drop table if exists OneWord");
			DATABASE.executeUpdate("create table OneWord (id integer, question string, answer string)");
			DATABASE.executeUpdate("drop table if exists MultipleChoice");
			DATABASE.executeUpdate("create table MultipleChoice (id integer, question string, option string, answer string)");
			DATABASE.executeUpdate("drop table if exists TrueOrFalse");
			DATABASE.executeUpdate("create table TrueOrFalse (id integer, question string, answer string)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	private static Statement createDatabase() {
		Connection connection = null;
		Statement statement = null;
		try {
			//Create the database connection
			connection = DriverManager
					.getConnection("jdbc:sqlite:TriviaMazeQuestion.db");
			statement = connection.createStatement();
			statement.setQueryTimeout(30);
		} catch(SQLException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}		
		return statement;
	}
	
	private static void setup() {
		addTables();
		addQuestions("TrueOrFalse.txt", "TrueOrFalse", false);
		addQuestions("OneWordQA.txt", "OneWord", false);
		addQuestions("MultipleChoice.txt", "MultipleChoice", true);
	}

	public static void close() {
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
	
	
	
	public String fetch(final String field, final int theQuestionID, final String theTable) {
		
	    String result = "";
		try {
			final ResultSet rs = DATABASE.executeQuery
					("select " + field + " from " + theTable + " where id = " + theQuestionID);
			result = rs.getString(field);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean isEntryAvailable(final int theQuestionID, final String theTable) {
		boolean result = true;
		try {
			ResultSet rs = DATABASE.executeQuery("select * from " + theTable + " where id =" + theQuestionID);
			if (rs.next() == false) {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
