package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Exemplar implements Serializable, ITabelavel, Comparable <Exemplar>{
	//DECLARA��O DAS CONSTANTES
	public static final int TAMANHO_NUMERO = 6;
	public static final int TAMANHO_SITUACAO = 10;
	
	//DECLARA��O DOS ATRIBUTOS
	private String numero;
	private String situacao;
	private Set<Emprestimo> listaExemplarEmprestimo;
	private Obra obra;
	
	/**
	 * M�todo construtor da classe Exemplar
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
	 * M�todo que retorna o n�mero do Exemplar
	 * @return numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * M�todo que insere/altera o numero do Exemplar
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
			throw new DadosException("O N�mero n�o pode ser nulo");
		for (int i = 0; i < numero.length(); i++)
			if(!Character.isDigit(numero.charAt(i)))
				throw new DadosException("O N�mero s� deve possui digitos!");
		if (numero.length() != TAMANHO_NUMERO)
			throw new DadosException("O ISBN deve ter " + TAMANHO_NUMERO + "digitos!");
	} 
		
	/**
	 * M�todo que retorna a situa��o do Exemplar
	 * @return situacao
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * M�todo que insere/altera a situa��o do Exemplar
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
			throw new DadosException("A Situ��o n�o pode ser nula!");
			
		if(situcao.length() != TAMANHO_SITUACAO)
			throw new DadosException("A Descri��o deve ter " + TAMANHO_SITUACAO + " caracteres!");		
	}
	
	/**
	 * M�todo que retorna lista de Exemplares do Empr�stimo
	 * @return listaExemplarEmprestimo
	 */
	public Set<Emprestimo> getListaExemplarEmprestimo() {
		return listaExemplarEmprestimo;
	}

	/**
	 * M�todo que insere/altera lista de Exemplares do Empr�stimo
	 * @param listaExemplarEmprestimo
	 */
	public void setListaExemplarEmprestimo(Set<Emprestimo> listaExemplarEmprestimo) {
		this.listaExemplarEmprestimo = listaExemplarEmprestimo;
	}

	/**
	 * M�todo que retorna a qual obra pertence o Exemplar
	 * @return obra
	 */
	public Obra getObra() {
		return obra;
	}

	/**
	 * M�todo que insere/altera a obra pertence o Exemplar
	 * @param obra
	 */
	public void setObra(Obra obra) {
		//Se a refer�ncia para Obra � a mesma que est� 
		//sendo recebida pelo par�metro, n�o h� necessidade de atualiza��o 
		if (this.obra == obra)
			return;
		//Se o par�metro � null, isto indica que o exemplar deve ser retirada da Obra
		if (obra == null){
			Obra antigo = this.obra;
			//Apago a refer�ncia antiga
			this.obra = null;
			//Solicito a Editora para retirar sua refer�ncia para a Obra
			antigo.removeExemplar(this);	
		}
		else{
				//Se a exemplar j� pertence a uma Obra, vou solicitar sua remo��o
				//para coloc�-lo em outra Obra
				if (this.obra != null){
					this.obra.removeExemplar(this);
				}
				//Estabele�o a referencia
				this.obra = obra;
				//Solicito ao Editora para adicionar uma refer�ncia para Obra
				obra.addExemplar(this);
			}
	}
	
	/**
	 * M�todo que adiciona um emprestimo ao aluno
	 * @param novo
	 */
	public void addEmprestimo(Emprestimo novo){
		//Se a refer�ncia para Obra j� est� presente na lista de Obra do Autor, n�o h�
		//necessidade de atuali��o.
		if (this.listaExemplarEmprestimo.contains(novo)){
			 return;
		}
		
		//Solicito ao objeto de cole��o referenciado por this.listaExemplarEmprestimo que adicione
		//uma refer�ncia para a novo emprestimo
		this.listaExemplarEmprestimo.add(novo);
			    
		//Solicito a Emprestimos que referencie o Exemplar
		novo.setExemplar(this);
	}
	
	/**
	 * M�todo que realiza a devolu��o do emprestimo
	 * @param dev
	 */
	public void removeEmprestimo(Emprestimo dev){
		//Se a refer�ncia para Empestimo n�o est� presente na lista de Emprestimos do
		//Exemplar, n�o h� necessidade de atualiza��o
		if (! this.listaExemplarEmprestimo.contains(dev)){
			return;
		}
		
		//Solicito ao objeto de cole��o referenciado por this.listaExemplarEmprestimo
		//que remova a refer�ncia para Emprestimo
		this.listaExemplarEmprestimo.remove(dev);
				
		//Solicito ao Emprestimo que deixe de referenciar o seu Exemplar
		dev.setExemplar(null);
	}
	
	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
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
	 * M�todo utilizado para colocar os empregados em ordem
	 */
	public int compareTo(Exemplar exemplar) {
		return this.numero.compareTo(exemplar.numero);
	}	
}
