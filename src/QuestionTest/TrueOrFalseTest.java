package QuestionTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Question.TrueOrFalse;

class TrueOrFalseTest {
	TrueOrFalse trueOrFalse=new TrueOrFalse();;

	@Test
	void testGetMyQuestionID() {
		//trueOrFalse=new TrueOrFalse();
		Assertions.assertEquals(1, trueOrFalse.getMyQuestionID());
		Assertions.assertNotEquals(2., trueOrFalse.getMyQuestionID());
	}

	@Test
	void testGetMyQuestion() {
		//trueOrFalse=new TrueOrFalse();
		String question="Java is owned by Oracle";
		Assertions.assertEquals(question, trueOrFalse.getMyQuestion());
	}

	@Test
	void testGetMyAnswer() {
		//trueOrFalse=new TrueOrFalse();
		String answer="True";
		Assertions.assertEquals(answer, trueOrFalse.getMyAnswer());
	}

	@Test
	void testGetMyOptions() {
		//trueOrFalse=new TrueOrFalse();
		String option="";
		Assertions.assertEquals(option, trueOrFalse.getMyOptions());
	}

	@Test
	void testTrueOrFalse() {
		//trueOrFalse=new TrueOrFalse();
		int id=1;
		Assertions.assertEquals(1, id);
	}

}
