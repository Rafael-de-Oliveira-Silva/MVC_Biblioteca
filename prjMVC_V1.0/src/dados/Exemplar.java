package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Exemplar implements Serializable, ITabelavel, Comparable <Exemplar>{
	//DECLARAÇÃO DAS CONSTANTES
	public static final int TAMANHO_NUMERO = 6;
	public static final int TAMANHO_SITUACAO = 10;
	
	//DECLARAÇÃO DOS ATRIBUTOS
	private String numero;
	private String situacao;
	private Set<Emprestimo> listaExemplarEmprestimo;
	private Obra obra;
	
	/**
	 * Método construtor da classe Exemplar
	 * @param numero
	 * @param situacao
	 * @param obra
	 */
	public Exemplar (String numero, Obra obra, String situacao) throws DadosException{
		super();
		this.setNumero(numero);
		this.setSituacao(situacao);
		this.setObra(obra);
		this.listaExemplarEmprestimo = new TreeSet<Emprestimo>();
	}

	/**
	 * Método que retorna o número do Exemplar
	 * @return numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Método que insere/altera o numero do Exemplar
	 * @param numero
	 */
	public void setNumero(String numero) throws DadosException{
		validarNumero(numero);
		this.numero = numero;
	}

	/**
	 * @RegraDeDominio
	 **/
	public static void validarNumero(String numero) throws DadosException{
		if (numero == null || numero.length() == 0)
			throw new DadosException("O Número não pode ser nulo");
		for (int i = 0; i < numero.length(); i++)
			if(!Character.isDigit(numero.charAt(i)))
				throw new DadosException("O Número só deve possui digitos!");
		if (numero.length() != TAMANHO_NUMERO)
			throw new DadosException("O ISBN deve ter " + TAMANHO_NUMERO + "digitos!");
	} 
		
	/**
	 * Método que retorna a situação do Exemplar
	 * @return situacao
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * Método que insere/altera a situação do Exemplar
	 * @param situacao
	 */
	public void setSituacao(String situacao) throws DadosException{
		validarSitucao(situacao);
		this.situacao = situacao;
	}

	/**
	 * @RegraDeDominio
	 */
	public static void validarSitucao(String situcao) throws DadosException {
		if(situcao == null || situcao.length() == 0) 
			throw new DadosException("A Situção não pode ser nula!");
			
		if(situcao.length() != TAMANHO_SITUACAO)
			throw new DadosException("A Descrição deve ter " + TAMANHO_SITUACAO + " caracteres!");		
	}
	
	/**
	 * Método que retorna lista de Exemplares do Empréstimo
	 * @return listaExemplarEmprestimo
	 */
	public Set<Emprestimo> getListaExemplarEmprestimo() {
		return listaExemplarEmprestimo;
	}

	/**
	 * Método que insere/altera lista de Exemplares do Empréstimo
	 * @param listaExemplarEmprestimo
	 */
	public void setListaExemplarEmprestimo(Set<Emprestimo> listaExemplarEmprestimo) {
		this.listaExemplarEmprestimo = listaExemplarEmprestimo;
	}

	/**
	 * Método que retorna a qual obra pertence o Exemplar
	 * @return obra
	 */
	public Obra getObra() {
		return obra;
	}

	/**
	 * Método que insere/altera a obra pertence o Exemplar
	 * @param obra
	 */
	public void setObra(Obra obra) {
		//Se a referência para Obra é a mesma que está 
		//sendo recebida pelo parâmetro, não há necessidade de atualização 
		if (this.obra == obra)
			return;
		//Se o parâmetro é null, isto indica que o exemplar deve ser retirada da Obra
		if (obra == null){
			Obra antigo = this.obra;
			//Apago a referência antiga
			this.obra = null;
			//Solicito a Editora para retirar sua referência para a Obra
			antigo.removeExemplar(this);	
		}
		else{
				//Se a exemplar já pertence a uma Obra, vou solicitar sua remoção
				//para colocá-lo em outra Obra
				if (this.obra != null){
					this.obra.removeExemplar(this);
				}
				//Estabeleço a referencia
				this.obra = obra;
				//Solicito ao Editora para adicionar uma referência para Obra
				obra.addExemplar(this);
			}
	}
	
	/**
	 * Método que adiciona um emprestimo ao aluno
	 * @param novo
	 */
	public void addEmprestimo(Emprestimo novo){
		//Se a referência para Obra já está presente na lista de Obra do Autor, não há
		//necessidade de atualição.
		if (this.listaExemplarEmprestimo.contains(novo)){
			 return;
		}
		
		//Solicito ao objeto de coleção referenciado por this.listaExemplarEmprestimo que adicione
		//uma referência para a novo emprestimo
		this.listaExemplarEmprestimo.add(novo);
			    
		//Solicito a Emprestimos que referencie o Exemplar
		novo.setExemplar(this);
	}
	
	/**
	 * Método que realiza a devolução do emprestimo
	 * @param dev
	 */
	public void removeEmprestimo(Emprestimo dev){
		//Se a referência para Empestimo não está presente na lista de Emprestimos do
		//Exemplar, não há necessidade de atualização
		if (! this.listaExemplarEmprestimo.contains(dev)){
			return;
		}
		
		//Solicito ao objeto de coleção referenciado por this.listaExemplarEmprestimo
		//que remova a referência para Emprestimo
		this.listaExemplarEmprestimo.remove(dev);
				
		//Solicito ao Emprestimo que deixe de referenciar o seu Exemplar
		dev.setExemplar(null);
	}
	
	/**
	 * Implementação do método toString que retorna uma String que descreve o
	 * objeto Empregado
	 */
	public String toString() {
		return this.numero + " - " + this.obra;
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * @return
	 */
	public Object[] getData() {
		return new Object[] { this.numero, this.obra, this.situacao };
	}

	/**
	 * Método utilizado para colocar os empregados em ordem
	 */
	public int compareTo(Exemplar exemplar) {
		return this.numero.compareTo(exemplar.numero);
	}	
}
