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
	
	//Test the Database class
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Database db = new Database();
//		
//		System.out.println(db.isEntryAvailable(2,"OneWord"));
//		System.out.println(db.fetch("answer", 2, "MultipleChoice"));
//		//System.out.println(db.fetch("options", 3, "MultipleChoice"));
//		
//
//	}
	
	private Database() {
		setup();
	}
	
	public static Database getInstance() {
		
		if (QUESTIONBANK == null) {
			QUESTIONBANK = new Database();
			return QUESTIONBANK;
		}
		return QUESTIONBANK;
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
	
	private static void addTables() {
		try {
			DATABASE.executeUpdate("drop table if exists OneWord");
			DATABASE.executeUpdate("create table OneWord (id integer, question string, answer string)");
			DATABASE.executeUpdate("drop table if exists MultipleChoice");
			DATABASE.executeUpdate("create table MultipleChoice (id integer, question string, options string, answer string)");
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
