import static org.junit.Assert.*;

import org.junit.Test;

public class UserAccountTest {

	@Test
	public void test() {
		UserAccount user = new UserAccount();
		user.setBudget(30);
		assertEquals(30, user.getBudget());
		
		user.setEleMeter(100);
		user.setEleUnitPrice(2.1);
		user.setGasMeter(200);
		user.setGasUnitPrice(1.2);
		assertEquals(450,user.checkCost(),2);
		
		assertEquals(1,user.checkAlert());
		assertEquals("0001       0078           0063                    78.00       1.89",user.getUserHistory("2018.4", "0001"));
	}

}
