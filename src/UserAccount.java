import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Set;

/**
 * The class to store and process individual user information
 * @author chenyilin
 * @author wangxudong
 */
public class UserAccount {
	private int accNo;
	private int gasMeter;
	private int eleMeter;
	private double gasUnitPrice;
	private double eleUnitPrice;
	private int budget;
	private int alert;

	/**
	* Returns value of accNo
	* @return accNo
	*/
	public int getAccNo() {
		return accNo;
	}
	/**
	 * Sets new value of accNo
	 * @param accNo The new account No.
	 */
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	/**
	* Returns value of gasMeter
	* @return gas meter
	*/
	public int getGasMeter() {
		return gasMeter;
	}

	/**
	* Sets new value of gasMeter
	* @param gasMeter The new gas meter
	*/
	public void setGasMeter(int gasMeter) {
		this.gasMeter = gasMeter;
	}

	/**
	* Returns value of eleMeter
	* @return element meter
	*/
	public int getEleMeter() {
		return eleMeter;
	}

	/**
	* Sets new value of eleMeter
	* @param eleMeter The new element number
	*/
	public void setEleMeter(int eleMeter) {
		this.eleMeter = eleMeter;
	}

	/**
	* Returns value of gasUnitPrice
	* @return the gas unit price
	*/
	public double getGasUnitPrice() {
		return gasUnitPrice;
	}

	/**
	* Sets new value of gasUnitPrice
	* @param gasUnitPrice The new gas unit price
	*/
	public void setGasUnitPrice(double gasUnitPrice) {
		this.gasUnitPrice = gasUnitPrice;
	}

	/**
	* Returns value of eleUnitPrice
	* @return The element unit price
	*/
	public double getEleUnitPrice() {
		return eleUnitPrice;
	}

	/**
	* Sets new value of eleUnitPrice
	* @param eleUnitPrice The new element unit price
	*/
	public void setEleUnitPrice(double eleUnitPrice) {
		this.eleUnitPrice = eleUnitPrice;
	}

	/**
	* Returns value of budget
	* @return The budget of user
	*/
	public int getBudget() {
		return budget;
	}

	/**
	* Sets new value of budget
	* @param budget The new budget of user
	*/
	public void setBudget(int budget) {
		this.budget = budget;
	}

	/**
	* The method to check the cost of user
	* @return The cost of user
	*/
	public double checkCost() {
		int G = this.getGasMeter();
		int E = this.getEleMeter();
		double GP = this.getGasUnitPrice();
		double EP = this.getEleUnitPrice();
		double currentCost = E * EP + G * GP;
		return currentCost;
	}

	/**
	 * Returns status of alert
	 * @return The status of alert
	 */
	public int getAlert() {
		return alert;
	}

	/**
	 * Set new status of alert
	 * @param alert The new status of alert
	 */
	public void setAlert(int alert) {
		this.alert = alert;
	}


	/**
	 * Check the alert
	 * @return THe status of alert
	 */
	public int checkAlert() {
		if (this.budget < this.gasMeter * this.gasUnitPrice + this.eleMeter * this.eleUnitPrice) {
			this.alert = 1;
		} else {
			this.alert = 0;
		}
		return this.alert;
	}

	/**
	 * @param date The date of history
	 * @param userID The user ID
	 * @return The history of user
	 */
	public String getUserHistory(String date, String userID) {
		MultiValueMap<String, String> historyMap = new LinkedMultiValueMap<>();
		String fileName = date + ".txt";
		try {
			File file = new File(fileName);
			InputStream input = new FileInputStream(file);
			byte[] b = new byte[(int) file.length()];
			input.read(b);
			String str = new String(b);
			String[] split = str.split(" ");
			for (int i = 0; i < (split.length / 7); i++) {
				int tempNo = Integer.parseInt(split[i * 7]);
				for (int j = 0; j < 6; j++) {
					historyMap.add(String.format("%4d", tempNo).replace(" ", "0"), split[i * 7 + j + 1]);
				}
			}
			input.close();
		} catch (Exception e) {
		}

		int n = historyMap.size();
		String[] history = new String[n + 1];
		history[0] = "UserId  GasMeter  ElectricityMeter  GasBill  ElectricityBill";
		String oneuser = null;
		Set<String> keys = historyMap.keySet();
		for (String key : keys) {
			if (key.equals(userID)) {
				String gasM = String.format(" %10s", historyMap.getValue(key, 0));
				String eleM = String.format(" %14s", historyMap.getValue(key, 1));
				Double gasBill = Integer.parseInt(historyMap.getValue(key, 0))
						* Double.parseDouble(historyMap.getValue(key, 2));
				Double eleBill = Integer.parseInt(historyMap.getValue(key, 1))
						* Double.parseDouble(historyMap.getValue(key, 3));
				String gasB = String.format(" %24.2f", gasBill);
				String eleB = String.format(" %10.2f", eleBill);
				oneuser = (key + gasM + eleM + gasB + eleB);
			}
		}
		return oneuser;
	}


	/**
	 * Default UserAccount constructor
	 * @param accNo The user account ID
	 * @param gasMeter The gas meter
	 * @param eleMeter The element number
	 * @param gasUnitPrice The gas unit price
	 * @param eleUnitPrice The element unit price
	 * @param budget The budget
	 * @param alert The status of alert
	 */
	public UserAccount(int accNo, int gasMeter, int eleMeter, double gasUnitPrice, double eleUnitPrice, int budget,
			int alert) {
		super();
		this.accNo = accNo;
		this.gasMeter = gasMeter;
		this.eleMeter = eleMeter;
		this.gasUnitPrice = gasUnitPrice;
		this.eleUnitPrice = eleUnitPrice;
		this.budget = budget;
		this.alert = alert;
	}

	/**
	 * Empty UserAccount constructor
	 */
	public UserAccount() {
		super();
	}

	public String toString() {
		return "Account number: " + String.format("%4d", accNo).replace(" ", "0") + "\n" + "Account Gas Meter: "
				+ String.format("%4d", gasMeter).replace(" ", "0") + "\n" + "Account Electric Meter:"
				+ String.format("%4d", eleMeter).replace(" ", "0") + "\n" + "Gas unit price: " + gasUnitPrice + "\n"
				+ "Ele unit price:" + eleUnitPrice + "\n" + "Budget:" + budget + "\n";
	}

}
