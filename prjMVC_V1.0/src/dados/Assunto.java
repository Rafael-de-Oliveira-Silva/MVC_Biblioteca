package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Assunto implements Serializable, ITabelavel, Comparable <Assunto> {
	//DECLARA��O DAS CONSTANTES
	public static final int TAMANHO_DESCRICAO = 25;
		
	//DECLARA��O DOS ATRIBUTOS
	private String descricao;
	private Set<Obra> listaAssuntoObra;
	
	/**
	 * M�todo construtor da classe Assunto
	 * @param descricao
	 */
	public Assunto(String descricao) throws DadosException{
		super();
		this.setDescricao(descricao);
		this.listaAssuntoObra = new TreeSet<Obra>();
	}

	/**
	 * M�todo que retorna a descri��o do Assunto
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * M�todo que insere/altera a descri��o do Assunto
	 * @param descricao
	 */
	public void setDescricao(String descricao) throws DadosException{
		validarDescricao(descricao);
		this.descricao = descricao;
	}

	/**
	 * @RegraDeDominio
	 */
	public static void validarDescricao(String descricao) throws DadosException {
		if(descricao == null || descricao.length() == 0) 
			throw new DadosException("A Descri��o n�o pode ser nula!");
			
		if(descricao.length() > TAMANHO_DESCRICAO)
			throw new DadosException("A Descri��o deve ter " + TAMANHO_DESCRICAO + " caracteres!");		
	}

	/**
	 * M�todo que retorna a lista de obras do Assunto
	 * @return listaAssuntoObra
	 */
	public Set<Obra> getListaAssuntoObra() {
		return listaAssuntoObra;
	}

	/**
	 * M�todo que insere/altera a liste de obras do assunto
	 * @param listaAssuntoObra
	 */
	public void setListaAssuntoObra(Set<Obra> listaAssuntoObra) {
		this.listaAssuntoObra = listaAssuntoObra;
	}

	/** M�todo que adiciona uma obra
	 * @param assuntoObra
	 */
	public void addObra(Obra assuntoObra){
		if (this.listaAssuntoObra.contains(assuntoObra)){
			 return;
		}
		this.listaAssuntoObra.add(assuntoObra);
		assuntoObra.setAssunto(this);
	}
	
	/**
	 * M�todo que remove uma obra
	 * @param assuntoObra
	 */
	public void removeObra(Obra assuntoObra){
		//Se a refer�ncia para Obra n�o est� presente na lista de Obras do
		//Assunto, n�o h� necessidade de atualiza��o
		if (! this.listaAssuntoObra.contains(assuntoObra)){
			return;
		}
		
		//Solicito ao objeto de cole��o referenciado por this.listaAssuntoObra
		//que remova a refer�ncia para Obra
		this.listaAssuntoObra.remove(assuntoObra);
				
		//Solicito a Obra que deixe de referenciar o seu Autor
		assuntoObra.setAssunto(null);
	}
	
	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
	 * objeto Assunto
	 */
	public String toString() {
		return this.descricao;
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * @return
	 */
	public Object[] getData() {
		return new Object[] {this.descricao, this.listaAssuntoObra.size()};
	}

	/**
	 * M�todo utilizado para colocar os assuntos em ordem
	 */
	public int compareTo(Assunto assunto) {
		return this.descricao.compareTo(assunto.descricao);
	}	
}
