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

public class ContatoSQLServer implements ContatoDAO {

	private Connection connection = null;

	public ContatoSQLServer(TipoDeConexao tipo) {
		if (connection == null) {
			connection = FabricaDeConexao.getInstancia(tipo);
		}
	}

	public void add(Contato contato) {
		// TODO Criar um datasource

		// https://pubs.vmware.com/vsphere-4-esx-vcenter/index.jsp?topic=/com.vmware.vsphere.installclassic.doc_41/install/prep_db/t_configure_microsoft_sql_server_tcp_ip_for_jdbc.html

		try {
			connection.setAutoCommit(false);
			
			String stringDeInseracaoContato = "insert into contatos (nome, cpf) values ( ?, ?)"; 
			String stringDeInsercaoTelefone = "insert into telefones (id_contato, numero, tipo) values ( ?, ?, ?)";
			
			String identificadores[] = new String[] { "id_contato" };
			
			PreparedStatement inserirContato = connection.prepareStatement(stringDeInseracaoContato, identificadores );			
			inserirContato.setString(1, contato.getNome());
			inserirContato.setString(2, contato.getCpf());

			// Executa o comando de cadastro do contato
			inserirContato.executeUpdate();
			
			// Obtem o id do contato inserido
			ResultSet teste = inserirContato.getGeneratedKeys();
			teste.next();
			int id_contato = teste.getInt(1);
			
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
		// TODO Implemente o c�digo

	}

	public Contato getContatoBy(int id) {
		// TODO Implemente o c�digo
		return null;
	}

	public List<Contato> all() {
		// TODO Implemente o c�digo
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
