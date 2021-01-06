package paymentGroup.paymentArtifact;

/**
 * 
 * To store the current console value
 *
 */
public class ConsoleInput {
	private volatile String currentInput = "";

	public String getCurrentInput() {
		return currentInput;
	}

	public void setCurrentInput(String currentInput) {
		this.currentInput = currentInput;
	}
}
