import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CashRegisterTest {

	@Test
	public void testCashRegister() {
		PennsylvaniaTax pt=new PennsylvaniaTax();
		Drawer myDrawer=new Drawer();
		CashRegister cr = new CashRegister(10,10,10,10,10,1000,10,10,10,100);
		CashRegister d =new CashRegister(myDrawer);
		BillPack bp=new BillPack(10,10,10,10,10,1000);
		CoinPack cp=new CoinPack(10,10,10,100);
		CashRegister cr2=new CashRegister(bp,cp,pt);
		CoinPack noCoins = new CoinPack(0,0,0,0);
		double price=2.00;
		for(int i=0;i<6;i++){
			assertEquals(bp.bills[i],cr.billsInDrawer().bills[i]);
		}
		double change=cr.purchaseItem(price,bp,cp);
		CashRegister cr3=new CashRegister(1,1,1,1,1,1,1,1,1,1,new PennsylvaniaTax());
		CashRegister blank=new CashRegister();
		assertEquals(0,blank.drawerValue(),0.1);
		for(int i=0;i<blank.coinsInDrawer().cents.length;i++){
			assertEquals(blank.coinsInDrawer().cents[i],noCoins.cents[i]);
		}
        double individualchange = cr.purchaseItem(price, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1);
	}

	@Test
	public void testCashRegisterTax() {
        CashRegister cr3=new CashRegister(1,1,1,1,1,1,1,1,1,1, new PennsylvaniaTax());
	}

	@Test
	public void testCashRegisterNoChange(){
		BillPack bp=new BillPack(1,0,0,0,0,0);
		CoinPack cp=new CoinPack(6,0,0,0);
		CashRegister cr=new CashRegister(0,0,0,0,0,0,0,0,0,0);
		double change=cr.purchaseItem(1.0,bp,cp);
		assertEquals(0,change,0.1);
	}
	
	@Test
	public void testCashRegisterScan() {
		CashRegister r = new CashRegister(10,10,10,10,10,1000,10,10,10,100);
		r.scanItem(9.99, "Burger");
	}

	@Test
	public void testCashRegisterPurchase() {
		BillPack bp=new BillPack(1,1,0,0,0,0);
		CoinPack cp=new CoinPack(0,0,0,0);
		CashRegister cr=new CashRegister(10,10,10,10,10,1000,10,10,10,100);
		double refund=cr.purchaseItem(5.0,bp,cp);
	}

	@Test(expected= IllegalStateException.class)
	public void testNegativePurchase() {
        BillPack bp=new BillPack(10, 10, 10, 10, 10, 10);
        CoinPack cp=new CoinPack(1000, 100, 100, 100);

        CashRegister multiItems = new CashRegister(bp, cp);

        List<Double> itemPrices = new ArrayList<>();
        List<String> itemNames = new ArrayList<>();

        itemPrices.add(-10000.00);
        itemNames.add("Car");
        itemPrices.add(24.99);
        itemNames.add("Very Good Chocolate");
        itemPrices.add(0.89);
        itemNames.add("Very Bad Chocolate");


        for(int i = 0; i < itemPrices.size(); ++i)
            multiItems.scanItem(itemPrices.get(i), itemNames.get(i));

        // The bills and coins the user will be using the purchase the above list of 3 items
        BillPack bills = new BillPack(1, 1, 2, 1, 0, 0);
        CoinPack coins = new CoinPack(10, 1, 2, 5);

        // Finalize the purchase and gather the returned change if the purchase is successful
        double multiChange = multiItems.finalizePurchase(bills, coins);
	}
	
	@Test
	public void testFinalizePurchase() {
		BillPack bp=new BillPack(1,0,0,0,0,0);
		CoinPack cp=new CoinPack(6,0,0,0);
		CashRegister cr=new CashRegister(0,0,0,0,0,0,0,0,0,0);
		double change=cr.purchaseItem(1.0, bp, cp);
		cr.finalizePurchase(bp, cp);
		assertEquals(change,0.0,0);
	}
	
	@Test(expected= IllegalStateException.class)
	public void testCashRegisterFinalizeScan() {
		BillPack bp=new BillPack(6,0,0,0,0,0);
		CoinPack cp=new CoinPack(0,0,0,0);
		CashRegister cr=new CashRegister(10,5,5,5,0,0,0,0,0,0);
		double change=cr.purchaseItem(5.0, bp, cp);
		cr.scanItem(19.99, "Big Burger");
		cr.finalizePurchase(bp, cp);
	}

	@Test(expected= IllegalStateException.class)
	public void testInsufficientChange() {
		BillPack bp=new BillPack(1,1,0,0,0,0);
		CoinPack cp=new CoinPack(0,0,0,0);
		CashRegister cr=new CashRegister(0,0,0,0,0,0,0,0,0,0);
		double change=cr.purchaseItem(1.5, bp, cp);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testCashRegisterError(){
		BillPack bp=new BillPack(1,0,0,0,0,0);
		CoinPack cp=new CoinPack(0,0,0,0);
		CashRegister cr = new CashRegister(10,10,10,10,10,1000,10,10,10,100);
		double change=cr.purchaseItem(5.0,bp,cp);
	}
}
