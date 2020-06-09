package dados;

import java.io.Serializable;

import controle.ITabelavel;

public class Emprestimo implements Serializable, ITabelavel, Comparable <Emprestimo>{
	//DECLARAÇÃO DAS CONSTANTES
	
	//DECLARAÇÃO DOS ATRIBUTOS
	private String dataEmp;
	private String horaEmp;
	private String devPrevista;
	private String devEfetiva;
	private Exemplar exemplar;
	private Aluno aluno;
	private Funcionario funcionario;
	
	/**
	 * MÉTODO CONSTRUTOR DA CLASSE EMPRÉSTIMO
	 */
	public Emprestimo(String dataEmp, String horaEmp, Exemplar exemplar, Aluno aluno, String devPrevista, Funcionario funcionario, String devEfetiva) throws DadosException{
		super();
		this.setDataEmp(dataEmp);
		this.setHoraEmp(horaEmp);
		this.setDevPrevista(devPrevista);
		this.devEfetiva = devEfetiva;
	    this.setExemplar(exemplar);
	    this.setAluno(aluno);
	    this.setFuncionario(funcionario);
	}

	public String getDataEmp() {
		return dataEmp;
	}

	public void setDataEmp(String dataEmp) throws DadosException{
		validarData(dataEmp);
		this.dataEmp = dataEmp;
	}
	
	@RegraDeDominio
	public static void validarData(String dataEmp) throws DadosException {
		if(dataEmp == null) 
			throw new DadosException("A Data não pode ser nula!");		
	}
	
	public String getHoraEmp() {
		return horaEmp;
	}

	public void setHoraEmp(String horaEmp) throws DadosException {
		validarHora(horaEmp);
		this.horaEmp = horaEmp;
	}

	@RegraDeDominio
	public static void validarHora(String horaEmp) throws DadosException {
		if(horaEmp == null) 
			throw new DadosException("A Hora não pode ser nula!");		
	}
		
	public String getDevPrevista() {
		return devPrevista;
	}

	public void setDevPrevista(String devPrevista) throws DadosException{
		validarDevPrevista(devPrevista);
		this.devPrevista = devPrevista;
	}

    @RegraDeDominio
	public static void validarDevPrevista(String devPrevista) throws DadosException {
		if(devPrevista == null) 
			throw new DadosException("A Data de Devolução não pode ser nula!");		
	}
	
	public String getDevEfetiva() {
		return devEfetiva;
	}

	public void setDevEfetiva(String devEfetiva){
		this.devEfetiva = devEfetiva;
	}
	
	public Exemplar getExemplar() {
		return exemplar;
	}

	public void setExemplar(Exemplar exemplar) {
		//Se a referência para Exemplar é a mesma que está 
		//sendo recebida pelo parâmetro, não há necessidade de atualização 
		if (this.exemplar == exemplar)
			return;
		//Se o parâmetro é null, isto indica que a obra deve ser retirada do Emprestimo
		if (exemplar == null){
			Exemplar antigo = this.exemplar;
			//Apago a referência antiga
			this.exemplar = null;
			//Solicito ao Exemplar para retirar sua referência para a Emprestimo
			antigo.removeEmprestimo(this);
		}
		else{
				//Se a obra já pertence a uma Editora, vou solicitar sua remoção
				//para colocá-lo em outra Editora
				if (this.exemplar != null){
					this.exemplar.removeEmprestimo(this);
				}
				//Estabeleço a referencia
				this.exemplar = exemplar;
				
			    //Solicito ao Editora para adicionar uma referência para Obra
				exemplar.addEmprestimo(this);
				
			}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		//Se a referência para Aluno é a mesma que está 
		//sendo recebida pelo parâmetro, não há necessidade de atualização 
		if (this.aluno == aluno)
			return;
		//Se o parâmetro é null, isto indica que a aluno deve ser retirada do Emprestimo
		if (aluno == null){
			Aluno antigo = this.aluno;
			//Apago a referência antiga
			this.aluno = null;
			//Solicito ao aluno para retirar sua referência para a Emprestimo
			antigo.removeEmprestimo(this);
		}
		else{
				//Se o aluno já pertence a uma emprestimo, vou solicitar sua remoção
				//para colocá-lo em outra emprestimo
				if (this.aluno != null){
					this.aluno.removeEmprestimo(this);
				}
				//Estabeleço a referencia
				this.aluno = aluno;
						
			    //Solicito ao aluno para adicionar uma referência para emprestimo
				aluno.addEmprestimo(this);	
			}	
	}

	/**
	 * Método que retorna o funcionário que realizou o empréstimo
	 * @return func
	 */
	public Funcionario getFuncionario() {
		return funcionario;
	}

	/**
	 * Método que insere/altera o funcionário que realizou o emprestimo
	 * @param func
	 */
	public void setFuncionario(Funcionario funcionario) {
		//Se a referência para Funcionario é a mesma que está 
		//sendo recebida pelo parâmetro, não há necessidade de atualização 
		if (this.funcionario == funcionario)
			return;
			//Se o parâmetro é null, isto indica que o Funcionario deve ser retirado do Emprestimo
			if (funcionario == null){
				Funcionario antigo = this.funcionario;
				//Apago a referência antiga
				this.funcionario = null;
				//Solicito ao Funcionario para retirar sua referência para a Emprestimo
				antigo.removeEmprestimo(this);
			}
			else{
					//Se o Funcionario já pertence a uma emprestimo, vou solicitar sua remoção
					//para colocá-lo em outra emprestimo
					if (this.funcionario != null){
						this.funcionario.removeEmprestimo(this);
					}
					//Estabeleço a referencia
					this.funcionario = funcionario;
								
					//Solicito ao Funcionario para adicionar uma referência para emprestimo
					funcionario.addEmprestimo(this);	
				}
	}
	
	/**
	 * Implementação do método toString que retorna uma String que descreve o
	 * objeto Emprestimo
	 */
	public String toString() {
		return this.exemplar+ "\n - Aluno: " + this.aluno.getNome() + " \n - Devolução Prevista: "  + this.devPrevista + " \n - Devolução Efetiva: "  +this.devEfetiva;
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		return new Object[] { this.dataEmp, this.horaEmp, this.exemplar.getNumero(), this.aluno.getNome(),  this.devPrevista, this.funcionario.getNome(), this.devEfetiva};
	}

	/**
	 * Método utilizado para colocar os Emprestimos em ordem
	 */
	public int compareTo(Emprestimo emprestimo) {
		return this.exemplar.compareTo(emprestimo.exemplar);
	}	
}
