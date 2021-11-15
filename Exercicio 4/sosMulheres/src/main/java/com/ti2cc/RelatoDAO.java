
import java.sql.*;

public class RelatoDAO {
		private Connection conexao;
		
		public RelatoDAO() {
			conexao = null;
		}
		
		public boolean conectar() {
			//String driverName = "org.postgresql.Driver";                    
			//String serverName = "localhost";
			//String mydatabase = "sosmulheres";
			int porta = 5432; 
			String url = "exercicio4.postgres.database.azure.com";
			String username = "ADM@exercicio4";
			String password = "malvadao@22";
			boolean status = false;

			try {
				Class.forName(driverName);
				conexao = DriverManager.getConnection(url, username, password);
				status = (conexao == null);
				System.out.println("Conexão efetuada com o postgres!");
			} catch (ClassNotFoundException e) { 
				System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
			} catch (SQLException e) {
				System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
			}

			return status;
		}
		
		public boolean close() {
			boolean status = false;
			
			try {
				conexao.close();
				status = true;
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
			return status;
		}

	public boolean add(Relato relato) {
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO relato (id, nome, quantidade) "
					       + "VALUES (" + relato.getId()+"', '" + relato.getNome() + "', '"  
					       + relato.getQuantidade() + "', '" );
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	

	public boolean update(Relato relato) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE relato SET nome = '" + relato.getNome() + "', '"  
				       + relato.getQuantidade() + "', '" "
					   + " WHERE id = " + relato.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean remove(String id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM relato WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Relato get(String id) {
		Relato usuarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM relato WHERE login = " + id);		
	        usuarios = new Relato();
	             
	        usuarios = new Relato(rs.getString("id"), rs.getString("nome"), rs.getString("quantidade"));
	     
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}
	
	public Relato[] getAll() {
		Relato[] usuarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM relato");		
	         if(rs.next()){
	             rs.last();
	             usuarios = new Relato[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                usuarios[i] = new Relato(rs.getString("id"), rs.getString("nome"), rs.getString("quantidade"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}

}

