/*
 * QuestionFactoryTest Class
 * Version Spring 2021
 * 06/07/2021
 */
package question;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for QuestionFactory
 *
 * @author Ruchik Chaudhari, Shirwa Ahmed, and Yongzhao Ye
 * @version Spring 2021
 */
public class QuestionFactoryTest {

	/**
	 * Test method for {@link question.QuestionFactory#createRandomQuestion()}.
	 */
	@Test
	public void testCreateRandomQuestion() {
		final Question toBeTested = QuestionFactory.createRandomQuestion();
		final int randomSelection = QuestionFactory.getRandomSelection();
		System.out.println(randomSelection);

		// if a multiple choice question.
		if (randomSelection == 0) {

			// multiple choice questions should have some options
			final String noOptions = "";
			assertNotEquals(toBeTested.getOptions(), noOptions);
			// multiple choice questions should have option other than "True or False"
			final String trueOrFalse = "True or False";
			assertNotEquals(toBeTested.getOptions(), trueOrFalse);
		} else if (randomSelection == 1) {// if a true or false question.

			// true or false questions should have only "True or False" options
			final String trueOrFalse = "True or False";
			assertEquals(toBeTested.getOptions(), trueOrFalse);
		} else if (randomSelection == 2) {// if a one word question.
			// one word questions should have no options
			final String noOptions = "";
			assertEquals(toBeTested.getOptions(), noOptions);
		}
	}

	/**
	 * Test method for {@link question.QuestionFactory#create(java.lang.String)}.
	 */
	@Test
	public void testCreateMultipleChoice() {
		final String type = "MultipleChoice";
		final String noOption = "";
		final String trueOrFalse = "True or False";
		final Question question = QuestionFactory.create(type);
		assertNotEquals(noOption, question.getOptions());
		assertNotEquals(trueOrFalse, question.getOptions());
	}

	/**
	 * Test method for {@link question.QuestionFactory#create(java.lang.String)}.
	 */
	@Test
	public void testCreateTrueOrFalse() {
		final String type = "TrueOrFalse";
		final String trueOrFalse = "True or False";
		final Question question = QuestionFactory.create(type);
		assertEquals(trueOrFalse, question.getOptions());
	}

	/**
	 * Test method for {@link question.QuestionFactory#create(java.lang.String)}.
	 */
	@Test
	public void testCreateOneWord() {
		final String type = "OneWord";
		final String noOption = "";
		final Question question = QuestionFactory.create(type);
		assertEquals(noOption, question.getOptions());
	}

	/**
	 * Test method for {@link question.QuestionFactory#create(java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCreateException() {
		final String type = "One";
		final Question question = QuestionFactory.create(type);
	}

}
