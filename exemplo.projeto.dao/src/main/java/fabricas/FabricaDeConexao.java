package fabricas;

import java.sql.Connection;

public class FabricaDeConexao {
	// Inje��o.
	public static Connection getInstancia(TipoDeConexao tipo) {
		return tipo.getInstancia();
	}
}
