package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dados.Contato;
import dados.Telefone;
import fabricas.FabricaDeConexao;
import fabricas.TipoDeConexao;

public class ContatoPostgres implements ContatoDAO {

	private Connection connection = null;

	public ContatoPostgres(TipoDeConexao tipo) {
		if (connection == null) {
			connection = FabricaDeConexao.getInstancia(tipo);
		}
	}

	public void add(Contato contato) {
		try {
			connection.setAutoCommit(false);
			
			String stringDeInseracaoContato = "insert into contatos (nome, cpf) values ( ?, ?) returning id_contato"; 
			String stringDeInsercaoTelefone = "insert into telefones (id_contato, numero, tipo) values ( ?, ?, ?)";
			
			PreparedStatement inserirContato = connection.prepareStatement(stringDeInseracaoContato);			
			inserirContato.setString(1, contato.getNome());
			inserirContato.setString(2, contato.getCpf());

			// Executa o comando de cadastro do contato
			ResultSet rs = inserirContato.executeQuery();
			rs.next();
			int id_contato = rs.getInt(1);
			
			// Insere os dados dos telefones
			if (id_contato != 0) {
				for (Telefone telefone : contato.getTelefones()) {
					PreparedStatement inserirTelefone = connection.prepareStatement(stringDeInsercaoTelefone);
					inserirTelefone.setInt(1, id_contato);
					inserirTelefone.setString(2, telefone.getNumero());
					inserirTelefone.setString(3, telefone.getTipo().toString());
					inserirTelefone.executeUpdate();
				}
			}
			// Executa 
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


	}

	public void removeContatoBy(int id) {
		// TODO Auto-generated method stub

	}

	public Contato getContatoBy(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Contato> all() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void close() {
		// TODO Auto-generated method stub
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
