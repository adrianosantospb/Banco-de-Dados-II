package dados;

import java.util.ArrayList;

public class Contato {

	private int id;	
	private String nome;
	private String cpf;
	
	private ArrayList<Telefone> telefones;
	
	public Contato() {
		telefones = new ArrayList<Telefone>();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ArrayList<Telefone> getTelefones() {
		return telefones;
	}
	
	public void addTelefone(Telefone telefone) {
		telefones.add(telefone);
	}
	
	public void removeTelefone(Telefone telefone) {
		telefones.remove(telefone);
	}
	
	// Você pode mudar os métodos e adicionar outros...
}
