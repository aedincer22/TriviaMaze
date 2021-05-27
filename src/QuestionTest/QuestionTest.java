package QuestionTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Question.MultipleChoice;
import Question.OneWord;
import Question.Question;
import Question.TrueOrFalse;

class QuestionTest {
	

	@Test
	void testCreateRandomQuestion() {
		Question question = Question.createRandomQuestion();
		OneWord ex1 = new OneWord();
		TrueOrFalse ex2 = new TrueOrFalse();
		MultipleChoice ex3 = new MultipleChoice();
		Assertions.assertEquals(ex1, Question.createRandomQuestion());
		Assertions.assertEquals(ex2, Question.createRandomQuestion());
		Assertions.assertEquals(ex3, Question.createRandomQuestion());
	}

	@Test
	void testCreateMultipleChoice() {
		MultipleChoice expected = new MultipleChoice();
		Question question=Question.create("MultipleChoice");
		System.out.println(question.getClass());
		Assertions.assertEquals(expected.getClass(), question.getClass());
	}
	
	@Test
	void testOneWord() {
		OneWord expected = new OneWord();
		Question question=Question.create("OneWord");
		System.out.println(question.getClass());
		Assertions.assertEquals(expected.getClass(), question.getClass());
	}
	
	@Test
	void TrueOrFalse() {
		TrueOrFalse expected = new TrueOrFalse();
		Question question=Question.create("TrueOrFalse");
		System.out.println(question.getClass());
		Assertions.assertEquals(expected.getClass(), question.getClass());
	}
}
