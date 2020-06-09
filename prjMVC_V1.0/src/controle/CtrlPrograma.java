/**
 * @author Rafael de Oliveira Silva
 * Matricula: 53055580
 */

package controle;

import janelas.JanelaPrincipal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dados.DAOAluno;
import dados.DAOAssunto;
import dados.DAOAutor;
import dados.DAOEditora;
import dados.DAOEmprestimo;
import dados.DAOExemplar;
import dados.DAOFuncionario;
import dados.DAOObra;
import dados.IDAOSerializavel;

public class CtrlPrograma implements ICtrlPrograma{
	//Declara��o de atributos para cada controlador de caso de uso
	private ICtrlManterExemplares 	ctrlExemplares;
	private ICtrlManterAlunos 		ctrlAlunos;
	private ICtrlManterAutores 		ctrlAutores;
	private ICtrlManterAssuntos 	ctrlAssuntos;
	private ICtrlManterEditoras 	ctrlEditoras;
	private ICtrlManterEmprestimos 	ctrlEmprestimos;
	private ICtrlManterFuncionarios ctrlFuncionarios;
	private ICtrlManterObras 		ctrlObras;

	//Declara��o de atributo para referenciar a janela principal
	private JanelaPrincipal jPrincipal;

	/**
	 * M�todo construtor do Controlador Programa
	 */
	public CtrlPrograma(){
		//Instanciando os controladores de caso de uso do sistema
		this.ctrlFuncionarios = new CtrlManterFuncionarios(this);
		this.ctrlAlunos = new CtrlManterAlunos(this);
		this.ctrlEmprestimos = new CtrlManterEmprestimos(this);
		this.ctrlExemplares = new CtrlManterExemplares(this);
		this.ctrlObras = new CtrlManterObras(this);
		this.ctrlAutores = new CtrlManterAutores(this);
		this.ctrlAssuntos = new CtrlManterAssuntos(this);
		this.ctrlEditoras = new CtrlManterEditoras(this);	
	}

