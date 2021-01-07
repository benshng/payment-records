package paymentGroup.paymentArtifact;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 
 * To display the payment records on console once per minute
 *
 */
public class ConsolePaymentRenderer implements PaymentRenderer {
	private PaymentReader dataProvider;
	
	public void output() {
		synchronized(dataProvider.getPaymentRecords()) {
			for (Map.Entry<String, BigDecimal> entry : this.dataProvider.getPaymentRecords().getRecords().entrySet()) {
				if (entry.getValue().compareTo(BigDecimal.ZERO) != 0) {
					System.out.println(entry.getKey() + " " + entry.getValue());
				}
			}
		}
	}

	public void setDataProvider(PaymentReader provider) {
		this.dataProvider = provider;
	}

	public PaymentReader getDataProvider() {
		return this.dataProvider;
	}

}
