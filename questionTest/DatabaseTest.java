package questionTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Question.Database;

class DatabaseTest {
	
	Database myQuestionBank;

	@Test
	void testGetInstance() {
		Database.getInstance();
		Assertions.assertNotNull(myQuestionBank);
	}

	@Test
	void testFetch() {
		myQuestionBank=Database.getInstance();
		String target="Next leap year is?";
		Assertions.assertEquals(target,myQuestionBank.fetch("question", 1, "MultipleChoice"));
	}

	@Test
	void testIsEntryAvailable() {
		myQuestionBank=Database.getInstance();
		Boolean target=true;
		Assertions.assertEquals(target,myQuestionBank.isEntryAvailable(1, "MultipleChoice"));
	}

}
