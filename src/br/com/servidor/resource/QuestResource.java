package br.com.servidor.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import br.com.servidor.controller.ControllerQuestsLocalizadas;
import br.com.servidor.model.QuestGeolocalizada;


@Path("/Quest")
public class QuestResource {
	private ControllerQuestsLocalizadas controllerQuests;
	
	@POST
	@Path("/addQuest")
	@Consumes(MediaType.APPLICATION_JSON)
	public void cadastrarNovaQuest(QuestGeolocalizada quest) {
		controllerQuests.adicionarNovaQuest(quest);
	}

}