	/**
	 * M�todo que iniciar a execu��o da janela principal
	 * e recupera os dados em disco
	 */
	public void iniciar(){
		this.jPrincipal = new JanelaPrincipal(this);
		// Recupera os DAOs do sistema
		// Recupera��o da serializa��o...
		IDAOSerializavel daoFuncionario = (IDAOSerializavel)DAOFuncionario.getSingleton();
		IDAOSerializavel daoAluno = (IDAOSerializavel)DAOAluno.getSingleton();
		IDAOSerializavel daoEmprestimo = (IDAOSerializavel)DAOEmprestimo.getSingleton();
		IDAOSerializavel daoExemplar = (IDAOSerializavel)DAOExemplar.getSingleton();
		IDAOSerializavel daoObra = (IDAOSerializavel)DAOObra.getSingleton();
		IDAOSerializavel daoAutor = (IDAOSerializavel)DAOAutor.getSingleton();
		IDAOSerializavel daoAssunto = (IDAOSerializavel)DAOAssunto.getSingleton();
		IDAOSerializavel daoEditora = (IDAOSerializavel)DAOEditora.getSingleton();
		
		//
		// Recupera��o dos objetos serializados no arquivo "dados.dat"
		//
		try {
			// Abrindo o arquivo para leitura bin�ria
			FileInputStream fis = new FileInputStream("dados.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// Solicita��o para os DAOs gerenciarem os objetos recuperados do arquivo
			daoFuncionario.recuperarObjetos(ois);
			daoAluno.recuperarObjetos(ois);
			daoEmprestimo.recuperarObjetos(ois);
			daoExemplar.recuperarObjetos(ois);
			daoObra.recuperarObjetos(ois);
			daoAutor.recuperarObjetos(ois);			
			daoAssunto.recuperarObjetos(ois);
			daoEditora.recuperarObjetos(ois);	
			// Fechando o arquivo 
			ois.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo dados.dat n�o encontrado");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlPrograma#terminar()
	 */
	@Override
	public void terminar(){
		// Recuperando os DAOs do sistema
		IDAOSerializavel daoFuncionario = (IDAOSerializavel)DAOFuncionario.getSingleton();
		IDAOSerializavel daoAluno = (IDAOSerializavel)DAOAluno.getSingleton();
		IDAOSerializavel daoEmprestimo = (IDAOSerializavel)DAOEmprestimo.getSingleton();
		IDAOSerializavel daoExemplar = (IDAOSerializavel)DAOExemplar.getSingleton();
		IDAOSerializavel daoObra = (IDAOSerializavel)DAOObra.getSingleton();
		IDAOSerializavel daoAutor = (IDAOSerializavel)DAOAutor.getSingleton();
		IDAOSerializavel daoAssunto = (IDAOSerializavel)DAOAssunto.getSingleton();
		IDAOSerializavel daoEditora = (IDAOSerializavel)DAOEditora.getSingleton();		
		try {
			// Abrindo o arquivo c:/dados.dat para escrita
			FileOutputStream fos = new FileOutputStream("dados.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// Salvando os objetos gerenciados pelos DAOs
			daoFuncionario.salvarObjetos(oos);
			daoAluno.salvarObjetos(oos);
			daoEmprestimo.salvarObjetos(oos);
			daoExemplar.salvarObjetos(oos);
			daoObra.salvarObjetos(oos);
			daoAutor.salvarObjetos(oos);
			daoAssunto.salvarObjetos(oos);
			daoEditora.salvarObjetos(oos);			
			// Fechando e salvando o arquivo
			oos.close();
		} catch (IOException e) {
				e.printStackTrace();
		  } 	
			// M�todo est�tico da classe System que encerra o programa
			System.exit(0);
	}

	/**
	 * M�todo para iniciar caso de uso ManterExemplares
	 * @return this.ctrlExemplares.iniciar()
	 */
	public boolean iniciarCasoDeUsoManterExemplares(){
		return this.ctrlExemplares.iniciar();
	}

	/**
	 * M�todo para finalizar caso de uso ManterExemplares
	 * @return true
	 */
	public boolean terminarCasoDeUsoManterExemplares(){
		return true;
	}

	/**
	 * M�todo para iniciar caso de uso ManterEditoras
	 * @return this.ctrlEditoras.iniciar()
	 */
	public boolean iniciarCasoDeUsoManterEditoras(){
		return this.ctrlEditoras.iniciar();
	}

	/**
	 * M�todo para finalizar caso de uso ManterEditoras
	 * @return true
	 */
	public boolean terminarCasoDeUsoManterEditoras(){
		return true;
	}

	/**
	 * M�todo para iniciar caso de uso ManterAlunos
	 * @return this.ctrlAlunos.iniciar()
	 */
	public boolean iniciarCasoDeUsoManterAlunos(){
		return this.ctrlAlunos.iniciar();
	}

	/**
	 * M�todo para finalizar caso de uso ManterAlunos
	 * @return true
	 */
	public boolean terminarCasoDeUsoManterAlunos(){
		return true;
	}

	/**
	 * M�todo para iniciar caso de uso ManterAutores
	 * @return this.ctrlAutores.iniciar()
	 */
	public boolean iniciarCasoDeUsoManterAutores(){
		return this.ctrlAutores.iniciar();
	}

	/**
	 * M�todo para finalizar caso de uso ManterAutores
	 * @return true
	 */
	public boolean terminarCasoDeUsoManterAutores(){
		return true;
	}

	/**
	 * M�todo para iniciar caso de uso ManterAssuntos
	 * @return this.ctrlAssuntos.iniciar()
	 */
	public boolean iniciarCasoDeUsoManterAssuntos(){
		return this.ctrlAssuntos.iniciar();
	}

	/**
	 * M�todo para finalizar caso de uso ManterAssuntos
	 * @return true
	 */
	public boolean terminarCasoDeUsoManterAssuntos(){
		return true;
	}

	/**
	 * M�todo para iniciar caso de uso ManterEmprestimos
	 * @return this.ctrlEmprestimos.iniciar()
	 */
	public boolean iniciarCasoDeUsoManterEmprestimos(){
		return this.ctrlEmprestimos.iniciar();
	}

	/**
	 * M�todo para finalizar caso de uso ManterEmprestimos
	 * @return true
	 */
	public boolean terminarCasoDeUsoManterEmprestimos(){
		return true;
	}

	/**
	 * M�todo para iniciar caso de uso ManterFuncionarios
	 * @return this.ctrlFuncionarios.iniciar()
	 */
	public boolean iniciarCasoDeUsoManterFuncionarios(){
		return this.ctrlFuncionarios.iniciar();
	}

	/**
	 * M�todo para finalizar caso de uso ManterFuncionarios
	 * @return true
	 */
	public boolean terminarCasoDeUsoManterFuncionarios(){
		return true;
	}

	/**
	 * M�todo para iniciar caso de uso ManterObras
	 * @return this.ctrlObras.iniciar()
	 */
	public boolean iniciarCasoDeUsoManterObras(){
		return this.ctrlObras.iniciar();
	}

	/**
	 * M�todo para finalizar caso de uso ManterObras
	 * @return true
	 */
	public boolean terminarCasoDeUsoManterObras(){
		return true;
	}

	/**
	 * M�todo que inicia a aplica��o
	 * @param args
	 */
	public static void main(String[] args){
		CtrlPrograma prg = new CtrlPrograma();
		prg.iniciar();	
	}
}
