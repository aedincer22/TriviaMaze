/*
 * Question Class
 * Version Spring 2021
 * 06/06/2021
 */
package Question;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test for QuestionBank.
 * 
 * @author Ruchik Chaudhari, Shirwa Ahmed, and Yongzhao Ye
 * @version Spring 2021
 */
public class QuestionBankTest {
	/**
	 * QuestionBank instance to be tested.
	 */
	private static QuestionBank myQB;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		myQB = QuestionBank.getInstance();
	}

	/**
	 * Test method for {@link question.QuestionBank#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotEquals(myQB, null);
	}

	/**
	 * Test method for {@link question.QuestionBank#getMultipleChoiceQuestions()}.
	 */
	@Test
	public void testGetMultipleChoiceQuestions() {
		// we should have read 25 questions from the database.
		final int expectedQuestions = 25;
		assertEquals(expectedQuestions, myQB.getMultipleChoiceQuestions().size());
	}

	/**
	 * Test method for {@link question.QuestionBank#getMultipleChoiceQuestions()}.
	 */
	@Test
	public void testGetMultipleChoiceQuestions1() {
		// The list must contain multiple choice questions.
		final Question expectedType = myQB.getMultipleChoiceQuestions().get(0);
		final String noOption = "";
		final String trueOrFalse = "True or False";
		assertNotEquals(expectedType.getOptions(), noOption);
		assertNotEquals(expectedType.getOptions(), trueOrFalse);
	}

	/**
	 * Test method for {@link question.QuestionBank#getMultipleChoiceQuestion()}.
	 */
	@Test
	public void testGetMultipleChoiceQuestion() {
		final Question expectedType = myQB.getMultipleChoiceQuestion();
		final String noOption = "";
		final String trueOrFalse = "True or False";
		assertNotEquals(expectedType.getOptions(), noOption);
		assertNotEquals(expectedType.getOptions(), trueOrFalse);
	}

	/**
	 * Test method for {@link question.QuestionBank#getOneWordQuestions()}.
	 */
	@Test
	public void testGetOneWordQuestions() {
		// we should have read 25 questions from the database.
		final int expectedQuestions = 25;
		assertEquals(expectedQuestions, myQB.getOneWordQuestions().size());
	}

	/**
	 * Test method for {@link question.QuestionBank#getOneWordQuestions()}.
	 */
	@Test
	public void testGetOneWordQuestions1() {
		// The list must contain one word questions.
		final Question expectedQuestionType = myQB.getOneWordQuestions().get(0);
		final String noOption = "";
		assertEquals(noOption, expectedQuestionType.getOptions());
	}

	/**
	 * Test method for {@link question.QuestionBank#getOneWordQuestion()}.
	 */
	@Test
	public void testGetOneWordQuestion() {
		final Question expectedQuestionType = myQB.getOneWordQuestion();
		final String noOption = "";
		assertEquals(noOption, expectedQuestionType.getOptions());
	}

	/**
	 * Test method for {@link question.QuestionBank#getTrueOrFalseQuestions()}.
	 */
	@Test
	public void testGetTrueOrFalseQuestions() {
		// we should have read 25 questions from the database.
		final int expectedQuestions = 25;
		assertEquals(expectedQuestions, myQB.getOneWordQuestions().size());
	}

	/**
	 * Test method for {@link question.QuestionBank#getTrueOrFalseQuestions()}.
	 */
	@Test
	public void testGetTrueOrFalseQuestions1() {
		// The question must be True or false question
		final Question q = myQB.getTrueOrFalseQuestions().get(0);
		final String expectedOptions = "True or False";
		assertEquals(q.getOptions(), expectedOptions);
	}

	/**
	 * Test method for {@link question.QuestionBank#getTrueOrFalseQuestion()}.
	 */
	@Test
	public void testGetTrueOrFalseQuestion() {
		final Question q = myQB.getTrueOrFalseQuestion();
		final String expectedOptions = "True or False";
		assertEquals(q.getOptions(), expectedOptions);
	}

}
