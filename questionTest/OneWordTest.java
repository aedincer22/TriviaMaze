package questionTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Question.OneWord;

class OneWordTest {
	OneWord oneWord;

	@Test
	void testGetMyQuestionID() {
		oneWord=new OneWord();
		Assertions.assertEquals(1, oneWord.getMyQuestionID());
		Assertions.assertNotEquals(2., oneWord.getMyQuestionID());
	}

	@Test
	void testGetMyQuestion() {
		oneWord=new OneWord();
		String question="A ____ atom in an atomic clock beats 9,192,631,770 times a second";
		Assertions.assertEquals(question, oneWord.getMyQuestion());
	}

	@Test
	void testGetMyAnswer() {
		oneWord=new OneWord();
		String answer="cesium";
		Assertions.assertEquals(answer, oneWord.getMyAnswer());
	}

	@Test
	void testGetMyOptions() {
		oneWord=new OneWord();
		String option="";
		Assertions.assertEquals(option, oneWord.getMyOptions());
	}

	@Test
	void testOneWord() {
		oneWord=new OneWord();
		int id=1;
		Assertions.assertEquals(1, id);
	}

}
