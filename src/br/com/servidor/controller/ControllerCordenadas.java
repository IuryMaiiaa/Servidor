package br.com.servidor.controller;

import java.util.ArrayList;

import br.com.servidor.model.CordenadaGeografica;
import br.com.servidor.repository.CordenadaGeogaficaRepository;
import br.com.servidor.repository.ElasticSearchDAO;
import br.com.servidor.repository.Impl.CordenadaGeograficaRepositoryImpl;

public class ControllerCordenadas {
	private CordenadaGeogaficaRepository cordenadaRepository = new CordenadaGeograficaRepositoryImpl();
	
	public ArrayList<CordenadaGeografica> listarTodos() {
		return (ArrayList<CordenadaGeografica>) cordenadaRepository.find(CordenadaGeografica.class);
	}

	public void add(CordenadaGeografica cordenada) {
		cordenadaRepository.save(cordenada);
		cordenada = cordenadaRepository.getCordenada(cordenada.getLat(), cordenada.getLon());
		if(cordenada != null) {
			ElasticSearchDAO.getInstance().adicionarCordenada(cordenada);
		}
		
	}

	public ArrayList<CordenadaGeografica> listarProximas(CordenadaGeografica cordenada, int raio) {
		ElasticSearchDAO es = new ElasticSearchDAO();
		return es.listarProximas(cordenada,raio);
	}

	public void update(CordenadaGeografica cordenada) {
		cordenadaRepository.update(cordenada);
		ElasticSearchDAO.getInstance().updateCordenada(cordenada);
	}

}
