package br.com.servidor.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.servidor.model.CordenadaGeografica;

public interface CordenadaGeogaficaRepository extends
		GenericRepository<CordenadaGeografica> {

	public CordenadaGeografica getCordenada(double lat, double lon);
}
