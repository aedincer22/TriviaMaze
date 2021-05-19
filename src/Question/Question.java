package Question;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import Utility.Database;
public abstract class Question {
	
	
	//Example on how to use the Question class
	public static void main(String [] args) {
		int choice = 0;
		
		
		for (int i = 0; i < 16; i++) {
			Random rand = new Random();
			choice = rand.nextInt(3);
			
			//Based on some conditions we need to create different type of questions
			Question question;
			if (choice ==  0) {
				question = Question.create("MultipleChoice");
			} else if (choice == 1) {
				question = Question.create("TrueOrFalse");
			} else {
				question = Question.create("OneWord");
			}
			System.out.println(question.getMyQuestion());
			System.out.println(question.getMyOptions());
			System.out.println(question.getMyAnswer());
			System.out.println("_______________");
		}
		Question.close();
		
	}
	
	public static Question create(final String theQuestionType) {
		

		final Question question;
		if (theQuestionType.equals("MultipleChoice")) {
			question = new MultipleChoice();
		} else if (theQuestionType.equals("TrueOrFalse")) {
			question = new TrueOrFalse();
		} else if (theQuestionType.equals("OneWord")){
			question = new OneWord();
		} else {
			throw new IllegalArgumentException("The type of question asked is invalid");
		}
		return question;
	}
	public static void close() {
		Database.close();
	}
	public abstract int getMyQuestionID();
	
	public abstract String getMyQuestion();
	
	public abstract String getMyAnswer();
	
	public abstract String getMyOptions();
	

	
}
