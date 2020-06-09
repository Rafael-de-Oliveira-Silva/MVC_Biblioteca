package controle;

import janelas.IViewerCadastroEditoras;
import janelas.IViewerEditora;
import janelas.JanelaCadastroEditoras;
import janelas.JanelaEditora;
import janelas.JanelaExcluirEditora;
import dados.DAOEditora;
import dados.DadosException;
import dados.Editora;
import dados.IDAO;

public class CtrlManterEditoras implements ICtrlManterEditoras {
	//
	// ATRIBUTOS
	//
	private enum Operacao {
		INCLUSAO, EXCLUSAO, ALTERACAO, DISPONIVEL;
	}
	
	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private ICtrlPrograma       		ctrlPrg;
	
	/**
	 * Refer�ncia para a janela do cadastro de Editoras
	 */
	private IViewerCadastroEditoras 	jCadastro;
	
	/**
	 * Refer�ncia para a janela Editoras que permitir� a 
	 * inclus�o e altera��o das Editoras
	 */
	private IViewerEditora 				jEditora;
	
	/**
	 * Refer�ncia para o objeto Editora sendo manipulado
	 */
	private Editora 					editoraAtual;
	
	/**
	 * Refer�ncia para o objeto DaoEditora 
	 */
	private IDAO<Editora> 				dao = DAOEditora.getSingleton();

	/**
	 * Atributo indicando se o caso de uso est� ou n�o em execu��o
	 */
	private boolean 					emExecucao;
	
	/**
	 * Atributo que indica qual opera��o est� em curso
	 */
	private Operacao 					operacao;
	
	//
	// M�TODOS
	//
	
	/**
	 * Construtor da classe CtrlManterEditoras
	 */
	public CtrlManterEditoras(ICtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar 
		// novamente a execu��o do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroEditoras(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informa��o que o caso de uso est� em execu��o
		this.emExecucao = true;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#terminar()
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
		this.ctrlPrg.terminarCasoDeUsoManterAutores();
		// Guardo a informa��o que o caso de uso est� encerrado
		this.emExecucao = false;
		// Indico que o caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Editoras
		this.jEditora = new JanelaEditora(this);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#cancelarIncluir()
	 */
	@Override
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jEditora.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#incluir(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String nome, String cidade) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Editora
		Editora novo = new Editora(nome, cidade);
		// Salvo o objeto Editora usando o DAO
		dao.salvar(novo);
		// Fecho a janela Editora
		this.jEditora.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recupero o  objeto Editora
		this.editoraAtual = dao.recuperar(pos);
		// Abro a janela Editora para altera��o
		this.jEditora = new JanelaEditora(this);
		this.jEditora.atualizarCampos(this.editoraAtual.getNome(),
									  this.editoraAtual.getCidade());
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#cancelarAlterar()
	 */
	@Override
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jEditora.setVisible(false);
			// N�o guardo uma refer�ncia para a Editora pois cancelei a altera��o
			this.editoraAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#alterar(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String nome, String cidade) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.editoraAtual.setNome(nome);
		this.editoraAtual.setCidade(cidade);
		
		// Salvo o objeto Editora usando o DAO
		dao.atualizar(this.editoraAtual);
		// Fecho a janela Editora
		this.jEditora.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Editora
		this.editoraAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recupero o  objeto Editora
		this.editoraAtual = dao.recuperar(pos);
		// Abro a janela Editora para perguntar sobre a exclus�o
		new JanelaExcluirEditora(this, this.editoraAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para a Editora pois cancelei a altera��o
			this.editoraAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Salvo o objeto Editora usando o DAO
		dao.remover(this.editoraAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Editora
		this.editoraAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#atualizarInterface()
	 */
	@Override
	public void atualizarInterface(){
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Para cada objeto Editora presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Editora
			Editora editora = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(editora);
		}
	}
}

