package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Editora implements Serializable, ITabelavel, Comparable <Editora>{
	//DECLARA��O DAS CONSTANTES
	public static final int TAMANHO_NOME = 15;
	public static final int TAMANHO_CIDADE = 25;
	
	//DECLARA��O DOS ATRIBUTOS
	private String nome;
	private String cidade;
	private Set<Obra> listaEditoraObra;
	
	/**
	 * M�todo construtor da classe Editora
	 * @param nome
	 * @param cidade
	 */
	public Editora(String nome, String cidade) throws DadosException{
		super();
		this.setNome(nome);
		this.setCidade(cidade);
		this.listaEditoraObra = new TreeSet<Obra>();
	}

	/**
	 * M�todo que retorna nome da Editora
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * M�todo que insere/altera nome da Editora
	 * @param nome
	 */
	public void setNome(String nome) throws DadosException {
		validarNome(nome);
		this.nome = nome;
	}

	/**
	 * @RegraDeDominio
	 */
	public static void validarNome(String nome) throws DadosException {
		if(nome == null || nome.length() == 0) 
			throw new DadosException("O Nome n�o pode ser nulo!");
			
		if(nome.length() > TAMANHO_NOME)
			throw new DadosException("O Nome deve ter " + TAMANHO_NOME + " caracteres!");		
	}
	
	/**
	 * M�todo  que retorna a cidade da Editora
	 * @return cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * M�todo que insere/altera cidade da Editora
	 * @param cidade
	 */
	public void setCidade(String cidade) throws DadosException {
		validarCidade(cidade);
		this.cidade = cidade;
	}

	/**
	 * @RegraDeDominio
	 */
	public static void validarCidade(String cidade) throws DadosException {
		if(cidade == null || cidade.length() == 0) 
			throw new DadosException("A Cidade n�o pode ser nula!");
			
		if(cidade.length() > TAMANHO_CIDADE)
			throw new DadosException("A Cidade deve ter " + TAMANHO_CIDADE + " caracteres!");		
	}
	
	/**
	 * M�todo que retorna uma cole��o de obras da Editora
	 * @return listaObra
	 */
	public Set<Obra> getListaEditoraObra() {
		return listaEditoraObra;
	}

	/**
	 * M�todo que insere/altera uma cole��o de obras da Editora
	 * @param listaObra
	 */
	public void setListaEditoraObra(Set<Obra> listaEditoraObra) {
		this.listaEditoraObra = listaEditoraObra;
	}

	/**
	 * M�todo que adiciona uma a obra a Editora
	 * @param publicaObra
	 */
	public void addObra(Obra publicaObra){
		//Se a refer�ncia para Obra j� est� presente na lista de Obra do Editora, n�o h�
		//necessidade de atuali��o.
		if (this.listaEditoraObra.contains(publicaObra)){
			 return;
		}
				
		//Solicito ao objeto de cole��o referenciado por this.listaEditoraObra que adicione
		//uma refer�ncia para a nova obra
		this.listaEditoraObra.add(publicaObra);
					    
		//Solicito a Obra que referencie o sua Editora
		publicaObra.setEditor(this);
	}
	
	/**
	 * M�todo que remove a Obra da Editora
	 * @param obra
	 */
	public void removeObra(Obra obra){
		//Se a refer�ncia para Obra n�o est� presente na lista de Obras de
		//Editora, n�o h� necessidade de atualiza��o
		if (! this.listaEditoraObra.contains(obra)){
			return;
		}
				
		//Solicito ao objeto de cole��o referenciado por this.listaEditoraObra
		//que remova a refer�ncia para Obra
		this.listaEditoraObra.remove(obra);
						
		//Solicito a Obra que deixe de referenciar o seu Autor
		obra.setEditor(null);
	}
	
	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
	 * objeto Empregado
	 */
	public String toString() {
		return this.nome;
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * @return
	 */
	public Object[] getData() {
		return new Object[] { this.nome, this.cidade, this.listaEditoraObra.size()};
	}

	/**
	 * M�todo utilizado para colocar os empregados em ordem
	 */
	public int compareTo(Editora editora) {
		return this.nome.compareTo(editora.nome);
	}	
}
