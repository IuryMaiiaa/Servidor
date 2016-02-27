package br.com.servidor.controller;

import java.util.ArrayList;

import br.com.servidor.model.CordenadaGeografica;
import br.com.servidor.repository.CordenadaGeogaficaRepository;
import br.com.servidor.repository.CordenadaGeograficaDAO;
import br.com.servidor.repository.ElasticSearchDAO;
import br.com.servidor.repository.Impl.CordenadaGeograficaRepositoryImpl;

public class ControllerCordenadas {
	private CordenadaGeogaficaRepository cordenadaRepository = new CordenadaGeograficaRepositoryImpl();
	
	public ArrayList<CordenadaGeografica> listarTodos() {
		return CordenadaGeograficaDAO.getInstance().listarTodasCordenada();
	}

	public void add(CordenadaGeografica cordenada) {
		if(CordenadaGeograficaDAO.getInstance().add(cordenada)) {
			CordenadaGeografica aux = CordenadaGeograficaDAO.getInstance()
							.getCordenada(cordenada.getLat(),cordenada.getLon());
			System.out.println(aux.getID() + " " + aux.getLat());
			ElasticSearchDAO.getInstance().adicionarCordenada(aux);
		}
		
	}

	public ArrayList<CordenadaGeografica> listarProximas(CordenadaGeografica cordenada, int raio) {
		ElasticSearchDAO es = new ElasticSearchDAO();
		return es.listarProximas(cordenada,raio);
	}

	public void update(CordenadaGeografica cordenada) {
		if(CordenadaGeograficaDAO.getInstance().update(cordenada)) {
			ElasticSearchDAO.getInstance().updateCordenada(cordenada);
		}
		
	}
	
	public void save(CordenadaGeografica cordenada) {
		cordenadaRepository.save(cordenada);
	}

}
