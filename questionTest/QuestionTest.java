package questionTest;

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
		Question question=Question.createRandomQuestion();
		OneWord ex1=new OneWord();
		TrueOrFalse ex2=new TrueOrFalse();
		MultipleChoice ex3=new MultipleChoice();
		Assertions.assertEquals(ex1, Question.createRandomQuestion());
		Assertions.assertEquals(ex2, Question.createRandomQuestion());
		Assertions.assertEquals(ex3, Question.createRandomQuestion());
	}

	@Test
	void testCreate() {
		MultipleChoice ex=new MultipleChoice();
		Question question=Question.create("MultipleChoice");
		Assertions.assertEquals(ex, question);
	}

}
