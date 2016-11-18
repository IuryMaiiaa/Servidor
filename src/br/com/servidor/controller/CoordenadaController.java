package br.com.servidor.controller;

import java.util.ArrayList;

import br.com.servidor.model.CoordenadaGeografica;
import br.com.servidor.repository.CoordenadaGeogaficaRepository;
import br.com.servidor.repository.ElasticSearchDAO;
import br.com.servidor.repository.Impl.CoordenadaGeograficaRepositoryImpl;

public class CoordenadaController {
	private CoordenadaGeogaficaRepository cordenadaRepository = new CoordenadaGeograficaRepositoryImpl();
	
	public ArrayList<CoordenadaGeografica> listarTodos() {
		return (ArrayList<CoordenadaGeografica>) cordenadaRepository.find(CoordenadaGeografica.class);
	}

	public void add(CoordenadaGeografica cordenada) {
		if (cordenadaRepository.getCordenada(cordenada.getLat(), cordenada.getLon()) == null) {
			cordenadaRepository.save(cordenada);
			cordenada = cordenadaRepository.getCordenada(cordenada.getLat(), cordenada.getLon());
			if(cordenada != null) {
				ElasticSearchDAO.getInstance().adicionarCordenada(cordenada);
			}
		}
	}
	
	public CoordenadaGeografica find(double lat, double lon) {
		return cordenadaRepository.getCordenada(lat,lon);
	}

	public ArrayList<CoordenadaGeografica> listarProximas(CoordenadaGeografica cordenada, int raio) {
		ElasticSearchDAO es = new ElasticSearchDAO();
		return es.getInstance().listarProximas(cordenada,raio);
	}

	public void update(CoordenadaGeografica cordenada) {
		cordenadaRepository.update(cordenada);
		ElasticSearchDAO.getInstance().updateCordenada(cordenada);
	}

	public void remove(CoordenadaGeografica cordenada) {
		cordenadaRepository.delete(cordenada);
		ElasticSearchDAO.getInstance().deletarCordenada(cordenada);
		
	}

}
