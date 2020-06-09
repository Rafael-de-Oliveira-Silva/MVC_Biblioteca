package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Funcionario extends Pessoa implements Serializable, ITabelavel, Comparable <Pessoa>{
	//DECLARAÇÃO DAS CONSTANTES
	public static final int TAMANHO_MATFUNC = 4;
		
	//DECLARAÇÃO DOS ATRIBUTOS 
	private String matFunc;
	private Set<Emprestimo> listaEmprestimoRealizado;
	
	public Funcionario(String cpf, String nome, String endereco, String telefone, String matFunc) throws DadosException{
		super(cpf, nome, endereco, telefone);
		this.setMatFunc(matFunc);
		this.listaEmprestimoRealizado = new TreeSet<Emprestimo>();
	}

	/**
	 * Método que retorna a matricula funcional do Funcionario
	 * @return matFunc
	 */
	public String getMatFunc() {
		return matFunc;
	}

	/**
	 * Método inserir/alterar a matricula funcional do Funcionario
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
			throw new DadosException("A Matricula não pode ser nula");
		for (int i = 0; i < matFunc.length(); i++)
			if(!Character.isDigit(matFunc.charAt(i)))
				throw new DadosException("A Matricula só deve possui digitos!");
		if (matFunc.length() != TAMANHO_MATFUNC)
			throw new DadosException("A Matricula deve ter " + TAMANHO_MATFUNC + "digitos!");
	} 
	
	/**
	 * Método que retorna a Lista de Emprestimos Realizados	
	 * @return listaEmprestimoRealizado
	 */
	public Set<Emprestimo> getListaEmprestimoRealizado() {
		return listaEmprestimoRealizado;
	}

	/**
	 * Método que insere/altera a Lista de Emprestimos Realizados
	 * @param listaEmprestimoRealizado
	 */
	public void setListaEmprestimoRealizado(Set<Emprestimo> listaEmprestimoRealizado) {
		this.listaEmprestimoRealizado = listaEmprestimoRealizado;
	}

	/**
	 * Método que realiza o emprestimo
	 * @param novo
	 */
	public void addEmprestimo(Emprestimo novo){
		//Se a referência para Emprestimo já está presente na lista de Emprestimos do Funcionario, não há
		//necessidade de atualição.
		if (this.listaEmprestimoRealizado.contains(novo)){
			 return;
		}
				
		//Solicito ao objeto de coleção referenciado por this.listaEmprestimoRealizado que adicione
		//uma referência para a novo emprestimo
		this.listaEmprestimoRealizado.add(novo);
					    
		//Solicito a Emprestimos que referencie o Exemplar
		novo.setFuncionario(this);
	}
	
	/**
	 * Método que realiza a devolução
	 * @param dev
	 */
	public void removeEmprestimo(Emprestimo dev){
		//Se a referência para Emprestimo não está presente na lista de Emprestimos do
		//funcionario, não há necessidade de atualização
		if (! this.listaEmprestimoRealizado.contains(dev)){
			return;
		}
				
		//Solicito ao objeto de coleção referenciado por this.listaEmprestimoRealizado
		//que remova a referência para Emprestimo
		this.listaEmprestimoRealizado.remove(dev);
						
		//Solicito ao Emprestimo que deixe de referenciar o seu Exemplar
		dev.setFuncionario(null);
	}
	
	/**
	 * Método utilizado para colocar os funcionários em ordem 
	 */
	public int compareTo(Funcionario f) {
		return this.matFunc.compareTo(f.matFunc);
	}
	
	/**
	 * Implementação do método toString que retorna uma String que descreve o
	 * objeto Empregado
	 */
	public String toString() {
		return this.matFunc + " - " + this.getNome();
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * @return Matricula Funcional, Nome, Cpf, Endereço, Telefone
	 */
	public Object[] getData() {
		return new Object[] { this.getCpf(), this.getNome(), this.getEndereco(), this.getTelefone(), this.matFunc };
	}
	

}
