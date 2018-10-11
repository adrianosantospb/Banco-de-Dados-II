package fabricas;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum TipoDeConexao {
      	SQL_SERVER {
        @Override
        public Connection getInstancia() {
        	try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				connection = DriverManager.getConnection(stringDeConexao, usuario, senha);
        	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
        		e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
        	return connection ;
        }   
      },
      	
       POSTGRES {

		@Override
		public Connection getInstancia() {
			// TODO Auto-generated method stub
			try {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(stringDeConexao, usuario, senha);
        	} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
        		e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
        	return connection ;
		}
    	  
      }, 
       
       LISTA{

		@Override
		public Connection getInstancia() {
			// Utilizado apenas de forma didática.
			return null;
		} 
      }
      ;

	// Utilizados como forma de configuração. No próximo exemplo, utilize um arquivo XML de configuração
    public abstract Connection getInstancia();
    private static Connection connection = null;
    
    private static String usuario = "postgres";
    private static String senha = "123456";
    
    // SQL_SERVER = "jdbc:sqlserver://localhost;instanceName=SQLEXPRESS;databaseName=agenda"
    
    private static String stringDeConexao = "jdbc:postgresql://localhost/agenda";
    
}