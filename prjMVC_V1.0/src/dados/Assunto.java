package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Assunto implements Serializable, ITabelavel, Comparable <Assunto> {
	//DECLARAÇÃO DAS CONSTANTES
	public static final int TAMANHO_DESCRICAO = 25;
		
	//DECLARAÇÃO DOS ATRIBUTOS
	private String descricao;
	private Set<Obra> listaAssuntoObra;
	
	/**
	 * Método construtor da classe Assunto
	 * @param descricao
	 */
	public Assunto(String descricao) throws DadosException{
		super();
		this.setDescricao(descricao);
		this.listaAssuntoObra = new TreeSet<Obra>();
	}

	/**
	 * Método que retorna a descrição do Assunto
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Método que insere/altera a descrição do Assunto
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
			throw new DadosException("A Descrição não pode ser nula!");
			
		if(descricao.length() > TAMANHO_DESCRICAO)
			throw new DadosException("A Descrição deve ter " + TAMANHO_DESCRICAO + " caracteres!");		
	}

	/**
	 * Método que retorna a lista de obras do Assunto
	 * @return listaAssuntoObra
	 */
	public Set<Obra> getListaAssuntoObra() {
		return listaAssuntoObra;
	}

	/**
	 * Método que insere/altera a liste de obras do assunto
	 * @param listaAssuntoObra
	 */
	public void setListaAssuntoObra(Set<Obra> listaAssuntoObra) {
		this.listaAssuntoObra = listaAssuntoObra;
	}

	/** Método que adiciona uma obra
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
	 * Método que remove uma obra
	 * @param assuntoObra
	 */
	public void removeObra(Obra assuntoObra){
		//Se a referência para Obra não está presente na lista de Obras do
		//Assunto, não há necessidade de atualização
		if (! this.listaAssuntoObra.contains(assuntoObra)){
			return;
		}
		
		//Solicito ao objeto de coleção referenciado por this.listaAssuntoObra
		//que remova a referência para Obra
		this.listaAssuntoObra.remove(assuntoObra);
				
		//Solicito a Obra que deixe de referenciar o seu Autor
		assuntoObra.setAssunto(null);
	}
	
	/**
	 * Implementação do método toString que retorna uma String que descreve o
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
	 * Método utilizado para colocar os assuntos em ordem
	 */
	public int compareTo(Assunto assunto) {
		return this.descricao.compareTo(assunto.descricao);
	}	
}
