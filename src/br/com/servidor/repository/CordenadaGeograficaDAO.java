package br.com.servidor.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.servidor.factory.ConnectionFactory;
import br.com.servidor.model.CordenadaGeografica;

public class CordenadaGeograficaDAO extends ConnectionFactory {
	
	private static CordenadaGeograficaDAO instace;
	
	public static CordenadaGeograficaDAO getInstance() {
		if(instace == null) {
			instace = new CordenadaGeograficaDAO();;
		}
		return instace;
	}
	
	
	
	
	public ArrayList<CordenadaGeografica> listarTodasCordenada() {
		
		ArrayList<CordenadaGeografica> cordenadas = new ArrayList<CordenadaGeografica>();
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = 	this.criarConexao();
		
		try {

			pstmt = conexao.prepareStatement("Select * from cordenadageografica");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CordenadaGeografica cg = new CordenadaGeografica();
				
				cg.setID(rs.getInt("id"));
				cg.setLat(rs.getDouble("lat"));
				cg.setLon(rs.getDouble("lon"));

				cordenadas.add(cg);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fecharConhexao(conexao, pstmt, rs);
		}
		return cordenadas;
		
	}




	public boolean add(CordenadaGeografica cordenada) {
		
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = 	this.criarConexao();
		
		String query = "insert into cordenadageografica( lat, lon) values( ?, ?)";
		 try {
			
			pstmt = conexao.prepareStatement(query);
			pstmt.setDouble(1, cordenada.getLat()); // set input parameter 1
		    pstmt.setDouble(2, cordenada.getLon()); // set input parameter 2
		    pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			fecharConhexao(conexao, pstmt, rs);
		} // create a statement
		
		return true;
		
	}




	public CordenadaGeografica getCordenada(double lat, double lon) {
		CordenadaGeografica cordenada = new CordenadaGeografica();
		
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = 	this.criarConexao();
		
		String query = "Select * from cordenadageografica where lat=? and lon=?";
		
		try {
			pstmt = conexao.prepareStatement(query);
			pstmt.setDouble(1, lat); // set input parameter 1
		    pstmt.setDouble(2, lon);
		    rs = pstmt.executeQuery();
		    
		    while(rs.next()) {
				
				cordenada.setID(rs.getInt("id"));
				cordenada.setLat(rs.getDouble("lat"));
				cordenada.setLon(rs.getDouble("lon"));
			}
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fecharConhexao(conexao, pstmt, rs);
		}
		
		return cordenada;
		
	}




	public boolean update(CordenadaGeografica cordenada) {
		
		Connection conexao = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conexao = 	this.criarConexao();
		
		String query = "UPDATE table_name SET lat=?,lon=? WHERE id=?";
		 try {
			
			pstmt = conexao.prepareStatement(query);
			pstmt.setDouble(1, cordenada.getLat()); // set input parameter 1
		    pstmt.setDouble(2, cordenada.getLon()); // set input parameter 2
		    pstmt.setInt(3, cordenada.getID());
		    pstmt.executeQuery(); // execute insert statement
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			fecharConhexao(conexao, pstmt, rs);
		} // create a statement
		
		return true;
	}
	
	

}
