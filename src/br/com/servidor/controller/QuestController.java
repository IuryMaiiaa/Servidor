package br.com.servidor.controller;

import java.util.ArrayList;

import br.com.servidor.model.CordenadaGeografica;
import br.com.servidor.model.QuestGeolocalizada;
import br.com.servidor.repository.CordenadaGeogaficaRepository;
import br.com.servidor.repository.QuestGeolocalizadaRepository;
import br.com.servidor.repository.Impl.CordenadaGeograficaRepositoryImpl;
import br.com.servidor.repository.Impl.QuestGeolocalizadaRepositoryImpl;

public class QuestController {
	private QuestGeolocalizadaRepository questRepository;
	private CordenadaGeogaficaRepository cordenadaRepository;
	
	public QuestController() {
		questRepository = new QuestGeolocalizadaRepositoryImpl();
		cordenadaRepository = new CordenadaGeograficaRepositoryImpl();
	}
	
	public void adicionarNovaQuest(QuestGeolocalizada quest) {
		System.out.println(quest.getCordenada().getLat() + " " + quest.getCordenada().getLon());
		cordenadaRepository.addCordenada(quest.getCordenada());
		CordenadaGeografica cordenada = cordenadaRepository.getCordenada(quest.getCordenada().getLat(), quest.getCordenada().getLon());
		if(cordenada == null) {
			questRepository.save(quest);
		} else {
			quest.setCordenada(cordenada);
			questRepository.save(quest);
		}
	}
	
	public void updateQuest(QuestGeolocalizada quest) {
		questRepository.update(quest);
	}
	
	public void deleteQuest(QuestGeolocalizada quest) {
		questRepository.delete(quest);
	}

	public ArrayList<QuestGeolocalizada> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<QuestGeolocalizada> listarProximas(
			CordenadaGeografica cordenada, int raio) {
		// TODO Auto-generated method stub
		return null;
	}

}
