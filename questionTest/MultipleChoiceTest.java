package questionTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Question.MultipleChoice;

class MultipleChoiceTest {
	
	MultipleChoice multipleChoice;

	@Test
	void testGetMyQuestionID() {
		multipleChoice=new MultipleChoice();
		Assertions.assertEquals(1, multipleChoice.getMyQuestionID());
		Assertions.assertNotEquals(2., multipleChoice.getMyQuestionID());
	}

	@Test
	void testGetMyQuestion() {
		multipleChoice=new MultipleChoice();
		String question="Next leap year is?";
		Assertions.assertEquals(question, multipleChoice.getMyQuestion());
	}

	@Test
	void testGetMyAnswer() {
		multipleChoice=new MultipleChoice();
		String answer="C";
		Assertions.assertEquals(answer, multipleChoice.getMyAnswer());
	}

	@Test
	void testGetMyOptions() {
		multipleChoice=new MultipleChoice();
		String option="";
		Assertions.assertEquals(option, multipleChoice.getMyOptions());
	}

	@Test
	void testMultipleChoice() {
		multipleChoice=new MultipleChoice();
		int id=1;
		Assertions.assertEquals(1, id);
	}

}
