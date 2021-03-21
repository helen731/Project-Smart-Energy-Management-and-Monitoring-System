import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author zhaoyixuan 
 * @author zhanhan
 *
 */
public class Company {
	public static MultiValueMap<String, String> stringMultiValueMap = new LinkedMultiValueMap<>();

	/**
	 * The method to read data from file
	 * @param fileName The file name
	 * @throws Exception Exception of IOread
	 */
	public void readFromFile(String fileName) throws Exception {
		try {
			stringMultiValueMap = new LinkedMultiValueMap<>();
			File file = new File(fileName);
			InputStream input = new FileInputStream(file);
			byte[] b = new byte[(int) file.length()];
			input.read(b);
			String str = new String(b);
			String[] split = str.split(" ");
			for (int i = 0; i < (split.length / 7); i++) {
				int tempNo = Integer.parseInt(split[i * 7]);
				for (int j = 0; j < 6; j++) {
					stringMultiValueMap.add(String.format("%4d", tempNo).replace(" ", "0"), split[i * 7 + j + 1]);//牛逼的补零方法
				}
			}
			input.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * The method to save data to file
	 * @param filename File name
	 * @throws Exception Exception of IOread
	 */
	public void saveToFile(String filename) throws Exception {
		File wfile = new File(filename);
		try {
			if (!wfile.exists())
				wfile.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

		InputStream input = new FileInputStream(wfile);
		byte[] b = new byte[(int) wfile.length()];
		input.read(b);
		String str = new String(b);
		String[] split = str.split(" ");
		for (int i = 0; i < (split.length / 7); i++) {
			int tempNo = Integer.parseInt(split[i * 7]);

			//stringMultiValueMap.add("000"+tempNo, split[i*7+j+1]);//愚蠢又暴力的补零方法
			stringMultiValueMap.setValue(String.format("%4d", tempNo).replace(" ", "0"), 0, split[i * 7 + 1]);
			stringMultiValueMap.setValue(String.format("%4d", tempNo).replace(" ", "0"), 1, split[i * 7 + 2]);

		}
		input.close();

		FileOutputStream output = new FileOutputStream(wfile, false);
		String space = " ";
		byte[] s = space.getBytes();
		Set<String> keySet = stringMultiValueMap.keySet();
		for (String key : keySet) {
			byte[] k = key.getBytes();
			output.write(k);
			output.write(s);
			List<String> values = stringMultiValueMap.getValues(key);
			for (String value : values) {
				byte[] v = value.getBytes();
				output.write(v);
				output.write(s);
			}
		}
		output.close();
	}

	/**
	 * The method to save to another new file
	 * @param filename The file name
	 * @throws Exception Exception of IOread
	 */
	public void saveToNewFile(String filename) throws Exception {
		File wfile = new File(filename);
		try {
			if (!wfile.exists())
				wfile.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		FileOutputStream output = new FileOutputStream(wfile, false);
		String space = " ";
		byte[] s = space.getBytes();
		Set<String> keySet = stringMultiValueMap.keySet();
		for (String key : keySet) {
			byte[] k = key.getBytes();
			output.write(k);
			output.write(s);
			List<String> values = stringMultiValueMap.getValues(key);
			for (String value : values) {
				byte[] v = value.getBytes();
				output.write(v);
				output.write(s);
			}
		}
		output.close();
	}

	/**
	 * Returns gas tariff
	 * @return Gas tariff
	 */
	public String getGasTariff() {
		Set<String> keys = stringMultiValueMap.keySet();
		
		String gas = stringMultiValueMap.getValue(keys.iterator().next(), 2);
		return gas;
	}

	/**
	 * Returns element tariff
	 * @return Element tariff
	 */
	public String getElectricityTariff() {
		String id = String.format("%04d", 1);
		String electricity = stringMultiValueMap.getValue(id, 3);
		return electricity;
	}

	/**
	 * Set gas tariff
	 * @param gasTariff Gas tariff
	 */
	public void setGasTariff(double gasTariff) {
//		int n = stringMultiValueMap.size();
//		for (int i = 1; i < n + 1; i++) {
//			String id = String.format("%04d", i);
//			stringMultiValueMap.setValue(id, 2, String.valueOf(gasTariff));
//		}
		Set<String> keys = stringMultiValueMap.keySet();
		for(String key: keys) {
			stringMultiValueMap.setValue(key, 2, String.valueOf(gasTariff));
		}
		
	}

	/**
	 * Set element tariff
	 * @param eleTariff element tariff
	 */
	public void setEleTariff(double eleTariff) {
		Set<String> keys = stringMultiValueMap.keySet();
		for(String key: keys) {
			stringMultiValueMap.setValue(key, 2, String.valueOf(eleTariff));
		}
	}

	/**
	 * The zero user data
	 */
	public void zero() {
		int n = stringMultiValueMap.size();
		for (int i = 1; i < n + 1; i++) {
			String id = String.format("%04d", i);
			stringMultiValueMap.setValue(id, 0, String.format("%04d", 0));
			stringMultiValueMap.setValue(id, 1, "0000");
			stringMultiValueMap.setValue(id, 5, "0");
		}
	}

	/**
	 * @param date The date of history
	 * @return The data of history
	 */
	public String[] getHistory(String date) {
		//读取data.txt文件，建立新map historyMap
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
		int i = 1;
		Set<String> keys = historyMap.keySet();
		for (String key : keys) {

			String gasM = String.format(" %10s", historyMap.getValue(key, 0));
			String eleM = String.format(" %14s", historyMap.getValue(key, 1));
			Double gasBill = Integer.parseInt(historyMap.getValue(key, 0))
					* Double.parseDouble(historyMap.getValue(key, 2));
			Double eleBill = Integer.parseInt(historyMap.getValue(key, 1))
					* Double.parseDouble(historyMap.getValue(key, 3));
			String gasB = String.format(" %24.2f", gasBill);
			String eleB = String.format(" %10.2f", eleBill);
			String oneuser = (key + gasM + eleM + gasB + eleB);
			history[i] = oneuser;
			i++;
		}
		/*			for(int i=1;i<n+1;i++){
						String id = String.format("%04d",i);
						String id2 = String.format("% 6d",i);
						String gasM = String.format(" %10s",historyMap.getValue(id,0));
						String eleM = String.format(" %14s",historyMap.getValue(id,1));
						Double gasBill = Integer.parseInt(historyMap.getValue(id,0)) * Double.parseDouble(historyMap.getValue(id,2));
						Double eleBill = Integer.parseInt(historyMap.getValue(id,1)) * Double.parseDouble(historyMap.getValue(id,3));
						String gasB = String.format(" %24.2f",gasBill);
						String eleB = String.format(" %10.2f",eleBill);
						String oneuser = (id2 + gasM + eleM + gasB + eleB);
						history[i] = oneuser;
					}*/
		return history;
	}

	/**
	 * Remove user
	 * @param id The user ID to remove
	 * @return The status of delete
	 */
	public int removeUserfromMap(int id) {//c成功返回1， 不成功返回0
		if (id < 10000 && id > -1) {//判定是不是四位数能撑下
			if (stringMultiValueMap.containsKey(String.format("%4d", id).replace(" ", "0")) == true) {//判定有没有人用过这个
				stringMultiValueMap.remove(String.format("%4d", id).replace(" ", "0"));
				return 1;
			}
		}
		return 0;
	}

	/**
	 * Add a new user
	 * @return Status of adding
	 */
	public int addUserToMap() {//c成功返回1， 不成功返回0
		Random r = new Random();
		int newKey = 1000 + r.nextInt(9000);
		if (newKey < 10000 && newKey > -1) {//判定是不是四位数能撑下
			if (stringMultiValueMap.containsKey(String.format("%4d", newKey).replace(" ", "0")) == false) {//判定有没有人用过这个
				stringMultiValueMap.add(String.format("%4d", newKey).replace(" ", "0"), "" + 0);
				stringMultiValueMap.add(String.format("%4d", newKey).replace(" ", "0"), "" + 0);
				stringMultiValueMap.add(String.format("%4d", newKey).replace(" ", "0"),
				stringMultiValueMap.getValue("0001", 2));
				stringMultiValueMap.add(String.format("%4d", newKey).replace(" ", "0"),
				stringMultiValueMap.getValue("0001", 3));
				stringMultiValueMap.add(String.format("%4d", newKey).replace(" ", "0"), "" + 0);
				stringMultiValueMap.add(String.format("%4d", newKey).replace(" ", "0"), "" + 0);
				return newKey;
			}
		}
		return 0;
	}

}
