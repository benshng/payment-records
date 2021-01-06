package paymentGroup.paymentArtifact;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {
	@Mock
	PaymentReader reader;
	
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	
	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}

	@Test
	public void testPaymentOutput() {
		PaymentRecord payment = new PaymentRecord();
		payment.addAmount("USD", BigDecimal.valueOf(2000));
		
		Mockito.when(reader.getPaymentRecords()).thenReturn(payment);
		
		PaymentRenderer pr = new ConsolePaymentRenderer();
		pr.setDataProvider(reader);
		
		pr.output();
		
		assertEquals("USD 2000", outputStreamCaptor.toString().trim());
	}
	
	@Test
	public void testPaymentInput() throws IOException {
		PaymentReader reader = new ConsolePaymentReader(new PaymentRecord(), false);
		reader.readData("HKD 100");
		PaymentRecord records = reader.getPaymentRecords();
		
		assertEquals(BigDecimal.valueOf(100), records.getRecords().get("HKD"));
	}

}
