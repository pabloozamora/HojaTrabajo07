package tests;
import dictionary.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DictionaryTest {

	@Test
	void translateTest() {
		Dictionary dictionary = new Dictionary();
		dictionary.addWord("hola", "hi", "salut");
		assertEquals("hi, hola ", dictionary.getEntries(1));
		assertEquals("hola", dictionary.translate("salut"));
	}

}
