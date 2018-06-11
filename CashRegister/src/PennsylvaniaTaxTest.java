import static org.junit.Assert.*;

import org.junit.Test;

public class PennsylvaniaTaxTest {
    //private ITax taxAlgo;

	@Test
	public void testCalculateTax() {
		PennsylvaniaTax pt = new PennsylvaniaTax();
        long notebookPrice = 10;
        double expected = 10;
		assertEquals(expected,(double)pt.applyTaxToPurchase(notebookPrice),0);

	}
}
