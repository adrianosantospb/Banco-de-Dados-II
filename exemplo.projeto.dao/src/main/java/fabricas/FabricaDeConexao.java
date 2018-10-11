package fabricas;

import java.sql.Connection;

public class FabricaDeConexao {
	// Injeção.
	public static Connection getInstancia(TipoDeConexao tipo) {
		return tipo.getInstancia();
	}
}
