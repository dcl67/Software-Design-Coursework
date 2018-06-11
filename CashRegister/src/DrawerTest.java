import static org.junit.Assert.*;

import org.junit.Test;

public class DrawerTest {
	
	@Test
	public void testDrawerParams() {
		Drawer myDrawer = new Drawer(1,1,1,1,1,1,1,1,1,1);
		for(int i=0; i<6;i++) {
			assertEquals(myDrawer.billPack().bills[i],1);
		}
		for(int i=0; i<3;i++) {
			assertEquals(myDrawer.coinPack().cents[i],1);
		}
	}

	@Test
	public void testDrawer() {
		Drawer myDrawer=new Drawer();
		assertEquals(myDrawer.coinPack().cents.length, 4);
		assertEquals(myDrawer.billPack().bills.length, 6);
	}

	@Test
	public void testDrawerCoinPackBillPack() {
		CoinPack coinp = new CoinPack(1,1,1,1);
		BillPack billp = new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		assertEquals(myDrawer.coinPack().cents.length,4);
		assertEquals(myDrawer.billPack().bills.length,6);
	}

	@Test
	public void testDrawerTotalInCents() {
		double expected=0.41;
		CoinPack coinp = new CoinPack(1,1,1,1);
		BillPack billp = new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		//assertEquals(myDrawer.drawerTotalInCents(), expected, 0.02);
		assertEquals(myDrawer.penny(),1);
		assertEquals(myDrawer.nickle(),1);
		assertEquals(myDrawer.dime(),1);
		assertEquals(myDrawer.quarter(),1);
		assertEquals(myDrawer.one(),1);
		assertEquals(myDrawer.five(),1);
		assertEquals(myDrawer.ten(),1);
		assertEquals(myDrawer.twenty(),1);
		assertEquals(myDrawer.fifty(),1);
		assertEquals(myDrawer.hundred(),1);

	}

	@Test
	public void testCoinDeposit() {
		double expected=18682;
		CoinPack coinp = new CoinPack(1,1,1,1);
		BillPack billp = new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
	    myDrawer.depositChange(1,1,1,1);
	    assertEquals(myDrawer.penny(),2);
	    assertEquals(myDrawer.nickle(),2);
	    assertEquals(myDrawer.dime(),2);
	    assertEquals(myDrawer.quarter(),2);
	    assertEquals(expected, myDrawer.drawerTotalInCents(), 0.1);
	}

	@Test
	public void testDepositChangeCoinPack() {
		double expected=18682;
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		CoinPack cp = new CoinPack(1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.depositChange(cp);
	    assertEquals(myDrawer.penny(),2);
	    assertEquals(myDrawer.nickle(),2);
	    assertEquals(myDrawer.dime(),2);
	    assertEquals(myDrawer.quarter(),2);
	    assertEquals(expected, myDrawer.drawerTotalInCents(),0.1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testDepositNegChange1() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.depositChange(1,1,1,-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDepositNegChange2() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.depositChange(1,1,-1,1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDepositNegChange3() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.depositChange(1,-1,1,1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDepositNegChange4() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.depositChange(-1,1,1,1);
	}
	
	@Test
	public void testDepositBills() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.depositBills(1,1,1,1,1,1);
	    assertEquals(myDrawer.one(),2);
	    assertEquals(myDrawer.five(),2);
	    assertEquals(myDrawer.ten(),2);
	    assertEquals(myDrawer.twenty(),2);
	    assertEquals(myDrawer.fifty(),2);
	    assertEquals(myDrawer.hundred(),2);
	    //assertEquals(myDrawer.totalCentValue(),372);
	}
	
	@Test
	public void testDepositChangeBillPack() {
		double expected=0.82;
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		BillPack bp = new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.depositBills(bp);
	    assertEquals(myDrawer.one(),2);
	    assertEquals(myDrawer.five(),2);
	    assertEquals(myDrawer.ten(),2);
	    assertEquals(myDrawer.twenty(),2);
	    assertEquals(myDrawer.fifty(),2);
	    assertEquals(myDrawer.hundred(),2);
	    //assertEquals(myDrawer.totalCentValue(),372);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDepositNegBills1() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.depositBills(1,1,1,1,1,-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDepositNegBills2() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.depositBills(1,1,1,1,-1,1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDepositNegBills3() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.depositBills(1,1,1,-1,1,1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDepositNegBills4() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.depositBills(1,1,-1,1,1,1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDepositNegBills5() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.depositBills(1,-1,1,1,1,1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDepositNegBills6() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.depositBills(-1,1,1,1,1,1);
	}

	@Test
	public void testRemoveChangeCoinPack() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		myDrawer.removeChange(1,1,1,1);
	    assertEquals(myDrawer.penny(),0);
	    assertEquals(myDrawer.nickle(),0);
	    assertEquals(myDrawer.dime(),0);
	    assertEquals(myDrawer.quarter(),0);
	}
	
	@Test
	public void testRemoveCoin1() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		assertFalse(myDrawer.removeChange(1,1,1,2));
	}
	
	@Test
	public void testRemoveCoin2() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		assertFalse(myDrawer.removeChange(1,1,2,1));
	}
	
	@Test
	public void testRemoveCoin3() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		assertFalse(myDrawer.removeChange(1,2,1,1));
	}
	
	@Test
	public void testRemoveCoin4() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		assertFalse(myDrawer.removeChange(2,1,1,1));
	}
	
	@Test
	public void testRemoveCoinPackChange() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		CoinPack removeCoins=new CoinPack(1,1,1,1);
		myDrawer.removeChange(removeCoins);
	    assertEquals(myDrawer.penny(),0);
	    assertEquals(myDrawer.nickle(),0);
	    assertEquals(myDrawer.dime(),0);
	    assertEquals(myDrawer.quarter(),0);
	}
	
	@Test
	public void testRemoveNegBill1() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		assertFalse(myDrawer.removeBills(1,1,1,1,1,2));
	}
	
	@Test
	public void testRemoveNegBill2() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		assertFalse(myDrawer.removeBills(1,1,1,1,2,1));
	}
	
	@Test
	public void testRemoveNegBill3() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		assertFalse(myDrawer.removeBills(1,1,1,2,1,1));
	}
	
	@Test
	public void testRemoveNegBill4() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		assertFalse(myDrawer.removeBills(1,1,2,1,1,1));
	}
	
	@Test
	public void testRemoveNegBill5() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		assertFalse(myDrawer.removeBills(1,2,1,1,1,1));
	}
	
	@Test
	public void testRemoveNegBill6() {
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		assertFalse(myDrawer.removeBills(2,1,1,1,1,1));
	}
	
	@Test
	public void testRemoveBill(){
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(2,2,2,2,2,2);
		Drawer myDrawer=new Drawer(coinp,billp);
		assertTrue(myDrawer.removeBills(1,1,1,1,1,1));
		assertEquals(myDrawer.one(),1);
		assertEquals(myDrawer.five(),1);
		assertEquals(myDrawer.ten(),1);
		assertEquals(myDrawer.twenty(),1);
		assertEquals(myDrawer.fifty(),1);
		assertEquals(myDrawer.hundred(),1);

	}

	@Test
	public void testRemoveBillPack(){
		CoinPack coinp=new CoinPack(1,1,1,1);
		BillPack billp=new BillPack(2,2,2,2,2,2);
		BillPack bp=new BillPack(1,1,1,1,1,1);
		Drawer myDrawer=new Drawer(coinp,billp);
		assertTrue(myDrawer.removeBills(bp));
	}
}
