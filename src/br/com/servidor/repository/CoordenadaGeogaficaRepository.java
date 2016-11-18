package br.com.servidor.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.servidor.model.CoordenadaGeografica;

public interface CoordenadaGeogaficaRepository extends
		GenericRepository<CoordenadaGeografica> {

	public CoordenadaGeografica getCordenada(double lat, double lon);
	
	public void addCordenada(CoordenadaGeografica cordenada);
}
