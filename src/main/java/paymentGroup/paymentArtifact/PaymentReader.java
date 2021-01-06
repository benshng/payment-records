package paymentGroup.paymentArtifact;

/**
 * 
 * To read payment records
 *
 */
public interface PaymentReader {
	public void readData(String input);
	public PaymentRecord getPaymentRecords();
}
