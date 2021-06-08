/*
 * QuestionTest Class
 * Version Spring 2021
 * 06/06/2021 
 */
package question;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test for the Question Class.
 * 
 * @author Ruchik Chaudhari, Shirwa Ahmed, and Yongzhao Ye
 * @version Spring 2021
 *
 */
public class QuestionTest {
	/**
	 * Test Question object.
	 */
	private Question question;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		question = new Question("testQuestion", "testOptions", "testAnswer");
	}

	/**
	 * Test method for
	 * {@link question.Question#Question(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testQuestion() {

		assertNotEquals(question.getQuestion(), null);
		assertNotEquals(question.getOptions(), null);
		assertNotEquals(question.getAnswer(), null);
	}

	/**
	 * Test method for {@link question.Question#getQuestion()}.
	 */
	@Test
	public void testGetQuestion() {
		final String expected = "testQuestion";
		assertEquals(expected, question.getQuestion());
	}

	/**
	 * Test method for {@link question.Question#getAnswer()}.
	 */
	@Test
	public void testGetAnswer() {
		final String expected = "testAnswer";
		assertEquals(expected, question.getAnswer());
	}

	/**
	 * Test method for {@link question.Question#getOptions()}.
	 */
	@Test
	public void testGetOptions() {
		final String expected = "testOptions";
		assertEquals(expected, question.getOptions());
	}

	/**
	 * Test method for {@link question.Question#toString()}.
	 */
	@Test
	public void testToString() {
		String expected = "testQuestion\ntestOptions";
		assertEquals(expected, question.toString());
	}

}
