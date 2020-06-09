package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Aluno extends Pessoa implements Serializable, ITabelavel, Comparable <Pessoa>{
	
	//DECLARAÇÃO DAS CONSTANTES
	public static final int TAMANHO_MATRICULA = 7;
	
	
	//DECLARAÇÃO DOS ATRIBUTOS 
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
	 * Método que retorna a matricula do Aluno
	 * @return matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Método que insere/altera a matricula do Aluno
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
			throw new DadosException("A Matricula não pode ser nula");
		for (int i = 0; i < matricula.length(); i++)
			if(!Character.isDigit(matricula.charAt(i)))
				throw new DadosException("A Matricula só deve possui digitos!");
		if (matricula.length() != TAMANHO_MATRICULA)
			throw new DadosException("A Matricula deve ter " + TAMANHO_MATRICULA + "digitos!");
	} 
	
	/**
	 * Método que adiciona um emprestimo ao aluno
	 * @param novo
	 */
	public void addEmprestimo(Emprestimo novo){
		//Se a referência para Emprestimo já está presente na lista de Emprestimos do Aluno, não há
		//necessidade de atualização.
		if (this.listaEmprestimo.contains(novo)){
			 return;
		}
						
		//Solicito ao objeto de coleção referenciado por this.listaEmprestimo que adicione
		//uma referência para a novo emprestimo
		this.listaEmprestimo.add(novo);
							    
		//Solicito a Emprestimos que referencie o Exemplar
		novo.setAluno(this);
	}
	
	/**
	 * Método que realiza a devolução do emprestimo
	 * @param dev
	 */
	public void removeEmprestimo(Emprestimo dev){
		//Se a referência para Emprestimo não está presente na lista de Emprestimos do
		//Aluno, não há necessidade de atualização
		if (! this.listaEmprestimo.contains(dev)){
			return;
		}
						
		//Solicito ao objeto de coleção referenciado por this.listaEmprestimo
		//que remova a referência para Emprestimo
		this.listaEmprestimo.remove(dev);
								
		//Solicito ao Emprestimo que deixe de referenciar o seu Aluno
		dev.setAluno(null);
	}
	
	
	/**
	 * Método utilizado para colocar os alunos em ordem 
	 */
	public int compareTo(Aluno a) {
		return this.matricula.compareTo(a.matricula);
	}	
	
	/**
	 * Implementação do método toString que retorna uma String que descreve o
	 * objeto Aluno
	 */
	public String toString() {
		return this.matricula + " - " + this.getNome();
	}
	
	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * @return Matricula, Nome, Cpf, Endereço, Telefone
	 */
	public Object[] getData() {
		return new Object[] { this.getCpf(), this.getNome(),  this.getEndereco(), this.getTelefone(), this.matricula };
	}
	
		
}
