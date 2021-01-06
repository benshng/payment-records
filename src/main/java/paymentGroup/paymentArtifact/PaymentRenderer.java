package paymentGroup.paymentArtifact;

/**
 * 
 * To display the payment records
 *
 */
public interface PaymentRenderer {
	public void output();
	public void setDataProvider(PaymentReader provider);
	public PaymentReader getDataProvider();
}
