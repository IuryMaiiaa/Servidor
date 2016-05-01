package br.com.servidor.controller;

import java.util.ArrayList;

import br.com.servidor.model.CordenadaGeografica;
import br.com.servidor.repository.CordenadaGeogaficaRepository;
import br.com.servidor.repository.ElasticSearchDAO;
import br.com.servidor.repository.Impl.CordenadaGeograficaRepositoryImpl;

public class CordenadaController {
	private CordenadaGeogaficaRepository cordenadaRepository = new CordenadaGeograficaRepositoryImpl();
	
	public ArrayList<CordenadaGeografica> listarTodos() {
		return (ArrayList<CordenadaGeografica>) cordenadaRepository.find(CordenadaGeografica.class);
	}
//D:

	public void add(CordenadaGeografica cordenada) {
		if (cordenadaRepository.getCordenada(cordenada.getLat(), cordenada.getLon()) == null) {
			cordenadaRepository.save(cordenada);
			cordenada = cordenadaRepository.getCordenada(cordenada.getLat(), cordenada.getLon());
			if(cordenada != null) {
				ElasticSearchDAO.getInstance().adicionarCordenada(cordenada);
			}
		}
	}
	
	public CordenadaGeografica find(double lat, double lon) {
		return cordenadaRepository.getCordenada(lat,lon);
	}

	public ArrayList<CordenadaGeografica> listarProximas(CordenadaGeografica cordenada, int raio) {
		ElasticSearchDAO es = new ElasticSearchDAO();
		return es.listarProximas(cordenada,raio);
	}

	public void update(CordenadaGeografica cordenada) {
		cordenadaRepository.update(cordenada);
		ElasticSearchDAO.getInstance().updateCordenada(cordenada);
	}

	public void remove(CordenadaGeografica cordenada) {
		cordenadaRepository.delete(cordenada);
		ElasticSearchDAO.getInstance().deletarCordenada(cordenada);
		
	}

}
