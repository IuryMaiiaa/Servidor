package br.com.servidor.repository.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;

import enumeration.QueryType;
import br.com.servidor.model.CordenadaGeografica;
import br.com.servidor.repository.CordenadaGeogaficaRepository;
import br.com.servidor.repository.GenericRepository;

public class CordenadaGeograficaRepositoryImpl extends
		JpaGenericRepositoryImpl<CordenadaGeografica> implements
		CordenadaGeogaficaRepository {

	@Override
	public CordenadaGeografica getCordenada(double lat, double lon) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lat", lat);
		params.put("lon", lon);
		
		CordenadaGeografica cordenada = (CordenadaGeografica) find(QueryType.JPQL,
				"Select * from cordenadageografica where lat=:lat and lon=:lon", params);
		

		return cordenada;
	}

}
