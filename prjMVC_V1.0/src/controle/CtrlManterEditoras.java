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
	 * Referência para o controlador do programa.
	 */
	private ICtrlPrograma       		ctrlPrg;
	
	/**
	 * Referência para a janela do cadastro de Editoras
	 */
	private IViewerCadastroEditoras 	jCadastro;
	
	/**
	 * Referência para a janela Editoras que permitirá a 
	 * inclusão e alteração das Editoras
	 */
	private IViewerEditora 				jEditora;
	
	/**
	 * Referência para o objeto Editora sendo manipulado
	 */
	private Editora 					editoraAtual;
	
	/**
	 * Referência para o objeto DaoEditora 
	 */
	private IDAO<Editora> 				dao = DAOEditora.getSingleton();

	/**
	 * Atributo indicando se o caso de uso está ou não em execução
	 */
	private boolean 					emExecucao;
	
	/**
	 * Atributo que indica qual operação está em curso
	 */
	private Operacao 					operacao;
	
	//
	// MÉTODOS
	//
	
	/**
	 * Construtor da classe CtrlManterEditoras
	 */
	public CtrlManterEditoras(ICtrlPrograma p) {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se já está em execução, não é necessário solicitar 
		// novamente a execução do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroEditoras(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informação que o caso de uso está em execução
		this.emExecucao = true;
		// Indico que o controlador de caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#terminar()
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
	 * @see controle.ICtrlManterEditoras#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador não está disponível, não inicia a inclusão
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está incluindo
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
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#incluir(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String nome, String cidade) throws DadosException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está alterando
		this.operacao = Operacao.ALTERACAO;
		// Recupero o  objeto Editora
		this.editoraAtual = dao.recuperar(pos);
		// Abro a janela Editora para alteração
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
			// Não guardo uma referência para a Editora pois cancelei a alteração
			this.editoraAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#alterar(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String nome, String cidade) throws DadosException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Deixo de guardar a referência para o Editora
		this.editoraAtual = null;
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recupero o  objeto Editora
		this.editoraAtual = dao.recuperar(pos);
		// Abro a janela Editora para perguntar sobre a exclusão
		new JanelaExcluirEditora(this, this.editoraAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// Não guardo uma referência para a Editora pois cancelei a alteração
			this.editoraAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador não tinha ativado uma exclusão, não faço nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Salvo o objeto Editora usando o DAO
		dao.remover(this.editoraAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Editora
		this.editoraAtual = null;
		// Indico que o controlador está disponível
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

