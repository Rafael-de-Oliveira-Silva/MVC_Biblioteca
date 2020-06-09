package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Editora implements Serializable, ITabelavel, Comparable <Editora>{
	//DECLARAÇÃO DAS CONSTANTES
	public static final int TAMANHO_NOME = 15;
	public static final int TAMANHO_CIDADE = 25;
	
	//DECLARAÇÃO DOS ATRIBUTOS
	private String nome;
	private String cidade;
	private Set<Obra> listaEditoraObra;
	
	/**
	 * Método construtor da classe Editora
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
	 * Método que retorna nome da Editora
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Método que insere/altera nome da Editora
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
			throw new DadosException("O Nome não pode ser nulo!");
			
		if(nome.length() > TAMANHO_NOME)
			throw new DadosException("O Nome deve ter " + TAMANHO_NOME + " caracteres!");		
	}
	
	/**
	 * Método  que retorna a cidade da Editora
	 * @return cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * Método que insere/altera cidade da Editora
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
			throw new DadosException("A Cidade não pode ser nula!");
			
		if(cidade.length() > TAMANHO_CIDADE)
			throw new DadosException("A Cidade deve ter " + TAMANHO_CIDADE + " caracteres!");		
	}
	
	/**
	 * Método que retorna uma coleção de obras da Editora
	 * @return listaObra
	 */
	public Set<Obra> getListaEditoraObra() {
		return listaEditoraObra;
	}

	/**
	 * Método que insere/altera uma coleção de obras da Editora
	 * @param listaObra
	 */
	public void setListaEditoraObra(Set<Obra> listaEditoraObra) {
		this.listaEditoraObra = listaEditoraObra;
	}

	/**
	 * Método que adiciona uma a obra a Editora
	 * @param publicaObra
	 */
	public void addObra(Obra publicaObra){
		//Se a referência para Obra já está presente na lista de Obra do Editora, não há
		//necessidade de atualição.
		if (this.listaEditoraObra.contains(publicaObra)){
			 return;
		}
				
		//Solicito ao objeto de coleção referenciado por this.listaEditoraObra que adicione
		//uma referência para a nova obra
		this.listaEditoraObra.add(publicaObra);
					    
		//Solicito a Obra que referencie o sua Editora
		publicaObra.setEditor(this);
	}
	
	/**
	 * Método que remove a Obra da Editora
	 * @param obra
	 */
	public void removeObra(Obra obra){
		//Se a referência para Obra não está presente na lista de Obras de
		//Editora, não há necessidade de atualização
		if (! this.listaEditoraObra.contains(obra)){
			return;
		}
				
		//Solicito ao objeto de coleção referenciado por this.listaEditoraObra
		//que remova a referência para Obra
		this.listaEditoraObra.remove(obra);
						
		//Solicito a Obra que deixe de referenciar o seu Autor
		obra.setEditor(null);
	}
	
	/**
	 * Implementação do método toString que retorna uma String que descreve o
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
	 * Método utilizado para colocar os empregados em ordem
	 */
	public int compareTo(Editora editora) {
		return this.nome.compareTo(editora.nome);
	}	
}
