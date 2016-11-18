package br.com.servidor.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.servidor.model.CoordenadaGeografica;
import br.com.servidor.model.QuestGeolocalizada;
import br.com.servidor.repository.CoordenadaGeogaficaRepository;
import br.com.servidor.repository.QuestGeolocalizadaRepository;
import br.com.servidor.repository.Impl.CoordenadaGeograficaRepositoryImpl;
import br.com.servidor.repository.Impl.QuestGeolocalizadaRepositoryImpl;

public class QuestController {
	private QuestGeolocalizadaRepository questRepository;
	private CoordenadaController coordenadaController;
	
	public QuestController() {
		questRepository = new QuestGeolocalizadaRepositoryImpl();
		coordenadaController = new CoordenadaController();
	}
	
	public void adicionarNovaQuest(QuestGeolocalizada quest) {
		System.out.println(quest.getCordenada().getLat() + " " + quest.getCordenada().getLon());
		coordenadaController.add(quest.getCordenada());
		CoordenadaGeografica cordenada = coordenadaController.find(quest.getCordenada().getLat(), quest.getCordenada().getLon());
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
		return (ArrayList<QuestGeolocalizada>) questRepository.find(QuestGeolocalizada.class);
	}

	public ArrayList<QuestGeolocalizada> listarProximas(
			CoordenadaGeografica cordenada, int raio) {
		List<CoordenadaGeografica> listaCordenadasProximas = coordenadaController.listarProximas(cordenada, raio);
		return (ArrayList<QuestGeolocalizada>) questRepository.findQuestsByCordenadas(listaCordenadasProximas);
	}

}
