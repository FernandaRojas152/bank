package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import binarySearchTree.BinarySearchTree;
import customException.EmptyStackException;

class BSTTest {
	BinarySearchTree<Integer,String> b;
	/** STAGES **/	
	public void setUpStage1() {
		b= new BinarySearchTree<Integer, String>();
	}
	
	public void setUpStage2() throws Exception {
		b= new BinarySearchTree<Integer, String>();
		b.addNode(3, "Poe");
		b.addNode(6, "Akutagawa");
		b.addNode(2, "Conan Doyle");
		b.addNode(1, "Woolf");
	}
	
	/** TESTS 
	 * @throws Exception */
	
	@Test
	void testAdd() throws Exception {
		setUpStage1();
		b.addNode(2, "Rimbaud");
		assertEquals("Rimbaud", b.searchNode(2).getV(), "Should be Rimbaud");
	}
	
	@Test
	void testAdd2() throws Exception {
		setUpStage1();
		b.addNode(2, "Pizarnik");
		b.addNode(1, "Verlaine");
		b.addNode(6, "Edogawa");
		Assertions.assertThrows(Exception.class, () ->b.addNode(1, "Baudelaire"), "Should throw an exception");
	}
	
	@Test
	void testUpdate() throws Exception {
		setUpStage2();
		b.updateNode(1, "Osamu");
		assertEquals("Osamu", b.searchNode(1).getV(), "Should be Osamu");
	}
	
	@Test
	void testSearch() throws Exception {
		setUpStage2();
		assertEquals("Poe", b.searchNode(3).getV(), "Should be Poe");
	}
	
	@Test
	void testDelete() throws Exception {
		setUpStage2();
		try{
			b.deleteNode(2);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	void testWeight() throws Exception {
		setUpStage2();
		assertEquals(4, b.getWeight(), "Should be 4");
		
	}
	
	@Test
	void testHeight() throws Exception {
		setUpStage2();
		assertEquals(3, b.getHeight(), "Should be 3");
	}
	
}
