package controle;

import janelas.IViewerAssunto;
import janelas.IViewerCadastroAssuntos;
import janelas.JanelaAssunto;
import janelas.JanelaCadastroAssuntos;
import janelas.JanelaExcluirAssunto;
import dados.Assunto;
import dados.DAOAssunto;
import dados.DadosException;
import dados.IDAO;

public class CtrlManterAssuntos implements ICtrlManterAssuntos {
	//
	// ATRIBUTOS
	//
	private enum Operacao {
		INCLUSAO, EXCLUSAO, ALTERACAO, DISPONIVEL;
	}
	
	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private ICtrlPrograma       ctrlPrg;
	
	/**
	 * Refer�ncia para a janela do cadastro de Assuntos
	 */
	private IViewerCadastroAssuntos jCadastro;
	
	/**
	 * Refer�ncia para a janela Assuntos que permitir� a 
	 * inclus�o e altera��o dos Assuntos
	 */
	private IViewerAssunto jAssunto;
	
	/**
	 * Refer�ncia para o objeto Assunto sendo manipulado
	 */
	private Assunto assuntoAtual;
	
	/**
	 * Refer�ncia para o objeto DaoAssunto 
	 */
	private IDAO<Assunto> dao = DAOAssunto.getSingleton();

	/**
	 * Atributo indicando se o caso de uso est� ou n�o em execu��o
	 */
	private boolean emExecucao;
	
	/**
	 * Atributo que indica qual opera��o est� em curso
	 */
	private Operacao operacao;
	
	//
	// M�TODOS
	//
	
	/**
	 * Construtor da classe CtrlManterAssuntos
	 */
	public CtrlManterAssuntos(ICtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssuntos#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar 
		// novamente a execu��o do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroAssuntos(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informa��o que o caso de uso est� em execu��o
		this.emExecucao = true;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssuntos#terminar()
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
		this.ctrlPrg.terminarCasoDeUsoManterAssuntos();
		// Guardo a informa��o que o caso de uso est� encerrado
		this.emExecucao = false;
		// Indico que o caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssuntos#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Assuntos
		this.jAssunto = new JanelaAssunto(this);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssuntos#cancelarIncluir()
	 */	@Override
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jAssunto.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssuntos#incluir(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String descricao) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Assunto
		Assunto novo = new Assunto(descricao);
		// Salvo o objeto Assunto usando o DAO
		dao.salvar(novo);
		// Fecho a janela Assunto
		this.jAssunto.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssuntos#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recupero o  objeto Assunto
		this.assuntoAtual = dao.recuperar(pos);
		// Abro a janela Aluno para altera��o
		this.jAssunto = new JanelaAssunto(this);
		this.jAssunto.atualizarCampos(this.assuntoAtual.getDescricao());
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssuntos#cancelarAlterar()
	 */
	@Override
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jAssunto.setVisible(false);
			// N�o guardo uma refer�ncia para o Assunto pois cancelei a altera��o
			this.assuntoAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssuntos#alterar(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String descricao) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.assuntoAtual.setDescricao(descricao);
		
		// Salvo o objeto Assunto usando o DAO
		dao.atualizar(this.assuntoAtual);
		// Fecho a janela Assunto
		this.jAssunto.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Assunto
		this.assuntoAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssuntos#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recupero o  objeto Assunto
		this.assuntoAtual = dao.recuperar(pos);
		// Abro a janela Assunto para perguntar sobre a exclus�o
		new JanelaExcluirAssunto(this, this.assuntoAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssuntos#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para o Assunto pois cancelei a altera��o
			this.assuntoAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssuntos#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Salvo o objeto Assunto usando o DAO
		dao.remover(this.assuntoAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Assunto
		this.assuntoAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssuntos#atualizarInterface()
	 */
	@Override
	public void atualizarInterface(){
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Para cada objeto Assunto presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Assunto
			Assunto assunto = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(assunto);
		}
	}
}

