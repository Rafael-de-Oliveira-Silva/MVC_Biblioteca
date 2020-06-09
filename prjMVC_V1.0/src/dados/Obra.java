package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Obra implements Serializable, ITabelavel, Comparable<Obra>{
	//DECLARAÇÃO DAS CONSTANTES
	public static final int TAMANHO_TITULO = 50;
	public static final int TAMANHO_ISBN = 13;
	public static final int TAMANHO_ANO = 4;
	
	//DECLARAÇÃO DOS ATRIBUTOS
	private String titulo;
	private String ano;
	private String isbn;
	private Editora editora;
	private Autor autor;
	private Assunto assunto;
	private Set<Exemplar> listaExemplares;
	
	/**
	 * Método construtor da classe Obra
	 * @param isbn
	 * @param titulo
	 * @param ano
	 * @param editora
	 * @param assunto
	 * @param autor
	 */
	public Obra(String isbn, String titulo, String ano, Editora editora, Assunto assunto, Autor autor) throws DadosException{
		super();
		this.setIsbn(isbn);
		this.setTitulo(titulo);
		this.setAno(ano);
		this.setEditor(editora);
		this.setAssunto(assunto);
		this.setAutor(autor);
		this.listaExemplares = new TreeSet<Exemplar>();
	}

	/**
	 * Método que retorna o isbn da Obra
	 * @return isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * Método que insere/altera o isbn da Obra
	 * @param isbn
	 */
	public void setIsbn(String isbn) throws DadosException{
		validarIsbn(isbn);
		this.isbn = isbn;
	}
	
	@RegraDeDominio
	public static void validarIsbn(String isbn) throws DadosException{
		if (isbn == null || isbn.length() == 0)
			throw new DadosException("O ISBN não pode ser nulo!");
		for (int i = 0; i < isbn.length(); i++)
			if(!Character.isDigit(isbn.charAt(i)))
				throw new DadosException("O ISBN só deve possui digitos!");
		if (isbn.length() > TAMANHO_ISBN)
			throw new DadosException("O ISBN deve ter " + TAMANHO_ISBN + " digitos!");
	} 
	
	/**
	 * Método que retorna o titulo da Obra
	 * @return titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Método que insere/altera o titulo da Obra
	 * @param titulo
	 */
	public void setTitulo(String titulo) throws DadosException{
		validarTitulo(titulo);
		this.titulo = titulo;
	}

	@RegraDeDominio
	public static void validarTitulo(String titulo) throws DadosException {
		if(titulo == null || titulo.length() == 0) 
			throw new DadosException("O Título não pode ser nulo!");
			
		if(titulo.length() > TAMANHO_TITULO)
			throw new DadosException("O Título deve ter " + TAMANHO_TITULO + " caracteres!");		
	}
	
	/**
	 * Método que recupera o ano da Obra
	 * @return ano
	 */
	public String getAno() {
		return ano;
	}

	/**
	 * Método que insere/altera o ano da Obra
	 * @param ano
	 */
	public void setAno(String ano) throws DadosException{
		validarAno(ano);
		this.ano = ano;
	}
	
	@RegraDeDominio
	public static void validarAno(String ano) throws DadosException {
		if(ano == null || ano.length() == 0) 
			throw new DadosException("O Ano não pode ser nulo!");
			
		if(ano.length() != TAMANHO_ANO)
			throw new DadosException("O Ano deve ter " + TAMANHO_ANO + " caracteres!");		
	}
			
	/**
	 * Método que retorna o editor da Obra
	 * @return editor
	 */
	public Editora getEditor() {
		return editora;
	}

	/**
	 * Método que insere/altera o editor da Obra
	 * @param editor
	 */
	public void setEditor(Editora editora) {
		//Se a referência para Editora é a mesma que está 
		//sendo recebida pelo parâmetro, não há necessidade de atualização 
		if (this.editora == editora)
			return;
		//Se o parâmetro é null, isto indica que a obra deve ser retirada da Editora
		if (editora == null){
			Editora antigo = this.editora;
			//Apago a referência antiga
			this.editora = null;
			//Solicito a Editora para retirar sua referência para a Obra
			antigo.removeObra(this);	
		}
		else{
			//Se a obra já pertence a uma Editora, vou solicitar sua remoção
			//para colocá-lo em outra Editora
			if (this.editora != null){
				this.editora.removeObra(this);
			}
			//Estabeleço a referencia
			this.editora = editora;
		    //Solicito ao Editora para adicionar uma referência para Obra
			editora.addObra(this);
		}
	}

	/**
	 * Método que retorna o assunto da Obra
	 * @return assunto
	 */
	public Assunto getAssunto() {
		return assunto;
	}

	/**
	 * Método que insere/altera o assunto da Obra
	 * @param assunto
	 */
	public void setAssunto(Assunto assunto) {
		//Se a referência para o assunto é a mesma que está 
		//sendo recebida pelo parâmetro, não há necessidade de atualização 
		if (this.assunto == assunto)
			return;
		//Se o parâmetro é null, isto indica que a obra deve ser retirada do assunto
		if (assunto == null){
			Assunto antigo = this.assunto;
			//Apago a referência antiga
			this.assunto = null;
			//Solicito o Assunto para retirar sua referência para a Obra
			antigo.removeObra(this);	
		}
		else{
			//Se a obra já pertence a um Assunto, vou solicitar sua remoção
			//para colocá-lo em outra Assunto
			if (this.assunto != null){
				this.assunto.removeObra(this);
			}
			//Estabeleço a referencia
			this.assunto = assunto;
		    //Solicito ao assunto para adicionar uma referência para Obra
			assunto.addObra(this);
		}
	}
	
	/**
	 * Método que retorna o autor da Obra
	 * @return autor
	 */
	public Autor getAutor() {
		return autor;
	}

	/**
	 * Método que insere/altera o autor da Obra
	 * @param autor
	 */
	public void setAutor(Autor autor) {
		//Se a referência para o assunto é a mesma que está 
		//sendo recebida pelo parâmetro, não há necessidade de atualização 
		if (this.autor == autor)
			return;
		//Se o parâmetro é null, isto indica que a obra deve ser retirada do assunto
		if (autor == null){
			Autor antigo = this.autor;
			//Apago a referência antiga
			this.autor = null;
			//Solicito o Assunto para retirar sua referência para a Obra
			antigo.removeObra(this);	
		}
		else{
			//Se a obra já pertence a um Assunto, vou solicitar sua remoção
			//para colocá-lo em outra Assunto
			if (this.autor != null){
				this.autor.removeObra(this);
			}
			//Estabeleço a referencia
			this.autor = autor;
		    //Solicito ao assunto para adicionar uma referência para Obra
			autor.addObra(this);
		}
	}
	
	/**
	 * Método que retorna a lista de Exemplares da Obra
	 * @return listaExemplarObra
	 */
	public Set<Exemplar> getListaExemplares() {
		return listaExemplares;
	}

	/**
	 * Método que insere/altera a lista de Exemplares da Obra
	 * @param listaExemplarObra
	 */
	public void setListaExemplares(Set<Exemplar> listaExemplares) {
		this.listaExemplares = listaExemplares;
	}
	
	/** Método que adiciona exemplares da obra
	 * @param exemplar
	 */
	public void addExemplar(Exemplar exemplar){
		//Se a referência para exemplar já está presente na lista de exemplares da Obra, não há
		//necessidade de atualição.
		if (this.listaExemplares.contains(exemplar)){
			 return;
		}
										
		//Solicito ao objeto de coleção referenciado por this.listaObraExemplar que adicione
		//uma referência para o novo exemplar
		this.listaExemplares.add(exemplar);
											    
		//Solicito ao exemplar que referencie o sua obra
		exemplar.setObra(this);
	}
	
	/**
	 * Método que remove um exemplar da obra
	 * @param exemplar
	 */
	public void removeExemplar(Exemplar exemplar){
		//Se a referência para exemplar não está presente na lista de exemplares da
		//Obra, não há necessidade de atualização
		if (! this.listaExemplares.contains(exemplar)){
			return;
		}
										
		//Solicito ao objeto de coleção referenciado por this.listaObraExemplar
		//que remova a referência para o exemplar
		this.listaExemplares.remove(exemplar);
											
		//Solicito ao Exemplar que deixe de referenciar o sua Obra
		exemplar.setObra(null);
	}
		
	/**
	 * Implementação do método toString que retorna uma String que descreve o
	 * objeto Empregado
	 */
	public String toString() {
		return this.autor.getNome()+ " - " +this.titulo+ " - "  +this.editora.getNome()+ " - " +this.ano;
	}
	
	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * @return
	 */
	public Object[] getData() {
		return new Object[] { this.isbn, this.titulo, this.ano, this.editora.getNome(), this.assunto.getDescricao(), this.autor.getNome(), this.listaExemplares.size()};
	}

	/**
	 * Método utilizado para colocar os empregados em ordem
	 */
	public int compareTo(Obra obra) {
		return this.isbn.compareTo(obra.isbn);
	}
}
