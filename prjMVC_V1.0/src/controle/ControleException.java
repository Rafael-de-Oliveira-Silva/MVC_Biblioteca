package controle;

public class ControleException extends Exception {
	public ControleException(String msg) {
		super("[Exceção] " + msg);
	}
}
