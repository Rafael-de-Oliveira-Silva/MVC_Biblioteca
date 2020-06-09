package dados;

import java.io.Serializable;

abstract public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	
	//DECLARAÇÃO DAS CONSTANTES
	public static final int TAMANHO_CPF = 11;
	public static final int TAMANHO_NOME = 40;
	public static final int TAMANHO_ENDERECO = 70;
	public static final int TAMANHO_TELEFONE = 9;
		
	//DECLARAÇÃO DOS ATRIBUTOS
	private String cpf;
	private String nome;
	private String endereco;
	private String telefone;
	
	//CONSTRUTOR DA CLASSE PESSOA
    public Pessoa (String cpf, String nome, String endereco, String telefone) throws DadosException{
    	super();
    	this.setCpf(cpf);
    	this.setNome(nome);
    	this.setEndereco(endereco);
    	this.setTelefone(telefone);
    }

    //GET CPF
	public String getCpf() {
		return cpf;
	}

	// SET CPF
	public void setCpf(String cpf) throws DadosException{
		validarCpf(cpf);
		this.cpf = cpf;
	}

	@RegraDeDominio
	public static void validarCpf(String cpf) throws DadosException{
		if (cpf == null || cpf.length() == 0)
			throw new DadosException("O CPF não pode ser nulo");
		for (int i = 0; i < cpf.length(); i++)
			if(!Character.isDigit(cpf.charAt(i)))
				throw new DadosException("O CPF só deve possui digitos!");
		if (cpf.length() != TAMANHO_CPF)
			throw new DadosException("O CPF deve ter " + TAMANHO_CPF + "digitos!");
	} 
	
	// GET NOME
	public String getNome() {
		return nome;
	}
    
	//SET NOME
	public void setNome(String nome) throws DadosException{
		validarNome(nome);
		this.nome = nome;
	}
	
    @RegraDeDominio
	public static void validarNome(String nome) throws DadosException{
		if (nome == null || nome.length() == 0)
			throw new DadosException("O Nome não pode ser nulo!");
		if (nome.length() > TAMANHO_NOME)
			throw new DadosException("O Nome não deve exceder a " + TAMANHO_NOME + " caracteres!");
	}
	
	//GET ENDEREÇO
	public String getEndereco() {
		return endereco;
	}

	//SET ENDEREÇO
	public void setEndereco(String endereco) throws DadosException{
		validarEndereco(endereco);
		this.endereco = endereco;
	}
     
	@RegraDeDominio
	public static void validarEndereco(String endereco) throws DadosException{
		if (endereco == null || endereco.length() == 0)
			throw new DadosException("O Endereço não pode ser nulo");
		if (endereco.length() > TAMANHO_ENDERECO)
			throw new DadosException("O Endereço não deve exceder a " + TAMANHO_ENDERECO + " caracteres!");
	}
	
	//GET TELEFONE
	public String getTelefone() {
		return telefone;
	}

	//SET TELEFONE
	public void setTelefone(String telefone) throws DadosException{
		validarTelefone(telefone);
		this.telefone = telefone;
	}
    
	@RegraDeDominio
	public static void validarTelefone(String telefone) throws DadosException{
		if (telefone == null || telefone.length() == 0)
			throw new DadosException("O Telefone não pode ser nulo");
		if (telefone.length() > TAMANHO_TELEFONE)
			throw new DadosException("O Telefone dever conter " + TAMANHO_TELEFONE + " caracteres!");
	}
	
	//MÉTODO PARA DESCREVER O OBJETO
	public String toString() {
		return this.cpf + " - " + this.nome;
	}
    
	//MÉTODO PARA COMPARAR OS OBJETOS
	public int compareTo(Pessoa d) {
		return this.cpf.compareTo(d.cpf);
	}
    
}
