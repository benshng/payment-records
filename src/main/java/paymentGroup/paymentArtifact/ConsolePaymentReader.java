package paymentGroup.paymentArtifact;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.StringTokenizer;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 
 * To read payment records from console input by user, or optionally from 
 * data file on app start up
 *
 */
public class ConsolePaymentReader implements PaymentReader {
	private PaymentRecord payment;
	private static final String FILE_NAME = "paymentRecords.txt";
	
	public ConsolePaymentReader(PaymentRecord payment, boolean loadFileOnStartUp) {
		this.payment = payment;
		
		if (loadFileOnStartUp) {
			loadFile();
		}
	}
	
	@Override
	public void readData(String input) {
		String currencyCode;
		BigDecimal amount;
		StringTokenizer st;
		
		if (!isValidInput(input)) {
			System.out.println("Invalid input value");
			System.out.println();
			
			return;
		}
		
		st = new StringTokenizer(input, " ");
		
		currencyCode = st.nextToken();
		amount = new BigDecimal(st.nextToken());
		
		this.payment.addAmount(currencyCode, amount);
		
		return;
	}
	
	@Override
	public PaymentRecord getPaymentRecords() {
		return this.payment;
	}
	
	private void loadFile() {
		Scanner fileReader;
		StringTokenizer st;
		String currencyCode;
		BigDecimal amount;
		
		try {
			ClassLoader classLoader = this.getClass().getClassLoader();
			fileReader = new Scanner(new File(classLoader.getResource(FILE_NAME).getFile()));
			
			while (fileReader.hasNextLine()) {
				String record = fileReader.nextLine();
				st = new StringTokenizer(record, " ");
				
				currencyCode = st.nextToken();
				amount = new BigDecimal(st.nextToken());
				
				this.payment.addAmount(currencyCode, amount);
			}
			
			fileReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Data file is not found.");
			e.printStackTrace();
		}
	}
	
	private boolean isValidInput(String input) {
		if (input == null || input.length() == 0)
			return false;
		
		StringTokenizer st = new StringTokenizer(input, " ");
		
		if (st.countTokens() != 2)
			return false;
		
		String currencyCode = st.nextToken();
		String amount = st.nextToken();
		
		if (currencyCode.length() != 3 && !currencyCode.chars().allMatch(Character::isLetter))
			return false;
		
		if (!NumberUtils.isCreatable(amount))
			return false;
		
		return true;
	}

}
