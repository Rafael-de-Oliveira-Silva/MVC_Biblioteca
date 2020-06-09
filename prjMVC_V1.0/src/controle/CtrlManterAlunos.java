package controle;

import janelas.IViewerAluno;
import janelas.IViewerCadastroAlunos;
import janelas.JanelaAluno;
import janelas.JanelaCadastroAlunos;
import janelas.JanelaExcluirAluno;
import dados.Aluno;
import dados.DAOAluno;
import dados.DadosException;
import dados.IDAO;

public class CtrlManterAlunos implements ICtrlManterAlunos {
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
	 * Refer�ncia para a janela do cadastro de Alunos
	 */
	private IViewerCadastroAlunos jCadastro;
	
	/**
	 * Refer�ncia para a janela Alunos que permitir� a 
	 * inclus�o e altera��o dos Alunos
	 */
	private IViewerAluno jAluno;
	
	/**
	 * Refer�ncia para o objeto Aluno sendo manipulado
	 */
	private Aluno alunoAtual;
	
	/**
	 * Refer�ncia para o objeto DaoAluno 
	 */
	private IDAO<Aluno> dao = DAOAluno.getSingleton();

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
	 * Construtor da classe CtrlManterAlunos
	 */
	public CtrlManterAlunos(ICtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar 
		// novamente a execu��o do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroAlunos(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informa��o que o caso de uso est� em execu��o
		this.emExecucao = true;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#terminar()
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
		this.ctrlPrg.terminarCasoDeUsoManterAlunos();
		// Guardo a informa��o que o caso de uso est� encerrado
		this.emExecucao = false;
		// Indico que o caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Alunos
		this.jAluno = new JanelaAluno(this);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#cancelarIncluir()
	 */
	@Override
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jAluno.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#incluir(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String cpf, String nome, String endereco, String telefone, String matricula) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Aluno
		Aluno novo = new Aluno(cpf, nome, endereco, telefone, matricula);
		// Salvo o objeto Aluno usando o DAO
		dao.salvar(novo);
		// Fecho a janela Aluno
		this.jAluno.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recupero o  objeto Aluno
		this.alunoAtual = dao.recuperar(pos);
		// Abro a janela Aluno para altera��o
		this.jAluno = new JanelaAluno(this);
		this.jAluno.atualizarCampos(this.alunoAtual.getCpf(),
									this.alunoAtual.getNome(),
				                    this.alunoAtual.getEndereco(),
				                    this.alunoAtual.getTelefone(),
				                    this.alunoAtual.getMatricula());
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#cancelarAlterar()
	 */
	@Override
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jAluno.setVisible(false);
			// N�o guardo uma refer�ncia para o Aluno pois cancelei a altera��o
			this.alunoAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#alterar(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String cpf, String nome, String endereco, String telefone, String matricula) throws DadosException {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.alunoAtual.setCpf(cpf);
		this.alunoAtual.setNome(nome);
		this.alunoAtual.setEndereco(endereco);
		this.alunoAtual.setTelefone(telefone);
		this.alunoAtual.setMatricula(matricula);
		// Salvo o objeto Aluno usando o DAO
		dao.atualizar(this.alunoAtual);
		// Fecho a janela Aluno
		this.jAluno.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Aluno
		this.alunoAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recupero o  objeto Aluno
		this.alunoAtual = dao.recuperar(pos);
		// Abro a janela Aluno para perguntar sobre a exclus�o
		new JanelaExcluirAluno(this, this.alunoAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para o Aluno pois cancelei a altera��o
			this.alunoAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Salvo o objeto Aluno usando o DAO
		dao.remover(this.alunoAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Aluno
		this.alunoAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#atualizarInterface()
	 */
	@Override
	public void atualizarInterface(){
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Para cada objeto Aluno presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Aluno
			Aluno al = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(al);
		}
	}
}
