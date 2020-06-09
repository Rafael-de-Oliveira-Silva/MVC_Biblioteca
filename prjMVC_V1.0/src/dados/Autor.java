package dados;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import controle.ITabelavel;

public class Autor implements Serializable, ITabelavel, Comparable <Autor>{
	//DECLARAÇÃO DAS CONSTANTES
	public static final int TAMANHO_NOME = 35;
	
	//DECLARAÇÃO DOS ATRIBUTOS
	private String nome;
	private Set<Obra> listaObra;
	
	
	public Autor(String nome) throws DadosException {
		super();
		this.setNome(nome);
		this.listaObra = new TreeSet<Obra>();
	}
    
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws DadosException {
		validarNome(nome);
		this.nome = nome;
	}

	public static void validarNome(String nome) throws DadosException {
		if(nome == null || nome.length() == 0) 
			throw new DadosException("O Autor não pode ser nulo!");
			
		if(nome.length() > TAMANHO_NOME)
			throw new DadosException("O Autor deve ter " + TAMANHO_NOME + " caracteres!");		
	}
	
	/**
	 * Método que retorna a lista de obras do Autor
	 * @return listaObraAutor
	 */
	public Set<Obra> getListaObra() {
		return listaObra;
	}

	public void setListaObra(Set<Obra> listaObra) {
		this.listaObra = listaObra;
	}

	public boolean addObra(Obra novaObra){
		if (this.listaObra.contains(novaObra))
			 return false;
		this.listaObra.add(novaObra);
		novaObra.setAutor(this);
		return true;
	}
	
	public boolean removeObra(Obra retiraObra){
		if (! this.listaObra.contains(retiraObra))
			return false;
		this.listaObra.remove(retiraObra);
		retiraObra.setAutor(null);
		return true;
	}
		
	public String toString() {
		return this.nome;
	}

	public Object[] getData() {
		return new Object[] { this.nome, this.listaObra.size()};
	}

	public int compareTo(Autor autor) {
		return this.nome.compareTo(autor.nome);
	}	
}
