package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Obra implements Serializable, ITabelavel, Comparable<Obra>{
	//DECLARA��O DAS CONSTANTES
	public static final int TAMANHO_TITULO = 50;
	public static final int TAMANHO_ISBN = 13;
	public static final int TAMANHO_ANO = 4;
	
	//DECLARA��O DOS ATRIBUTOS
	private String titulo;
	private String ano;
	private String isbn;
	private Editora editora;
	private Autor autor;
	private Assunto assunto;
	private Set<Exemplar> listaExemplares;
	
	/**
	 * M�todo construtor da classe Obra
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
	 * M�todo que retorna o isbn da Obra
	 * @return isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * M�todo que insere/altera o isbn da Obra
	 * @param isbn
	 */
	public void setIsbn(String isbn) throws DadosException{
		validarIsbn(isbn);
		this.isbn = isbn;
	}
	
	@RegraDeDominio
	public static void validarIsbn(String isbn) throws DadosException{
		if (isbn == null || isbn.length() == 0)
			throw new DadosException("O ISBN n�o pode ser nulo!");
		for (int i = 0; i < isbn.length(); i++)
			if(!Character.isDigit(isbn.charAt(i)))
				throw new DadosException("O ISBN s� deve possui digitos!");
		if (isbn.length() > TAMANHO_ISBN)
			throw new DadosException("O ISBN deve ter " + TAMANHO_ISBN + " digitos!");
	} 
	
	/**
	 * M�todo que retorna o titulo da Obra
	 * @return titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * M�todo que insere/altera o titulo da Obra
	 * @param titulo
	 */
	public void setTitulo(String titulo) throws DadosException{
		validarTitulo(titulo);
		this.titulo = titulo;
	}

	@RegraDeDominio
	public static void validarTitulo(String titulo) throws DadosException {
		if(titulo == null || titulo.length() == 0) 
			throw new DadosException("O T�tulo n�o pode ser nulo!");
			
		if(titulo.length() > TAMANHO_TITULO)
			throw new DadosException("O T�tulo deve ter " + TAMANHO_TITULO + " caracteres!");		
	}
	
	/**
	 * M�todo que recupera o ano da Obra
	 * @return ano
	 */
	public String getAno() {
		return ano;
	}

	/**
	 * M�todo que insere/altera o ano da Obra
	 * @param ano
	 */
	public void setAno(String ano) throws DadosException{
		validarAno(ano);
		this.ano = ano;
	}
	
	@RegraDeDominio
	public static void validarAno(String ano) throws DadosException {
		if(ano == null || ano.length() == 0) 
			throw new DadosException("O Ano n�o pode ser nulo!");
			
		if(ano.length() != TAMANHO_ANO)
			throw new DadosException("O Ano deve ter " + TAMANHO_ANO + " caracteres!");		
	}
			
	/**
	 * M�todo que retorna o editor da Obra
	 * @return editor
	 */
	public Editora getEditor() {
		return editora;
	}

	/**
	 * M�todo que insere/altera o editor da Obra
	 * @param editor
	 */
	public void setEditor(Editora editora) {
		//Se a refer�ncia para Editora � a mesma que est� 
		//sendo recebida pelo par�metro, n�o h� necessidade de atualiza��o 
		if (this.editora == editora)
			return;
		//Se o par�metro � null, isto indica que a obra deve ser retirada da Editora
		if (editora == null){
			Editora antigo = this.editora;
			//Apago a refer�ncia antiga
			this.editora = null;
			//Solicito a Editora para retirar sua refer�ncia para a Obra
			antigo.removeObra(this);	
		}
		else{
			//Se a obra j� pertence a uma Editora, vou solicitar sua remo��o
			//para coloc�-lo em outra Editora
			if (this.editora != null){
				this.editora.removeObra(this);
			}
			//Estabele�o a referencia
			this.editora = editora;
		    //Solicito ao Editora para adicionar uma refer�ncia para Obra
			editora.addObra(this);
		}
	}

	/**
	 * M�todo que retorna o assunto da Obra
	 * @return assunto
	 */
	public Assunto getAssunto() {
		return assunto;
	}

	/**
	 * M�todo que insere/altera o assunto da Obra
	 * @param assunto
	 */
	public void setAssunto(Assunto assunto) {
		//Se a refer�ncia para o assunto � a mesma que est� 
		//sendo recebida pelo par�metro, n�o h� necessidade de atualiza��o 
		if (this.assunto == assunto)
			return;
		//Se o par�metro � null, isto indica que a obra deve ser retirada do assunto
		if (assunto == null){
			Assunto antigo = this.assunto;
			//Apago a refer�ncia antiga
			this.assunto = null;
			//Solicito o Assunto para retirar sua refer�ncia para a Obra
			antigo.removeObra(this);	
		}
		else{
			//Se a obra j� pertence a um Assunto, vou solicitar sua remo��o
			//para coloc�-lo em outra Assunto
			if (this.assunto != null){
				this.assunto.removeObra(this);
			}
			//Estabele�o a referencia
			this.assunto = assunto;
		    //Solicito ao assunto para adicionar uma refer�ncia para Obra
			assunto.addObra(this);
		}
	}
	
	/**
	 * M�todo que retorna o autor da Obra
	 * @return autor
	 */
	public Autor getAutor() {
		return autor;
	}

	/**
	 * M�todo que insere/altera o autor da Obra
	 * @param autor
	 */
	public void setAutor(Autor autor) {
		//Se a refer�ncia para o assunto � a mesma que est� 
		//sendo recebida pelo par�metro, n�o h� necessidade de atualiza��o 
		if (this.autor == autor)
			return;
		//Se o par�metro � null, isto indica que a obra deve ser retirada do assunto
		if (autor == null){
			Autor antigo = this.autor;
			//Apago a refer�ncia antiga
			this.autor = null;
			//Solicito o Assunto para retirar sua refer�ncia para a Obra
			antigo.removeObra(this);	
		}
		else{
			//Se a obra j� pertence a um Assunto, vou solicitar sua remo��o
			//para coloc�-lo em outra Assunto
			if (this.autor != null){
				this.autor.removeObra(this);
			}
			//Estabele�o a referencia
			this.autor = autor;
		    //Solicito ao assunto para adicionar uma refer�ncia para Obra
			autor.addObra(this);
		}
	}
	
	/**
	 * M�todo que retorna a lista de Exemplares da Obra
	 * @return listaExemplarObra
	 */
	public Set<Exemplar> getListaExemplares() {
		return listaExemplares;
	}

	/**
	 * M�todo que insere/altera a lista de Exemplares da Obra
	 * @param listaExemplarObra
	 */
	public void setListaExemplares(Set<Exemplar> listaExemplares) {
		this.listaExemplares = listaExemplares;
	}
	
	/** M�todo que adiciona exemplares da obra
	 * @param exemplar
	 */
	public void addExemplar(Exemplar exemplar){
		//Se a refer�ncia para exemplar j� est� presente na lista de exemplares da Obra, n�o h�
		//necessidade de atuali��o.
		if (this.listaExemplares.contains(exemplar)){
			 return;
		}
										
		//Solicito ao objeto de cole��o referenciado por this.listaObraExemplar que adicione
		//uma refer�ncia para o novo exemplar
		this.listaExemplares.add(exemplar);
											    
		//Solicito ao exemplar que referencie o sua obra
		exemplar.setObra(this);
	}
	
	/**
	 * M�todo que remove um exemplar da obra
	 * @param exemplar
	 */
	public void removeExemplar(Exemplar exemplar){
		//Se a refer�ncia para exemplar n�o est� presente na lista de exemplares da
		//Obra, n�o h� necessidade de atualiza��o
		if (! this.listaExemplares.contains(exemplar)){
			return;
		}
										
		//Solicito ao objeto de cole��o referenciado por this.listaObraExemplar
		//que remova a refer�ncia para o exemplar
		this.listaExemplares.remove(exemplar);
											
		//Solicito ao Exemplar que deixe de referenciar o sua Obra
		exemplar.setObra(null);
	}
		
	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
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
	 * M�todo utilizado para colocar os empregados em ordem
	 */
	public int compareTo(Obra obra) {
		return this.isbn.compareTo(obra.isbn);
	}
}
