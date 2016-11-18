package br.com.servidor.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.servidor.model.CoordenadaGeografica;
import br.com.servidor.model.QuestGeolocalizada;

public interface QuestGeolocalizadaRepository extends GenericRepository<QuestGeolocalizada>{
	
	public List<QuestGeolocalizada> findQuestsByCordenadas(List<CoordenadaGeografica> cordenadas);
}
