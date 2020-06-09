package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Funcionario extends Pessoa implements Serializable, ITabelavel, Comparable <Pessoa>{
	//DECLARA��O DAS CONSTANTES
	public static final int TAMANHO_MATFUNC = 4;
		
	//DECLARA��O DOS ATRIBUTOS 
	private String matFunc;
	private Set<Emprestimo> listaEmprestimoRealizado;
	
	public Funcionario(String cpf, String nome, String endereco, String telefone, String matFunc) throws DadosException{
		super(cpf, nome, endereco, telefone);
		this.setMatFunc(matFunc);
		this.listaEmprestimoRealizado = new TreeSet<Emprestimo>();
	}

	/**
	 * M�todo que retorna a matricula funcional do Funcionario
	 * @return matFunc
	 */
	public String getMatFunc() {
		return matFunc;
	}

	/**
	 * M�todo inserir/alterar a matricula funcional do Funcionario
	 * @param matFunc
	 */
	public void setMatFunc(String matFunc) throws DadosException{
		validarMatriculaFuncional(matFunc);
		this.matFunc = matFunc;
	}
	
	/**
	 * @RegraDeDominio
	 **/
	public static void validarMatriculaFuncional(String matFunc) throws DadosException{
		if (matFunc == null || matFunc.length() == 0)
			throw new DadosException("A Matricula n�o pode ser nula");
		for (int i = 0; i < matFunc.length(); i++)
			if(!Character.isDigit(matFunc.charAt(i)))
				throw new DadosException("A Matricula s� deve possui digitos!");
		if (matFunc.length() != TAMANHO_MATFUNC)
			throw new DadosException("A Matricula deve ter " + TAMANHO_MATFUNC + "digitos!");
	} 
	
	/**
	 * M�todo que retorna a Lista de Emprestimos Realizados	
	 * @return listaEmprestimoRealizado
	 */
	public Set<Emprestimo> getListaEmprestimoRealizado() {
		return listaEmprestimoRealizado;
	}

	/**
	 * M�todo que insere/altera a Lista de Emprestimos Realizados
	 * @param listaEmprestimoRealizado
	 */
	public void setListaEmprestimoRealizado(Set<Emprestimo> listaEmprestimoRealizado) {
		this.listaEmprestimoRealizado = listaEmprestimoRealizado;
	}

	/**
	 * M�todo que realiza o emprestimo
	 * @param novo
	 */
	public void addEmprestimo(Emprestimo novo){
		//Se a refer�ncia para Emprestimo j� est� presente na lista de Emprestimos do Funcionario, n�o h�
		//necessidade de atuali��o.
		if (this.listaEmprestimoRealizado.contains(novo)){
			 return;
		}
				
		//Solicito ao objeto de cole��o referenciado por this.listaEmprestimoRealizado que adicione
		//uma refer�ncia para a novo emprestimo
		this.listaEmprestimoRealizado.add(novo);
					    
		//Solicito a Emprestimos que referencie o Exemplar
		novo.setFuncionario(this);
	}
	
	/**
	 * M�todo que realiza a devolu��o
	 * @param dev
	 */
	public void removeEmprestimo(Emprestimo dev){
		//Se a refer�ncia para Emprestimo n�o est� presente na lista de Emprestimos do
		//funcionario, n�o h� necessidade de atualiza��o
		if (! this.listaEmprestimoRealizado.contains(dev)){
			return;
		}
				
		//Solicito ao objeto de cole��o referenciado por this.listaEmprestimoRealizado
		//que remova a refer�ncia para Emprestimo
		this.listaEmprestimoRealizado.remove(dev);
						
		//Solicito ao Emprestimo que deixe de referenciar o seu Exemplar
		dev.setFuncionario(null);
	}
	
	/**
	 * M�todo utilizado para colocar os funcion�rios em ordem 
	 */
	public int compareTo(Funcionario f) {
		return this.matFunc.compareTo(f.matFunc);
	}
	
	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
	 * objeto Empregado
	 */
	public String toString() {
		return this.matFunc + " - " + this.getNome();
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * @return Matricula Funcional, Nome, Cpf, Endere�o, Telefone
	 */
	public Object[] getData() {
		return new Object[] { this.getCpf(), this.getNome(), this.getEndereco(), this.getTelefone(), this.matFunc };
	}
	

}
