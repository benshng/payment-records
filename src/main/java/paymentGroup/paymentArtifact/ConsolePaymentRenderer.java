package paymentGroup.paymentArtifact;

import java.math.BigDecimal;

/**
 * 
 * To display the payment records on console once per minute
 *
 */
public class ConsolePaymentRenderer implements PaymentRenderer {
	private PaymentReader dataProvider;
	
	@Override
	public void output() {
		this.dataProvider.getPaymentRecords().getRecords()
			.forEach((currencyCode, amount) -> {
				if (amount.compareTo(BigDecimal.ZERO) != 0)
					System.out.println(currencyCode + " " + amount);
				});
	}

	@Override
	public void setDataProvider(PaymentReader provider) {
		this.dataProvider = provider;
	}

	@Override
	public PaymentReader getDataProvider() {
		return this.dataProvider;
	}

}
