package br.com.servidor.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionFactory {
	
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/Servidor";
	private static final String USUARIO = "postgres";
	private static final String SENHA = "postgres";
	
	public Connection criarConexao() {
		
		Connection conexao = null;
		
		try {
			
			Class.forName(DRIVER);
			conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			
		} catch (Exception e) {
			System.out.println("Tento Criar conexão");
			e.printStackTrace();
		}
		
		return conexao;
	}
	
	public void fecharConhexao(Connection conexao, PreparedStatement pstmt, ResultSet rs) {
		
		try {
			
			if(conexao != null) {
				conexao.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(rs != null) {
				rs.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}



}
