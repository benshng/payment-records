package paymentGroup.paymentArtifact;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * Payment records
 *
 */
public class PaymentRecord {
	private Map<String, BigDecimal> records = new ConcurrentHashMap<>();
	
	public void addAmount(final String currencyCode, final BigDecimal amount) {
		this.records.merge(currencyCode, amount, (oldValue, newValue) -> oldValue.add(newValue));
	}
	
	public Map<String, BigDecimal> getRecords() {
		return this.records;
	}
}
