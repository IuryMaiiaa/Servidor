package br.com.servidor.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import br.com.servidor.controller.QuestController;
import br.com.servidor.model.QuestGeolocalizada;


@Path("/Quest")
public class QuestResource {
	private QuestController Questcontroller;
	
	public QuestResource() {
		Questcontroller = new QuestController();
	}
	
	@POST
	@Path("/addQuest")
	@Consumes(MediaType.APPLICATION_JSON)
	public void cadastrarNovaQuest(QuestGeolocalizada quest) {
		Questcontroller.adicionarNovaQuest(quest);
	}
	
	@POST
	@Path("/removeQuest")
	@Consumes(MediaType.APPLICATION_JSON)
	public void removerQuest(QuestGeolocalizada quest) {
		Questcontroller.deleteQuest(quest);
	}
	
	@POST
	@Path("/updateQuest")
	@Consumes(MediaType.APPLICATION_JSON)
	public void atualizarQuest(QuestGeolocalizada quest) {
		Questcontroller.updateQuest(quest);
	}

}
