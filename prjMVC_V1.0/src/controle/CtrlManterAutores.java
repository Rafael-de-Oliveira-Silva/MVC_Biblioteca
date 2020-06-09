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
	 * Referência para o controlador do programa.
	 */
	private ICtrlPrograma       	ctrlPrg;
	
	/**
	 * Referência para a janela do cadastro de Autores
	 */
	private IViewerCadastroAutores 	jCadastro;
	
	/**
	 * Referência para a janela Autores que permitirá a 
	 * inclusão e alteração dos Autores
	 */
	private IViewerAutor 			jAutor;
	
	/**
	 * Referência para o objeto Autor sendo manipulado
	 */
	private Autor 					autorAtual;
	
	/**
	 * Referência para o objeto DaoAutor 
	 */
	private IDAO<Autor> 			dao = DAOAutor.getSingleton();

	/**
	 * Atributo indicando se o caso de uso está ou não em execução
	 */
	private boolean 				emExecucao;
	
	/**
	 * Atributo que indica qual operação está em curso
	 */
	private Operacao 				operacao;
	
	//
	// MÉTODOS
	//
	
	/**
	 * Construtor da classe CtrlManterAutores
	 */
	public CtrlManterAutores(ICtrlPrograma p) {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se já está em execução, não é necessário solicitar 
		// novamente a execução do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroAutores(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informação que o caso de uso está em execução
		this.emExecucao = true;
		// Indico que o controlador de caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#terminar()
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
		this.ctrlPrg.terminarCasoDeUsoManterAutores();
		// Guardo a informação que o caso de uso está encerrado
		this.emExecucao = false;
		// Indico que o caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador não está disponível, não inicia a inclusão
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está incluindo
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
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#incluir(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String nome) throws DadosException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está alterando
		this.operacao = Operacao.ALTERACAO;
		// Recupero o  objeto Autor
		this.autorAtual = dao.recuperar(pos);
		// Abro a janela Autor para alteração
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
			// Não guardo uma referência para o Autor pois cancelei a alteração
			this.autorAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#alterar(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String nome) throws DadosException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Deixo de guardar a referência para o Autor
		this.autorAtual = null;
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recupero o  objeto Autor
		this.autorAtual = dao.recuperar(pos);
		// Abro a janela Autor para perguntar sobre a exclusão
		new JanelaExcluirAutor(this, this.autorAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// Não guardo uma referência para o Autor pois cancelei a alteração
			this.autorAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAutores#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador não tinha ativado uma exclusão, não faço nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Salvo o objeto Autor usando o DAO
		dao.remover(this.autorAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Autor
		this.autorAtual = null;
		// Indico que o controlador está disponível
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

