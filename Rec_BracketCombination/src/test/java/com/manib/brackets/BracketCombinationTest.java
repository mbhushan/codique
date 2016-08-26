package test.java.com.manib.brackets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import main.java.com.manib.brackets.BracketCombination;

import org.junit.Assert;
import org.junit.Test;

public class BracketCombinationTest {

	@Test
	public void testBrackets() {
		BracketCombination bc = new BracketCombination();
		List<String> actual = bc.combinations(3);
		String [] strs = {"((()))", "(()())", "(())()", "()(())", "()()()"};
		List<String> expected = new ArrayList<String>();
		
		Collections.addAll(expected, strs);
		
		Assert.assertTrue(actual.size() == expected.size() && new HashSet(actual).equals(new HashSet(expected)));
	}

}
