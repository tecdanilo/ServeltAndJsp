package br.com.avaliacaojavajr.webtest.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;



public class Conexao {
	
	private static Logger logger = Logger.getLogger(Conexao.class);

	private Connection con;

	private static String URL = "jdbc:mysql://localhost:3306/stling";	
	
	private static String usuario = "root";	
	//private static String password = "root";
	private static String password = "s1mpl3s";
	private static String DRIVER = "com.mysql.jdbc.Driver";

	public Conexao() throws SQLException {
		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, usuario, password);
			logger.debug("Conexao obtida com sucesso");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getMessage());

		}
	}
	
	
	public ResultSet consulta(String query) throws SQLException{
		if (con != null){
			Statement stmt = con.createStatement();
			return stmt.executeQuery(query);
		}else{
			return null;
		}
	}
	
	public int update(String query) throws SQLException{
		if (con != null){
			Statement stmt = con.createStatement();
			return stmt.executeUpdate(query);
		}else{
			return 0 ;
		}
	}
	
	public void fechaConexao(){
		if (con!= null){
			try {
				con.close();
			} catch (SQLException e) {
				con = null;
			}
		}
	}
	

	public Connection getCon() {
		return con;
	}
	
	
//	public static void main(String[] args) throws SQLException {
//		Conexao c = new Conexao();
//		Connection conn = c.getCon();
//		
//	}
	

	
}