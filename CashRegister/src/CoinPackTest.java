import static org.junit.Assert.*;

import org.junit.Test;

public class CoinPackTest {
	CoinPack c = new CoinPack(1,1,1,1);
	
	@Test
	public void testEmptCoinPack() {
		CoinPack cp = new CoinPack();
		assertEquals(cp.cents.length,4);
	}

	@Test
	public void testCoinPack() {
		CoinPack cp = new CoinPack(1,1,1,1);
		for(int i=0;i<4;i++){
			assertEquals(c.cents[i],cp.cents[i]);
		}
		assertEquals(cp.cents.length,4);
	}

	@Test
	public void testGetters() {
		CoinPack cp = new CoinPack(1,1,1,1);
		assertEquals(cp.pennies(),cp.cents[0]);
		assertEquals(cp.nickles(),cp.cents[1]);
		assertEquals(cp.dimes(),cp.cents[2]);
		assertEquals(cp.quarters(),cp.cents[3]);
	}

	@Test
	public void testSetters() {
		CoinPack cp = new CoinPack(0,0,0,0);
		assertEquals(cp.pennies(1),true);
		assertEquals(cp.nickles(1),true);
		assertEquals(cp.dimes(1),true);
		assertEquals(cp.quarters(1),true);
	}

	@Test
	public void testNegSetters(){
		long n=-1;
		CoinPack cp = new CoinPack(0,0,0,0);
		assertFalse(cp.pennies(n));
		assertFalse(cp.nickles(n));
		assertFalse(cp.dimes(n));
		assertFalse(cp.quarters(n));

	}

	@Test(expected=IllegalArgumentException.class)
	public void testNegCoinPack1(){
		CoinPack cp = new CoinPack(-1,1,1,1);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNegCoinPack2(){
	CoinPack cp2 = new CoinPack(1,-1,1,1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testNegCoinPack3(){
	CoinPack cp3 = new CoinPack(1,1,-1,1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testNegCoinPack4(){
	CoinPack cp4 = new CoinPack(1,1,1,-1);
	}
}
