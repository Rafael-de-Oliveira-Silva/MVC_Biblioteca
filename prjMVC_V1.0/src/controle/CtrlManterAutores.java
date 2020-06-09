package controle;

import janelas.IViewerAutor;
import janelas.IViewerCadastroAutores;
import janelas.JanelaAutor;
import janelas.JanelaCadastroAutores;
import janelas.JanelaExcluirAutor;
import dados.Autor;
import dados.DAOAutor;
import dados.DadosException;
import dados.IDAO;

public class CtrlManterAutores implements ICtrlManterAutores {
	//
	// ATRIBUTOS
	//
	private enum Operacao {
		INCLUSAO, EXCLUSAO, ALTERACAO, DISPONIVEL;
	}
	
	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private ICtrlPrograma       	ctrlPrg;
	
	/**
	 * Refer�ncia para a janela do cadastro de Autores
	 */
	private IViewerCadastroAutores 	jCadastro;
	
	/**
	 * Refer�ncia para a janela Autores que permitir� a 
	 * inclus�o e altera��o dos Autores
	 */
	private IViewerAutor 			jAutor;
	
	/**
	 * Refer�ncia para o objeto Autor sendo manipulado
	 */
	private Autor 					autorAtual;
	
	/**
	 * Refer�ncia para o objeto DaoAutor 
	 */
	private IDAO<Autor> 			dao = DAOAutor.getSingleton();

	/**
	 * Atributo indicando se o caso de uso est� ou n�o em execu��o
	 */
	private boolean 				emExecucao;
	
	/**
	 * Atributo que indica qual opera��o est� em curso
	 */
	private Operacao 				operacao;
	
	//
	// M�TODOS
	//
	
	/**
	 * Construtor da classe CtrlManterAutores
	 */
	public CtrlManterAutores(ICtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar 
		// novamente a execu��o do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroAutores(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informa��o que o caso de uso est� em execu��o
		this.emExecucao = true;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#terminar()
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
	 * @see controle.ICtrlManterAutores#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Autores
		this.jAutor = new JanelaAutor(this);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#cancelarIncluir()
	 */
	@Override
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jAutor.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#incluir(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String nome) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Autor
		Autor novo = new Autor(nome);
		// Salvo o objeto Autor usando o DAO
		dao.salvar(novo);
		// Fecho a janela Autor
		this.jAutor.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recupero o  objeto Autor
		this.autorAtual = dao.recuperar(pos);
		// Abro a janela Autor para altera��o
		this.jAutor = new JanelaAutor(this);
		this.jAutor.atualizarCampos(this.autorAtual.getNome());
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#cancelarAlterar()
	 */
	@Override
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jAutor.setVisible(false);
			// N�o guardo uma refer�ncia para o Autor pois cancelei a altera��o
			this.autorAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#alterar(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String nome) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.autorAtual.setNome(nome);
		
		// Salvo o objeto Autor usando o DAO
		dao.atualizar(this.autorAtual);
		// Fecho a janela Autor
		this.jAutor.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Autor
		this.autorAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recupero o  objeto Autor
		this.autorAtual = dao.recuperar(pos);
		// Abro a janela Autor para perguntar sobre a exclus�o
		new JanelaExcluirAutor(this, this.autorAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para o Autor pois cancelei a altera��o
			this.autorAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Salvo o objeto Autor usando o DAO
		dao.remover(this.autorAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Autor
		this.autorAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#atualizarInterface()
	 */
	@Override
	public void atualizarInterface(){
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Para cada objeto Autor presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Autor
			Autor autor = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(autor);
		}
	}
}

