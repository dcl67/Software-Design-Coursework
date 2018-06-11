import static org.junit.Assert.*;

import org.junit.Test;

public class BillPackTest {
	BillPack b = new BillPack(1,1,1,1,1,1);
	
	@Test
	public void testEmptBillPack(){
		BillPack bp = new BillPack();
		assertEquals(bp.bills.length,6);
	}
	
	@Test
	public void testBillPack() {
		BillPack bp = new BillPack(1,1,1,1,1,1);
		for(int i=0;i<6;i++){
			assertEquals(b.bills[i],bp.bills[i]);
		}
		assertEquals(bp.bills.length,6);
	}

	@Test
	public void testGetters() {
		BillPack bp = new BillPack(1,1,1,1,1,1);
		assertEquals(bp.ones(),bp.bills[0]);
		assertEquals(bp.fives(),bp.bills[1]);
		assertEquals(bp.tens(),bp.bills[2]);
		assertEquals(bp.twenties(),bp.bills[3]);
		assertEquals(bp.fifties(),bp.bills[4]);
		assertEquals(bp.hundreds(),bp.bills[5]);
	}

	@Test
	public void testSetters() {
		BillPack bp = new BillPack(0,0,0,0,0,0);
		assertEquals(bp.ones(1),true);
		assertEquals(bp.fives(1),true);
		assertEquals(bp.tens(1),true);
		assertEquals(bp.twenties(1),true);
		assertEquals(bp.fifties(1),true);
		assertEquals(bp.hundreds(1),true);
	}
	
	@Test
	public void testNegSetters(){
		long n=-1;
		BillPack bp = new BillPack(0,0,0,0,0,0);
		assertFalse(bp.ones(n));
		assertFalse(bp.fives(n));
		assertFalse(bp.tens(n));
		assertFalse(bp.twenties(n));
		assertFalse(bp.fifties(n));
		assertFalse(bp.hundreds(n));
	}

	

	@Test(expected=IllegalArgumentException.class)
	public void testNegBillPack1(){
		BillPack bp = new BillPack(1,1,1,1,1,-1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNegBillPack2(){
	BillPack bp2 = new BillPack(1,1,1,1,-1,1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testNegBillPack3(){
	BillPack bp3 = new BillPack(1,1,1,-1,1,1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testNegBillPack4(){
	BillPack bp4 = new BillPack(1,1,-1,1,1,1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testNegBillPack5(){
	BillPack bp5 = new BillPack(1,-1,1,1,1,1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testNegBillPack6(){
	BillPack bp6 = new BillPack(-1,1,1,1,1,1);
	}
}
