package br.com.servidor.controller;

import br.com.servidor.model.QuestGeolocalizada;
import br.com.servidor.repository.QuestGeolocalizadaRepository;
import br.com.servidor.repository.Impl.QuestGeolocalizadaRepositoryImpl;

public class ControllerQuestsLocalizadas {
	private QuestGeolocalizadaRepository questRepository;
	
	public ControllerQuestsLocalizadas() {
		questRepository = new QuestGeolocalizadaRepositoryImpl();
	}
	
	public void adicionarNovaQuest(QuestGeolocalizada quest) {
		questRepository.save(quest);
	}

}
