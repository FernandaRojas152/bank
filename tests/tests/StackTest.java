package tests;

import static org.junit.Assert.assertTrue;
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
	
	public void setUpStage2() {
		stack= new IStack<Integer>();
		stack.push(12);
		stack.push(432);
		stack.push(14);
		stack.push(65);
		stack.push(76);
		stack.push(67);
		stack.push(19);
		stack.push(1);
		stack.push(23);
		stack.push(34);
		stack.push(45);
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
		Assertions.assertThrows(EmptyStackException.class, () ->stack.peek(), "Should throw an exception");
	}
	
	@Test
	void testPeekShowsProperly() throws EmptyStackException {
		setUpStage2();
		assertEquals(45,stack.peek(), "Should be 45");
	}
	
	@Test
	void testShowsSize() {
		setUpStage2();
		assertEquals(11,stack.getSize(), "Should be 11");
	}
	
	@Test
	void testisEmpty() throws EmptyStackException {
		setUpStage1();
		assertTrue(stack.isEmpty());
	}
	
	@Test
	void testPushElementinEmptyStack() throws EmptyStackException {
		setUpStage1();
		stack.push(19);
		assertEquals(19,stack.peek(), "Should be 19");
	}
	
	@Test
	void testPushElement() throws EmptyStackException {
		setUpStage2();
		stack.push(14);
		assertEquals(14,stack.peek(), "Should be 14");
	}
}
