package paymentGroup.paymentArtifact;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * Payment records project
 *
 */
public class App 
{
	private static final String QUIT = "quit";
	
    public static void main( String[] args )
    {
    	ConfigLoader.loadProperties();
		PaymentRecord payment = new PaymentRecord();
		final PaymentReader provider = new ConsolePaymentReader(payment, ConfigLoader.readDataFileOnStartUp());
		final PaymentRenderer renderer = new ConsolePaymentRenderer();
		renderer.setDataProvider(provider);
		
		ExecutorService es = Executors.newFixedThreadPool(2);
		final ConsoleInput input = new ConsoleInput();
		
		Runnable readInput = new Runnable() {
			
			public void run() {
				Scanner console = new Scanner(System.in);
				
				System.out.println("Please input the currency followed by the amount, e.g. USD 1000");
				System.out.println("Type 'quit' to quit the program");
				System.out.println("");
				
				try {
					while (console.hasNextLine()) {
						input.setCurrentInput(console.nextLine());
						
						if (input.getCurrentInput().equalsIgnoreCase(QUIT)) {
							console.close();
							System.exit(0);
						}
						
						provider.readData(input.getCurrentInput());
					}
				} finally {
					if (console != null)
						console.close();
				}
			}
		};
		
		es.submit(readInput);
		
		Runnable output = new Runnable() {
			
			public void run() {
				while (!input.getCurrentInput().equalsIgnoreCase(QUIT)) {
					try {
						Thread.sleep(ConfigLoader.getOutputIntervalTime());
					} catch (InterruptedException e) {
						System.out.println("InterruptedException occured.");
						e.printStackTrace();
					}
					
					renderer.output();
				}
			}
		};
		
		es.submit(output);
    }
}
