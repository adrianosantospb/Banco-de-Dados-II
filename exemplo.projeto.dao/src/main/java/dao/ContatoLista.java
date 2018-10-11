package dao;

import java.util.ArrayList;
import java.util.List;

import dados.Contato;
import fabricas.TipoDeConexao;

//Exemplo didático. Na vida real, favor usar expressões lambdas.

public class ContatoLista implements ContatoDAO {

	private List<Contato> contatos;

	public ContatoLista(TipoDeConexao tipo) {
		if (contatos == null) {
			contatos = new ArrayList<Contato>();
		}
	}

	public void add(Contato contato) {
		// TODO Auto-generated method stub
		contatos.add(contato);

	}

	// Exemplo didático
	public void removeContatoBy(int id) {
		// TODO Auto-generated method stub
		for (Contato contato : contatos) {
			if (contato.getId() == id) {
				contatos.remove(contato); 
			}
		}
	}

	// Exemplo didático
	public Contato getContatoBy(int id) {
		// TODO Auto-generated method stub
		for (Contato contato : contatos) {
			if (contato.getId() == id) {
				return contato; // Exemplo didático
			}
		}
		return null;
	}

	public List<Contato> all() {
		// TODO Auto-generated method stub
		return this.contatos;
	}

	public void close() {
		contatos = null;
	}

}
