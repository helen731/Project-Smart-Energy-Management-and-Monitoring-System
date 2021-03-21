import static org.junit.Assert.*;
import org.junit.Test;

public class CompanyTest {

	@Test
	public void test() {
		Company c = new Company();
		try {
			c.readFromFile("2018.4.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.setGasTariff(1.0);
		try {
			c.saveToFile("2018.4.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("1.0",c.getGasTariff());
		
		String[] his = {"UserId  GasMeter  ElectricityMeter  GasBill  ElectricityBill",
				        "0001       0078           0063                    78.00       1.89",
				        "0002       0007           0041                     7.00       1.23",
				        "3568       0359           0356                   359.00      10.68"};
		assertArrayEquals(his,c.getHistory("2018.4"));
	}

}
