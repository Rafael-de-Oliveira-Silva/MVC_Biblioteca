package dados;

public class DadosException extends Exception {
	public DadosException (String mensagem){
		super("[Exce��o] " + mensagem);
	}
}
