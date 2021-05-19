package Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;



public class Database {

	public static final Statement DATABASE = setup();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		addTables();
		addOneWordQuestions();
		addTrueOrFalseQuestions();
		addMultipleChoiceQuestions();
	}
	
	private static void addOneWordQuestions() {
		int id = 1;
	    try {
	        File myObj = new File("OneWordQA.txt");
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String question = myReader.nextLine();
	          String answer = myReader.nextLine();
	          String query = String.format("(%d, '%s', '%s')", id, question, answer);
	          try {
				DATABASE.executeUpdate("insert into OneWord values" + query);
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
	
	private static void addTrueOrFalseQuestions() {
		int id = 1;
	    try {
	        File myObj = new File("TrueOrFalse.txt");
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String question = myReader.nextLine();
	          String answer = myReader.nextLine();
	          String query = String.format("(%d, '%s', '%s')", id, question, answer);
	          try {
				DATABASE.executeUpdate("insert into TrueOrFalse values" + query);
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
	
	
	private static void addMultipleChoiceQuestions() {
		int id = 1;
	    try {
	        File myObj = new File("MultipleChoice.txt");
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String question = myReader.nextLine();
	          String options = myReader.nextLine();
	          String answer = myReader.nextLine();
	          String query = String.format("(%d, '%s', '%s', '%s')", id, question, options, answer);
	          try {
				DATABASE.executeUpdate("insert into MultipleChoice values" + query);
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
			DATABASE.executeUpdate("create table MultipleChoice (id integer, question string, option string, answer string)");
			DATABASE.executeUpdate("drop table if exists TrueOrFalse");
			DATABASE.executeUpdate("create table TrueOrFalse (id integer, question string, answer string)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	private static Statement setup() {
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
}
