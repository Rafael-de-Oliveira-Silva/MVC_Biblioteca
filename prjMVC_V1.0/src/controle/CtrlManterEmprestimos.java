package controle;

import janelas.IViewerCadastroEmprestimos;
import janelas.IViewerEmprestimo;
import janelas.JanelaCadastroEmprestimos;
import janelas.JanelaEmprestimo;
import janelas.JanelaExcluirEmprestimo;
import dados.Aluno;
import dados.DAOAluno;
import dados.DAOEmprestimo;
import dados.DAOExemplar;
import dados.DAOFuncionario;
import dados.DadosException;
import dados.Emprestimo;
import dados.Exemplar;
import dados.Funcionario;
import dados.IDAO;

public class CtrlManterEmprestimos implements ICtrlManterEmprestimos {
	//
	// ATRIBUTOS
	//
	private enum Operacao {
		INCLUSAO, EXCLUSAO, ALTERACAO, DISPONIVEL;
	}
	
	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private ICtrlPrograma       			ctrlPrg;
	
	/**
	 * Refer�ncia para a janela do cadastro de Emprestimos
	 */
	private IViewerCadastroEmprestimos 		jCadastro;
	
	/**
	 * Refer�ncia para a janela Emprestimos que permitir� a 
	 * inclus�o e altera��o dos Emprestimos
	 */
	private IViewerEmprestimo 				jEmprestimo;
	
	/**
	 * Refer�ncia para o objeto Emprestimo sendo manipulado
	 */
	private Emprestimo 						emprestimoAtual;
	
	/**
	 * Refer�ncia para o objeto DaoEmprestimo 
	 */
	private IDAO<Emprestimo> 				dao = DAOEmprestimo.getSingleton();

	/**
	 * Atributo indicando se o caso de uso est� ou n�o em execu��o
	 */
	private boolean 						emExecucao;
	
	/**
	 * Atributo que indica qual opera��o est� em curso
	 */
	private Operacao 						operacao;
	
	//
	// M�TODOS
	//
	
	/**
	 * Construtor da classe CtrlManterEmprestimos
	 */
	public CtrlManterEmprestimos(ICtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmprestimos#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar 
		// novamente a execu��o do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroEmprestimos(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informa��o que o caso de uso est� em execu��o
		this.emExecucao = true;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmprestimos#terminar()
	 */
	@Override
	public boolean terminar() {
		// Se n�o est� em execu��o, n�o � necess�rio solicitar 
		// novamente o t�rmino do caso de uso
		if(!this.emExecucao)
			return false;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Notifico ao controlador do programa o t�rmino do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterEmprestimos();
		// Guardo a informa��o que o caso de uso est� encerrado
		this.emExecucao = false;
		// Indico que o caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmprestimos#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		// Recupero os objetos Aluno do DAO
		IDAO<Aluno> daoAluno = DAOAluno.getSingleton();
		Aluno[] alunos = daoAluno.getListaObjs();
		
		// Recupero os objetos Funcionario do DAO
		IDAO<Funcionario> daoFuncionario = DAOFuncionario.getSingleton();
		Funcionario[] funcionarios = daoFuncionario.getListaObjs();
		
		// Recupero os objetos Exemplar do DAO
		IDAO<Exemplar> daoExemplar = DAOExemplar.getSingleton();
		Exemplar[] exemplares = daoExemplar.getListaObjs();
		
		// Abro a janela de Emprestimos
		this.jEmprestimo = new JanelaEmprestimo(this, funcionarios, alunos,  exemplares);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmprestimos#cancelarIncluir()
	 */
	@Override
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jEmprestimo.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmprestimos#incluir(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String dataEmp, String horaEmp, Object exemplar, Object aluno, String devPrevista, Object funcionario, String devEfetiva) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Emprestimo
		Emprestimo novo = new Emprestimo(dataEmp, horaEmp,(Exemplar)exemplar,(Aluno)aluno, devPrevista,(Funcionario)funcionario, devEfetiva);
		// Salvo o objeto Emprestimo usando o DAO
		dao.salvar(novo);
		// Fecho a janela Emprestimo
		this.jEmprestimo.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmprestimos#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recupero o  objeto Emprestimo
		this.emprestimoAtual = dao.recuperar(pos);
		
		// Recupero os objetos Aluno do DAO
		IDAO<Aluno> daoAluno = DAOAluno.getSingleton();
		Aluno[] alunos = daoAluno.getListaObjs();
		
		// Recupero os objetos Funcionario do DAO
		IDAO<Funcionario> daoFuncionario = DAOFuncionario.getSingleton();
		Funcionario[] funcionarios = daoFuncionario.getListaObjs();
		
		// Recupero os objetos Exemplar do DAO
		IDAO<Exemplar> daoExemplar = DAOExemplar.getSingleton();
		Exemplar[] exemplares = daoExemplar.getListaObjs();
		
		// Abro a janela Emprestimo para altera��o
		this.jEmprestimo = new JanelaEmprestimo(this, funcionarios, alunos, exemplares);
		this.jEmprestimo.atualizarCampos(this.emprestimoAtual.getDataEmp(),
										 this.emprestimoAtual.getHoraEmp(),
										 this.emprestimoAtual.getExemplar(),
										 this.emprestimoAtual.getAluno(),
										 this.emprestimoAtual.getDevPrevista(),
										 this.emprestimoAtual.getFuncionario(),
										 this.emprestimoAtual.getDevEfetiva());
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmprestimos#cancelarAlterar()
	 */
	@Override
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jEmprestimo.setVisible(false);
			// N�o guardo uma refer�ncia para a Emprestimo pois cancelei a altera��o
			this.emprestimoAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmprestimos#alterar(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String dataEmp, String horaEmp, Object exemplar, Object aluno, String devPrevista, Object funcionario, String devEfetiva) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.emprestimoAtual.setDataEmp(dataEmp);
		this.emprestimoAtual.setHoraEmp(horaEmp);
		this.emprestimoAtual.setExemplar((Exemplar)exemplar);
		this.emprestimoAtual.setAluno((Aluno)aluno);
		this.emprestimoAtual.setDevPrevista(devPrevista);
		this.emprestimoAtual.setFuncionario((Funcionario)funcionario);
		this.emprestimoAtual.setDevEfetiva(devEfetiva);
		
		// Salvo o objeto Emprestimo usando o DAO
		dao.atualizar(this.emprestimoAtual);
		// Fecho a janela Emprestimo
		this.jEmprestimo.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Emprestimo
		this.emprestimoAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmprestimos#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recupero o  objeto Emprestimo
		this.emprestimoAtual = dao.recuperar(pos);
		// Abro a janela Emprestimo para perguntar sobre a exclus�o
		new JanelaExcluirEmprestimo(this, this.emprestimoAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmprestimos#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para a Emprestimo pois cancelei a altera��o
			this.emprestimoAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmprestimos#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Salvo o objeto Emprestimo usando o DAO
		dao.remover(this.emprestimoAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Emprestimo
		this.emprestimoAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmprestimos#atualizarInterface()
	 */
	@Override
	public void atualizarInterface(){
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Para cada objeto Emprestimo presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Emprestimo
			Emprestimo emprestimo = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(emprestimo);
		}
	}
}

