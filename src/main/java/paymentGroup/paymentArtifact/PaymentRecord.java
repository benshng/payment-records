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
	private Map<String, BigDecimal> records = new ConcurrentHashMap<String, BigDecimal>();
	
	public synchronized void addAmount(final String currencyCode, final BigDecimal amount) {
		if (!this.records.containsKey(currencyCode)) {
			this.records.put(currencyCode, amount);
		} else {
			BigDecimal oldValue = this.records.get(currencyCode);
			BigDecimal newValue = oldValue.add(amount);
			this.records.put(currencyCode, newValue);
		}
	}
	
	public Map<String, BigDecimal> getRecords() {
		return this.records;
	}
}
