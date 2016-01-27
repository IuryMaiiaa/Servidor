package br.com.servidor.controller;

import java.util.ArrayList;

import br.com.servidor.dao.CordenadaGeograficaDAO;
import br.com.servidor.dao.ElasticSearchDAO;
import br.com.servidor.model.CordenadaGeografica;

public class ControllerCordenadas {
	
	public ArrayList<CordenadaGeografica> listarTodos() {
		return CordenadaGeograficaDAO.getInstance().listarTodasCordenada();
	}

	public void add(CordenadaGeografica cordenada) {
		if(CordenadaGeograficaDAO.getInstance().add(cordenada)) {
			CordenadaGeografica aux = CordenadaGeograficaDAO.getInstance()
							.getCordenada(cordenada.getLat(),cordenada.getLon());
			ElasticSearchDAO es = new ElasticSearchDAO();
			es.adicionarCordenada(aux);
		}
		
	}

	public ArrayList<CordenadaGeografica> listarProximas(CordenadaGeografica cordenada, int raio) {
		ElasticSearchDAO es = new ElasticSearchDAO();
		return es.listarProximas(cordenada,raio);
	}

}
