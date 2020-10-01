package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import queue.IQueue;

class QueueTest {
	private IQueue<Integer> q;
	
	/**STAGES */
	public void setUpStage1() {
		q= new IQueue<Integer>();
	}
	
	public void setUpStage2() {
		q= new IQueue<Integer>();
		q.enqueue(76);
		q.enqueue(54);
		q.enqueue(22);
		q.enqueue(32);
	}
	
	/**TESTS */
	@Test
	void testEmpty() {
		setUpStage1();
		assertTrue(q.isEmpty());
	}
	
	@Test
	void testEnqueue() {
		setUpStage1();
		q.enqueue(14);
		q.enqueue(33);
		assertEquals(14, q.peek().getT(), "Should be 14");
	}
	
	@Test
	void testDequeue() {
		setUpStage2();
		assertEquals(76, q.dequeue().getT(), "Should be 76");
	}
	
	@Test
	void testSize() {
		setUpStage2();
		assertEquals(4, q.size(), "Should be 4");
	}
	
	@Test
	void testRear() {
		setUpStage2();
		assertEquals(76, q.getFront().getT(), "Should be 76");
	}
	
	@Test
	void testFront() {
		setUpStage2();
		assertEquals(32, q.getRear().getT(), "Should be 32");
	}

}
