package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Aluno extends Pessoa implements Serializable, ITabelavel, Comparable <Pessoa>{
	
	//DECLARA��O DAS CONSTANTES
	public static final int TAMANHO_MATRICULA = 7;
	
	
	//DECLARA��O DOS ATRIBUTOS 
	private String matricula;
	private Set<Emprestimo> listaEmprestimo;
	
	
	public Aluno(String cpf, String nome, String endereco, String telefone, String matricula) throws DadosException{
		super(cpf, nome, endereco, telefone);
		this.setMatricula(matricula);
		this.listaEmprestimo = new TreeSet<Emprestimo>();
	}

	public Set<Emprestimo> getListaEmprestimo() {
		return listaEmprestimo;
	}

	public void setListaEmprestimo(Set<Emprestimo> listaEmprestimo) {
		this.listaEmprestimo = listaEmprestimo;
	}

	/**
	 * M�todo que retorna a matricula do Aluno
	 * @return matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * M�todo que insere/altera a matricula do Aluno
	 * @param matricula
	 */
	public void setMatricula(String matricula) throws DadosException{
		validarMatricula(matricula);
		this.matricula = matricula;
	}
	
	/**
	 * @RegraDeDominio
	 **/
	public static void validarMatricula(String matricula) throws DadosException{
		if (matricula == null || matricula.length() == 0)
			throw new DadosException("A Matricula n�o pode ser nula");
		for (int i = 0; i < matricula.length(); i++)
			if(!Character.isDigit(matricula.charAt(i)))
				throw new DadosException("A Matricula s� deve possui digitos!");
		if (matricula.length() != TAMANHO_MATRICULA)
			throw new DadosException("A Matricula deve ter " + TAMANHO_MATRICULA + "digitos!");
	} 
	
	/**
	 * M�todo que adiciona um emprestimo ao aluno
	 * @param novo
	 */
	public void addEmprestimo(Emprestimo novo){
		//Se a refer�ncia para Emprestimo j� est� presente na lista de Emprestimos do Aluno, n�o h�
		//necessidade de atualiza��o.
		if (this.listaEmprestimo.contains(novo)){
			 return;
		}
						
		//Solicito ao objeto de cole��o referenciado por this.listaEmprestimo que adicione
		//uma refer�ncia para a novo emprestimo
		this.listaEmprestimo.add(novo);
							    
		//Solicito a Emprestimos que referencie o Exemplar
		novo.setAluno(this);
	}
	
	/**
	 * M�todo que realiza a devolu��o do emprestimo
	 * @param dev
	 */
	public void removeEmprestimo(Emprestimo dev){
		//Se a refer�ncia para Emprestimo n�o est� presente na lista de Emprestimos do
		//Aluno, n�o h� necessidade de atualiza��o
		if (! this.listaEmprestimo.contains(dev)){
			return;
		}
						
		//Solicito ao objeto de cole��o referenciado por this.listaEmprestimo
		//que remova a refer�ncia para Emprestimo
		this.listaEmprestimo.remove(dev);
								
		//Solicito ao Emprestimo que deixe de referenciar o seu Aluno
		dev.setAluno(null);
	}
	
	
	/**
	 * M�todo utilizado para colocar os alunos em ordem 
	 */
	public int compareTo(Aluno a) {
		return this.matricula.compareTo(a.matricula);
	}	
	
	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
	 * objeto Aluno
	 */
	public String toString() {
		return this.matricula + " - " + this.getNome();
	}
	
	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * @return Matricula, Nome, Cpf, Endere�o, Telefone
	 */
	public Object[] getData() {
		return new Object[] { this.getCpf(), this.getNome(),  this.getEndereco(), this.getTelefone(), this.matricula };
	}
	
		
}
