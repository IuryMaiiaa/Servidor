package br.com.servidor.utilites;

import br.com.servidor.model.Etapa;
import br.com.servidor.model.QuestGeolocalizada;

public class QuestReferenciaCircular {
	
	public QuestGeolocalizada removendoReferenciasCirculares(QuestGeolocalizada quest) {
		for(Etapa etapa: quest.getEtapas()) {
			etapa.setQuestGeolocalizada(null);
		}
		return quest;
	}

	public QuestGeolocalizada adicionarReferencaisCirculares(QuestGeolocalizada quest) {
		for(Etapa etapa : quest.getEtapas()) {
			etapa.setQuestGeolocalizada(quest);
		}
		return quest;
	}

}
