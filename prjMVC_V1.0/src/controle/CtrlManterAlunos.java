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
	 * Referência para o controlador do programa.
	 */
	private ICtrlPrograma       ctrlPrg;
	
	/**
	 * Referência para a janela do cadastro de Alunos
	 */
	private IViewerCadastroAlunos jCadastro;
	
	/**
	 * Referência para a janela Alunos que permitirá a 
	 * inclusão e alteração dos Alunos
	 */
	private IViewerAluno jAluno;
	
	/**
	 * Referência para o objeto Aluno sendo manipulado
	 */
	private Aluno alunoAtual;
	
	/**
	 * Referência para o objeto DaoAluno 
	 */
	private IDAO<Aluno> dao = DAOAluno.getSingleton();

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
	 * Construtor da classe CtrlManterAlunos
	 */
	public CtrlManterAlunos(ICtrlPrograma p) {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se já está em execução, não é necessário solicitar 
		// novamente a execução do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroAlunos(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informação que o caso de uso está em execução
		this.emExecucao = true;
		// Indico que o controlador de caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#terminar()
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
		this.ctrlPrg.terminarCasoDeUsoManterAlunos();
		// Guardo a informação que o caso de uso está encerrado
		this.emExecucao = false;
		// Indico que o caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador não está disponível, não inicia a inclusão
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está incluindo
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
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#incluir(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String cpf, String nome, String endereco, String telefone, String matricula) throws DadosException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está alterando
		this.operacao = Operacao.ALTERACAO;
		// Recupero o  objeto Aluno
		this.alunoAtual = dao.recuperar(pos);
		// Abro a janela Aluno para alteração
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
			// Não guardo uma referência para o Aluno pois cancelei a alteração
			this.alunoAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#alterar(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String cpf, String nome, String endereco, String telefone, String matricula) throws DadosException {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Deixo de guardar a referência para o Aluno
		this.alunoAtual = null;
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recupero o  objeto Aluno
		this.alunoAtual = dao.recuperar(pos);
		// Abro a janela Aluno para perguntar sobre a exclusão
		new JanelaExcluirAluno(this, this.alunoAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// Não guardo uma referência para o Aluno pois cancelei a alteração
			this.alunoAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAlunos#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador não tinha ativado uma exclusão, não faço nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Salvo o objeto Aluno usando o DAO
		dao.remover(this.alunoAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Aluno
		this.alunoAtual = null;
		// Indico que o controlador está disponível
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
