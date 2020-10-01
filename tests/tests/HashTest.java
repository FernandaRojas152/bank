package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import hashtable.HashTable;

class HashTest {
	HashTable<Integer, String> hash;
	
	/** STAGES **/	
	public void setUpStage1() {
		hash= new HashTable<Integer, String>();
	}
	
	public void setUpStage2() {
		hash= new HashTable<Integer, String>();
		hash.insert(2, "Pedro");
		hash.insert(2, "Alejandra");
		hash.insert(5, "Maria");
		hash.insert(1, "Susana");
	}
	
	/** TESTS */
	@Test
	void testSearchWithTheSameKey() {
		setUpStage2();
		assertEquals("Alejandra", hash.get(2), "Should be Alejandra");
	}
	
	@Test
	void testSearch() {
		setUpStage2();
		assertEquals("Susana", hash.get(1), "Should be Susana");
	}
	
	@Test
	void testisEmpty() {
		setUpStage1();
		assertTrue(hash.getSize()== 0);
	}
	
	@Test
	void testSize() {
		setUpStage2();
		assertTrue(hash.getSize()== 4);
	}
	
	@Test
	void testInsert() {
		setUpStage1();
		hash.insert(6, "Amanda");
		assertEquals("Amanda", hash.get(6), "Should be Amanda");
	}
	
	@Test
	void testInsertWithTheSameKey() {
		setUpStage2();
		hash.insert(1, "Amanda");
		assertEquals("Amanda", hash.get(1), "Should be Amanda");
	}
	
	@Test
	void testDelete() {
		setUpStage2();
		hash.delete(1);
		assertTrue(hash.get(1)== null);
	}

}
