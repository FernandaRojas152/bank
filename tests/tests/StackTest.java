package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import customException.EmptyStackException;
import stack.IStack;

class StackTest {
	private IStack<Integer> stack;
	
	/** STAGES **/	
	public void setUpStage1() {
		stack= new IStack<Integer>();
	}
	
	/** TESTS */
	@Test
	void testPopWhenStackIsEmpty() throws EmptyStackException {
		setUpStage1();
		Assertions.assertThrows(EmptyStackException.class, () ->stack.pop(), "Should throw an exception");
	}
	
	@Test
	void testPeekWhenIsEmpty() throws EmptyStackException {
		setUpStage1();
		Assertions.assertThrows(EmptyStackException.class, () ->stack.pop(), "Should throw an exception");
	}
	
	
	

}
