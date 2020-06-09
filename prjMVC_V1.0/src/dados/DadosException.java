package dados;

public class DadosException extends Exception {
	public DadosException (String mensagem){
		super("[Exceção] " + mensagem);
	}
}
