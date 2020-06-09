package controle;

import janelas.IViewerCadastroFuncionarios;
import janelas.IViewerFuncionario;
import janelas.JanelaCadastroFuncionarios;
import janelas.JanelaExcluirFuncionario;
import janelas.JanelaFuncionario;
import dados.DAOFuncionario;
import dados.DadosException;
import dados.Funcionario;
import dados.IDAO;

public class CtrlManterFuncionarios implements ICtrlManterFuncionarios {
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
	 * Refer�ncia para a janela do cadastro de Funcionarios
	 */
	private IViewerCadastroFuncionarios 	jCadastro;
	
	/**
	 * Refer�ncia para a janela Funcionarios que permitir� a 
	 * inclus�o e altera��o dos Funcionarios
	 */
	private IViewerFuncionario 				jFuncionario;
	
	/**
	 * Refer�ncia para o objeto Funcionario sendo manipulado
	 */
	private Funcionario 					funcionarioAtual;
	
	/**
	 * Refer�ncia para o objeto DaoFuncionario 
	 */
	private IDAO<Funcionario> 				dao = DAOFuncionario.getSingleton();

	/**
	 * Atributo indicando se o caso de uso est� ou n�o em execu��o
	 */
	private boolean 						emExecucao;
	
	/**
	 * Atributo que indica qual opera��o est� em curso
	 */
	private Operacao 					operacao;
	
	//
	// M�TODOS
	//
	
	/**
	 * Construtor da classe CtrlManterFuncionarios
	 */
	public CtrlManterFuncionarios(ICtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar 
		// novamente a execu��o do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroFuncionarios(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informa��o que o caso de uso est� em execu��o
		this.emExecucao = true;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#terminar()
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
		this.ctrlPrg.terminarCasoDeUsoManterFuncionarios();
		// Guardo a informa��o que o caso de uso est� encerrado
		this.emExecucao = false;
		// Indico que o caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Funcionarios
		this.jFuncionario = new JanelaFuncionario(this);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#cancelarIncluir()
	 */
	@Override
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jFuncionario.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#incluir(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String cpf, String nome, String endereco, String telefone, String matFunc) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Funcionario
		Funcionario novo = new Funcionario(cpf, nome, endereco, telefone, matFunc);
		// Salvo o objeto Funcionario usando o DAO
		dao.salvar(novo);
		// Fecho a janela Funcionario
		this.jFuncionario.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recupero o  objeto Funcionario
		this.funcionarioAtual = dao.recuperar(pos);
		// Abro a janela Funcionario para altera��o
		this.jFuncionario = new JanelaFuncionario(this);
		this.jFuncionario.atualizarCampos(this.funcionarioAtual.getCpf(),
										  this.funcionarioAtual.getNome(),
										  this.funcionarioAtual.getEndereco(),
										  this.funcionarioAtual.getTelefone(),
										  this.funcionarioAtual.getMatFunc());
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#cancelarAlterar()
	 */
	@Override
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jFuncionario.setVisible(false);
			// N�o guardo uma refer�ncia para o Funcionario pois cancelei a altera��o
			this.funcionarioAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#alterar(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String cpf, String nome, String endereco, String telefone, String matFunc) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.funcionarioAtual.setCpf(cpf);
		this.funcionarioAtual.setNome(nome);
		this.funcionarioAtual.setEndereco(endereco);
		this.funcionarioAtual.setTelefone(telefone);
		this.funcionarioAtual.setMatFunc(matFunc);
				
		// Salvo o objeto Funcionario usando o DAO
		dao.atualizar(this.funcionarioAtual);
		// Fecho a janela Funcionario
		this.jFuncionario.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Funcionario
		this.funcionarioAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recupero o  objeto Funcionario
		this.funcionarioAtual = dao.recuperar(pos);
		// Abro a janela Funcionario para perguntar sobre a exclus�o
		new JanelaExcluirFuncionario(this, this.funcionarioAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para a Funcionario pois cancelei a altera��o
			this.funcionarioAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Salvo o objeto Funcionario usando o DAO
		dao.remover(this.funcionarioAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Funcionario
		this.funcionarioAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#atualizarInterface()
	 */
	@Override
	public void atualizarInterface(){
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Para cada objeto Funcionario presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Funcionario
			Funcionario func = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(func);
		}
	}
}



