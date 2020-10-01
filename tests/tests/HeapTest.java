package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import heap.IHeap;

class HeapTest {
	private IHeap<Integer> heap;
	
	/** STAGES */
	public void setUpStage1() {
		heap= new IHeap<Integer>(100, true);
	}
	
	public void setUpStage2() {
		heap= new IHeap<Integer>(100, true);
		heap.insert(7);
		heap.insert(8);
		heap.insert(6);
		heap.insert(50);
		heap.insert(86);
	}
	
	/** TESTS */
	@Test
	void testisEmpty() {
		setUpStage1();
		assertTrue(heap.isEmpty());
	}
	
	@Test
	void testExtract() {
		setUpStage2();
		assertEquals(86,heap.extract(), "Should be 86");
	}
	
	@Test
	void testExtractAll() {
		setUpStage2();
		heap.extract();
		heap.extract();
		heap.extract();
		heap.extract();
		assertEquals(6,heap.extract(), "Should be 6");
	}
	
	@Test
	void testMax() {
		setUpStage2();
		heap.extract();
		assertEquals(50,heap.max(), "Should be 50");
	}
	
	@Test
	void testInsert() {
		setUpStage1();
		heap.insert(4);
		heap.insert(14);
		heap.insert(12);
		assertEquals(14,heap.max(), "Should be 14");
	}
	
	@Test
	void testHeapSize() {
		setUpStage2();
		assertEquals(5,heap.getHeap_Size(), "Should be 5");
	}

}
