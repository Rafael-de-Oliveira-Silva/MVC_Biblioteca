package controle;

import janelas.IViewerCadastroExemplares;
import janelas.IViewerExemplar;
import janelas.JanelaCadastroExemplares;
import janelas.JanelaExcluirExemplar;
import janelas.JanelaExemplar;
import dados.DAOExemplar;
import dados.DAOObra;
import dados.DadosException;
import dados.Exemplar;
import dados.IDAO;
import dados.Obra;

public class CtrlManterExemplares implements ICtrlManterExemplares {
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
	 * Refer�ncia para a janela do cadastro de Exemplares
	 */
	private IViewerCadastroExemplares 	jCadastro;
	
	/**
	 * Refer�ncia para a janela Exemplares que permitir� a 
	 * inclus�o e altera��o dos Exemplares
	 */
	private IViewerExemplar 				jExemplar;
	
	/**
	 * Refer�ncia para o objeto Exemplar sendo manipulado
	 */
	private Exemplar 					exemplarAtual;
	
	/**
	 * Refer�ncia para o objeto DaoExemplar 
	 */
	private IDAO<Exemplar> 				dao = DAOExemplar.getSingleton();

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
	 * Construtor da classe CtrlManterExemplares
	 */
	public CtrlManterExemplares(ICtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterExemplares#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar 
		// novamente a execu��o do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroExemplares(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informa��o que o caso de uso est� em execu��o
		this.emExecucao = true;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterExemplares#terminar()
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
		this.ctrlPrg.terminarCasoDeUsoManterExemplares();
		// Guardo a informa��o que o caso de uso est� encerrado
		this.emExecucao = false;
		// Indico que o caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterExemplares#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		
		// Recupero os objetos Departamento do DAO
		IDAO<Obra> daoObra = DAOObra.getSingleton();
		Obra[] obras = daoObra.getListaObjs();
		
		// Abro a janela de Exemplares
		this.jExemplar = new JanelaExemplar(this, obras);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterExemplares#cancelarIncluir()
	 */
	@Override
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jExemplar.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterExemplares#incluir(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String numero, Object obra, String situacao) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Exemplar
		Exemplar novo = new Exemplar(numero, (Obra)obra, situacao);
		// Salvo o objeto Exemplar usando o DAO
		dao.salvar(novo);
		// Fecho a janela Exemplar
		this.jExemplar.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterExemplares#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recupero o  objeto Exemplar
		this.exemplarAtual = dao.recuperar(pos);
		// Recupero os objetos Obra do DAO
		IDAO<Obra> daoObra = DAOObra.getSingleton();
		Obra[] obras = daoObra.getListaObjs();
		// Abro a janela Exemplar para altera��o
		this.jExemplar = new JanelaExemplar(this, obras);
		this.jExemplar.atualizarCampos(this.exemplarAtual.getNumero(),
									   this.exemplarAtual.getObra(),
									   this.exemplarAtual.getSituacao());
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#cancelarAlterar()
	 */
	@Override
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jExemplar.setVisible(false);
			// N�o guardo uma refer�ncia para a Editora pois cancelei a altera��o
			this.exemplarAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterExemplares#alterar(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String numero, Object obra, String situacao) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.exemplarAtual.setNumero(numero);
		this.exemplarAtual.setObra((Obra)obra);
		this.exemplarAtual.setSituacao(situacao);
		
		// Salvo o objeto Editora usando o DAO
		dao.atualizar(this.exemplarAtual);
		// Fecho a janela Editora
		this.jExemplar.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Exemplar
		this.exemplarAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterExemplares#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recupero o  objeto Editora
		this.exemplarAtual = dao.recuperar(pos);
		// Abro a janela Editora para perguntar sobre a exclus�o
		new JanelaExcluirExemplar(this, this.exemplarAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEditoras#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para a Exemplar pois cancelei a altera��o
			this.exemplarAtual = null;
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
		// Salvo o objeto Exemplar usando o DAO
		dao.remover(this.exemplarAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Exemplar
		this.exemplarAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterExemplares#atualizarInterface()
	 */
	@Override
	public void atualizarInterface(){
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Para cada objeto Exemplar presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Exemplar
			Exemplar exemp = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(exemp);
		}
	}
}


