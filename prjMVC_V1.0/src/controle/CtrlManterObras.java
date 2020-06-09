package controle;

import janelas.IViewerCadastroObras;
import janelas.IViewerObra;
import janelas.JanelaCadastroObras;
import janelas.JanelaExcluirObra;
import janelas.JanelaObra;
import dados.Assunto;
import dados.Autor;
import dados.DAOAssunto;
import dados.DAOAutor;
import dados.DAOEditora;
import dados.DAOObra;
import dados.DadosException;
import dados.Editora;
import dados.IDAO;
import dados.Obra;

public class CtrlManterObras implements ICtrlManterObras {
	//
	// ATRIBUTOS
	//
	private enum Operacao {
		INCLUSAO, EXCLUSAO, ALTERACAO, DISPONIVEL;
	}
	
	/**
	 * Referência para o controlador do programa.
	 */
	private ICtrlPrograma       ctrlPrg;
	
	/**
	 * Referência para a janela do cadastro de Empregados
	 */
	private IViewerCadastroObras jCadastro;
	
	/**
	 * Referência para a janela Empregado que permitirá a 
	 * inclusão e alteração do Empregado
	 */
	private IViewerObra jObra;
	
	/**
	 * Referência para o objeto Empregado sendo manipulado
	 */
	private Obra obraAtual;
	
	/**
	 * Referência para o DaoEmpregado
	 */
	private IDAO<Obra> dao = DAOObra.getSingleton();

	/**
	 * Atributo indicando se o caso de uso está ou não em execução
	 */
	private boolean emExecucao;
	
	/**
	 * Atributo que indica qual operação está em curso
	 */
	private Operacao operacao;
	
	//
	// MÉTODOS
	//
	
	/**
	 * Construtor da classe CtrlManterEmpregados
	 */
	public CtrlManterObras(ICtrlPrograma p) {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmpregados#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se já está em execução, não é necessário solicitar 
		// novamente a execução do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroObras(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informação que o caso de uso está em execução
		this.emExecucao = true;
		// Indico que o controlador de caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmpregados#terminar()
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
		this.ctrlPrg.terminarCasoDeUsoManterObras();
		// Guardo a informação que o caso de uso está encerrado
		this.emExecucao = false;
		// Indico que o caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmpregados#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador não está disponível, não inicia a inclusão
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está incluindo
		this.operacao = Operacao.INCLUSAO;
		// Recupero os objetos Departamento do DAO
		IDAO<Editora> daoEditora = DAOEditora.getSingleton();
		Editora[] editoras = daoEditora.getListaObjs();
		
		IDAO<Assunto> daoAssunto = DAOAssunto.getSingleton();
		Assunto[] assuntos = daoAssunto.getListaObjs();
		
		IDAO<Autor> dao = DAOAutor.getSingleton();
		Autor[] autores = dao.getListaObjs();
		
		// Abro a janela de empregado
		this.jObra = new JanelaObra(this, editoras, assuntos, autores);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmpregados#cancelarIncluir()
	 */
	@Override
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jObra.setVisible(false);
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmpregados#incluir(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean incluir(String isbn, String titulo, String ano, Editora editora, Assunto assunto, Object autor) throws DadosException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Empregado
		Obra novo = new Obra(isbn, titulo, ano, editora, assunto, (Autor)autor);
		// Salvo o objeto Empregado usando o DAO
		dao.salvar(novo);
		// Fecho a janela Empregado
		this.jObra.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmpregados#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está alterando
		this.operacao = Operacao.ALTERACAO;
		// Recupero o  objeto Empregado
		this.obraAtual = dao.recuperar(pos);
		// Recupero os objetos Departamento do DAO
		IDAO<Editora> daoEditora = DAOEditora.getSingleton();
		Editora[] editoras = daoEditora.getListaObjs();
		
		IDAO<Assunto> daoAssunto = DAOAssunto.getSingleton();
		Assunto[] assuntos = daoAssunto.getListaObjs();
				
		IDAO<Autor> daoAutor = DAOAutor.getSingleton();
		Autor[] autores = daoAutor.getListaObjs();
		// Abro a janela Empregado para alteração
		this.jObra = new JanelaObra(this, editoras, assuntos, autores);
		this.jObra.atualizarCampos(this.obraAtual.getIsbn(), 
								   this.obraAtual.getTitulo(),
								   this.obraAtual.getAno(),
								   this.obraAtual.getEditor(),
								   this.obraAtual.getAssunto(),
								   this.obraAtual.getAutor());
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmpregados#cancelarAlterar()
	 */
	@Override
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jObra.setVisible(false);
			// Não guardo uma referência para o Empregado pois cancelei a alteração
			this.obraAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmpregados#alterar(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean alterar(String isbn, String titulo, String ano, Editora editora, Assunto assunto, Object autor) throws DadosException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.obraAtual.setIsbn(isbn);
		this.obraAtual.setTitulo(titulo);
		this.obraAtual.setAno(ano);
		this.obraAtual.setEditor(editora);
		this.obraAtual.setAssunto(assunto);
		
		this.obraAtual.setAutor((Autor)autor);
		
		// Salvo o objeto Empregado usando o DAO
		dao.atualizar(this.obraAtual);
		// Fecho a janela Empregado
		this.jObra.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Empregado
		this.obraAtual = null;
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmpregados#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recupero o  objeto Empregado
		this.obraAtual = dao.recuperar(pos);
		// Abro a janela Empregado para perguntar sobre a exclusão
		new JanelaExcluirObra(this, this.obraAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmpregados#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// Não guardo uma referência para o Empregado pois cancelei a alteração
			this.obraAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmpregados#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador não tinha ativado uma exclusão, não faço nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Salvo o objeto Empregado usando o DAO
		dao.remover(this.obraAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Empregado
		this.obraAtual = null;
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterEmpregados#atualizarInterface()
	 */
	@Override
	public void atualizarInterface(){
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Para cada objeto Empregado presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Empregado
			Obra e = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(e);
		}
	}
}



