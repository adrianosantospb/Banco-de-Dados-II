package dao;

import java.util.List;

import dados.Contato;

public interface ContatoDAO {
	void add(Contato contato);
	void removeContatoBy(int id);
	Contato getContatoBy(int id);
	List<Contato> all();
	void close();
}
