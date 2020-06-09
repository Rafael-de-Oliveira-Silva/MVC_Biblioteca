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
	 * Referência para o controlador do programa.
	 */
	private ICtrlPrograma       			ctrlPrg;
	
	/**
	 * Referência para a janela do cadastro de Funcionarios
	 */
	private IViewerCadastroFuncionarios 	jCadastro;
	
	/**
	 * Referência para a janela Funcionarios que permitirá a 
	 * inclusão e alteração dos Funcionarios
	 */
	private IViewerFuncionario 				jFuncionario;
	
	/**
	 * Referência para o objeto Funcionario sendo manipulado
	 */
	private Funcionario 					funcionarioAtual;
	
	/**
	 * Referência para o objeto DaoFuncionario 
	 */
	private IDAO<Funcionario> 				dao = DAOFuncionario.getSingleton();

	/**
	 * Atributo indicando se o caso de uso está ou não em execução
	 */
	private boolean 						emExecucao;
	
	/**
	 * Atributo que indica qual operação está em curso
	 */
	private Operacao 					operacao;
	
	//
	// MÉTODOS
	//
	
	/**
	 * Construtor da classe CtrlManterFuncionarios
	 */
	public CtrlManterFuncionarios(ICtrlPrograma p) {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se já está em execução, não é necessário solicitar 
		// novamente a execução do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroFuncionarios(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informação que o caso de uso está em execução
		this.emExecucao = true;
		// Indico que o controlador de caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#terminar()
	 */
	@Override
	public boolean terminar() {
		// Se não está em execução, não é necessário solicitar 
		// novamente o término do caso de uso
		if(!this.emExecucao)
			return false;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Notifico ao controlador do programa o término do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterFuncionarios();
		// Guardo a informação que o caso de uso está encerrado
		this.emExecucao = false;
		// Indico que o caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador não está disponível, não inicia a inclusão
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está incluindo
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
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#incluir(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String cpf, String nome, String endereco, String telefone, String matFunc) throws DadosException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está alterando
		this.operacao = Operacao.ALTERACAO;
		// Recupero o  objeto Funcionario
		this.funcionarioAtual = dao.recuperar(pos);
		// Abro a janela Funcionario para alteração
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
			// Não guardo uma referência para o Funcionario pois cancelei a alteração
			this.funcionarioAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#alterar(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String cpf, String nome, String endereco, String telefone, String matFunc) throws DadosException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Deixo de guardar a referência para o Funcionario
		this.funcionarioAtual = null;
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recupero o  objeto Funcionario
		this.funcionarioAtual = dao.recuperar(pos);
		// Abro a janela Funcionario para perguntar sobre a exclusão
		new JanelaExcluirFuncionario(this, this.funcionarioAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// Não guardo uma referência para a Funcionario pois cancelei a alteração
			this.funcionarioAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterFuncionarios#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador não tinha ativado uma exclusão, não faço nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Salvo o objeto Funcionario usando o DAO
		dao.remover(this.funcionarioAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Funcionario
		this.funcionarioAtual = null;
		// Indico que o controlador está disponível
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



